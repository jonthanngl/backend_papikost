package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PengajuanOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String namaLengkap;
    private String email;
    private String namaKost;
    private String alamatKost;
    private String daerah;
    
    // Berkas URL
    private String ktpUrl;
    private String suratKepemilikanUrl;
    private String fotoKostUrl;
    
    // Status verifikasi per berkas (BELUM, PENDING, DISETUJUI, DITOLAK)
    private String ktpStatus = "BELUM";
    private String suratKepemilikanStatus = "BELUM";
    private String fotoKostStatus = "BELUM";
    
    // Komentar admin jika ditolak
    private String ktpKomentar;
    private String suratKepemilikanKomentar;
    private String fotoKostKomentar;
    
    private String status; // PENDING, DISETUJUI, DITOLAK
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNamaKost() { return namaKost; }
    public void setNamaKost(String namaKost) { this.namaKost = namaKost; }
    public String getAlamatKost() { return alamatKost; }
    public void setAlamatKost(String alamatKost) { this.alamatKost = alamatKost; }
    public String getDaerah() { return daerah; }
    public void setDaerah(String daerah) { this.daerah = daerah; }
    public String getKtpUrl() { return ktpUrl; }
    public void setKtpUrl(String ktpUrl) { this.ktpUrl = ktpUrl; }
    public String getSuratKepemilikanUrl() { return suratKepemilikanUrl; }
    public void setSuratKepemilikanUrl(String suratKepemilikanUrl) { this.suratKepemilikanUrl = suratKepemilikanUrl; }
    public String getFotoKostUrl() { return fotoKostUrl; }
    public void setFotoKostUrl(String fotoKostUrl) { this.fotoKostUrl = fotoKostUrl; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getKtpStatus() { return ktpStatus; }
    public void setKtpStatus(String ktpStatus) { this.ktpStatus = ktpStatus; }

    public String getSuratKepemilikanStatus() { return suratKepemilikanStatus; }
    public void setSuratKepemilikanStatus(String suratKepemilikanStatus) { this.suratKepemilikanStatus = suratKepemilikanStatus; }

    public String getFotoKostStatus() { return fotoKostStatus; }
    public void setFotoKostStatus(String fotoKostStatus) { this.fotoKostStatus = fotoKostStatus; }

    public String getKtpKomentar() { return ktpKomentar; }
    public void setKtpKomentar(String ktpKomentar) { this.ktpKomentar = ktpKomentar; }

    public String getSuratKepemilikanKomentar() { return suratKepemilikanKomentar; }
    public void setSuratKepemilikanKomentar(String suratKepemilikanKomentar) { this.suratKepemilikanKomentar = suratKepemilikanKomentar; }

    public String getFotoKostKomentar() { return fotoKostKomentar; }
    public void setFotoKostKomentar(String fotoKostKomentar) { this.fotoKostKomentar = fotoKostKomentar; }
}