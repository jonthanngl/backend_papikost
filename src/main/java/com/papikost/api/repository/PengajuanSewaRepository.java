package com.papikost.api.repository;

import com.papikost.api.entity.PengajuanSewa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PengajuanSewaRepository extends JpaRepository<PengajuanSewa, Long> {
    // Method khusus untuk mencari riwayat transaksi user, diurutkan dari yang terbaru
    List<PengajuanSewa> findByUserIdOrderByTanggalPengajuanDesc(Long userId);
}