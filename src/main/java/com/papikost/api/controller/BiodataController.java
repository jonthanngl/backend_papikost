package com.papikost.api.controller;

import com.papikost.api.entity.Biodata;
import com.papikost.api.repository.BiodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/biodata")
public class BiodataController {

    @Autowired
    private BiodataRepository biodataRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<Biodata> getBiodata(@PathVariable Long userId) {
        return biodataRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> saveBiodata(
            @PathVariable Long userId, 
            @RequestBody Biodata biodataInput) {
        
        biodataInput.setUserId(userId);
        // Jika data sebelumnya belum diverifikasi, pertahankan status false
        Biodata savedBiodata = biodataRepository.save(biodataInput);
        
        Map<String, Object> response = new HashMap<>();
        response.put("biodata", savedBiodata);
        response.put("message", "Data diri berhasil disimpan!");
        
        return ResponseEntity.ok(response);
    }
}