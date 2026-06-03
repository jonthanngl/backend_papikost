package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
public class InfoKost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId; // ID owner yang memiliki kost ini
    
    private String namaKost;
    private String daerah;
    @Column(length = 2000)
    private String alamatLengkap;
    
    // Kategori: "Putra", "Putri", "Campur"
    private String kategori;
    
    private Double hargaDasar; // Harga dasar per bulan
    
    // Fasilitas umum kost (checkbox)
    private Boolean wifi;
    private Boolean parkir;
    private Boolean laundry;
    private Boolean dapur;
    private Boolean security;
    
    // Nomor WhatsApp pemilik untuk tombol "Tanya Pemilik"
    private String nomorWhatsApp;

    // Constructor
    public InfoKost() {}

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getNamaKost() { return namaKost; }
    public void setNamaKost(String namaKost) { this.namaKost = namaKost; }

    public String getDaerah() { return daerah; }
    public void setDaerah(String daerah) { this.daerah = daerah; }

    public String getAlamatLengkap() { return alamatLengkap; }
    public void setAlamatLengkap(String alamatLengkap) { this.alamatLengkap = alamatLengkap; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public Double getHargaDasar() { return hargaDasar; }
    public void setHargaDasar(Double hargaDasar) { this.hargaDasar = hargaDasar; }

    public Boolean getWifi() { return wifi; }
    public void setWifi(Boolean wifi) { this.wifi = wifi; }

    public Boolean getParkir() { return parkir; }
    public void setParkir(Boolean parkir) { this.parkir = parkir; }

    public Boolean getLaundry() { return laundry; }
    public void setLaundry(Boolean laundry) { this.laundry = laundry; }

    public Boolean getDapur() { return dapur; }
    public void setDapur(Boolean dapur) { this.dapur = dapur; }

    public Boolean getSecurity() { return security; }
    public void setSecurity(Boolean security) { this.security = security; }

    public String getNomorWhatsApp() { return nomorWhatsApp; }
    public void setNomorWhatsApp(String nomorWhatsApp) { this.nomorWhatsApp = nomorWhatsApp; }
}
