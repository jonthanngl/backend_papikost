package com.papikost.api.service;

import com.papikost.api.entity.Akun;
import com.papikost.api.entity.Biodata;
import com.papikost.api.entity.PengajuanOwner;
import com.papikost.api.repository.AkunRepository;
import com.papikost.api.repository.BiodataRepository;
import com.papikost.api.repository.PengajuanOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminVerifikasiService {

    private final BiodataRepository biodataRepository;
    private final PengajuanOwnerRepository pengajuanOwnerRepository;
    private final AkunRepository akunRepository;

    public AdminVerifikasiService(BiodataRepository biodataRepository, 
                                  PengajuanOwnerRepository pengajuanOwnerRepository,
                                  AkunRepository akunRepository) {
        this.biodataRepository = biodataRepository;
        this.pengajuanOwnerRepository = pengajuanOwnerRepository;
        this.akunRepository = akunRepository;
    }

    // --- LOGIKA VERIFIKASI BIODATA ---
    public List<Biodata> getAllVerifikasiDataDiri() {
        return biodataRepository.findAll();
    }

    public Biodata updateStatusDataDiri(Long userId, String status) {
        Optional<Biodata> optionalBiodata = biodataRepository.findById(userId);
        if (optionalBiodata.isPresent()) {
            Biodata biodata = optionalBiodata.get();
            // Jika status disetujui, isVerified menjadi true
            biodata.setIsVerified("DISETUJUI".equalsIgnoreCase(status));
            // Catatan: Anda bisa menambahkan field "verifikasiStatus" di entitas Biodata nanti 
            // jika ingin menyimpan status string PENDING/DISETUJUI/DITOLAK secara presisi.
            return biodataRepository.save(biodata);
        }
        throw new RuntimeException("Biodata dengan ID " + userId + " tidak ditemukan.");
    }

    // --- LOGIKA PENGAJUAN OWNER ---
    public PengajuanOwner buatPengajuanOwner(PengajuanOwner pengajuan) {
        return pengajuanOwnerRepository.save(pengajuan);
    }

    public List<PengajuanOwner> getAllPengajuanOwner() {
        return pengajuanOwnerRepository.findAllByOrderByCreatedAtDesc();
    }

    public PengajuanOwner updateStatusPengajuanOwner(Long id, String status) {
        Optional<PengajuanOwner> optionalPengajuan = pengajuanOwnerRepository.findById(id);
        if (optionalPengajuan.isPresent()) {
            PengajuanOwner pengajuan = optionalPengajuan.get();
            pengajuan.setStatus(status);
            
            // Jika disetujui, kita ubah role akun menjadi 'PEMILIK'
            if ("DISETUJUI".equalsIgnoreCase(status)) {
                Optional<Akun> akun = akunRepository.findById(pengajuan.getUserId());
                akun.ifPresent(a -> {
                    a.setRole("PEMILIK");
                    akunRepository.save(a);
                });
            }
            return pengajuanOwnerRepository.save(pengajuan);
        }
        throw new RuntimeException("Pengajuan Owner tidak ditemukan.");
    }
}