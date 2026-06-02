package com.papikost.api.service;

import com.papikost.api.entity.Reservasi;
import com.papikost.api.repository.KamarKostRepository;
import com.papikost.api.repository.PenyewaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservasiService {

    @Autowired
    private KamarKostRepository kamarKostRepository;

    @Autowired
    private PenyewaRepository penyewaRepository;

    // TODO: Pindahkan logika perhitungan harga (ReservasiSolo / ReservasiPatungan) ke sini
    
    public String hitungTotal(Long kamarId, int durasiBulan, boolean isPatungan) {
        // Logika polimorfisme Anda bisa diletakkan di sini
        return "Harga sedang dihitung..."; 
    }
    
    // Method untuk menyimpan data ke database
    public void simpanReservasi(Reservasi reservasi) {
        // Logika simpan
    }
}