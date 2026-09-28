<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Lập Phiếu Mượn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="header.jsp" />
<div class="container mt-5" style="max-width: 650px;">
    <div class="card shadow p-4">
        <h3 class="text-center text-warning mb-4">Lập Phiếu Mượn Sách</h3>
        <c:if test="${param.success != null}">
            <div class="alert alert-success">Tạo phiếu mượn thành công!</div>
        </c:if>
        <c:if test="${param.error == 'date'}">
            <div class="alert alert-danger">Ngày mượn không được chọn ngày tương lai!</div>
        </c:if>
        <form action="/phieumuon/save" method="post">
            <div class="mb-3">
                <label class="form-label">Chọn độc giả:</label>
                <select name="maDocGia" class="form-select" required>
                    <c:forEach items="${docGias}" var="dg">
                        <option value="${dg.maDocGia}">${dg.hoTen} (${dg.loaiThe})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="card p-3 mb-3 bg-light">
                <h6>Chi tiết mượn sách</h6>
                <div class="mb-2">
                    <label class="form-label">Tên sách:</label>
                    <select name="maSach" class="form-select" required>
                        <c:forEach items="${sachs}" var="s">
                            <option value="${s.maSach}">${s.tenSach} - ${s.tacgia}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-2">
                    <label class="form-label">Số lượng mượn:</label>
                    <input type="number" name="soLuong" min="1" class="form-control" value="1" required/>
                </div>
            </div>
            <div class="mb-4">
                <label class="form-label">Ngày mượn:</label>
                <input type="date" id="ngayMuon" name="ngayMuon" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-warning w-100 text-white">Xác nhận mượn</button>
            <a href="/" class="btn btn-link w-100 mt-2 text-decoration-none">Quay lại</a>
        </form>
    </div>
</div>
<script>
    document.getElementById('ngayMuon').valueAsDate = new Date();
</script>
<jsp:include page="footer.jsp" />
</body>
</html>