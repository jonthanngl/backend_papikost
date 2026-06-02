package com.papikost.api.dto.request;

public class InviteRequestDTO {
    private String fromUserId;
    private String fromUserName;
    private String toUserId;
    private Long kamarId;
    private String namaKost;
    private Double hargaDasar;
    private Integer jumlahOrang;
    private Integer durasi;

    // Getter dan Setter
    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public String getFromUserName() { return fromUserName; }
    public void setFromUserName(String fromUserName) { this.fromUserName = fromUserName; }
    public String getToUserId() { return toUserId; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }
    public Long getKamarId() { return kamarId; }
    public void setKamarId(Long kamarId) { this.kamarId = kamarId; }
    public String getNamaKost() { return namaKost; }
    public void setNamaKost(String namaKost) { this.namaKost = namaKost; }
    public Double getHargaDasar() { return hargaDasar; }
    public void setHargaDasar(Double hargaDasar) { this.hargaDasar = hargaDasar; }
    public Integer getJumlahOrang() { return jumlahOrang; }
    public void setJumlahOrang(Integer jumlahOrang) { this.jumlahOrang = jumlahOrang; }
    public Integer getDurasi() { return durasi; }
    public void setDurasi(Integer durasi) { this.durasi = durasi; }
}