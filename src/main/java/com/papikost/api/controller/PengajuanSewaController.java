package com.papikost.api.controller;

import com.papikost.api.dto.request.PengajuanSewaRequestDTO;
import com.papikost.api.entity.PengajuanSewa;
import com.papikost.api.service.PengajuanSewaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pengajuan")
public class PengajuanSewaController {

    @Autowired
    private PengajuanSewaService pengajuanSewaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> submitPengajuan(@RequestBody PengajuanSewaRequestDTO request) {
        PengajuanSewa hasil = pengajuanSewaService.prosesPengajuan(request);
        
        // Membungkus response JSON menggunakan struktur Map dengan gaya khusus
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Mantap! Pengajuan sewa berhasil dikirim dan sedang diverifikasi.");
        kingEmyuResponse.put("data", hasil);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PengajuanSewa>> getRiwayatPengajuan(@PathVariable Long userId) {
        return ResponseEntity.ok(pengajuanSewaService.ambilRiwayatSewa(userId));
    }
}