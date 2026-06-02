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
    // 1. ENDPOINT VERIFIKASI DATA DIRI
    // ==========================================
    @GetMapping("/verifikasi-data-diri")
    public ResponseEntity<List<Biodata>> getSemuaDataDiri() {
        return ResponseEntity.ok(verifikasiService.getAllVerifikasiDataDiri());
    }

    @PutMapping("/verifikasi-data-diri/{id}")
    public ResponseEntity<Map<String, Object>> updateStatusDataDiri(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        
        String status = request.get("status");
        Biodata diupdate = verifikasiService.updateStatusDataDiri(id, status);
        
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Status biodata berhasil diubah menjadi: " + status);
        kingEmyuResponse.put("data", diupdate);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }

    // ==========================================
    // 2. ENDPOINT PENGAJUAN OWNER
    // ==========================================
    @PostMapping("/pengajuan-owner")
    public ResponseEntity<Map<String, Object>> submitPengajuanOwner(@RequestBody PengajuanOwner request) {
        PengajuanOwner hasil = verifikasiService.buatPengajuanOwner(request);
        
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Pengajuan menjadi owner berhasil dikirim ke Admin.");
        kingEmyuResponse.put("data", hasil);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }

    @GetMapping("/pengajuan-owner")
    public ResponseEntity<List<PengajuanOwner>> getSemuaPengajuanOwner() {
        return ResponseEntity.ok(verifikasiService.getAllPengajuanOwner());
    }

    @PutMapping("/pengajuan-owner/{id}")
    public ResponseEntity<Map<String, Object>> updateStatusPengajuanOwner(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        
        String status = request.get("status");
        PengajuanOwner diupdate = verifikasiService.updateStatusPengajuanOwner(id, status);
        
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Status pengajuan owner diubah menjadi: " + status);
        kingEmyuResponse.put("data", diupdate);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }
}