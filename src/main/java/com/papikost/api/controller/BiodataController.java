package com.papikost.api.controller;

import com.papikost.api.entity.Biodata;
import com.papikost.api.repository.BiodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/biodata")
public class BiodataController {

    @Autowired
    private BiodataRepository biodataRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<Biodata> getBiodata(@PathVariable @NonNull Long userId) {
        return biodataRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> saveBiodata(
            @PathVariable @NonNull Long userId,
            @RequestBody @NonNull Biodata biodataInput) {

        // Ambil data yang sudah ada (jika ada), untuk mempertahankan status berkas
        Biodata existing = biodataRepository.findById(userId).orElse(new Biodata());

        existing.setUserId(userId);
        existing.setNamaLengkap(biodataInput.getNamaLengkap());
        existing.setTanggalLahir(biodataInput.getTanggalLahir());
        existing.setTempatLahir(biodataInput.getTempatLahir());
        existing.setJenisKelamin(biodataInput.getJenisKelamin());
        existing.setNoHp(biodataInput.getNoHp());
        existing.setAlamat(biodataInput.getAlamat());
        existing.setPekerjaan(biodataInput.getPekerjaan());

        // Update URL berkas jika ada yang baru dikirim
        if (biodataInput.getKtpUrl() != null && !biodataInput.getKtpUrl().isEmpty()) {
            existing.setKtpUrl(biodataInput.getKtpUrl());
            existing.setKtpStatus("PENDING"); // Reset status berkas saat upload ulang
            existing.setKtpKomentar(null);
        }
        if (biodataInput.getKkUrl() != null && !biodataInput.getKkUrl().isEmpty()) {
            existing.setKkUrl(biodataInput.getKkUrl());
            existing.setKkStatus("PENDING");
            existing.setKkKomentar(null);
        }
        if (biodataInput.getFotoUrl() != null && !biodataInput.getFotoUrl().isEmpty()) {
            existing.setFotoUrl(biodataInput.getFotoUrl());
            existing.setFotoStatus("PENDING");
            existing.setFotoKomentar(null);
        }

        // Set status keseluruhan ke PENDING setiap kali user submit
        existing.setIsVerified(false);
        existing.setVerifikasiStatus("PENDING");

        Biodata savedBiodata = biodataRepository.save(existing);

        Map<String, Object> response = new HashMap<>();
        response.put("biodata", savedBiodata);
        response.put("message", "Data diri berhasil disimpan dan dikirim ke admin untuk diverifikasi.");

        return ResponseEntity.ok(response);
    }
}
