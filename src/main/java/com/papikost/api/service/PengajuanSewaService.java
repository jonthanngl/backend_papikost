package com.papikost.api.service;

import com.papikost.api.dto.request.PengajuanSewaRequestDTO;
import com.papikost.api.entity.Biodata;
import com.papikost.api.entity.PengajuanSewa;
import com.papikost.api.entity.UnitKamar;
import com.papikost.api.repository.AkunRepository;
import com.papikost.api.repository.BiodataRepository;
import com.papikost.api.repository.PengajuanSewaRepository;
import com.papikost.api.repository.UnitKamarRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PengajuanSewaService {

    private final PengajuanSewaRepository pengajuanSewaRepository;
    private final UnitKamarRepository unitKamarRepository;
    private final AkunRepository akunRepository;
    private final BiodataRepository biodataRepository;

    public PengajuanSewaService(PengajuanSewaRepository repository,
                                UnitKamarRepository unitKamarRepository,
                                AkunRepository akunRepository,
                                BiodataRepository biodataRepository) {
        this.pengajuanSewaRepository = repository;
        this.unitKamarRepository = unitKamarRepository;
        this.akunRepository = akunRepository;
        this.biodataRepository = biodataRepository;
    }

    public PengajuanSewa prosesPengajuan(@NonNull PengajuanSewaRequestDTO request) {
        // Validasi: biodata user harus sudah DISETUJUI
        Optional<Biodata> biodataOpt = biodataRepository.findById(request.getUserId());
        if (biodataOpt.isEmpty() || !Boolean.TRUE.equals(biodataOpt.get().getIsVerified())) {
            throw new RuntimeException("Biodata Anda belum diverifikasi admin. Harap lengkapi dan ajukan verifikasi terlebih dahulu.");
        }

        // Validasi: tidak boleh ada pengajuan PENDING sebelumnya untuk user yang sama
        List<PengajuanSewa> pengajuanAktif = pengajuanSewaRepository.findByUserIdAndStatus(request.getUserId(), "PENDING");
        if (!pengajuanAktif.isEmpty()) {
            throw new RuntimeException("Anda masih memiliki pengajuan sewa yang sedang menunggu persetujuan.");
        }

        PengajuanSewa pengajuan = new PengajuanSewa();
        pengajuan.setUserId(request.getUserId());
        pengajuan.setKamarId(request.getKamarId()); // ini adalah infoKostId
        pengajuan.setTipeSewa(request.getTipeSewa());
        pengajuan.setDurasiBulan(request.getDurasiBulan());
        pengajuan.setTotalTagihan(request.getTotalTagihan());
        pengajuan.setRoommateId(request.getRoommateId());
        pengajuan.setRoommateUsername(request.getRoommateUsername());
        return pengajuanSewaRepository.save(pengajuan);
    }

    public List<PengajuanSewa> ambilRiwayatSewa(@NonNull Long userId) {
        return pengajuanSewaRepository.findByUserIdOrderByTanggalPengajuanDesc(userId);
    }

    /**
     * Untuk Owner: list pengajuan yang masuk ke kost mereka.
     * Berdasarkan kamarId yang cocok dengan infoKost milik owner.
     */
    public List<PengajuanSewa> getPengajuanByKostId(Long infoKostId) {
        return pengajuanSewaRepository.findByKamarIdOrderByTanggalPengajuanDesc(infoKostId);
    }

    public List<PengajuanSewa> getAllPengajuanMasuk() {
        return pengajuanSewaRepository.findAll();
    }

    /**
     * Update status pengajuan sewa.
     * Jika DITERIMA → cari unit kamar yang kosong sesuai tipe sewa, alokasikan ke penyewa.
     */
    public PengajuanSewa updateStatusPengajuan(@NonNull Long id, @NonNull String status) {
        PengajuanSewa pengajuan = pengajuanSewaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data pengajuan sewa tidak ditemukan dengan ID: " + id));
        
        pengajuan.setStatus(status);

        if ("DITERIMA".equalsIgnoreCase(status)) {
            // Tentukan kapasitas berdasarkan tipe sewa
            int kapasitas = "DUO".equalsIgnoreCase(pengajuan.getTipeSewa()) ? 2 : 1;
            Long infoKostId = pengajuan.getKamarId();

            // Cari unit kamar kosong dengan kapasitas sesuai
            List<UnitKamar> unitKosong = unitKamarRepository
                    .findByInfoKostIdAndKapasitasAndStatus(infoKostId, kapasitas, "KOSONG");

            if (unitKosong.isEmpty()) {
                throw new RuntimeException("Tidak ada unit kamar kosong tersedia untuk tipe sewa ini.");
            }

            UnitKamar unit = unitKosong.get(0);
            
            // Ambil nama penyewa
            String namaPenyewa = akunRepository.findById(pengajuan.getUserId())
                    .map(akun -> akun.getNamaLengkap())
                    .orElse("Penyewa");

            // Set info penyewa pada unit
            unit.setStatus("TERISI");
            unit.setPenyewaId(pengajuan.getUserId());
            unit.setNamaPenyewa(namaPenyewa);
            unit.setTanggalMasuk(LocalDate.now());
            unit.setBatasKontrak(LocalDate.now().plusMonths(pengajuan.getDurasiBulan()));
            unitKamarRepository.save(unit);

            // Simpan referensi unit di pengajuan
            pengajuan.setUnitKamarId(unit.getId());
            pengajuan.setTanggalMulai(unit.getTanggalMasuk());
            pengajuan.setBatasKontrak(unit.getBatasKontrak());
        }

        return pengajuanSewaRepository.save(pengajuan);
    }
}

