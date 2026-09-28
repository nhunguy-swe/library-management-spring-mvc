package com.example.quanlythuvien.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "DOC_GIA")
public class DocGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_doc_gia")
    private int maDocGia;

    @NotBlank(message = "Họ tên không được trống")
    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "loai_the")
    private String loaiThe;

    @NotBlank(message = "Email không được trống")
    @Email(message = "Email phải đúng định dạng (VD: abc@def.com)")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Số điện thoại không được trống")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phải từ 10-11 chữ số")
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    public DocGia() {
    }

    public DocGia(int maDocGia, String hoTen, String loaiThe, String email, String soDienThoai) {
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.loaiThe = loaiThe;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
