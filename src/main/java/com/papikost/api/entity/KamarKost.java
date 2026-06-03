package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
public class KamarKost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Relasi ke InfoKost dan owner
    private Long ownerId;
    private Long infoKostId;
    
    private String namaKost;
    private String daerah;
    private String alamatLengkap;
    
    // Kategori: "Putra", "Putri", "Campur"
    private String kategori;
    
    private Double hargaDasar;
    
    // Status: "Tersedia" atau "Tidak Tersedia"
    private String status;
    
    private Double rating;
    private String image;
    
    // Fasilitas umum
    private Boolean wifiCepat;
    private Boolean mejaBelajar;
    private Boolean ac;
    private Boolean parkir;
    private Boolean laundry;
    private Boolean dapur;
    
    // Jumlah unit tersedia per kapasitas
    private Integer availableRoomsSolo;  // kamar untuk 1 orang
    private Integer availableRoomsDuo;   // kamar untuk 2 orang
    
    @Column(length = 1000)
    private String description;
    
    // Nomor WhatsApp pemilik
    private String nomorWhatsApp;

    // Semua foto dari unit-unit kamar (comma-separated URLs)
    @Column(length = 2000)
    private String fotoUrls;

    // Constructor Default
    public KamarKost() {}

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    
    public Long getInfoKostId() { return infoKostId; }
    public void setInfoKostId(Long infoKostId) { this.infoKostId = infoKostId; }
    
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
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Boolean getWifiCepat() { return wifiCepat; }
    public void setWifiCepat(Boolean wifiCepat) { this.wifiCepat = wifiCepat; }
    
    public Boolean getMejaBelajar() { return mejaBelajar; }
    public void setMejaBelajar(Boolean mejaBelajar) { this.mejaBelajar = mejaBelajar; }
    
    public Boolean getAc() { return ac; }
    public void setAc(Boolean ac) { this.ac = ac; }
    
    public Boolean getParkir() { return parkir; }
    public void setParkir(Boolean parkir) { this.parkir = parkir; }
    
    public Boolean getLaundry() { return laundry; }
    public void setLaundry(Boolean laundry) { this.laundry = laundry; }
    
    public Boolean getDapur() { return dapur; }
    public void setDapur(Boolean dapur) { this.dapur = dapur; }
    
    public Integer getAvailableRoomsSolo() { return availableRoomsSolo; }
    public void setAvailableRoomsSolo(Integer availableRoomsSolo) { this.availableRoomsSolo = availableRoomsSolo; }
    
    public Integer getAvailableRoomsDuo() { return availableRoomsDuo; }
    public void setAvailableRoomsDuo(Integer availableRoomsDuo) { this.availableRoomsDuo = availableRoomsDuo; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getNomorWhatsApp() { return nomorWhatsApp; }
    public void setNomorWhatsApp(String nomorWhatsApp) { this.nomorWhatsApp = nomorWhatsApp; }

    public String getFotoUrls() { return fotoUrls; }
    public void setFotoUrls(String fotoUrls) { this.fotoUrls = fotoUrls; }
}
