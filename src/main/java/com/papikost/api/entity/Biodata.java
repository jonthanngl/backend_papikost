package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
public class Biodata {

    @Id
    private Long userId; // ID sama dengan Akun

    private String namaLengkap;
    private String tanggalLahir;
    private String tempatLahir;
    private String jenisKelamin;
    private String noHp;
    private String alamat;
    private String pekerjaan;

    private String ktpUrl;
    private String kkUrl;
    private String fotoUrl;

    // Status verifikasi per berkas (BELUM, PENDING, DISETUJUI, DITOLAK)
    private String ktpStatus = "BELUM";
    private String kkStatus = "BELUM";
    private String fotoStatus = "BELUM";
    
    // Komentar admin jika ditolak
    private String ktpKomentar;
    private String kkKomentar;
    private String fotoKomentar;

    private Boolean isVerified = false;

    // Status verifikasi keseluruhan: BELUM, PENDING, DISETUJUI, DITOLAK
    private String verifikasiStatus = "BELUM";

    // ── Getter & Setter lengkap ──────────────────────────────────────────────

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getTempatLahir() { return tempatLahir; }
    public void setTempatLahir(String tempatLahir) { this.tempatLahir = tempatLahir; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public String getKtpUrl() { return ktpUrl; }
    public void setKtpUrl(String ktpUrl) { this.ktpUrl = ktpUrl; }

    public String getKkUrl() { return kkUrl; }
    public void setKkUrl(String kkUrl) { this.kkUrl = kkUrl; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }

    public String getVerifikasiStatus() { return verifikasiStatus; }
    public void setVerifikasiStatus(String verifikasiStatus) { this.verifikasiStatus = verifikasiStatus; }

    public String getKtpStatus() { return ktpStatus; }
    public void setKtpStatus(String ktpStatus) { this.ktpStatus = ktpStatus; }

    public String getKkStatus() { return kkStatus; }
    public void setKkStatus(String kkStatus) { this.kkStatus = kkStatus; }

    public String getFotoStatus() { return fotoStatus; }
    public void setFotoStatus(String fotoStatus) { this.fotoStatus = fotoStatus; }

    public String getKtpKomentar() { return ktpKomentar; }
    public void setKtpKomentar(String ktpKomentar) { this.ktpKomentar = ktpKomentar; }

    public String getKkKomentar() { return kkKomentar; }
    public void setKkKomentar(String kkKomentar) { this.kkKomentar = kkKomentar; }

    public String getFotoKomentar() { return fotoKomentar; }
    public void setFotoKomentar(String fotoKomentar) { this.fotoKomentar = fotoKomentar; }
}
