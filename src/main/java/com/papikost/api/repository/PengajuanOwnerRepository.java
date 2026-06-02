package com.papikost.api.repository;

import com.papikost.api.entity.PengajuanOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PengajuanOwnerRepository extends JpaRepository<PengajuanOwner, Long> {
    List<PengajuanOwner> findAllByOrderByCreatedAtDesc();
}