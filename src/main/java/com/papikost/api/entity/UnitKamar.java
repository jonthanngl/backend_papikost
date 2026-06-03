package com.papikost.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UnitKamar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long infoKostId; // Relasi ke InfoKost
    
    private String nomorKamar;
    
    // Kapasitas: 1 atau 2 orang
    private Integer kapasitas;
    
    private Double harga;
    
    @Column(length = 2000)
    private String deskripsi;
    
    // Fasilitas kamar (checkbox)
    private Boolean ac;
    private Boolean kamarMandiDalam;
    private Boolean kasur;
    private Boolean lemari;
    private Boolean meja;
    private Boolean kursi;
    
    // Array foto (maksimal 3, disimpan sebagai string separated by comma)
    @Column(length = 2000)
    private String fotoUrls; // "url1,url2,url3"
    
    // Status: "KOSONG", "TERISI", "MAINTENANCE"
    private String status = "KOSONG";
    
    // Jika terisi, simpan informasi penyewa
    private Long penyewaId;
    private String namaPenyewa;
    private LocalDate tanggalMasuk;
    private LocalDate batasKontrak;

    // Constructor
    public UnitKamar() {}

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInfoKostId() { return infoKostId; }
    public void setInfoKostId(Long infoKostId) { this.infoKostId = infoKostId; }

    public String getNomorKamar() { return nomorKamar; }
    public void setNomorKamar(String nomorKamar) { this.nomorKamar = nomorKamar; }

    public Integer getKapasitas() { return kapasitas; }
    public void setKapasitas(Integer kapasitas) { this.kapasitas = kapasitas; }

    public Double getHarga() { return harga; }
    public void setHarga(Double harga) { this.harga = harga; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public Boolean getAc() { return ac; }
    public void setAc(Boolean ac) { this.ac = ac; }

    public Boolean getKamarMandiDalam() { return kamarMandiDalam; }
    public void setKamarMandiDalam(Boolean kamarMandiDalam) { this.kamarMandiDalam = kamarMandiDalam; }

    public Boolean getKasur() { return kasur; }
    public void setKasur(Boolean kasur) { this.kasur = kasur; }

    public Boolean getLemari() { return lemari; }
    public void setLemari(Boolean lemari) { this.lemari = lemari; }

    public Boolean getMeja() { return meja; }
    public void setMeja(Boolean meja) { this.meja = meja; }

    public Boolean getKursi() { return kursi; }
    public void setKursi(Boolean kursi) { this.kursi = kursi; }

    public String getFotoUrls() { return fotoUrls; }
    public void setFotoUrls(String fotoUrls) { this.fotoUrls = fotoUrls; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getPenyewaId() { return penyewaId; }
    public void setPenyewaId(Long penyewaId) { this.penyewaId = penyewaId; }

    public String getNamaPenyewa() { return namaPenyewa; }
    public void setNamaPenyewa(String namaPenyewa) { this.namaPenyewa = namaPenyewa; }

    public LocalDate getTanggalMasuk() { return tanggalMasuk; }
    public void setTanggalMasuk(LocalDate tanggalMasuk) { this.tanggalMasuk = tanggalMasuk; }

    public LocalDate getBatasKontrak() { return batasKontrak; }
    public void setBatasKontrak(LocalDate batasKontrak) { this.batasKontrak = batasKontrak; }
}
