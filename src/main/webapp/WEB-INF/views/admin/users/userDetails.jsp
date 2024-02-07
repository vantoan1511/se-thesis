<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Hồ sơ ${userDetails.fullName()}"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>
<c:set var="userDetails" value="${userDetails}"/>

<!doctype html>
<html lang="vi">
<head>
    <title>${pageTitle}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <%@ include file="../../components/admin/contentHeader.jsp" %>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <!-- Profile Image -->
                    <div class="card card-primary card-outline">
                        <div class="card-body box-profile">
                            <div class="text-center">
                                <c:choose>
                                    <c:when test="${not empty userDetails.avatarUrl}">
                                        <img class="profile-user-img img-fluid img-circle"
                                             src="${userDetails.avatarUrl}"
                                             alt="${userDetails.username}-avatar">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="profile-user-img img-fluid img-circle"
                                             src="<c:url value="/static/admin/dist/img/avatar.png"/>"
                                             alt="${userDetails.username}-avatar">
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <h3 class="profile-username text-center">${userDetails.fullName()}</h3>

                            <p class="text-muted text-center">&#64;${userDetails.username}</p>

                            <ul class="list-group list-group-unbordered mb-3">
                                <li class="list-group-item">
                                    <b>Bài viết</b> <a class="float-right">1,322</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Bình luận</b> <a class="float-right">543</a>
                                </li>
                            </ul>

                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->

                    <!-- About Me Box -->
                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Chi tiết</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <strong><i class="ri-time-line"></i> Đã tham gia</strong>

                            <p class="text-muted">
                                <fmt:formatDate value="${userDetails.createdAt}" pattern="dd MMM yyyy"/>
                            </p>
                        </div>
                    </div>
                    <!-- /.card -->
                </div>
                <div class="col-md-9">

                    <c:if test="${not empty message}">
                        <%@ include file="../../components/utils/alert.jsp" %>
                    </c:if>

                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Thông tin chung</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">

                            <form:form id="user-details-form"
                                       modelAttribute="userDetails"
                                       method="post"
                                       cssClass="form-horizontal"
                                       acceptCharset="UTF-8"
                                       enctype="UTF-8"
                                       htmlEscape="true">
                                <input type="hidden" name="action" value="updateProfile">
                                <div class="form-group row">
                                    <form:label path="firstName"
                                                cssClass="col-md-2 col-form-label">Họ</form:label>
                                    <div class="col-md-10">
                                        <form:input path="firstName"
                                                    cssClass="form-control"
                                                    placeholder="Họ"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <form:label path="lastName"
                                                class="col-sm-2 col-form-label">Tên</form:label>
                                    <div class="col-sm-10">
                                        <form:input path="lastName"
                                                    cssClass="form-control"
                                                    placeholder="Tên"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <form:label path="email"
                                                class="col-sm-2 col-form-label">Email</form:label>
                                    <div class="col-sm-10">
                                        <form:input path="email"
                                                    cssClass="form-control"
                                                    placeholder="Email"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-sm-2 col-sm-10">
                                        <button type="submit"
                                                class="btn btn-default bg-gradient-success">
                                            Lưu <i class="ri-send-plane-fill"></i>
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.card-body -->
                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Tài khoản</h3>
                        </div>
                        <div class="card-body">
                            <form id="user-account-form"
                                  method="post">
                                <input type="hidden" name="action" value="changePassword">
                                <div class="form-group row">
                                    <label for="password"
                                           class="col-md-2 col-form-label">Mật khẩu hiện tại</label>
                                    <div class="col-sm-10">
                                        <input type="password"
                                               id="password"
                                               name="password"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="newPW"
                                           class="col-md-2 col-form-label">Mật khẩu mới</label>
                                    <div class="col-sm-10">
                                        <input type="password"
                                               id="newPW"
                                               name="newPW"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="rePW"
                                           class="col-md-2 col-form-label">Nhập lại mật khẩu mới</label>
                                    <div class="col-sm-10">
                                        <input type="password"
                                               id="rePW"
                                               name="rePW"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-sm-2 col-sm-10">
                                        <button type="submit"
                                                class="btn btn-default bg-gradient-success">
                                            Lưu <i class="ri-send-plane-fill"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.card -->
    </section>
    <!-- /.content -->
</div>
<script src="<c:url value="/static/custom/js/user/details.js"/>"></script>
</body>
</html>