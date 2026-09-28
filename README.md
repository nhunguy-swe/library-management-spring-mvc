# BOOK LIBRARY MANAGEMENT SYSTEM

<p>
  <img src="https://img.shields.io/badge/Java-orange" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20MVC-brightgreen" alt="Spring MVC">
  <img src="https://img.shields.io/badge/Hibernate%2FJPA-blue" alt="Hibernate/JPA">
  <img src="https://img.shields.io/badge/Build-Maven-red" alt="Maven">
</p>

## 1. Introduction

The Book Library Management System is built to support managing books, readers, and borrow/return activities. The system allows data storage, creating borrow slips, searching for books, and tracking readers' borrow/return status.

---

## 2. Tech Stack

- Java
- Spring MVC
- Hibernate/JPA
- MySQL or SQL Server
- JSP/Servlet
- Bootstrap (optional)
- Maven

---

## 3. Database Design

### BOOK Table (SACH)

| Column         | Data Type                 | Description                       |
| -------------- | -------------------------- | ---------------------------------- |
| maSach         | INT (PK, AUTO_INCREMENT)   | Book ID                            |
| tenSach        | VARCHAR(255)                 | Book title                         |
| tacGia         | VARCHAR(100)                   | Author                             |
| theLoai        | VARCHAR(50)                      | Science, Literature, Foreign Language |
| soLuongHienCo  | INT                                 | Available quantity                 |

### READER Table (DOC_GIA)

| Column      | Data Type                 | Description   |
| ----------- | -------------------------- | ------------- |
| maDocGia    | INT (PK, AUTO_INCREMENT)   | Reader ID     |
| hoTen       | VARCHAR(100)                 | Full name     |
| loaiThe     | VARCHAR(20)                    | Standard, VIP |
| email       | VARCHAR(100)                     | Email         |
| soDienThoai | VARCHAR(10)                        | Phone number  |

### BORROW SLIP Table (PHIEU_MUON)

| Column    | Data Type                 | Description         |
| --------- | -------------------------- | -------------------- |
| maPhieu   | INT (PK, AUTO_INCREMENT)   | Slip ID              |
| maDocGia  | INT (FK)                     | Reader               |
| ngayMuon  | DATE                            | Borrow date          |
| trangThai | VARCHAR(20)                        | Borrowing, Returned  |

### BORROW DETAIL Table (CHI_TIET_MUON)

| Column      | Data Type | Description       |
| ----------- | --------- | ------------------ |
| maPhieu     | INT (FK)  | Borrow slip        |
| maSach      | INT (FK)    | Book                |
| soLuongMuon | INT           | Quantity borrowed   |

### Relationships

- A Reader can have many Borrow Slips.
- A Borrow Slip can contain many Books.
- A Book can appear in many Borrow Slips.
- The N-N relationship between Book and Borrow Slip is resolved via the CHI_TIET_MUON table.

---

## 4. System Features

### 4.1 Book Management

Add new book info: Title, Author, Genre, Available quantity.

**Genre list:** Science, Literature, Foreign Language

**Validation — Available quantity:** must be an integer, greater than 0.

```java
@Min(value = 1, message = "Quantity must be greater than 0")
private Integer soLuongHienCo;
```

### 4.2 Reader Management

Add new reader info: Full name, Card type, Email, Phone number.

**Card type list:** Standard, VIP

**Validation — Email:** valid email format, must contain `@` and `.`

```java
@Email(message = "Invalid email")
private String email;
```

**Validation — Phone number:** starts with 0, exactly 10 digits.

```
^0[0-9]{9}$
```

### 4.3 Create Borrow Slip

Create a book borrow slip with: Reader (ComboBox from DB), Borrow date, Status, List of borrowed books (select book from dropdown, enter quantity).

**Validation — Borrow date:** defaults to the current date, cannot be later than today.

```java
@PastOrPresent
private LocalDate ngayMuon;
```

**Validation — Quantity borrowed:** must be greater than 0.

---

## 5. Search Features

### 5.1 Search Books

Search by Title / Author. Results show: Book ID, Title, Author, Genre, Available quantity.

### 5.2 Search Unreturned Borrow Slips

Search slips with status `Borrowing`. Results show: Reader's full name, Book title, Borrow date, Quantity borrowed.

---

## 6. Project Architecture

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

## 7. Technical Requirements

**Framework:** Spring MVC, Hibernate/JPA

**MVC Pattern:** Servlet receives request → Controller handles business logic → DAO handles data access → JSP renders the UI.

**Database:** MySQL or SQL Server.

**Coding Convention:** Class names in PascalCase, variable names in camelCase, clear separation between Controller/Service/DAO/Entity, readable and maintainable code.

---

## 8. UI

Recommended: Bootstrap 5, responsive CSS, intuitive input forms, clean and easy-to-read data tables.

---

## Getting Started

### Requirements

- JDK 17+
- MySQL or SQL Server
- IDE: IntelliJ IDEA / Eclipse / VS Code

### Installation

```bash
git clone https://github.com/nhunguy-swe/library-management-spring-mvc.git
cd library-management-spring-mvc
```

### Database Setup

1. Run the SQL script in the `database/` folder to create tables per the design in section 3.
2. Update the connection info in the Hibernate/Spring config file (`application.properties` or `HibernateConfig`).

> ⚠️ Don't hard-code the database password directly in your code if pushing to a public GitHub repo — use environment variables or a config file added to `.gitignore` instead.

### Running the Application

```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Or deploy to Tomcat if the project uses traditional Servlet/JSP.

---

## 9. Conclusion

The system fully meets the requirements:

✔ Book Management · ✔ Reader Management · ✔ Borrow Slip Creation · ✔ N-N relationship management between Book and Borrow Slip · ✔ Input validation · ✔ Book search · ✔ Unreturned borrow slip search · ✔ Applies Spring MVC and Hibernate · ✔ Servlet/JSP follows MVC pattern correctly · ✔ Follows Java coding convention

---

## Author

- GitHub: [@nhunguy-swe](https://github.com/nhunguy-swe)

---

## License

Created for learning/academic purposes.
