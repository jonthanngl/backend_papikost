package com.papikost.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// ==========================================
// INHERITANCE (Pilar PBO)
// ==========================================
// PemilikKost mewarisi (extends) fungsionalitas dasar 'Akun' dan menambahkan fungsionalitas unik pemilik kost.

@Entity
@Table(name = "pemilik_kost")
@PrimaryKeyJoinColumn(name = "id_akun")
public class PemilikKost extends Akun {

    private String namaPemilik;
    private String nomorHandphone;

    // Constructor Default
    public PemilikKost() {
        super();
        this.setRole("PEMILIK");
    }

    // Constructor Overloaded
    public PemilikKost(String email, String password, String namaPemilik, String nomorHandphone) {
        super(email, password, "PEMILIK");
        this.namaPemilik = namaPemilik;
        this.nomorHandphone = nomorHandphone;
    }

    // ENCAPSULATION
    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }
}
