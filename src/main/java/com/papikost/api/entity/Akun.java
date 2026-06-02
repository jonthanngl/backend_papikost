package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Akun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username; // Ditambahkan untuk kebutuhan login
    
    private String email;
    private String password;
    private String namaLengkap; // Ditambahkan dari form register
    private String role; 

    public Akun() {}
    public Akun(String username, String email, String password, String namaLengkap, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.role = role;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}