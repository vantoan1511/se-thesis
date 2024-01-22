<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách chuyên mục bài viết</title>
</head>

<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Danh sách chuyên mục bài viết</h1>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="card">
            <div class="card-body row">
                <div class="col-md-3 col-sm-6">
                    <a href="<c:url value="/admin/categories/new"/>" class="btn btn-block bg-gradient-success">
                        <i class="ri-add-circle-line"></i> Mới
                    </a>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <div class="row">
                    <div class="col-md-2">
                        <div class="row">
                            <select name="sort-by" id="sort-by" class="col form-control custom-select">
                                <option value="title">Tiêu đề</option>
                                <option value="lastModifiedAt">Ngày sửa đổi</option>
                                <option value="createdBy">Tác giả</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="row">
                            <select name="sort-order" id="sort-order" class="col form-control custom-select">
                                <option value="ASC">Tăng dần</option>
                                <option value="DESC">Giảm dần</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body p-0">
                <table id="myTable" class="table table-bordered table-hover projects">
                    <thead>
                    <tr>
                        <th>
                            <div class="form-check icheck-material-red">
                                <input onchange="handleSelectAllCheckboxClick(this, '.check-box')"
                                       id="select-all"
                                       class="form-check-input check-all"
                                       type="checkbox">
                                <label for="select-all"></label>
                            </div>
                        </th>
                        <th>Id</th>
                        <th>Tiêu đề</th>
                        <th>Đã tạo</th>
                        <th>Tác giả</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="category" items="${categories}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input onchange="handleSingleCheckboxClick('#select-all', '.check-box')"
                                           class="form-check-input check-box"
                                           type="checkbox"
                                           id="${category.id}"
                                           value="${category.id}">
                                    <label for="${category.id}"></label>
                                </div>
                            </td>
                            <td>${category.id}</td>
                            <td>
                                <a class="text-truncate"
                                   href="#">
                                        ${category.title} <i class="ri-edit-box-line"></i>
                                </a>
                                <p style="font-size: 85%">
                                    Alias: ${category.alias}<br>
                                    Bài viết: <c:out value="${category.articles.size()}"/>
                                </p>
                            </td>
                            <td>
                                <fmt:formatDate value="${category.createdAt}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td><c:out value="${category.createdBy}"/></td>
                            <td>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${categories.size() lt 1}">
                        <tr>
                            <td colspan="8" class="text-center">Trống</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="card-footer clear-fix row"></div>
        </div>
    </section>
    <!---->
</div>
</body>
</html>