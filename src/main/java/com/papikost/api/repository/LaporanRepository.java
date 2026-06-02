package com.papikost.api.repository;
import com.papikost.api.entity.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LaporanRepository extends JpaRepository<Laporan, Long> {
    List<Laporan> findByUserIdOrderByIdDesc(Long userId);
}