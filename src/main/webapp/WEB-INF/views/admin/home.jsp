<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib.jsp" %>

<c:set var="pageTitle" value="Trang chủ"/>
<c:set var="breadcrumbs" value="${['Trang chủ']}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <%@ include file="../components/admin/contentHeader.jsp" %>
    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-6">
                    <div class="small-box bg-gradient-success">
                        <div class="inner">
                            <h3>${totalArticles}</h3>
                            <p>bài viết</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-ios-paper"></i>
                        </div>
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="<c:url value="/admin/articles"/>"
                               class="small-box-footer">Quản lý bài viết <i class="ion ion-arrow-right-a"></i></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('WRITER')">
                            <a href="<c:url value="/admin/articles/new"/>"
                               class="small-box-footer">Tạo <i class="ion ion-plus"></i></a>
                        </sec:authorize>
                    </div>
                </div>
                <sec:authorize access="hasRole('ADMIN')">
                    <div class="col-lg-3 col-6">
                        <div class="small-box bg-gradient-info">
                            <div class="inner">
                                <h3>${totalUsers}</h3>
                                <p>người dùng</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person"></i>
                            </div>
                            <a href="<c:url value="/admin/users"/>"
                               class="small-box-footer">Quản lý người dùng <i class="ion ion-arrow-right-a"></i></a>
                        </div>
                    </div>
                </sec:authorize>
                <div class="col-lg-3 col-6">
                    <div class="small-box bg-gradient-warning">
                        <div class="inner">
                            <h3>${totalCategories}</h3>
                            <p>chuyên mục</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-android-create"></i>
                        </div>
                        <a href="<c:url value="/admin/categories/new"/>"
                           class="small-box-footer">Tạo <i class="ion ion-plus"></i></a>
                    </div>
                </div>
                <div class="col-lg-3 col-6">
                    <div class="small-box bg-gradient-gray">
                        <div class="inner">
                            <h3>${totalImages}</h3>
                            <p>ảnh</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-image"></i>
                        </div>
                        <a href="<c:url value="/admin/gallery"/>"
                           class="small-box-footer">Đi đến thư viện <i class="ion ion-arrow-right-a"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>