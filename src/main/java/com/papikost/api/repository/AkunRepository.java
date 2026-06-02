package com.papikost.api.repository;

import com.papikost.api.entity.Akun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AkunRepository extends JpaRepository<Akun, Long> {
    // Method untuk mengecek data login
    Optional<Akun> findByUsername(String username);
}