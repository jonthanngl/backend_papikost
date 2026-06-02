package com.papikost.api.entity;

import jakarta.persistence.*;

@Entity
public class KamarKost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String namaKost;
    private String daerah;
    private Double hargaDasar;
    private String status;
    private Double rating;
    private String image;
    private Boolean wifiCepat;
    private Boolean mejaBelajar;
    private Boolean ac;
    private Integer availableRooms;
    
    @Column(length = 1000)
    private String description;

    // Constructor Default
    public KamarKost() {}

    // Constructor Lengkap
    public KamarKost(String namaKost, String daerah, Double hargaDasar, String status, 
                     Double rating, String image, Boolean wifiCepat, Boolean mejaBelajar, 
                     Boolean ac, Integer availableRooms, String description) {
        this.namaKost = namaKost;
        this.daerah = daerah;
        this.hargaDasar = hargaDasar;
        this.status = status;
        this.rating = rating;
        this.image = image;
        this.wifiCepat = wifiCepat;
        this.mejaBelajar = mejaBelajar;
        this.ac = ac;
        this.availableRooms = availableRooms;
        this.description = description;
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNamaKost() { return namaKost; }
    public void setNamaKost(String namaKost) { this.namaKost = namaKost; }
    public String getDaerah() { return daerah; }
    public void setDaerah(String daerah) { this.daerah = daerah; }
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
    public Integer getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(Integer availableRooms) { this.availableRooms = availableRooms; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}