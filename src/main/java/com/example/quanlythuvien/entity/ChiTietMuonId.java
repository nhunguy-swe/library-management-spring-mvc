package com.example.quanlythuvien.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietMuonId implements Serializable {
    private int maPhieu;
    private int maSach;

    public ChiTietMuonId() {}

    public ChiTietMuonId(int maPhieu, int maSach) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
    }

    public int getMaPhieu() { return maPhieu; }

    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }

    public int getMaSach() { return maSach; }

    public void setMaSach(int maSach) { this.maSach = maSach; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietMuonId that = (ChiTietMuonId) o;
        return maPhieu == that.maPhieu && maSach == that.maSach;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhieu, maSach);
    }
}