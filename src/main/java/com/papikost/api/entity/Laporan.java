package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Laporan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long unitKamarId; // Referensi ke unit kamar yang dilaporkan
    private LocalDate tanggal;
    private String kategori;
    private String kendala;
    @Column(length = 2000)
    private String detail;
    
    // Status: BARU, ACC, DIPROSES, SELESAI, DITOLAK
    private String status;
    
    // Catatan dari owner
    @Column(length = 1000)
    private String catatanOwner;

    @PrePersist
    protected void onCreate() {
        this.tanggal = LocalDate.now();
        if (this.status == null) this.status = "BARU";
    }

    // ── Getter & Setter lengkap ──────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getUnitKamarId() { return unitKamarId; }
    public void setUnitKamarId(Long unitKamarId) { this.unitKamarId = unitKamarId; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getKendala() { return kendala; }
    public void setKendala(String kendala) { this.kendala = kendala; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCatatanOwner() { return catatanOwner; }
    public void setCatatanOwner(String catatanOwner) { this.catatanOwner = catatanOwner; }
}
