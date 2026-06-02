package com.papikost.api.controller;

import com.papikost.api.entity.PengajuanSewa;
import com.papikost.api.service.PengajuanSewaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private PengajuanSewaService pengajuanSewaService;

    // Endpoint untuk menampilkan daftar pengajuan di dashboard Owner
    @GetMapping("/pengajuan-masuk")
    public ResponseEntity<List<PengajuanSewa>> getSemuaPengajuanSewa() {
        List<PengajuanSewa> daftarPengajuan = pengajuanSewaService.getAllPengajuanMasuk();
        return ResponseEntity.ok(daftarPengajuan);
    }

    // Endpoint untuk menekan tombol Setujui / Tolak
    @PutMapping("/pengajuan-masuk/{id}")
    public ResponseEntity<Map<String, Object>> prosesPengajuanSewa(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody) {

        String statusBaru = requestBody.get("status");
        PengajuanSewa diupdate = pengajuanSewaService.updateStatusPengajuan(id, statusBaru);

        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Mantap! Status pengajuan sewa berhasil diubah menjadi: " + statusBaru);
        kingEmyuResponse.put("data", diupdate);

        return ResponseEntity.ok(kingEmyuResponse);
    }
}