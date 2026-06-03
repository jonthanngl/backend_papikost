package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class PengajuanSewa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long kamarId; // Relasi ke UnitKamar

    // "SOLO" atau "DUO"
    private String tipeSewa;
    private Integer durasiBulan;
    private Double totalTagihan;
    
    // PENDING, DITERIMA, DITOLAK
    private String status;
    private LocalDateTime tanggalPengajuan;
    
    // Untuk tipe DUO (Sewa 2 Orang)
    private Long roommateId; // ID user yang diajak
    private String roommateUsername;
    
    // Diisi oleh Owner setelah acc: kamar yang dialokasikan
    private Long unitKamarId;
    private LocalDate tanggalMulai;
    private LocalDate batasKontrak;

    @PrePersist
    protected void onCreate() {
        this.tanggalPengajuan = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getKamarId() { return kamarId; }
    public void setKamarId(Long kamarId) { this.kamarId = kamarId; }
    public String getTipeSewa() { return tipeSewa; }
    public void setTipeSewa(String tipeSewa) { this.tipeSewa = tipeSewa; }
    public Integer getDurasiBulan() { return durasiBulan; }
    public void setDurasiBulan(Integer durasiBulan) { this.durasiBulan = durasiBulan; }
    public Double getTotalTagihan() { return totalTagihan; }
    public void setTotalTagihan(Double totalTagihan) { this.totalTagihan = totalTagihan; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getTanggalPengajuan() { return tanggalPengajuan; }
    public void setTanggalPengajuan(LocalDateTime tanggalPengajuan) { this.tanggalPengajuan = tanggalPengajuan; }
    public Long getRoommateId() { return roommateId; }
    public void setRoommateId(Long roommateId) { this.roommateId = roommateId; }
    public String getRoommateUsername() { return roommateUsername; }
    public void setRoommateUsername(String roommateUsername) { this.roommateUsername = roommateUsername; }
    public Long getUnitKamarId() { return unitKamarId; }
    public void setUnitKamarId(Long unitKamarId) { this.unitKamarId = unitKamarId; }
    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(LocalDate tanggalMulai) { this.tanggalMulai = tanggalMulai; }
    public LocalDate getBatasKontrak() { return batasKontrak; }
    public void setBatasKontrak(LocalDate batasKontrak) { this.batasKontrak = batasKontrak; }
}
