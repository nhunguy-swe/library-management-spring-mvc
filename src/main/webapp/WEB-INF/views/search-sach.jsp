<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Tìm kiếm Sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5">
    <div class="card shadow p-4">
        <h3 class="mb-4">Tìm Kiếm Sách</h3>
        <form action="/search/sach" method="get" class="row g-2 mb-4">
            <div class="col-sm-9">
                <input type="text" name="keyword" class="form-control" placeholder="Nhập tên sách hoặc tác giả..." value="${param.keyword}"/>
            </div>
            <div class="col-sm-3">
                <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
            </div>
        </form>
        <table class="table table-hover table-striped">
            <thead class="table-primary">
            <tr><th>Mã sách</th><th>Tên Sách</th><th>Tác Giả</th><th>Thể Loại</th><th>Số lượng</th></tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="b">
                <tr><td>${b.maSach}</td><td>${b.tenSach}</td><td>${b.tacgia}</td><td>${b.theLoai}</td><td>${b.soLuongHienCo}</td></tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/" class="btn btn-link text-decoration-none">Quay lại Trang chủ</a>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>