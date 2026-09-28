<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Thêm Sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5" style="max-width: 600px;">
    <div class="card shadow p-4">
        <h3 class="text-center text-primary mb-4">Nhập Sách Mới</h3>
        <c:if test="${param.success != null}">
            <div class="alert alert-success">Lưu thông tin sách thành công!</div>
        </c:if>
        <form:form action="/sach/save" method="post" modelAttribute="sach">
            <div class="mb-3">
                <label class="form-label">Tên sách:</label>
                <form:input path="tenSach" class="form-control"/>
                <form:errors path="tenSach" class="text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Tác giả:</label>
                <form:input path="tacgia" class="form-control"/>
                <form:errors path="tacgia" class="text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Thể loại:</label>
                <form:select path="theLoai" class="form-select">
                    <form:option value="Khoa học" label="Khoa học"/>
                    <form:option value="Văn học" label="Văn học"/>
                    <form:option value="Ngoại ngữ" label="Ngoại ngữ"/>
                </form:select>
            </div>
            <div class="mb-3">
                <label class="form-label">Số lượng hiện có:</label>
                <form:input type="number" path="soLuongHienCo" class="form-control"/>
                <form:errors path="soLuongHienCo" class="text-danger small"/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Lưu Sách</button>
            <a href="/" class="btn btn-link w-100 mt-2 text-decoration-none">Quay lại Trang chủ</a>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>