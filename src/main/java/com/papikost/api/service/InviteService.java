package com.papikost.api.service;

import com.papikost.api.dto.request.InviteRequestDTO;
import com.papikost.api.entity.InvitePatungan;
import com.papikost.api.repository.InvitePatunganRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InviteService {

    private final InvitePatunganRepository inviteRepository;

    public InviteService(InvitePatunganRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    public InvitePatungan kirimUndangan(InviteRequestDTO request) {
        InvitePatungan invite = new InvitePatungan();
        invite.setFromUserId(request.getFromUserId());
        invite.setFromUserName(request.getFromUserName());
        invite.setToUserId(request.getToUserId());
        invite.setKamarId(request.getKamarId());
        invite.setNamaKost(request.getNamaKost());
        invite.setHargaDasar(request.getHargaDasar());
        invite.setJumlahOrang(request.getJumlahOrang());
        invite.setDurasi(request.getDurasi());
        
        return inviteRepository.save(invite);
    }

    public List<InvitePatungan> getUndanganUntukUser(String userId) {
        // Mengambil undangan di mana user adalah penerima
        return inviteRepository.findByToUserIdOrderByCreatedAtDesc(userId);
    }

    public InvitePatungan responsUndangan(Long inviteId, String status) {
        Optional<InvitePatungan> optionalInvite = inviteRepository.findById(inviteId);
        if (optionalInvite.isPresent()) {
            InvitePatungan invite = optionalInvite.get();
            invite.setStatus(status); // "DITERIMA" atau "DITOLAK"
            return inviteRepository.save(invite);
        }
        throw new RuntimeException("Undangan tidak ditemukan dengan ID: " + inviteId);
    }
}