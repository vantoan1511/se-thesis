<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${category.name}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <h1>Chuyên mục: ${category.name}</h1>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <%--@elvariable id="category" type=""--%>
        <form:form modelAttribute="category" id="form">
            <form:hidden path="id"/>
            <sec:authorize access="hasRole('ADMIN')">
                <%@ include file="../../components/admin/detailsFunctionalities.jsp" %>
            </sec:authorize>
            <sec:authorize access="hasRole('WRITER')">
                <%@ include file="../../components/author/detailsFunctionalities.jsp" %>
            </sec:authorize>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="title">Tiêu đề</label>
                            <form:input path="name" cssClass="form-control form-control-border"
                                        cssErrorClass="is-invalid"
                                        placeholder="VD: Bài viết 1"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="alias">Alias</label>
                            <form:input path="code" id="alias"
                                        class="form-control form-control-border"
                                        readonly="false"
                                        placeholder="Được khởi tạo tự động dựa trên tiêu đề"/>
                            <p class="text-gray">Alias sẽ được sử dụng làm một phần cho URL bài viết</p>
                        </div>
                    </div>
                    <ul class="nav nav-tabs" id="content-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="content-general-tab" data-toggle="pill"
                               href="#general" role="tab" aria-controls="general"
                               aria-selected="true">Thông tin chung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="article-content-tab" data-toggle="pill"
                               href="#article-content" role="tab"
                               aria-controls="article-content" aria-selected="false">Nội dung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="media-and-links-tab" data-toggle="pill"
                               href="#media-and-links" role="tab"
                               aria-controls="media-and-links" aria-selected="false">Hình ảnh & Đa phương
                                tiện</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="details-tab" data-toggle="pill"
                               href="#details" role="tab"
                               aria-controls="details" aria-selected="false">Chi tiết</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="tab-content">
                        <div class="tab-pane fade active show" id="general" role="tabpanel"
                             aria-labelledby="content-general-tab">
                        </div>
                        <div class="tab-pane fade" id="article-content" role="tabpanel"
                             aria-labelledby="article-content-tab">
                        </div>
                        <div class="tab-pane fade" id="media-and-links" role="tabpanel"
                             aria-labelledby="media-and-links-tab">
                        </div>
                        <div class="tab-pane fade" id="details" role="tabpanel"
                             aria-labelledby="details-tab">
                            <div>
                                <h3>Chi tiết</h3>
                                <p>Đã tạo: <fmt:formatDate value="${category.createdAt}"
                                                           pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                <p>Bởi: <c:out value="${category.createdBy}"/></p>
                                <p>Lần sửa đổi cuối: <fmt:formatDate value="${category.lastModifiedAt}"
                                                                     pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                <p>Bởi: <c:out value="${category.lastModifiedBy}"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </section>
</div>
</body>
</html>