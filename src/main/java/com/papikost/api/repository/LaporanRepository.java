package com.papikost.api.repository;
import com.papikost.api.entity.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LaporanRepository extends JpaRepository<Laporan, Long> {
    List<Laporan> findByUserIdOrderByIdDesc(Long userId);
    
    /**
     * Ambil semua laporan dari unit kamar yang berada di bawah infoKost tertentu.
     * Gunakan query JOIN dengan UnitKamar.
     */
    @Query("SELECT l FROM Laporan l JOIN UnitKamar u ON l.unitKamarId = u.id WHERE u.infoKostId = :infoKostId ORDER BY l.id DESC")
    List<Laporan> findByInfoKostIdOrderByIdDesc(@Param("infoKostId") Long infoKostId);
}
