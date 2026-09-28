<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản Lý Thư Viện</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5">
    <div class="p-5 mb-4 bg-white rounded-3 shadow">
        <h1 class="display-5 fw-bold text-primary">Hệ Thống Quản Lý Thư Viện Sách</h1>
        <hr class="my-4">
        <div class="row g-3">
            <div class="col-md-4"><a class="btn btn-outline-primary btn-lg w-100" href="/sach/new">Nhập Sách Mới</a></div>
            <div class="col-md-4"><a class="btn btn-outline-success btn-lg w-100" href="/docgia/new">Thêm Độc Giả</a></div>
            <div class="col-md-4"><a class="btn btn-outline-warning btn-lg w-100" href="/phieumuon/new">Lập Phiếu Mượn</a></div>
            <div class="col-md-6"><a class="btn btn-info btn-lg w-100 text-white" href="/search/sach">Tìm Kiếm Sách</a></div>
            <div class="col-md-6"><a class="btn btn-dark btn-lg w-100" href="/search/phieu">Phiếu Chưa Trả</a></div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>