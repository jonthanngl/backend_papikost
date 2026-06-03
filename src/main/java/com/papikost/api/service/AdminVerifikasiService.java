package com.papikost.api.service;

import com.papikost.api.entity.Akun;
import com.papikost.api.entity.Biodata;
import com.papikost.api.entity.PengajuanOwner;
import com.papikost.api.repository.AkunRepository;
import com.papikost.api.repository.BiodataRepository;
import com.papikost.api.repository.PengajuanOwnerRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // ── Verifikasi Data Diri (User) ──────────────────────────────────────────

    public List<Biodata> getAllVerifikasiDataDiri() {
        // Tampilkan semua biodata yang sudah diajukan (PENDING, DISETUJUI, DITOLAK)
        return biodataRepository.findAll().stream()
                .filter(b -> b.getVerifikasiStatus() != null && !"BELUM".equals(b.getVerifikasiStatus()))
                .sorted((a, b) -> {
                    int order = statusOrder(a.getVerifikasiStatus()) - statusOrder(b.getVerifikasiStatus());
                    if (order != 0) return order;
                    return Long.compare(b.getUserId(), a.getUserId());
                })
                .collect(Collectors.toList());
    }

    private int statusOrder(String status) {
        if ("PENDING".equals(status))   return 0;
        if ("DITOLAK".equals(status))   return 1;
        if ("DISETUJUI".equals(status)) return 2;
        return 3;
    }

    /**
     * Update status satu berkas user (ktp, kk, atau foto) beserta komentar.
     * Setelah semua berkas di-update, tentukan status keseluruhan:
     * - Semua DISETUJUI → verifikasiStatus = DISETUJUI, isVerified = true
     * - Ada yang DITOLAK → verifikasiStatus = DITOLAK
     * - Ada yang masih PENDING → tetap PENDING
     */
    public Biodata updateStatusBerkasUser(@NonNull Long userId, @NonNull Map<String, String> request) {
        Biodata biodata = biodataRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Biodata dengan ID " + userId + " tidak ditemukan."));

        String jenisBerkas = request.get("jenisBerkas"); // "ktp", "kk", "foto"
        String status = request.get("status").toUpperCase(); // "DISETUJUI" atau "DITOLAK"
        String komentar = request.get("komentar"); // wajib diisi jika DITOLAK

        switch (jenisBerkas.toLowerCase()) {
            case "ktp":
                biodata.setKtpStatus(status);
                biodata.setKtpKomentar(komentar);
                break;
            case "kk":
                biodata.setKkStatus(status);
                biodata.setKkKomentar(komentar);
                break;
            case "foto":
                biodata.setFotoStatus(status);
                biodata.setFotoKomentar(komentar);
                break;
            default:
                throw new RuntimeException("Jenis berkas tidak valid: " + jenisBerkas);
        }

        // Tentukan status keseluruhan berdasarkan semua berkas
        recalculateVerifikasiStatusUser(biodata);

        return biodataRepository.save(biodata);
    }

    private void recalculateVerifikasiStatusUser(Biodata biodata) {
        String ktpSt = biodata.getKtpStatus();
        String kkSt = biodata.getKkStatus();
        String fotoSt = biodata.getFotoStatus();

        if ("DISETUJUI".equals(ktpSt) && "DISETUJUI".equals(kkSt) && "DISETUJUI".equals(fotoSt)) {
            biodata.setVerifikasiStatus("DISETUJUI");
            biodata.setIsVerified(true);
        } else if ("DITOLAK".equals(ktpSt) || "DITOLAK".equals(kkSt) || "DITOLAK".equals(fotoSt)) {
            biodata.setVerifikasiStatus("DITOLAK");
            biodata.setIsVerified(false);
        } else {
            // Masih ada yang PENDING atau BELUM
            biodata.setVerifikasiStatus("PENDING");
            biodata.setIsVerified(false);
        }
    }

    // ── Pengajuan Owner ──────────────────────────────────────────────────────

    public PengajuanOwner buatPengajuanOwner(@NonNull PengajuanOwner pengajuan) {
        return pengajuanOwnerRepository.save(pengajuan);
    }

    public List<PengajuanOwner> getAllPengajuanOwner() {
        return pengajuanOwnerRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * Update status satu berkas owner (ktp, suratKepemilikan, atau fotoKost) beserta komentar.
     * Setelah semua berkas di-update, tentukan status keseluruhan.
     */
    public PengajuanOwner updateStatusBerkasOwner(@NonNull Long id, @NonNull Map<String, String> request) {
        PengajuanOwner pengajuan = pengajuanOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengajuan Owner tidak ditemukan."));

        String jenisBerkas = request.get("jenisBerkas"); // "ktp", "suratKepemilikan", "fotoKost"
        String status = request.get("status").toUpperCase();
        String komentar = request.get("komentar");

        switch (jenisBerkas.toLowerCase()) {
            case "ktp":
                pengajuan.setKtpStatus(status);
                pengajuan.setKtpKomentar(komentar);
                break;
            case "suratkepemilikan":
                pengajuan.setSuratKepemilikanStatus(status);
                pengajuan.setSuratKepemilikanKomentar(komentar);
                break;
            case "fotokost":
                pengajuan.setFotoKostStatus(status);
                pengajuan.setFotoKostKomentar(komentar);
                break;
            default:
                throw new RuntimeException("Jenis berkas tidak valid: " + jenisBerkas);
        }

        // Tentukan status keseluruhan
        recalculateStatusOwner(pengajuan);

        // Jika semua berkas disetujui, ubah role akun jadi pemilik
        if ("DISETUJUI".equals(pengajuan.getStatus())) {
            Long userId = pengajuan.getUserId();
            if (userId != null) {
                Optional<Akun> akunOpt = akunRepository.findById(userId);
                akunOpt.ifPresent(akun -> {
                    akun.setRole("pemilik");
                    akunRepository.save(akun);
                });
            }
        }

        return pengajuanOwnerRepository.save(pengajuan);
    }

    private void recalculateStatusOwner(PengajuanOwner pengajuan) {
        String ktpSt = pengajuan.getKtpStatus();
        String suratSt = pengajuan.getSuratKepemilikanStatus();
        String fotoSt = pengajuan.getFotoKostStatus();

        if ("DISETUJUI".equals(ktpSt) && "DISETUJUI".equals(suratSt) && "DISETUJUI".equals(fotoSt)) {
            pengajuan.setStatus("DISETUJUI");
        } else if ("DITOLAK".equals(ktpSt) || "DITOLAK".equals(suratSt) || "DITOLAK".equals(fotoSt)) {
            pengajuan.setStatus("DITOLAK");
        } else {
            pengajuan.setStatus("PENDING");
        }
    }
}

