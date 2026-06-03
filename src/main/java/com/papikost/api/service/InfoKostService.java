package com.papikost.api.service;

import com.papikost.api.entity.InfoKost;
import com.papikost.api.entity.KamarKost;
import com.papikost.api.entity.UnitKamar;
import com.papikost.api.repository.InfoKostRepository;
import com.papikost.api.repository.KamarKostRepository;
import com.papikost.api.repository.UnitKamarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoKostService {

    private final InfoKostRepository infoKostRepository;
    private final KamarKostRepository kamarKostRepository;
    private final UnitKamarRepository unitKamarRepository;

    public InfoKostService(InfoKostRepository infoKostRepository,
                           KamarKostRepository kamarKostRepository,
                           UnitKamarRepository unitKamarRepository) {
        this.infoKostRepository = infoKostRepository;
        this.kamarKostRepository = kamarKostRepository;
        this.unitKamarRepository = unitKamarRepository;
    }

    /**
     * Ambil info kost milik seorang owner.
     */
    public Optional<InfoKost> getInfoKostByOwner(Long ownerId) {
        return infoKostRepository.findByOwnerId(ownerId);
    }

    /**
     * Simpan atau update info kost owner.
     * Setelah save, sinkronisasi data ke KamarKost agar muncul di halaman user.
     */
    public InfoKost saveInfoKost(InfoKost infoKost) {
        InfoKost saved = infoKostRepository.save(infoKost);
        syncToKamarKost(saved);
        return saved;
    }

    /**
     * Sinkronisasi InfoKost ke entitas KamarKost yang digunakan untuk tampilan user.
     * Menghitung jumlah unit KOSONG untuk solo dan duo.
     * Juga mengambil foto representatif dari unit kamar pertama yang tersedia.
     */
    public void syncToKamarKost(InfoKost infoKost) {
        List<UnitKamar> allUnits = unitKamarRepository.findByInfoKostId(infoKost.getId());

        long soloKosong = allUnits.stream()
                .filter(u -> u.getKapasitas() != null && u.getKapasitas() == 1 && "KOSONG".equals(u.getStatus()))
                .count();
        long duoKosong = allUnits.stream()
                .filter(u -> u.getKapasitas() != null && u.getKapasitas() == 2 && "KOSONG".equals(u.getStatus()))
                .count();

        // Tentukan status keseluruhan kost
        String status = (soloKosong > 0 || duoKosong > 0) ? "Tersedia" : "Tidak Tersedia";

        // Ambil foto representatif: foto pertama dari unit pertama yang punya foto
        String representativeImage = allUnits.stream()
                .filter(u -> u.getFotoUrls() != null && !u.getFotoUrls().isBlank())
                .findFirst()
                .map(u -> u.getFotoUrls().split(",")[0].trim())
                .orElse(null);

        // Kumpulkan semua fotoUrls dari semua unit (untuk detail page)
        String allFotoUrls = allUnits.stream()
                .filter(u -> u.getFotoUrls() != null && !u.getFotoUrls().isBlank())
                .map(UnitKamar::getFotoUrls)
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "," + b);

        Optional<KamarKost> existingOpt = kamarKostRepository.findByInfoKostId(infoKost.getId());
        KamarKost kamarKost = existingOpt.orElse(new KamarKost());

        kamarKost.setOwnerId(infoKost.getOwnerId());
        kamarKost.setInfoKostId(infoKost.getId());
        kamarKost.setNamaKost(infoKost.getNamaKost());
        kamarKost.setDaerah(infoKost.getDaerah());
        kamarKost.setAlamatLengkap(infoKost.getAlamatLengkap());
        kamarKost.setKategori(infoKost.getKategori());
        kamarKost.setHargaDasar(infoKost.getHargaDasar());
        kamarKost.setStatus(status);
        kamarKost.setAvailableRoomsSolo((int) soloKosong);
        kamarKost.setAvailableRoomsDuo((int) duoKosong);
        kamarKost.setWifiCepat(infoKost.getWifi());
        kamarKost.setParkir(infoKost.getParkir());
        kamarKost.setLaundry(infoKost.getLaundry());
        kamarKost.setDapur(infoKost.getDapur());
        kamarKost.setNomorWhatsApp(infoKost.getNomorWhatsApp());
        kamarKost.setRating(kamarKost.getRating() != null ? kamarKost.getRating() : 0.0);
        // Update foto — pakai foto unit jika ada, jangan timpa kalau sudah punya
        if (representativeImage != null) {
            kamarKost.setImage(representativeImage);
        }
        // Simpan semua URL foto untuk dipakai di detail page
        if (!allFotoUrls.isEmpty()) {
            kamarKost.setFotoUrls(allFotoUrls);
        }

        kamarKostRepository.save(kamarKost);
    }
}
