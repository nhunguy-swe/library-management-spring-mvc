package com.example.quanlythuvien.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PHIEU_MUON")
public class PhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_phieu")
    private int maPhieu;

    @ManyToOne
    @JoinColumn(name = "ma_doc_gia")
    private DocGia docGia;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_muon")
    private Date ngayMuon;

    @Column(name = "trang_thai")
    private String trangThai; // "Đang mượn", "Đã trả"

    @OneToMany(mappedBy = "phieuMuon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChiTietMuon> chiTietMuons;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPhieu, DocGia docGia, Date ngayMuon, String trangThai, List<ChiTietMuon> chiTietMuons) {
        this.maPhieu = maPhieu;
        this.docGia = docGia;
        this.ngayMuon = ngayMuon;
        this.trangThai = trangThai;
        this.chiTietMuons = chiTietMuons;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public DocGia getDocGia() {
        return docGia;
    }

    public void setDocGia(DocGia docGia) {
        this.docGia = docGia;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietMuon> getChiTietMuons() {
        return chiTietMuons;
    }

    public void setChiTietMuons(List<ChiTietMuon> chiTietMuons) {
        this.chiTietMuons = chiTietMuons;
    }
}
