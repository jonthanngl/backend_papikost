package com.papikost.api.repository;

import com.papikost.api.entity.Penyewa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenyewaRepository extends JpaRepository<Penyewa, Long> {
    // Mewarisi fungsi-fungsi standar CRUD JPA
}
