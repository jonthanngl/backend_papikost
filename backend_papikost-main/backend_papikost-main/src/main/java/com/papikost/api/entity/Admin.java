package com.papikost.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// ==========================================
// INHERITANCE (Pilar PBO)
// ==========================================
// Class Admin mewarisi (extends) semua field dan method publik dari class induk 'Akun'.
// Menggunakan @PrimaryKeyJoinColumn untuk mencocokkan kunci utama tabel anak dengan tabel induk.

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id_akun")
public class Admin extends Akun {

    private String namaLengkap;

    // Constructor Default
    public Admin() {
        super();
        this.setRole("ADMIN");
    }

    // Constructor Overloaded
    public Admin(String email, String password, String namaLengkap) {
        super(email, password, "ADMIN");
        this.namaLengkap = namaLengkap;
    }

    // ENCAPSULATION
    public String getNamaLengkap() {
        return this.namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
}
