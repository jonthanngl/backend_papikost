package com.papikost.api.entity;

import jakarta.persistence.Entity;

@Entity
public class Penyewa extends Akun {
    private String namaPenyewa;
    private String pekerjaan;
    private String kotaAsal;

    public Penyewa() {}
    public Penyewa(String email, String password, String namaPenyewa, String pekerjaan, String kotaAsal) {
        super(email, password, "PENYEWA");
        this.namaPenyewa = namaPenyewa;
        this.pekerjaan = pekerjaan;
        this.kotaAsal = kotaAsal;
    }

    public String getNamaPenyewa() { return namaPenyewa; }
    public void setNamaPenyewa(String namaPenyewa) { this.namaPenyewa = namaPenyewa; }
    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }
    public String getKotaAsal() { return kotaAsal; }
    public void setKotaAsal(String kotaAsal) { this.kotaAsal = kotaAsal; }
}