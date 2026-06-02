package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InvitePatungan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromUserId;
    private String fromUserName;
    private String toUserId;
    
    private Long kamarId;
    private String namaKost;
    private Double hargaDasar;
    private Integer jumlahOrang;
    private Integer durasi;
    
    private String status; // PENDING, DITERIMA, DITOLAK
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
    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public String getFromUserName() { return fromUserName; }
    public void setFromUserName(String fromUserName) { this.fromUserName = fromUserName; }
    public String getToUserId() { return toUserId; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }
    public Long getKamarId() { return kamarId; }
    public void setKamarId(Long kamarId) { this.kamarId = kamarId; }
    public String getNamaKost() { return namaKost; }
    public void setNamaKost(String namaKost) { this.namaKost = namaKost; }
    public Double getHargaDasar() { return hargaDasar; }
    public void setHargaDasar(Double hargaDasar) { this.hargaDasar = hargaDasar; }
    public Integer getJumlahOrang() { return jumlahOrang; }
    public void setJumlahOrang(Integer jumlahOrang) { this.jumlahOrang = jumlahOrang; }
    public Integer getDurasi() { return durasi; }
    public void setDurasi(Integer durasi) { this.durasi = durasi; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}