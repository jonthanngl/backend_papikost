package com.papikost.api.controller;

import com.papikost.api.model.Reservasi;
import com.papikost.api.model.ReservasiPatungan;
import com.papikost.api.model.ReservasiSolo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// ==========================================
// REST CONTROLLER & POLYMORPHISM (PBO & Web)
// ==========================================
// Controller ini menyediakan REST API endpoints yang memproses reservasi sewa kost.
// @CrossOrigin diaktifkan agar frontend React murni yang berjalan di port terpisah dapat mengakses endpoint ini.

@RestController
@RequestMapping("/api/reservasi")
@CrossOrigin(origins = "*") // Mengizinkan cross-origin request dari React
public class ReservasiController {

    @GetMapping("/hitung")
    public ResponseEntity<Map<String, Object>> hitungReservasi(
            @RequestParam(defaultValue = "solo") String tipe,
            @RequestParam(defaultValue = "1500000") double hargaDasar,
            @RequestParam(defaultValue = "6") int durasi,
            @RequestParam(defaultValue = "2") int jumlahOrang
    ) {
        // ==========================================
        // POLYMORPHISM - RUNTIME POLYMORPHISM (PBO)
        // ==========================================
        // Kita menggunakan variabel referensi bertipe kelas induk ('Reservasi')
        // untuk menampung objek dari kelas turunan ('ReservasiSolo' atau 'ReservasiPatungan').
        // Ketika metode 'hitungTotalTagihan()' dipanggil, program secara dinamis menentukan
        // metode mana yang akan dieksekusi berdasarkan jenis objek asli yang ditunjuk pada runtime.

        Reservasi reservasi;

        if (tipe.equalsIgnoreCase("patungan")) {
            // Instansiasi subclass patungan
            reservasi = new ReservasiPatungan("RES-PTG-100", hargaDasar, durasi, jumlahOrang);
        } else {
            // Instansiasi subclass solo
            reservasi = new ReservasiSolo("RES-SLO-200", hargaDasar, durasi);
        }

        // Pemanggilan Polymorphism (Metode yang dieksekusi berbeda tergantung tipe objek)
        double totalTagihan = reservasi.hitungTotalTagihan();

        // Mengemas hasil dalam Map untuk dikembalikan dalam format standard JSON
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("idReservasi", reservasi.getIdReservasi());
        hasil.put("tipe", tipe);
        hasil.put("hargaDasarSewa", hargaDasar);
        hasil.put("durasiBulan", durasi);
        hasil.put("totalTagihanHasil", totalTagihan);

        // Menambahkan info penjelas demonstrasi konsep ke JSON response
        if (reservasi instanceof ReservasiPatungan) {
            ReservasiPatungan patungan = (ReservasiPatungan) reservasi;
            hasil.put("jumlahOrangPatungan", patungan.getJumlahOrang());
            hasil.put("hargaPerOrangPerBulan", hargaDasar / patungan.getJumlahOrang());
            hasil.put("deskripsiKonsep", "Mengeksekusi ReservasiPatungan.hitungTotalTagihan() -> [Polymorphism]");
            hasil.put("formulaPBO", "Total = (hargaDasar / " + patungan.getJumlahOrang() + ") * " + durasi);
        } else {
            hasil.put("jumlahOrang", 1);
            hasil.put("deskripsiKonsep", "Mengeksekusi ReservasiSolo.hitungTotalTagihan() -> [Polymorphism]");
            hasil.put("formulaPBO", "Total = hargaDasar * " + durasi);
        }

        return ResponseEntity.ok(hasil);
    }
}
