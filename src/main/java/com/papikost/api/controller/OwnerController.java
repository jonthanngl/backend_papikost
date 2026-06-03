package com.papikost.api.controller;

import com.papikost.api.entity.Laporan;
import com.papikost.api.entity.PengajuanSewa;
import com.papikost.api.repository.LaporanRepository;
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

    @Autowired
    private LaporanRepository laporanRepository;

    /**
     * Pengajuan sewa yang masuk ke kost tertentu (berdasarkan infoKostId).
     */
    @GetMapping("/pengajuan-masuk/{infoKostId}")
    public ResponseEntity<List<PengajuanSewa>> getPengajuanByKost(@PathVariable Long infoKostId) {
        return ResponseEntity.ok(pengajuanSewaService.getPengajuanByKostId(infoKostId));
    }

    /**
     * Owner menyetujui / menolak pengajuan sewa.
     * Body: { status: "DITERIMA" | "DITOLAK" }
     * Jika DITERIMA → otomatis alokasi unit kamar.
     */
    @PutMapping("/pengajuan-masuk/{id}")
    public ResponseEntity<Map<String, Object>> prosesPengajuanSewa(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody) {

        String statusBaru = requestBody.get("status");
        PengajuanSewa diupdate = pengajuanSewaService.updateStatusPengajuan(id, statusBaru);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Status pengajuan sewa berhasil diubah menjadi: " + statusBaru);
        response.put("data", diupdate);

        return ResponseEntity.ok(response);
    }

    /**
     * GET semua laporan kerusakan yang masuk (tiket perbaikan) per kost.
     * Karena laporan terikat ke unitKamar, owner bisa melihat semua laporan dari unit mereka.
     */
    @GetMapping("/tiket-perbaikan/{infoKostId}")
    public ResponseEntity<List<Laporan>> getTiketPerbaikan(@PathVariable Long infoKostId) {
        List<Laporan> tiket = laporanRepository.findByInfoKostIdOrderByIdDesc(infoKostId);
        return ResponseEntity.ok(tiket);
    }

    /**
     * Owner mengubah status tiket perbaikan.
     * Body: { status: "ACC" | "DIPROSES" | "SELESAI" | "DITOLAK", catatanOwner: "..." }
     */
    @PutMapping("/tiket-perbaikan/{id}")
    public ResponseEntity<Map<String, Object>> updateStatusTiket(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody) {

        Laporan laporan = laporanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tiket perbaikan tidak ditemukan."));
        
        laporan.setStatus(requestBody.get("status"));
        if (requestBody.get("catatanOwner") != null) {
            laporan.setCatatanOwner(requestBody.get("catatanOwner"));
        }
        
        Laporan updated = laporanRepository.save(laporan);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Status tiket perbaikan diperbarui.");
        response.put("data", updated);

        return ResponseEntity.ok(response);
    }
}
