package com.papikost.api.service;

import com.papikost.api.dto.request.ReservasiRequestDTO;
import com.papikost.api.dto.response.ReservasiResponseDTO;
import com.papikost.api.model.Reservasi;
import com.papikost.api.model.ReservasiPatungan;
import com.papikost.api.model.ReservasiSolo;
import org.springframework.stereotype.Service;

@Service
public class ReservasiService {

    public ReservasiResponseDTO prosesKalkulasiTagihan(ReservasiRequestDTO request) {
        ReservasiResponseDTO response = new ReservasiResponseDTO();
        
        response.setTipe(request.getTipe());
        response.setHargaDasarSewa(request.getHargaDasar());
        response.setDurasiBulan(request.getDurasi());

        // MENGGUNAKAN POLIMORFISME (PBO)
        Reservasi reservasi;
        if ("patungan".equalsIgnoreCase(request.getTipe())) {
            reservasi = new ReservasiPatungan("RES-PTG-100", request.getHargaDasar(), request.getDurasi(), request.getJumlahOrang());
        } else {
            reservasi = new ReservasiSolo("RES-SLO-200", request.getHargaDasar(), request.getDurasi());
        }

        // Panggilan method abstrak yang di-override
        double totalTagihan = reservasi.hitungTotalTagihan();

        response.setIdReservasi(reservasi.getIdReservasi());
        response.setTotalTagihanHasil(totalTagihan);

        if (reservasi instanceof ReservasiPatungan) {
            ReservasiPatungan p = (ReservasiPatungan) reservasi;
            response.setJumlahOrang(p.getJumlahOrang());
            response.setFormulaPBO("Total = (hargaDasar / " + p.getJumlahOrang() + ") * " + request.getDurasi());
            response.setDeskripsiKonsep("ReservasiPatungan.hitungTotalTagihan()");
        } else {
            response.setJumlahOrang(1);
            response.setFormulaPBO("Total = hargaDasar * " + request.getDurasi());
            response.setDeskripsiKonsep("ReservasiSolo.hitungTotalTagihan()");
        }

        return response;
    }
}