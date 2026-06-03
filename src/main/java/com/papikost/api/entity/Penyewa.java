package com.papikost.api.entity;

import jakarta.persistence.Entity;

@Entity
public class Penyewa extends Akun {

    private String namaPenyewa;
    private String pekerjaan;
    private String kotaAsal;

    // ── Constructor default (wajib untuk JPA) ───────────────────────────────
    public Penyewa() {}

    // ── Constructor lengkap pakai setter (lebih fleksibel) ──────────────────
    public Penyewa(String username, String email, String password,
                   String namaLengkap, String role,
                   String pekerjaan, String kotaAsal) {
        super(username, email, password, namaLengkap, role);
        this.namaPenyewa = namaLengkap;
        this.pekerjaan   = pekerjaan;
        this.kotaAsal    = kotaAsal;
    }

    // ── Getter & Setter ──────────────────────────────────────────────────────
    public String getNamaPenyewa() { return namaPenyewa; }
    public void setNamaPenyewa(String namaPenyewa) { this.namaPenyewa = namaPenyewa; }
    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }
    public String getKotaAsal() { return kotaAsal; }
    public void setKotaAsal(String kotaAsal) { this.kotaAsal = kotaAsal; }
}
