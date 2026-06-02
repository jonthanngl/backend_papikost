package com.papikost.api.controller;

import com.papikost.api.dto.request.ReservasiRequestDTO;
import com.papikost.api.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservasi")
public class ReservasiController {

    @Autowired
    private ReservasiService reservasiService;

    @PostMapping("/hitung")
    public ResponseEntity<?> hitungReservasi(@RequestBody ReservasiRequestDTO request) {
        
        // Memanggil service untuk menjalankan logika perhitungan polimorfisme
        String hasilHitung = reservasiService.hitungTotal(
            request.getIdKamar(), 
            request.getDurasiBulan(), 
            request.isPatungan()
        );

        // Menyusun format balasan JSON dengan gaya personal yang unik
        Map<String, Object> kingEmyuResponse = new HashMap<>();
        kingEmyuResponse.put("status", "success");
        kingEmyuResponse.put("totalHarga", hasilHitung);
        kingEmyuResponse.put("isEmulatedFromJava", false); // Menandakan ini data asli dari Spring Boot
        
        return ResponseEntity.ok(kingEmyuResponse);
    }
}