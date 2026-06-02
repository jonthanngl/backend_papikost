package com.papikost.api.controller;

import com.papikost.api.dto.request.ReservasiRequestDTO;
import com.papikost.api.dto.response.ReservasiResponseDTO;
import com.papikost.api.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservasi")
public class ReservasiController {

    @Autowired
    private ReservasiService reservasiService;

    @GetMapping("/hitung")
    public ResponseEntity<ReservasiResponseDTO> hitungReservasi(
            @ModelAttribute ReservasiRequestDTO requestDTO) {
        
        // Controller mendelegasikan tugas ke Service
        ReservasiResponseDTO hasil = reservasiService.prosesKalkulasiTagihan(requestDTO);
        
        return ResponseEntity.ok(hasil);
    }
}