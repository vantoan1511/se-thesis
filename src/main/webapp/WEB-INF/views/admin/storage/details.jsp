<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý tài nguyên</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <h1>Quản lý tài nguyên</h1>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <%--@elvariable id="file" type=""--%>
        <form:form modelAttribute="file" id="form">
            <form:hidden path="id"/>

            <div class="card">
                <div class="card-body row">
                    <div class="col-md-3 col-sm-auto">
                        <button onclick="handleImageDetailsSaveButton(event, '#form', false, false)"
                                id="saveBtn"
                                class="btn btn-block bg-gradient-success">
                            <i class="ri-save-line"></i> Lưu
                        </button>
                    </div>
                    <div class="col-auto">
                        <button onclick="handleImageDetailsSaveButton(event, '#form', true, false)"
                                id="saveAndCloseBtn"
                                class="btn btn-block btn-default">
                            <i class="ri-check-double-line text-success"></i> Lưu & Đóng
                        </button>
                    </div>
                    <div class="col-auto">
                        <a href="<c:url value="/admin/my-storage"/>" class="btn btn-block btn-default">
                            <i class="fas fa-undo text-success"></i> Quay lại
                        </a>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="title">Tiêu đề</label>
                            <form:input path="title" cssClass="form-control form-control-border"
                                        cssErrorClass="is-invalid"
                                        placeholder="VD: Ảnh 123"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="alias">Alias</label>
                            <form:input path="alias" id="alias"
                                        class="form-control form-control-border"
                                        readonly="false"
                                        placeholder="Để trống để khởi tạo tự động dựa trên tiêu đề"/>
                            <p class="text-gray">Alias sẽ được sử dụng làm một phần cho URL</p>
                        </div>
                    </div>
                    <ul class="nav nav-tabs" id="content-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="description-tab" data-toggle="pill"
                               href="#description" role="tab" aria-controls="description"
                               aria-selected="true">Chung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="details-tab" data-toggle="pill"
                               href="#details" role="tab"
                               aria-controls="details" aria-selected="false">Chi tiết</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="tab-description">
                        <div class="tab-pane fade active show" id="description" role="tabpanel"
                             aria-labelledby="description-tab">
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <form:label path="description">Mô tả</form:label>
                                    <form:textarea path="description"
                                                   placeholder="Mô tả"
                                                   cssClass="form-control form-control-border"/>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="details" role="tabpanel"
                             aria-labelledby="details-tab">
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <h5>Chi tiết</h5>
                                    <p>Đã tạo: <fmt:formatDate value="${file.createdAt}"
                                                               pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                    <p>Bởi: <c:out value="${file.createdBy}"/></p>
                                    <p>Lần sửa đổi cuối: <fmt:formatDate value="${file.lastModifiedAt}"
                                                                         pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                    <p>Bởi: <c:out value="${file.lastModifiedBy}"/></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer"></div>
            </div>
        </form:form>
    </section>
</div>
<script src="<c:url value="/static/custom/js/storage/main.js"/>"></script>
</body>
</html>