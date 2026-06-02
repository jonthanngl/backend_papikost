package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
public class Biodata {
    @Id
    private Long userId; // Menggunakan ID yang sama dengan Akun

    private String namaLengkap;
    private String tanggalLahir;
    private String tempatLahir;
    private String jenisKelamin;
    private String noHp;
    private String alamat;
    private String pekerjaan;
    
    // URL Berkas
    private String ktpUrl;
    private String kkUrl;
    private String fotoUrl;

    private Boolean isVerified = false;

    // Tambahkan Constructor, Getter, dan Setter (Atau gunakan @Data dari Lombok)
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    // ... (Getter & Setter lainnya disesuaikan)
}