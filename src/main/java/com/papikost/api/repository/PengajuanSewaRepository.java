package com.papikost.api.repository;

import com.papikost.api.entity.PengajuanSewa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PengajuanSewaRepository extends JpaRepository<PengajuanSewa, Long> {
    List<PengajuanSewa> findByUserIdOrderByTanggalPengajuanDesc(Long userId);
    List<PengajuanSewa> findByKamarIdOrderByTanggalPengajuanDesc(Long kamarId);
    List<PengajuanSewa> findByUserIdAndStatus(Long userId, String status);
}
