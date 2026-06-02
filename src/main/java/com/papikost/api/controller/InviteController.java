package com.papikost.api.controller;

import com.papikost.api.dto.request.InviteRequestDTO;
import com.papikost.api.entity.InvitePatungan;
import com.papikost.api.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invite")
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> buatUndangan(@RequestBody InviteRequestDTO request) {
        InvitePatungan hasil = inviteService.kirimUndangan(request);
        
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Undangan patungan berhasil dikirim!");
        kingEmyuResponse.put("data", hasil);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<InvitePatungan>> getUndangan(@PathVariable String userId) {
        return ResponseEntity.ok(inviteService.getUndanganUntukUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStatusUndangan(
            @PathVariable Long id, 
            @RequestBody Map<String, String> requestBody) {
        
        String status = requestBody.get("status");
        InvitePatungan diupdate = inviteService.responsUndangan(id, status);
        
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("success", true);
        kingEmyuResponse.put("message", "Status undangan diubah menjadi " + status);
        kingEmyuResponse.put("data", diupdate);
        
        return ResponseEntity.ok(kingEmyuResponse);
    }
}