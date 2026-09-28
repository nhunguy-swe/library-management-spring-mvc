# HỆ THỐNG QUẢN LÝ THƯ VIỆN SÁCH

<p>
  <img src="https://img.shields.io/badge/Java-orange" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20MVC-brightgreen" alt="Spring MVC">
  <img src="https://img.shields.io/badge/Hibernate%2FJPA-blue" alt="Hibernate/JPA">
  <img src="https://img.shields.io/badge/Build-Maven-red" alt="Maven">
</p>

## 1. Giới thiệu

Hệ thống Quản lý Thư viện Sách được xây dựng nhằm hỗ trợ quản lý thông tin sách, độc giả và hoạt động mượn/trả sách. Hệ thống cho phép lưu trữ dữ liệu, lập phiếu mượn, tìm kiếm sách và theo dõi tình trạng mượn trả của độc giả.

---

## 2. Công nghệ sử dụng

- Java
- Spring MVC
- Hibernate/JPA
- MySQL hoặc SQL Server
- JSP/Servlet
- Bootstrap (tùy chọn)
- Maven

---

## 3. Thiết kế cơ sở dữ liệu

### Bảng SACH

| Tên cột       | Kiểu dữ liệu              | Mô tả                        |
| ------------- | ------------------------- | ---------------------------- |
| maSach        | INT (PK, AUTO_INCREMENT)  | Mã sách                      |
| tenSach       | VARCHAR(255)               | Tên sách                     |
| tacGia        | VARCHAR(100)                | Tác giả                      |
| theLoai       | VARCHAR(50)                  | Khoa học, Văn học, Ngoại ngữ |
| soLuongHienCo | INT                            | Số lượng hiện có             |

### Bảng DOC_GIA

| Tên cột     | Kiểu dữ liệu              | Mô tả         |
| ----------- | ------------------------- | ------------- |
| maDocGia    | INT (PK, AUTO_INCREMENT)  | Mã độc giả    |
| hoTen       | VARCHAR(100)                | Họ tên        |
| loaiThe     | VARCHAR(20)                  | Thường, VIP   |
| email       | VARCHAR(100)                  | Email         |
| soDienThoai | VARCHAR(10)                    | Số điện thoại |

### Bảng PHIEU_MUON

| Tên cột   | Kiểu dữ liệu              | Mô tả             |
| --------- | ------------------------- | ----------------- |
| maPhieu   | INT (PK, AUTO_INCREMENT)  | Mã phiếu mượn     |
| maDocGia  | INT (FK)                    | Độc giả           |
| ngayMuon  | DATE                          | Ngày mượn         |
| trangThai | VARCHAR(20)                     | Đang mượn, Đã trả |

### Bảng CHI_TIET_MUON

| Tên cột     | Kiểu dữ liệu | Mô tả         |
| ----------- | ------------ | ------------- |
| maPhieu     | INT (FK)     | Phiếu mượn    |
| maSach      | INT (FK)       | Sách          |
| soLuongMuon | INT              | Số lượng mượn |

### Quan hệ

- Một Độc giả có thể có nhiều Phiếu mượn.
- Một Phiếu mượn có thể chứa nhiều Sách.
- Một Sách có thể xuất hiện trong nhiều Phiếu mượn.
- Quan hệ N-N giữa Sách và Phiếu mượn được giải quyết thông qua bảng CHI_TIET_MUON.

---

## 4. Chức năng hệ thống

### 4.1 Quản lý Sách

Cho phép thêm mới thông tin sách: Tên sách, Tác giả, Thể loại, Số lượng hiện có.

**Danh sách Thể loại:** Khoa học, Văn học, Ngoại ngữ

**Validation — Số lượng hiện có:** phải là số nguyên, lớn hơn 0.

```java
@Min(value = 1, message = "Số lượng phải lớn hơn 0")
private Integer soLuongHienCo;
```

### 4.2 Quản lý Độc giả

Cho phép thêm mới độc giả: Họ tên, Loại thẻ, Email, Số điện thoại.

**Danh sách Loại thẻ:** Thường, VIP

**Validation — Email:** đúng định dạng, chứa ký tự `@` và `.`

