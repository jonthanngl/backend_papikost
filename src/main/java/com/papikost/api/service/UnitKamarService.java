package com.papikost.api.service;

import com.papikost.api.entity.InfoKost;
import com.papikost.api.entity.UnitKamar;
import com.papikost.api.repository.InfoKostRepository;
import com.papikost.api.repository.UnitKamarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitKamarService {

    private final UnitKamarRepository unitKamarRepository;
    private final InfoKostService infoKostService;
    private final InfoKostRepository infoKostRepository;

    public UnitKamarService(UnitKamarRepository unitKamarRepository,
                            InfoKostService infoKostService,
                            InfoKostRepository infoKostRepository) {
        this.unitKamarRepository = unitKamarRepository;
        this.infoKostService = infoKostService;
        this.infoKostRepository = infoKostRepository;
    }

    public List<UnitKamar> getUnitByInfoKost(Long infoKostId) {
        return unitKamarRepository.findByInfoKostId(infoKostId);
    }

    public UnitKamar tambahUnit(UnitKamar unit) {
        UnitKamar saved = unitKamarRepository.save(unit);
        syncKamarKost(unit.getInfoKostId());
        return saved;
    }

    public UnitKamar updateUnit(Long id, UnitKamar unitBaru) {
        UnitKamar existing = unitKamarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit kamar tidak ditemukan: " + id));
        
        existing.setNomorKamar(unitBaru.getNomorKamar());
        existing.setKapasitas(unitBaru.getKapasitas());
        existing.setHarga(unitBaru.getHarga());
        existing.setDeskripsi(unitBaru.getDeskripsi());
        existing.setAc(unitBaru.getAc());
        existing.setKamarMandiDalam(unitBaru.getKamarMandiDalam());
        existing.setKasur(unitBaru.getKasur());
        existing.setLemari(unitBaru.getLemari());
        existing.setMeja(unitBaru.getMeja());
        existing.setKursi(unitBaru.getKursi());
        existing.setFotoUrls(unitBaru.getFotoUrls());
        
        UnitKamar saved = unitKamarRepository.save(existing);
        syncKamarKost(existing.getInfoKostId());
        return saved;
    }

    public void hapusUnit(Long id) {
        UnitKamar unit = unitKamarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit kamar tidak ditemukan: " + id));
        Long infoKostId = unit.getInfoKostId();
        unitKamarRepository.deleteById(id);
        syncKamarKost(infoKostId);
    }

    /**
     * Set status MAINTENANCE - kamar tidak akan muncul sebagai tersedia di halaman user.
     */
    public UnitKamar setMaintenance(Long id, boolean maintenance) {
        UnitKamar unit = unitKamarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit kamar tidak ditemukan: " + id));
        
        if (maintenance) {
            unit.setStatus("MAINTENANCE");
        } else {
            unit.setStatus("KOSONG");
        }
        
        UnitKamar saved = unitKamarRepository.save(unit);
        syncKamarKost(unit.getInfoKostId());
        return saved;
    }

    /**
     * Sinkronkan ketersediaan kamar ke KamarKost (tampilan user).
     */
    private void syncKamarKost(Long infoKostId) {
        if (infoKostId != null) {
            infoKostRepository.findById(infoKostId).ifPresent(infoKostService::syncToKamarKost);
        }
    }
}
