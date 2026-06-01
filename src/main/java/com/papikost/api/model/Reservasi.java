package com.papikost.api.model;

// ==========================================
// ABSTRACTION (Pilar PBO)
// ==========================================
// Class ini dideklarasikan sebagai abstract class, yang bertindak sebagai template atau
// spesifikasi bagi sistem reservasi PapiKost. Kita tidak bisa langsung membuat instansiasi
// (menggunakan keyword 'new') dari class 'Reservasi'.

public abstract class Reservasi {

    private String idReservasi;
    private double hargaKostDasar; // Harga per bulan
    private int durasiBulan;

    // Default Constructor
    public Reservasi() {}

    // Overloaded Constructor
    public Reservasi(String idReservasi, double hargaKostDasar, int durasiBulan) {
        this.idReservasi = idReservasi;
        this.hargaKostDasar = hargaKostDasar;
        this.durasiBulan = durasiBulan;
    }

    // ==========================================
    // ABSTRACTION - ABSTRACT METHOD (Pilar PBO)
    // ==========================================
    // Sebuah abstrak metode yang harus di-override (diimplementasikan) secara berbeda
    // oleh masing-masing kelas turunan (ReservasiSolo vs. ReservasiPatungan).
    public abstract double hitungTotalTagihan();

    // ENCAPSULATION Getters & Setters
    public String getIdReservasi() {
        return idReservasi;
    }

    public void setIdReservasi(String idReservasi) {
        this.idReservasi = idReservasi;
    }

    public double getHargaKostDasar() {
        return hargaKostDasar;
    }

    public void setHargaKostDasar(double hargaKostDasar) {
        this.hargaKostDasar = hargaKostDasar;
    }

    public int getDurasiBulan() {
        return durasiBulan;
    }

    public void setDurasiBulan(int durasiBulan) {
        this.durasiBulan = durasiBulan;
    }
}
