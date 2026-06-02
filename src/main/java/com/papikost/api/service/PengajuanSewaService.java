package com.papikost.api.service;

import com.papikost.api.dto.request.PengajuanSewaRequestDTO;
import com.papikost.api.entity.PengajuanSewa;
import com.papikost.api.repository.PengajuanSewaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PengajuanSewaService {
    
    private final PengajuanSewaRepository pengajuanSewaRepository;

    public PengajuanSewaService(PengajuanSewaRepository repository) {
        this.pengajuanSewaRepository = repository;
    }

    public PengajuanSewa prosesPengajuan(PengajuanSewaRequestDTO request) {
        PengajuanSewa pengajuan = new PengajuanSewa();
        pengajuan.setUserId(request.getUserId());
        pengajuan.setKamarId(request.getKamarId());
        pengajuan.setTipeSewa(request.getTipeSewa());
        pengajuan.setDurasiBulan(request.getDurasiBulan());
        pengajuan.setTotalTagihan(request.getTotalTagihan());
        
        return pengajuanSewaRepository.save(pengajuan);
    }

    public List<PengajuanSewa> ambilRiwayatSewa(Long userId) {
        return pengajuanSewaRepository.findByUserIdOrderByTanggalPengajuanDesc(userId);
    }
    // Method untuk mengambil semua pengajuan yang masuk
    public List<PengajuanSewa> getAllPengajuanMasuk() {
        return pengajuanSewaRepository.findAll();
    }

    // Method untuk menyetujui atau menolak pengajuan
    public PengajuanSewa updateStatusPengajuan(Long id, String status) {
        Optional<PengajuanSewa> optionalPengajuan = pengajuanSewaRepository.findById(id);
        
        if (optionalPengajuan.isPresent()) {
            PengajuanSewa pengajuan = optionalPengajuan.get();
            pengajuan.setStatus(status); // Set status menjadi "DISETUJUI" atau "DITOLAK"
            return pengajuanSewaRepository.save(pengajuan);
        }
        
        throw new RuntimeException("Data pengajuan sewa tidak ditemukan dengan ID: " + id);
    }
}