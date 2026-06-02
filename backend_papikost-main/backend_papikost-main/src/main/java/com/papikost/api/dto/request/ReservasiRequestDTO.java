package com.papikost.api.dto.request;

public class ReservasiRequestDTO {
    private Long idPenyewa;
    private Long idKamar;
    private int durasiBulan;
    private boolean isPatungan;
    
    // Generate Getter dan Setter di IDE Anda
    public Long getIdPenyewa() { return idPenyewa; }
    public void setIdPenyewa(Long idPenyewa) { this.idPenyewa = idPenyewa; }
    
    public Long getIdKamar() { return idKamar; }
    public void setIdKamar(Long idKamar) { this.idKamar = idKamar; }

    public int getDurasiBulan() { return durasiBulan; }
    public void setDurasiBulan(int durasiBulan) { this.durasiBulan = durasiBulan; }

    public boolean isPatungan() { return isPatungan; }
    public void setPatungan(boolean patungan) { isPatungan = patungan; }
}