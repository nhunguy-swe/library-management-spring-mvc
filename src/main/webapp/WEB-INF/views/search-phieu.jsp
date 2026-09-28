<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Phiếu mượn chưa trả</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5">
    <div class="card shadow p-4">
        <h3 class="mb-4 text-dark">Tra cứu Sách Đang Mượn (Chưa Trả)</h3>
        <form action="/search/phieu" method="get" class="row g-2 mb-4">
            <div class="col-sm-9">
                <input type="text" name="tenDocGia" class="form-control" placeholder="Nhập họ tên độc giả..." value="${param.tenDocGia}"/>
            </div>
            <div class="col-sm-3">
                <button type="submit" class="btn btn-dark w-100">Tra cứu</button>
            </div>
        </form>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr><th>Họ tên độc giả</th><th>Tên sách</th><th>Ngày mượn</th><th>Số lượng mượn</th></tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="res">
                <tr>
                    <td>${res.phieuMuon.docGia.hoTen}</td>
                    <td>${res.sach.tenSach}</td>
                    <td>${res.phieuMuon.ngayMuon}</td>
                    <td>${res.soLuongMuon}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/" class="btn btn-link text-decoration-none">Quay lại Trang chủ</a>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>