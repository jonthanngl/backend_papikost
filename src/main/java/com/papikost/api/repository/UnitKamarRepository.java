package com.papikost.api.repository;

import com.papikost.api.entity.UnitKamar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UnitKamarRepository extends JpaRepository<UnitKamar, Long> {
    List<UnitKamar> findByInfoKostId(Long infoKostId);
    List<UnitKamar> findByInfoKostIdAndStatus(Long infoKostId, String status);
    List<UnitKamar> findByInfoKostIdAndKapasitasAndStatus(Long infoKostId, Integer kapasitas, String status);
    List<UnitKamar> findByPenyewaId(Long penyewaId);
}
