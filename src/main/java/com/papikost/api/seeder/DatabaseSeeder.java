package com.papikost.api.seeder;

import com.papikost.api.entity.Penyewa;
import com.papikost.api.repository.AkunRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AkunRepository akunRepository;

    public DatabaseSeeder(AkunRepository akunRepository) {
        this.akunRepository = akunRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // ── Seed Akun Admin (satu-satunya akun default) ──────────────────────
        if (akunRepository.findByUsername("admin").isEmpty()) {
            Penyewa admin = new Penyewa(
                "admin", "admin@papikost.com", "admin123",
                "Administrator", "admin", "-", "-"
            );
            akunRepository.save(admin);
            System.out.println("[Seeder] Akun admin berhasil dibuat!");
        }
        
        System.out.println("[PapiKost] Database siap. Tidak ada data dummy selain akun admin.");
    }
}

