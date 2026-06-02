package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kamar_kost")
public class KamarKost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaKost;
    private String daerah; // Padang Bulan, Dr. Mansyur, Setia Budi
    private double hargaDasar;
    private String status; // Tersedia, Penuh, Sisa 1 Kamar

    public KamarKost() {}

    public KamarKost(String namaKost, String daerah, double hargaDasar, String status) {
        this.namaKost = namaKost;
        this.daerah = daerah;
        this.hargaDasar = hargaDasar;
        this.status = status;
    }

    // ENCAPSULATION Getter/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKost() {
        return namaKost;
    }

    public void setNamaKost(String namaKost) {
        this.namaKost = namaKost;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public void setHargaDasar(double hargaDasar) {
        this.hargaDasar = hargaDasar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
