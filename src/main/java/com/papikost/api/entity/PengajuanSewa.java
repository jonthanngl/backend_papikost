package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PengajuanSewa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long kamarId;

    private String tipeSewa; 
    private Integer durasiBulan;
    private Double totalTagihan;
    private String status; 
    private LocalDateTime tanggalPengajuan;

    @PrePersist
    protected void onCreate() {
        this.tanggalPengajuan = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING"; // Default status saat pertama kali diajukan
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
}