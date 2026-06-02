package com.papikost.api.entity;

import jakarta.persistence.*;

// ==========================================
// 1. ABSTRACTION & INHERITANCE (Pilar PBO)
// ==========================================
// Class ini adalah abstract class, merepresentasikan generalisasi Akun dalam sistem.
// Menerapkan strategi pewarisan JOINED di JPA untuk memetakan subclass ke tabel terpisah.

@Entity
@Table(name = "akun")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Akun {

    // ==========================================
    // 2. ENCAPSULATION (Pilar PBO)
    // ==========================================
    // Menyembunyikan detail atribut dengan membatasi akses melalui keyword 'private'.
    // Atribut hanya dapat dibaca dan dimodifikasi menggunakan metode Getter dan Setter publik.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // e.g. "ADMIN", "PEMILIK", "PENYEWA"

    // Constructor Default required for Hibernate
    public Akun() {}

    // Overloaded Constructor
    public Akun(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ==========================================
    // GETTERS & SETTERS (Metode Akses Kapsul)
    // ==========================================

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
