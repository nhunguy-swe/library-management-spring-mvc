<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Thêm Độc Giả</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5" style="max-width: 600px;">
    <div class="card shadow p-4">
        <h3 class="text-center text-success mb-4">Đăng Ký Độc Giả</h3>
        <c:if test="${param.success != null}">
            <div class="alert alert-success">Thêm mới độc giả thành công!</div>
        </c:if>
        <form:form action="/docgia/save" method="post" modelAttribute="docGia">
            <div class="mb-3">
                <label class="form-label">Họ và tên:</label>
                <form:input path="hoTen" class="form-control"/>
                <form:errors path="hoTen" class="text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Loại thẻ:</label>
                <form:select path="loaiThe" class="form-select">
                    <form:option value="Thường" label="Thường"/>
                    <form:option value="VIP" label="VIP"/>
                </form:select>
            </div>
            <div class="mb-3">
                <label class="form-label">Email:</label>
                <form:input path="email" class="form-control"/>
                <form:errors path="email" class="text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại:</label>
                <form:input path="soDienThoai" class="form-control"/>
                <form:errors path="soDienThoai" class="text-danger small"/>
            </div>
            <button type="submit" class="btn btn-success w-100">Đăng ký</button>
            <a href="/" class="btn btn-link w-100 mt-2 text-decoration-none">Quay lại</a>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>