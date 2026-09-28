package com.example.quanlythuvien.controller;

import com.example.quanlythuvien.entity.*;
import com.example.quanlythuvien.repository.ThuVienRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ThuVienController {

    @Autowired
    private ThuVienRepository repo;

    @GetMapping
    public String home() { return "index"; }

    // --- FORM NHẬP SÁCH ---
    @GetMapping("/sach/new")
    public String showSachForm(Model model) {
        model.addAttribute("sach", new Sach());
        return "sach-form";
    }

    @PostMapping("/sach/save")
    public String saveSach(@Valid @ModelAttribute("sach") Sach sach, BindingResult result) {
        if (result.hasErrors()) return "sach-form";
        repo.saveSach(sach);
        return "redirect:/sach/new?success";
    }

    // --- FORM NHẬP ĐỘC GIẢ ---
    @GetMapping("/docgia/new")
    public String showDocGiaForm(Model model) {
        model.addAttribute("docGia", new DocGia());
        return "docgia-form";
    }

    @PostMapping("/docgia/save")
    public String saveDocGia(@Valid @ModelAttribute("docGia") DocGia dg, BindingResult result) {
        if (result.hasErrors()) return "docgia-form";
        repo.saveDocGia(dg);
        return "redirect:/docgia/new?success";
    }

    // --- FORM LẬP PHIẾU MƯỢN ---
    @GetMapping("/phieumuon/new")
    public String showPhieuForm(Model model) {
        model.addAttribute("docGias", repo.getAllDocGia());
        model.addAttribute("sachs", repo.getAllSach());
        return "phieumuon-form";
    }

    @PostMapping("/phieumuon/save")
    public String savePhieuMuon(@RequestParam("maDocGia") int maDocGia,
                                @RequestParam("maSach") int maSach,
                                @RequestParam("soLuong") int soLuong,
                                @RequestParam("ngayMuon") java.sql.Date ngayMuon) {
        // Kiểm tra ràng buộc ngày mượn không được sau ngày hiện tại
        if (ngayMuon.after(new Date())) {
            return "redirect:/phieumuon/new?error=date";
        }

        PhieuMuon pm = new PhieuMuon();
        DocGia dg = new DocGia(); dg.setMaDocGia(maDocGia);
        pm.setDocGia(dg);
        pm.setNgayMuon(ngayMuon);
        pm.setTrangThai("Đang mượn");

        ChiTietMuon ctm = new ChiTietMuon();
        Sach s = new Sach(); s.setMaSach(maSach);
        ctm.setSach(s);
        ctm.setSoLuongMuon(soLuong);
        ctm.setPhieuMuon(pm);

        List<ChiTietMuon> chiTiets = new ArrayList<>();
        chiTiets.add(ctm);
        pm.setChiTietMuons(chiTiets);

        repo.savePhieuMuon(pm);
        return "redirect:/phieumuon/new?success";
    }

    // --- CHỨC NĂNG TÌM KIẾM ---
    @GetMapping("/search/sach")
    public String searchSach(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("books", repo.searchSach(keyword));
        } else {
            model.addAttribute("books", repo.getAllSach()); // Hiển thị toàn bộ sách nếu chưa tìm kiếm
        }
        return "search-sach";
    }

    @GetMapping("/search/phieu")
    public String searchPhieu(@RequestParam(value = "tenDocGia", required = false) String tenDocGia, Model model) {
        if (tenDocGia != null) {
            model.addAttribute("results", repo.searchPhieuChuaTra(tenDocGia));
        }
        return "search-phieu";
    }
}
