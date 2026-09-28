package com.example.quanlythuvien.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SACH")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach")
    private int maSach;

    @NotBlank(message = "Tên sách không được để trống")
    @Column(name = "ten_sach")
    private String tenSach;

    @NotBlank(message = "Tác giả không được để trống")
    @Column(name = "tac_gia")
    private String tacGia;

    @Column(name = "the_loai")
    private String theLoai;

    @NotNull(message = "Số lượng không được trống")
    @Min(value = 1, message = "Số lượng phải là số nguyên dương lớn hơn 0")
    @Column(name = "so_luong_hien_co")
    private Integer soLuongHienCo;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, String tacGia, String theLoai, Integer soLuongHienCo) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.soLuongHienCo = soLuongHienCo;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacgia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) { this.tacGia = tacGia;}

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public Integer getSoLuongHienCo() {
        return soLuongHienCo;
    }

    public void setSoLuongHienCo(Integer soLuongHienCo) {
        this.soLuongHienCo = soLuongHienCo;
    }
}
