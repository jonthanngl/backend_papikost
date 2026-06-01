package com.papikost.api.seeder;

import com.papikost.api.entity.KamarKost;
import com.papikost.api.entity.Penyewa;
import com.papikost.api.repository.KamarKostRepository;
import com.papikost.api.repository.PenyewaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final PenyewaRepository penyewaRepository;
    private final KamarKostRepository kamarKostRepository;

    public DatabaseSeeder(PenyewaRepository penyewaRepository, KamarKostRepository kamarKostRepository) {
        this.penyewaRepository = penyewaRepository;
        this.kamarKostRepository = kamarKostRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Memulai Seeding Database H2 PapiKost...");

        // 1. Seed Data Penyewa (Rio Johanes, Andreas Pegri Damanik, Maruli Ricardo)
        if (penyewaRepository.count() == 0) {
            Penyewa p1 = new Penyewa("riojohanes@email.com", "password123", "Rio Johanes", "Mahasiswa USU", "Medan");
            Penyewa p2 = new Penyewa("andreas@email.com", "password123", "Andreas Pegri Damanik", "Karyawan Swasta", "Simalungun");
            Penyewa p3 = new Penyewa("maruli@email.com", "password123", "Maruli Ricardo", "Karyawan Swasta", "Toba");

            penyewaRepository.save(p1);
            penyewaRepository.save(p2);
            penyewaRepository.save(p3);

            System.out.println("Berhasil seeding data Penyewa.");
        }

        // 2. Seed Data Kamar Kost Medan (daerah Padang Bulan, Dr. Mansyur, Setia Budi)
        if (kamarKostRepository.count() == 0) {
            KamarKost k1 = new KamarKost("Kost Putra Padang Bulan", "Padang Bulan", 1500000.0, "Tersedia 2 Kamar");
            KamarKost k2 = new KamarKost("Kost Eksklusif Setia Budi", "Setia Budi", 2200000.0, "Tersedia");
            KamarKost k3 = new KamarKost("Kost Putri Dr. Mansyur", "Dr. Mansyur", 1300000.0, "Sisa 1 Kamar");

            kamarKostRepository.save(k1);
            kamarKostRepository.save(k2);
            kamarKostRepository.save(k3);

            System.out.println("Berhasil seeding data Kamar Kost Medan.");
        }

        System.out.println("Seeding Database PapiKost selesai!");
    }
}
