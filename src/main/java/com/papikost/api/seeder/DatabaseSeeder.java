package com.papikost.api.seeder;

import com.papikost.api.entity.Penyewa;
import com.papikost.api.entity.KamarKost;
import com.papikost.api.repository.PenyewaRepository;
import com.papikost.api.repository.KamarKostRepository;
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
        // Seed Akun Penyewa
        if (penyewaRepository.count() == 0) {
            penyewaRepository.save(new Penyewa("andreas@email.com", "pass123", "Andreas Pegri", "Karyawan", "Medan"));
            penyewaRepository.save(new Penyewa("maruli@email.com", "pass123", "Maruli Ricardo", "Karyawan", "Medan"));
            System.out.println("Data Penyewa berhasil di-seed!");
        }

        // Seed Daftar Kamar Kost
        if (kamarKostRepository.count() == 0) {
            kamarKostRepository.save(new KamarKost(
                "Kost Putra Padang Bulan", "Padang Bulan", 1500000.0, "Tersedia 2 Kamar", 4.8,
                "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?auto=format&fit=crop&q=80&w=600",
                true, true, false, 2,
                "Kost premium minimalis dengan pencahayaan alami melimpah, dirancang khusus untuk kenyamanan belajar mahasiswa USU dekat pintu gerbang utama."
            ));
            
            kamarKostRepository.save(new KamarKost(
                "Kost Eksklusif Setia Budi", "Setia Budi", 2200000.0, "Tersedia", 4.9,
                "https://images.unsplash.com/photo-1505691938895-1758d7feb511?auto=format&fit=crop&q=80&w=600",
                true, true, true, 5,
                "Kost mewah berfasilitas lengkap dekat pusat kuliner Setia Budi Medan. Dilengkapi AC, kamar mandi dalam, smart lock, dan area parkir luas."
            ));
            
            kamarKostRepository.save(new KamarKost(
                "Kost Putri Dr. Mansyur", "Dr. Mansyur", 1300000.0, "Sisa 1 Kamar", 4.7,
                "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?auto=format&fit=crop&q=80&w=600",
                true, false, false, 1,
                "Kost putri asri dan kondusif berlokasi strategis di Jalan Dr. Mansyur Medan. Lingkungan aman dengan penjagaan sekuriti 24 jam."
            ));

            System.out.println("Data Kamar Kost berhasil di-seed!");
        }
    }
}