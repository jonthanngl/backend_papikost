package com.papikost.api.controller;

import com.papikost.api.entity.KamarKost;
import com.papikost.api.repository.KamarKostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kost")
public class KamarKostController {

    @Autowired
    private KamarKostRepository kamarKostRepository;

    @GetMapping("/medan")
    public ResponseEntity<List<KamarKost>> getKostMedan() {
        List<KamarKost> daftarKost = kamarKostRepository.findAll();
        return ResponseEntity.ok(daftarKost);
    }
}