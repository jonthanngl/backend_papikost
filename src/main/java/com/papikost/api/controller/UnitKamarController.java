package com.papikost.api.controller;

import com.papikost.api.entity.UnitKamar;
import com.papikost.api.service.UnitKamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owner/unit-kamar")
public class UnitKamarController {

    @Autowired
    private UnitKamarService unitKamarService;

    /**
     * GET semua unit kamar berdasarkan infoKostId.
     */
    @GetMapping("/{infoKostId}")
    public ResponseEntity<List<UnitKamar>> getUnitByInfoKost(@PathVariable Long infoKostId) {
        return ResponseEntity.ok(unitKamarService.getUnitByInfoKost(infoKostId));
    }

    /**
     * CREATE unit kamar baru.
     * Body: { infoKostId, nomorKamar, kapasitas, harga, deskripsi, ac, kamarMandiDalam,
     *         kasur, lemari, meja, kursi, fotoUrls }
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> tambahUnit(@RequestBody UnitKamar unit) {
        UnitKamar saved = unitKamarService.tambahUnit(unit);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Unit kamar " + saved.getNomorKamar() + " berhasil ditambahkan.");
        response.put("data", saved);
        
        return ResponseEntity.ok(response);
    }

    /**
     * UPDATE unit kamar.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUnit(@PathVariable Long id, @RequestBody UnitKamar unit) {
        UnitKamar updated = unitKamarService.updateUnit(id, unit);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Unit kamar berhasil diperbarui.");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE unit kamar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> hapusUnit(@PathVariable Long id) {
        unitKamarService.hapusUnit(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Unit kamar berhasil dihapus.");
        
        return ResponseEntity.ok(response);
    }

    /**
     * SET MAINTENANCE unit kamar.
     * Body: { maintenance: true/false }
     */
    @PutMapping("/{id}/maintenance")
    public ResponseEntity<Map<String, Object>> setMaintenance(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body) {
        
        boolean maintenance = Boolean.TRUE.equals(body.get("maintenance"));
        UnitKamar updated = unitKamarService.setMaintenance(id, maintenance);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", maintenance 
            ? "Kamar berhasil diset maintenance. Kamar tidak akan muncul sebagai tersedia."
            : "Status maintenance kamar berhasil diangkat.");
        response.put("data", updated);
        
        return ResponseEntity.ok(response);
    }
}
