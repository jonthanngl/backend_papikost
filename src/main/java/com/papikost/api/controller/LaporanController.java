package com.papikost.api.controller;

import com.papikost.api.dto.request.LaporanRequestDTO;
import com.papikost.api.entity.Laporan;
import com.papikost.api.service.LaporanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/laporan")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    // Menangani endpoint: GET /api/laporan?userId=xxx
    @GetMapping
    public ResponseEntity<List<Laporan>> getLaporanUser(@RequestParam Long userId) {
        return ResponseEntity.ok(laporanService.getLaporanByUserId(userId));
    }

    // Menangani endpoint: POST /api/laporan
    @PostMapping
    public ResponseEntity<Laporan> submitLaporan(@RequestBody LaporanRequestDTO request) {
        Laporan laporanBaru = laporanService.buatLaporanBaru(request);
        return ResponseEntity.ok(laporanBaru);
    }
}