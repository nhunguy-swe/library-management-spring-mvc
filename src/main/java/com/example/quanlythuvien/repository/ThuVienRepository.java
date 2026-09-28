package com.example.quanlythuvien.repository;

import com.example.quanlythuvien.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ThuVienRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // ==========================================
    // 1. CÁC HÀM LƯU DỮ LIỆU (SAVE / PERSIST)
    // ==========================================

    public void saveSach(Sach s) {
        getSession().persist(s);
    }

    public void saveDocGia(DocGia dg) {
        getSession().persist(dg);
    }

    /**
     * Hàm lưu Phiếu mượn nâng cao
     * Giải quyết triệt để lỗi 500: detached entity passed to persist cho thực thể Sach và DocGia
     */
    @Transactional
    public void savePhieuMuon(PhieuMuon pm) {
        // 1. Đồng bộ Độc Giả từ Database để đưa về trạng thái Managed
        if (pm.getDocGia() != null) {
            DocGia managedDocGia = getSession().get(DocGia.class, pm.getDocGia().getMaDocGia());
            pm.setDocGia(managedDocGia);
        }

        // 2. Lưu trước PhieuMuon rỗng nếu là thêm mới để lấy maPhieu tự tăng (Identity)
        // Cần xóa tạm danh sách con để lệnh persist đầu tiên không kích hoạt cascade lỗi dữ liệu
        List<ChiTietMuon> danhSachGoc = pm.getChiTietMuons();
        pm.setChiTietMuons(null);
        getSession().persist(pm); // Lưu cha trước để sinh ID

        // 3. Đưa danh sách gốc trở lại và đồng bộ ID hỗn hợp trực tiếp trên đó
        if (danhSachGoc != null && !danhSachGoc.isEmpty()) {
            for (ChiTietMuon ctm : danhSachGoc) {
                // Thiết lập liên kết hai chiều bắt buộc giữa Cha và Con
                ctm.setPhieuMuon(pm);

                // Đồng bộ thực thể Sách từ DB lên để tránh lỗi detached entity
                if (ctm.getSach() != null) {
                    Sach managedSach = getSession().get(Sach.class, ctm.getSach().getMaSach());
                    ctm.setSach(managedSach);

                    // Nạp trực tiếp dữ liệu vào Khóa chính hỗn hợp của phần tử trong danh sách gốc
                    if (ctm.getId() == null) {
                        ctm.setId(new ChiTietMuonId());
                    }
                    ctm.getId().setMaPhieu(pm.getMaPhieu()); // maPhieu vừa sinh sau lệnh persist ở bước 2
                    ctm.getId().setMaSach(managedSach.getMaSach());
                }
            }

            // Gán lại danh sách đã đồng bộ ID hoàn chỉnh vào PhieuMuon
            pm.setChiTietMuons(danhSachGoc);
        }

        // 4. Sử dụng merge đối tượng cha. Cơ chế CascadeType.ALL trong PhieuMuon.java
        // sẽ tự động đồng bộ một phiên bản duy nhất của ChiTietMuon xuống DB.
        getSession().merge(pm);

        // 5. Đẩy dữ liệu xuống MySQL an toàn
        getSession().flush();
    }

    // ==========================================
    // 2. CÁC HÀM TÌM KIẾM & LẤY DANH SÁCH (READ)
    // ==========================================

    public List<Sach> getAllSach() {
        return getSession().createQuery("from Sach", Sach.class).list();
    }

    public List<DocGia> getAllDocGia() {
        return getSession().createQuery("from DocGia", DocGia.class).list();
    }

    // Yêu cầu tìm kiếm Sách theo Tên hoặc Tác giả
    public List<Sach> searchSach(String keyword) {
        return getSession().createQuery("from Sach where tenSach like :k or tacGia like :k", Sach.class)
                .setParameter("k", "%" + keyword + "%").list();
    }

    // Yêu cầu tìm kiếm phiếu chưa trả của độc giả
    public List<ChiTietMuon> searchPhieuChuaTra(String tenDocGia) {
        String hql = "from ChiTietMuon c where c.phieuMuon.trangThai = 'Đang mượn' " +
                "and c.phieuMuon.docGia.hoTen like :name";
        return getSession().createQuery(hql, ChiTietMuon.class)
                .setParameter("name", "%" + tenDocGia + "%").list();
    }
}