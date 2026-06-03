package com.papikost.api.controller;

import com.papikost.api.entity.InfoKost;
import com.papikost.api.service.InfoKostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/owner/info-kost")
public class InfoKostController {

    @Autowired
    private InfoKostService infoKostService;

    /**
     * Ambil info kost milik owner.
     */
    @GetMapping("/{ownerId}")
    public ResponseEntity<?> getInfoKost(@PathVariable Long ownerId) {
        return infoKostService.getInfoKostByOwner(ownerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Simpan atau update info kost.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveInfoKost(@RequestBody InfoKost infoKost) {
        InfoKost saved = infoKostService.saveInfoKost(infoKost);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Info kost berhasil disimpan!");
        response.put("data", saved);
        
        return ResponseEntity.ok(response);
    }
}
