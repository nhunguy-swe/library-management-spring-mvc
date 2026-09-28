package com.example.quanlythuvien.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CHI_TIET_MUON")
public class ChiTietMuon {

    @EmbeddedId
    private ChiTietMuonId id = new ChiTietMuonId();

    @ManyToOne
    @MapsId("maPhieu")
    @JoinColumn(name = "ma_phieu")
    private PhieuMuon phieuMuon;

    @ManyToOne
    @MapsId("maSach")
    @JoinColumn(name = "ma_sach")
    private Sach sach;

    @Column(name = "so_luong_muon")
    private int soLuongMuon;

    public ChiTietMuon() {}

    public ChiTietMuon(ChiTietMuonId id, PhieuMuon phieuMuon, Sach sach, int soLuongMuon) {
        this.id = id;
        this.phieuMuon = phieuMuon;
        this.sach = sach;
        this.soLuongMuon = soLuongMuon;
    }

    public ChiTietMuonId getId() { return id; }

    public void setId(ChiTietMuonId id) { this.id = id; }

    public PhieuMuon getPhieuMuon() { return phieuMuon; }

    public void setPhieuMuon(PhieuMuon phieuMuon) { this.phieuMuon = phieuMuon; }

    public Sach getSach() { return sach; }

    public void setSach(Sach sach) { this.sach = sach; }

    public int getSoLuongMuon() { return soLuongMuon; }

    public void setSoLuongMuon(int soLuongMuon) { this.soLuongMuon = soLuongMuon; }
}