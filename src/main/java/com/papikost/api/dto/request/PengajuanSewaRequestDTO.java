package com.papikost.api.dto.request;

public class PengajuanSewaRequestDTO {
    private Long userId;
    private Long kamarId;
    private String tipeSewa; // "solo" atau "patungan"
    private Integer durasiBulan;
    private Double totalTagihan;

    // Getter dan Setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getKamarId() { return kamarId; }
    public void setKamarId(Long kamarId) { this.kamarId = kamarId; }
    public String getTipeSewa() { return tipeSewa; }
    public void setTipeSewa(String tipeSewa) { this.tipeSewa = tipeSewa; }
    public Integer getDurasiBulan() { return durasiBulan; }
    public void setDurasiBulan(Integer durasiBulan) { this.durasiBulan = durasiBulan; }
    public Double getTotalTagihan() { return totalTagihan; }
    public void setTotalTagihan(Double totalTagihan) { this.totalTagihan = totalTagihan; }
}