package com.papikost.api.dto.response;

public class ReservasiResponseDTO {
    private String idReservasi;
    private String tipe;
    private Double hargaDasarSewa;
    private Integer durasiBulan;
    private Double totalTagihanHasil;
    private String deskripsiKonsep;
    private String formulaPBO;
    private Integer jumlahOrang;

    // Getter dan Setter
    public String getIdReservasi() { return idReservasi; }
    public void setIdReservasi(String idReservasi) { this.idReservasi = idReservasi; }
    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public Double getHargaDasarSewa() { return hargaDasarSewa; }
    public void setHargaDasarSewa(Double hargaDasarSewa) { this.hargaDasarSewa = hargaDasarSewa; }
    public Integer getDurasiBulan() { return durasiBulan; }
    public void setDurasiBulan(Integer durasiBulan) { this.durasiBulan = durasiBulan; }
    public Double getTotalTagihanHasil() { return totalTagihanHasil; }
    public void setTotalTagihanHasil(Double totalTagihanHasil) { this.totalTagihanHasil = totalTagihanHasil; }
    public String getDeskripsiKonsep() { return deskripsiKonsep; }
    public void setDeskripsiKonsep(String deskripsiKonsep) { this.deskripsiKonsep = deskripsiKonsep; }
    public String getFormulaPBO() { return formulaPBO; }
    public void setFormulaPBO(String formulaPBO) { this.formulaPBO = formulaPBO; }
    public Integer getJumlahOrang() { return jumlahOrang; }
    public void setJumlahOrang(Integer jumlahOrang) { this.jumlahOrang = jumlahOrang; }
}