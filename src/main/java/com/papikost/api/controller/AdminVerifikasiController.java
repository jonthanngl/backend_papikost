package com.papikost.api.controller;

import com.papikost.api.entity.Biodata;
import com.papikost.api.entity.PengajuanOwner;
import com.papikost.api.service.AdminVerifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminVerifikasiController {

    @Autowired
    private AdminVerifikasiService verifikasiService;

    // ==========================================
    // 1. ENDPOINT VERIFIKASI USER (Data Diri)
    // ==========================================

    /**
     * GET semua biodata yang status PENDING (sudah mengajukan verifikasi).
     */
    @GetMapping("/verifikasi-data-diri")
    public ResponseEntity<List<Biodata>> getSemuaDataDiri() {
        return ResponseEntity.ok(verifikasiService.getAllVerifikasiDataDiri());
    }

    /**
     * PUT update status berkas per item (ktp/kk/foto) + komentar jika ditolak.
     * Body: { jenisBerkas: "ktp"|"kk"|"foto", status: "DISETUJUI"|"DITOLAK", komentar: "..." }
     */
    @PutMapping("/verifikasi-data-diri/{userId}/berkas")
    public ResponseEntity<Map<String, Object>> updateStatusBerkasUser(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {
        
        Biodata updated = verifikasiService.updateStatusBerkasUser(userId, request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Status berkas " + request.get("jenisBerkas") + " diperbarui.");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }

    // ==========================================
    // 2. ENDPOINT PENGAJUAN OWNER
    // ==========================================

    @PostMapping("/pengajuan-owner")
    public ResponseEntity<Map<String, Object>> submitPengajuanOwner(@RequestBody PengajuanOwner request) {
        PengajuanOwner hasil = verifikasiService.buatPengajuanOwner(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pengajuan menjadi owner berhasil dikirim ke Admin.");
        response.put("data", hasil);
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET semua pengajuan owner yang masuk.
     */
    @GetMapping("/pengajuan-owner")
    public ResponseEntity<List<PengajuanOwner>> getSemuaPengajuanOwner() {
        return ResponseEntity.ok(verifikasiService.getAllPengajuanOwner());
    }

    /**
     * PUT update status berkas per item pengajuan owner (ktp/suratKepemilikan/fotoKost).
     * Body: { jenisBerkas: "ktp"|"suratKepemilikan"|"fotoKost", status: "DISETUJUI"|"DITOLAK", komentar: "..." }
     */
    @PutMapping("/pengajuan-owner/{id}/berkas")
    public ResponseEntity<Map<String, Object>> updateStatusBerkasOwner(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        
        PengajuanOwner updated = verifikasiService.updateStatusBerkasOwner(id, request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Status berkas " + request.get("jenisBerkas") + " diperbarui.");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
}
