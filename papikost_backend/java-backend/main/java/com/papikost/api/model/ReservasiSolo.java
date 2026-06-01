package com.papikost.api.model;

// ==========================================
// INHERITANCE & POLYMORPHISM (Pilar PBO)
// ==========================================
// ReservasiSolo mewarisi (extends) dari kelas abstrak induk 'Reservasi'.
// Menerapkan metodologi Polymorphism dengan melakukan OVERRIDE pada metode abstract 'hitungTotalTagihan()'.

public class ReservasiSolo extends Reservasi {

    public ReservasiSolo() {
        super();
    }

    public ReservasiSolo(String idReservasi, double hargaKostDasar, int durasiBulan) {
        super(idReservasi, hargaKostDasar, durasiBulan);
    }

    // ==========================================
    // POLYMORPHISM - OVERRIDING (Pilar PBO)
    // ==========================================
    // Implementasi spesifik hitungTotalTagihan untuk sewa mandiri (tanpa patungan).
    // Total Tagihan = Harga Dasar per Bulan * Durasi Bulan.
    @Override
    public double hitungTotalTagihan() {
        return this.getHargaKostDasar() * this.getDurasiBulan();
    }
}
