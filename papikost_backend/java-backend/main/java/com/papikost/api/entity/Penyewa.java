package com.papikost.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// ==========================================
// INHERITANCE (Pilar PBO)
// ==========================================
// Penyewa mewarisi (extends) fungsionalitas dasar 'Akun' dan menambahkan fungsionalitas unik penyewa kost.

@Entity
@Table(name = "penyewa")
@PrimaryKeyJoinColumn(name = "id_akun")
public class Penyewa extends Akun {

    private String namaPenyewa;
    private String pekerjaan;
    private String kotaAsal;

    // Constructor Default
    public Penyewa() {
        super();
        this.setRole("PENYEWA");
    }

    // Constructor Overloaded
    public Penyewa(String email, String password, String namaPenyewa, String pekerjaan, String kotaAsal) {
        super(email, password, "PENYEWA");
        this.namaPenyewa = namaPenyewa;
        this.pekerjaan = pekerjaan;
        this.kotaAsal = kotaAsal;
    }

    // ENCAPSULATION
    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }
}