```java
@Email(message = "Email không hợp lệ")
private String email;
```

**Validation — Số điện thoại:** bắt đầu bằng số 0, đủ 10 chữ số.

```
^0[0-9]{9}$
```

### 4.3 Lập Phiếu mượn

Cho phép tạo phiếu mượn sách, gồm: Độc giả (ComboBox từ CSDL), Ngày mượn, Trạng thái, Danh sách sách mượn (chọn sách từ Dropdown, nhập số lượng mượn).

**Validation — Ngày mượn:** mặc định là ngày hiện tại, không được lớn hơn ngày hiện tại.

```java
@PastOrPresent
private LocalDate ngayMuon;
```

**Validation — Số lượng mượn:** phải lớn hơn 0.

---

## 5. Chức năng tìm kiếm

### 5.1 Tìm kiếm Sách

Tìm theo Tên sách / Tác giả. Kết quả hiển thị: Mã sách, Tên sách, Tác giả, Thể loại, Số lượng hiện có.

### 5.2 Tìm kiếm Phiếu mượn chưa trả

Tìm các phiếu có trạng thái `Đang mượn`. Kết quả hiển thị: Họ tên độc giả, Tên sách, Ngày mượn, Số lượng mượn.

---

## 6. Kiến trúc dự án

```
src/main/java
│
├── controller
│   ├── SachController
│   ├── DocGiaController
│   ├── PhieuMuonController
│   └── TimKiemController
│
├── entity
│   ├── Sach
│   ├── DocGia
│   ├── PhieuMuon
│   └── ChiTietMuon
│
├── dao
│   └── ThuVienDAO
│
├── service
│
└── config
    ├── WebConfig
    └── HibernateConfig
```

---

## 7. Yêu cầu kỹ thuật

**Framework:** Spring MVC, Hibernate/JPA

**Mô hình MVC:** Servlet nhận request → Controller xử lý nghiệp vụ → DAO thao tác dữ liệu → JSP hiển thị giao diện.

**Cơ sở dữ liệu:** MySQL hoặc SQL Server.

**Coding Convention:** Tên lớp theo PascalCase, tên biến theo camelCase, phân tách rõ Controller/Service/DAO/Entity, code dễ đọc, dễ bảo trì.

---

## 8. Giao diện

Khuyến khích sử dụng: Bootstrap 5, CSS Responsive, form nhập liệu trực quan, bảng dữ liệu đẹp, dễ theo dõi.

---

## Bắt đầu (Getting Started)

### Yêu cầu

- JDK 17+
- MySQL hoặc SQL Server
- IDE: IntelliJ IDEA / Eclipse / VS Code

### Cài đặt

```bash
git clone https://github.com/nhunguy-swe/library-management-spring-mvc.git
cd library-management-spring-mvc
```

### Cấu hình Database

1. Chạy script SQL trong thư mục `database/` để tạo bảng theo thiết kế ở mục 3.
2. Cập nhật thông tin kết nối trong file cấu hình Hibernate/Spring (`application.properties` hoặc `HibernateConfig`).

> ⚠️ Không hard-code mật khẩu database trực tiếp trong code nếu push lên GitHub public — dùng biến môi trường hoặc file cấu hình đã thêm vào `.gitignore`.

### Chạy ứng dụng

```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Hoặc deploy lên Tomcat nếu dự án dùng Servlet/JSP truyền thống.

---

## 9. Kết luận

Hệ thống đáp ứng đầy đủ các yêu cầu:

✔ Quản lý Sách · ✔ Quản lý Độc giả · ✔ Lập Phiếu mượn · ✔ Quản lý quan hệ N-N giữa Sách và Phiếu mượn · ✔ Kiểm tra dữ liệu đầu vào bằng Validation · ✔ Tìm kiếm Sách · ✔ Tìm kiếm Phiếu mượn chưa trả · ✔ Áp dụng Spring MVC và Hibernate · ✔ Servlet/JSP đúng mô hình MVC · ✔ Tuân thủ Java Coding Convention

---

## Tác giả

- GitHub: [@nhunguy-swe](https://github.com/nhunguy-swe)

---

## Giấy phép

Dự án này được thực hiện cho mục đích học tập/đồ án cá nhân.
