package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Laporan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private LocalDate tanggal;
    private String kategori;
    private String kendala;
    private String detail;
    private String status; // BARU, DIPROSES, SELESAI

    @PrePersist
    protected void onCreate() {
        this.tanggal = LocalDate.now();
        if (this.status == null) this.status = "BARU";
    }

    // Tambahkan Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public String getKendala() { return kendala; }
    public void setKendala(String kendala) { this.kendala = kendala; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}