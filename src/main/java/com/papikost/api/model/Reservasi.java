package com.papikost.api.model;

public abstract class Reservasi {

    private String idReservasi;
    private double hargaKostDasar; 
    private int durasiBulan;

    public Reservasi() {}

    public Reservasi(String idReservasi, double hargaKostDasar, int durasiBulan) {
        this.idReservasi = idReservasi;
        this.hargaKostDasar = hargaKostDasar;
        this.durasiBulan = durasiBulan;
    }

    // Metode abstrak untuk Polimorfisme
    public abstract double hitungTotalTagihan();

    public String getIdReservasi() { return idReservasi; }
    public void setIdReservasi(String idReservasi) { this.idReservasi = idReservasi; }

    public double getHargaKostDasar() { return hargaKostDasar; }
    public void setHargaKostDasar(double hargaKostDasar) { this.hargaKostDasar = hargaKostDasar; }

    public int getDurasiBulan() { return durasiBulan; }
    public void setDurasiBulan(int durasiBulan) { this.durasiBulan = durasiBulan; }
}