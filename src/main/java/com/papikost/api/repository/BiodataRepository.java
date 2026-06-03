package com.papikost.api.repository;
import com.papikost.api.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Long> {
    List<Biodata> findAllByVerifikasiStatusOrderByUserIdDesc(String verifikasiStatus);
}
