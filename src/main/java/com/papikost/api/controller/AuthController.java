package com.papikost.api.controller;

import com.papikost.api.entity.Akun;
import com.papikost.api.entity.Penyewa;
import com.papikost.api.repository.AkunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AkunRepository akunRepository;

    // ==========================================
    // 1. ENDPOINT LOGIN
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> kingEmyuResponse = new HashMap<>();

        Optional<Akun> userOpt = akunRepository.findByUsername(username);

        // Validasi sederhana (Catatan: Untuk produksi nyata, password harus di-hash dengan BCrypt)
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            Akun user = userOpt.get();
            
            kingEmyuResponse.put("success", true);
            kingEmyuResponse.put("message", "Login berhasil!");

            // Menyesuaikan dengan kebutuhan state di React Frontend
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId().toString());
            userData.put("username", user.getUsername());
            userData.put("name", user.getNamaLengkap());
            userData.put("email", user.getEmail());
            userData.put("role", user.getRole());
            // Berikan flag boolean dasar agar frontend tidak error
            userData.put("hasKamar", false);

            kingEmyuResponse.put("user", userData);
            return ResponseEntity.ok(kingEmyuResponse);
        } else {
            kingEmyuResponse.put("success", false);
            kingEmyuResponse.put("error", "Username atau password salah!");
            return ResponseEntity.badRequest().body(kingEmyuResponse);
        }
    }

    // ==========================================
    // 2. ENDPOINT REGISTER
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        Map<String, Object> kingEmyuResponse = new HashMap<>();

        String username = request.get("username");
        String password = request.get("password");
        String name = request.get("name");
        String email = request.get("email");
        String role = request.get("role"); // "pencari" atau "pemilik" dari frontend

        // Cek apakah username sudah ada
        if (akunRepository.findByUsername(username).isPresent()) {
            kingEmyuResponse.put("success", false);
            kingEmyuResponse.put("error", "Username sudah digunakan, coba yang lain!");
            return ResponseEntity.badRequest().body(kingEmyuResponse);
        }

        // Karena Penyewa adalah turunan dari Akun, kita instansiasi Penyewa untuk pencari kost
        Akun akunBaru;
        if ("pencari".equalsIgnoreCase(role)) {
            Penyewa penyewa = new Penyewa();
            penyewa.setUsername(username);
            penyewa.setPassword(password);
            penyewa.setNamaLengkap(name);
            penyewa.setNamaPenyewa(name); // Field turunan Penyewa
            penyewa.setEmail(email);
            penyewa.setRole("pencari");
            akunBaru = penyewa;
        } else {
            // Jika dia mendaftar sebagai pemilik
            akunBaru = new Penyewa(); // Kita pakai entitas sementara, nanti di-update oleh Admin
            akunBaru.setUsername(username);
            akunBaru.setPassword(password);
            akunBaru.setNamaLengkap(name);
            akunBaru.setEmail(email);
            akunBaru.setRole("pemilik");
        }

        akunRepository.save(akunBaru);

        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Akun berhasil dibuat! Silakan login.");

        // Langsung auto-login setelah register
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", akunBaru.getId().toString());
        userData.put("username", akunBaru.getUsername());
        userData.put("name", akunBaru.getNamaLengkap());
        userData.put("email", akunBaru.getEmail());
        userData.put("role", akunBaru.getRole());
        userData.put("hasKamar", false);

        kingEmyuResponse.put("user", userData);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }
}