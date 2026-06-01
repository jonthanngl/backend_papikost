package com.papikost.api.model;

// ==========================================
// INHERITANCE & POLYMORPHISM (Pilar PBO)
// ==========================================
// ReservasiPatungan mewarisi (extends) dari kelas abstrak induk 'Reservasi'.
// Menerapkan metodologi Polymorphism dengan melakukan OVERRIDE pada metode abstract 'hitungTotalTagihan()'.

public class ReservasiPatungan extends Reservasi {

    private int jumlahOrang;

    public ReservasiPatungan() {
        super();
        this.jumlahOrang = 1;
    }

    public ReservasiPatungan(String idReservasi, double hargaKostDasar, int durasiBulan, int jumlahOrang) {
        super(idReservasi, hargaKostDasar, durasiBulan);
        this.jumlahOrang = Math.max(1, jumlahOrang); // Mencegah pembagian dengen nol atau angka negatif
    }

    // ==========================================
    // POLYMORPHISM - OVERRIDING (Pilar PBO)
    // ==========================================
    // Mengimplementasikan perhitungan tagihan yang dimodifikasi khusus untuk sistem sewa patungan.
    // Total Tagihan = (Harga Dasar per Bulan / Jumlah Orang) * Durasi Bulan.
    @Override
    public double hitungTotalTagihan() {
        double hargaPatunganPerOrang = this.getHargaKostDasar() / this.jumlahOrang;
        return hargaPatunganPerOrang * this.getDurasiBulan();
    }

    // ENCAPSULATION Getter & Setter unik untuk sub-class ini
    public int getJumlahOrang() {
        return this.jumlahOrang;
    }

    public void setJumlahOrang(int jumlahOrang) {
        this.jumlahOrang = Math.max(1, jumlahOrang);
    }
}
