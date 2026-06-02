package com.papikost.api.service;

import com.papikost.api.dto.request.LaporanRequestDTO;
import com.papikost.api.entity.Laporan;
import com.papikost.api.repository.LaporanRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaporanService {
    
    private final LaporanRepository laporanRepository;

    public LaporanService(LaporanRepository laporanRepository) {
        this.laporanRepository = laporanRepository;
    }

    public List<Laporan> getLaporanByUserId(Long userId) {
        return laporanRepository.findByUserIdOrderByIdDesc(userId);
    }

    public Laporan buatLaporanBaru(LaporanRequestDTO request) {
        Laporan laporan = new Laporan();
        laporan.setUserId(request.getUserId());
        laporan.setKategori(request.getKategori());
        laporan.setKendala(request.getKendala());
        laporan.setDetail(request.getDetail() != null ? request.getDetail() : "-");
        
        return laporanRepository.save(laporan);
    }
}