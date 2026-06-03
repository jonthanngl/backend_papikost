package com.papikost.api.service;

import com.papikost.api.dto.request.InviteRequestDTO;
import com.papikost.api.entity.InvitePatungan;
import com.papikost.api.repository.InvitePatunganRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteService {

    private final InvitePatunganRepository inviteRepository;

    public InviteService(InvitePatunganRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    public InvitePatungan kirimUndangan(@NonNull InviteRequestDTO request) {
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

    public List<InvitePatungan> getUndanganUntukUser(@NonNull String userId) {
        return inviteRepository.findByToUserIdOrderByCreatedAtDesc(userId);
    }

    public InvitePatungan responsUndangan(@NonNull Long inviteId, @NonNull String status) {
        InvitePatungan invite = inviteRepository.findById(inviteId)
                .orElseThrow(() -> new RuntimeException("Undangan tidak ditemukan dengan ID: " + inviteId));
        invite.setStatus(status);
        return inviteRepository.save(invite);
    }
}
