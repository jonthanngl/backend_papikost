package com.papikost.api.repository;

import com.papikost.api.entity.KamarKost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface KamarKostRepository extends JpaRepository<KamarKost, Long> {
    List<KamarKost> findByDaerah(String daerah);
    Optional<KamarKost> findByInfoKostId(Long infoKostId);
    List<KamarKost> findByOwnerId(Long ownerId);
}
