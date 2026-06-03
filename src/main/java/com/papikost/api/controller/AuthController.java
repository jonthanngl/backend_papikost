package com.papikost.api.controller;

import com.papikost.api.entity.Akun;
import com.papikost.api.entity.PengajuanOwner;
import com.papikost.api.entity.Penyewa;
import com.papikost.api.repository.AkunRepository;
import com.papikost.api.repository.PengajuanOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AkunRepository akunRepository;

    @Autowired
    private PengajuanOwnerRepository pengajuanOwnerRepository;

    // ==========================================
    // 1. ENDPOINT LOGIN
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();
        Optional<Akun> userOpt = akunRepository.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            Akun user = userOpt.get();

            Map<String, Object> userData = buildUserData(user);
            response.put("success", true);
            response.put("message", "Login berhasil!");
            response.put("user", userData);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("error", "Username atau password salah!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ==========================================
    // 2. ENDPOINT REGISTER
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        String username = request.get("username");
        String password = request.get("password");
        String name     = request.get("name");
        String email    = request.get("email");
        String role     = request.get("role");

        if (akunRepository.findByUsername(username).isPresent()) {
            response.put("success", false);
            response.put("error", "Username sudah digunakan, coba yang lain!");
            return ResponseEntity.badRequest().body(response);
        }

        Penyewa akunBaru = new Penyewa();
        akunBaru.setUsername(username);
        akunBaru.setPassword(password);
        akunBaru.setNamaLengkap(name);
        akunBaru.setNamaPenyewa(name);
        akunBaru.setEmail(email);
        akunBaru.setRole("pemilik".equalsIgnoreCase(role) ? "pemilik" : "pencari");

        Akun saved = akunRepository.save(akunBaru);

        Map<String, Object> userData = buildUserData(saved);
        response.put("success", true);
        response.put("message", "Akun berhasil dibuat!");
        response.put("user", userData);
        return ResponseEntity.ok(response);
    }

    // ==========================================
    // HELPER: Build user data map dengan status owner
    // ==========================================
    private Map<String, Object> buildUserData(Akun user) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("id",       user.getId().toString());
        userData.put("username", user.getUsername());
        userData.put("name",     user.getNamaLengkap());
        userData.put("email",    user.getEmail());
        userData.put("role",     user.getRole());
        userData.put("hasKamar", false);

        // Jika owner: sertakan status pengajuan terbaru
        if ("pemilik".equals(user.getRole())) {
            List<PengajuanOwner> pengajuanList =
                pengajuanOwnerRepository.findByUserIdOrderByCreatedAtDesc(user.getId());

            if (pengajuanList.isEmpty()) {
                // Belum pernah mengajukan berkas sama sekali
                userData.put("ownerVerifikasiStatus", "BELUM");
                userData.put("ownerPengajuanId",      null);
                userData.put("ownerKomentar",         null);
            } else {
                PengajuanOwner latest = pengajuanList.get(0);
                userData.put("ownerVerifikasiStatus", latest.getStatus());
                userData.put("ownerPengajuanId",      latest.getId());
                // Komentar penolakan per berkas
                Map<String, String> komentar = new HashMap<>();
                komentar.put("ktp",             latest.getKtpKomentar());
                komentar.put("suratKepemilikan",latest.getSuratKepemilikanKomentar());
                komentar.put("fotoKost",        latest.getFotoKostKomentar());
                userData.put("ownerKomentar", komentar);
            }
        }

        return userData;
    }

    // ==========================================
    // 3. ENDPOINT: Refresh status owner (dipanggil saat app mount / F5)
    // ==========================================
    @GetMapping("/owner-status/{userId}")
    public ResponseEntity<Map<String, Object>> getOwnerStatus(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<PengajuanOwner> list = pengajuanOwnerRepository.findByUserIdOrderByCreatedAtDesc(userId);

        if (list.isEmpty()) {
            response.put("ownerVerifikasiStatus", "BELUM");
            response.put("ownerPengajuanId",      null);
            response.put("ownerKomentar",         null);
        } else {
            PengajuanOwner latest = list.get(0);
            response.put("ownerVerifikasiStatus", latest.getStatus());
            response.put("ownerPengajuanId",      latest.getId());
            Map<String, String> komentar = new HashMap<>();
            komentar.put("ktp",              latest.getKtpKomentar());
            komentar.put("suratKepemilikan", latest.getSuratKepemilikanKomentar());
            komentar.put("fotoKost",         latest.getFotoKostKomentar());
            response.put("ownerKomentar", komentar);
        }
        return ResponseEntity.ok(response);
    }
}
