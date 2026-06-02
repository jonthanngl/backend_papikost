package com.papikost.api.dto.request;

public class LaporanRequestDTO {
    private Long userId;
    private String kategori;
    private String kendala;
    private String detail;

    // Getter dan Setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public String getKendala() { return kendala; }
    public void setKendala(String kendala) { this.kendala = kendala; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
}