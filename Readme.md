# HỆ THỐNG QUẢN LÝ THƯ VIỆN SÁCH

## 1. Giới thiệu

Hệ thống Quản lý Thư viện Sách được xây dựng nhằm hỗ trợ quản lý thông tin sách, độc giả và hoạt động mượn/trả sách. Hệ thống cho phép lưu trữ dữ liệu, lập phiếu mượn, tìm kiếm sách và theo dõi tình trạng mượn trả của độc giả.

---

## 2. Công nghệ sử dụng

* Java
* Spring MVC
* Hibernate/JPA
* MySQL hoặc SQL Server
* JSP/Servlet
* Bootstrap (tùy chọn)
* Maven

---

## 3. Thiết kế cơ sở dữ liệu

### Bảng SACH

| Tên cột       | Kiểu dữ liệu             | Mô tả                        |
| ------------- | ------------------------ | ---------------------------- |
| maSach        | INT (PK, AUTO_INCREMENT) | Mã sách                      |
| tenSach       | VARCHAR(255)             | Tên sách                     |
| tacGia        | VARCHAR(100)             | Tác giả                      |
| theLoai       | VARCHAR(50)              | Khoa học, Văn học, Ngoại ngữ |
| soLuongHienCo | INT                      | Số lượng hiện có             |

---

### Bảng DOC_GIA

| Tên cột     | Kiểu dữ liệu             | Mô tả         |
| ----------- | ------------------------ | ------------- |
| maDocGia    | INT (PK, AUTO_INCREMENT) | Mã độc giả    |
| hoTen       | VARCHAR(100)             | Họ tên        |
| loaiThe     | VARCHAR(20)              | Thường, VIP   |
| email       | VARCHAR(100)             | Email         |
| soDienThoai | VARCHAR(10)              | Số điện thoại |

---

### Bảng PHIEU_MUON

| Tên cột   | Kiểu dữ liệu             | Mô tả             |
| --------- | ------------------------ | ----------------- |
| maPhieu   | INT (PK, AUTO_INCREMENT) | Mã phiếu mượn     |
| maDocGia  | INT (FK)                 | Độc giả           |
| ngayMuon  | DATE                     | Ngày mượn         |
| trangThai | VARCHAR(20)              | Đang mượn, Đã trả |

---

### Bảng CHI_TIET_MUON

| Tên cột     | Kiểu dữ liệu | Mô tả         |
| ----------- | ------------ | ------------- |
| maPhieu     | INT (FK)     | Phiếu mượn    |
| maSach      | INT (FK)     | Sách          |
| soLuongMuon | INT          | Số lượng mượn |

### Quan hệ

* Một Độc giả có thể có nhiều Phiếu mượn.
* Một Phiếu mượn có thể chứa nhiều Sách.
* Một Sách có thể xuất hiện trong nhiều Phiếu mượn.
* Quan hệ N-N giữa Sách và Phiếu mượn được giải quyết thông qua bảng CHI_TIET_MUON.

---

## 4. Chức năng hệ thống

### 4.1 Quản lý Sách

Cho phép thêm mới thông tin sách:

* Tên sách
* Tác giả
* Thể loại
* Số lượng hiện có

#### Danh sách Thể loại

* Khoa học
* Văn học
* Ngoại ngữ

#### Validation

**Số lượng hiện có**

* Phải là số nguyên.
* Lớn hơn 0.

Ví dụ:

```java
@Min(value = 1, message = "Số lượng phải lớn hơn 0")
private Integer soLuongHienCo;
```

---

### 4.2 Quản lý Độc giả

Cho phép thêm mới độc giả:

* Họ tên
* Loại thẻ
* Email
* Số điện thoại

#### Danh sách Loại thẻ

* Thường
* VIP

#### Validation

**Email**

* Đúng định dạng email.
* Phải chứa ký tự @ và .

Ví dụ:

```java
@Email(message = "Email không hợp lệ")
private String email;
```

**Số điện thoại**

* Bắt đầu bằng số 0.
* Đủ 10 chữ số.

Regex:

```java
^0[0-9]{9}$
```

---

### 4.3 Lập Phiếu mượn

Cho phép tạo phiếu mượn sách.

Thông tin gồm:

* Độc giả (ComboBox từ CSDL)
* Ngày mượn
* Trạng thái
* Danh sách sách mượn

Chi tiết phiếu:

* Chọn sách từ Dropdown
* Nhập số lượng mượn

#### Validation

**Ngày mượn**

* Mặc định là ngày hiện tại.
* Không được lớn hơn ngày hiện tại.

Ví dụ:

```java
@PastOrPresent
private LocalDate ngayMuon;
```

**Số lượng mượn**

* Phải lớn hơn 0.

---

## 5. Chức năng tìm kiếm

### 5.1 Tìm kiếm Sách

Cho phép tìm kiếm theo:

* Tên sách
* Tác giả

Kết quả hiển thị:

* Mã sách
* Tên sách
* Tác giả
* Thể loại
* Số lượng hiện có

---

### 5.2 Tìm kiếm Phiếu mượn chưa trả

Tìm kiếm các phiếu có trạng thái:

```text
Đang mượn
```

Kết quả hiển thị:

* Họ tên độc giả
* Tên sách
* Ngày mượn
* Số lượng mượn

---

## 6. Kiến trúc dự án

```text
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

### Framework

* Spring MVC
* Hibernate/JPA

### Mô hình MVC

* Servlet nhận request.
* Controller xử lý nghiệp vụ.
* DAO thao tác dữ liệu.
* JSP hiển thị giao diện.

### Cơ sở dữ liệu

* MySQL hoặc SQL Server.

### Coding Convention

* Tên lớp theo PascalCase.
* Tên biến theo camelCase.
* Phân tách rõ Controller, Service, DAO, Entity.
* Code dễ đọc, dễ bảo trì.

---

## 8. Giao diện

Khuyến khích sử dụng:

* Bootstrap 5
* CSS Responsive
* Form nhập liệu trực quan
* Bảng dữ liệu đẹp, dễ theo dõi

Điểm cộng tối đa:

* 1.0 điểm

---

## 9. Kết luận

Hệ thống đáp ứng đầy đủ các yêu cầu:

✔ Quản lý Sách

✔ Quản lý Độc giả

✔ Lập Phiếu mượn

✔ Quản lý quan hệ N-N giữa Sách và Phiếu mượn

✔ Kiểm tra dữ liệu đầu vào bằng Validation

✔ Tìm kiếm Sách

✔ Tìm kiếm Phiếu mượn chưa trả

✔ Áp dụng Spring MVC và Hibernate

✔ Servlet/JSP đúng mô hình MVC

✔ Tuân thủ Java Coding Convention
