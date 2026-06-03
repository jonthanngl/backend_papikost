package com.papikost.api.repository;

import com.papikost.api.entity.InfoKost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InfoKostRepository extends JpaRepository<InfoKost, Long> {
    Optional<InfoKost> findByOwnerId(Long ownerId);
}
