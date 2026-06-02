package com.papikost.api.dto.request;

public class ReservasiRequestDTO {
    private String tipe;
    private Double hargaDasar;
    private Integer durasi;
    private Integer jumlahOrang;

    public String getTipe() { return tipe != null ? tipe : "solo"; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public Double getHargaDasar() { return hargaDasar != null ? hargaDasar : 1500000.0; }
    public void setHargaDasar(Double hargaDasar) { this.hargaDasar = hargaDasar; }
    public Integer getDurasi() { return durasi != null ? durasi : 6; }
    public void setDurasi(Integer durasi) { this.durasi = durasi; }
    public Integer getJumlahOrang() { return jumlahOrang != null ? jumlahOrang : 2; }
    public void setJumlahOrang(Integer jumlahOrang) { this.jumlahOrang = jumlahOrang; }
}