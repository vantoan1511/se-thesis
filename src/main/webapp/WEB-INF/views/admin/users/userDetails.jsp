<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="loggedUsername" value="${pageContext. request.userPrincipal.name}"/>
<c:set var="pageTitle" value="Hồ sơ ${profile.fullName()}"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>
<c:set var="userDetails" value="${profile}"/>

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
                                        <img class="avatar img-circle"
                                             src="${userDetails.avatarUrl}"
                                             alt="${userDetails.username}-avatar"
                                             width="110"
                                             height="110">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="avatar img-circle"
                                             src="<c:url value="/static/public/images/avatar.png"/>"
                                             alt="${userDetails.username}-avatar"
                                             width="110"
                                             height="110">
                                    </c:otherwise>
                                </c:choose>

                            </div>

                            <h3 class="profile-username text-center">${userDetails.fullName()}</h3>

                            <p class="text-muted text-center">&#64;${userDetails.username}</p>

                            <c:choose>
                                <c:when test="${userDetails.enabled}">
                                    <p class="text-center text-success"><b><i class="ri-circle-fill"></i> Đang hoạt động</b>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p class="text-center text-danger"><b><i class="ri-circle-fill"></i> Bị vô hiệu</b>
                                    </p>
                                </c:otherwise>
                            </c:choose>

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
                        <%@ include file="../../components/admin/alert.jsp" %>
                    </c:if>

                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Thông tin chung</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">

                            <%--@elvariable id="profile" type=""--%>
                            <form:form id="user-details-form"
                                       modelAttribute="profile"
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
                                <c:if test="${loggedUsername eq userDetails.username}">
                                    <div class="form-group row">
                                        <label class="col-sm-2">Mật khẩu hiện tại</label>
                                        <div class="col-sm-10">
                                            <i>Đã ẩn</i>
                                            <a href="<c:url value="/admin/change-password"/>">
                                                <i class="ri-edit-box-line">Chỉnh sửa</i>
                                            </a>
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
                                </c:if>
                            </form:form>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>

                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Vai trò</h3>
                        </div>
                        <div class="card-body row">
                            <div class="col-md-3">
                                <select id="available-roles"
                                        class="form-control custom-select"
                                        size="3">
                                    <c:forEach items="${allRoles}" var="role">
                                        <c:if test="${not userDetails.getRoles().contains(role.authority)}">
                                            <option value="${role.authority}">${role.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select id="user-roles"
                                        class="form-control custom-select"
                                        size="3">
                                    <c:forEach items="${userDetails.authorities}" var="role">
                                        <option value="${role.authority}">${role.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-auto">
                                <button id="grant-privileges-btn"
                                        class="btn bg-gradient-success">Lưu
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="card card-primary">
                        <div class=" card-header">
                            <h3 class="card-title">Tài khoản</h3>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label>Xóa tài khoản vĩnh viễn</label>
                                <button id="delete-account-btn"
                                        class="btn bg-gradient-danger col-md-3">Xóa tài khoản
                                </button>
                            </div>
                            <sec:authorize access="hasRole('ADMIN')">
                                <div class="form-group">
                                    <label>Vô hiệu tài khoản</label>
                                    <c:choose>
                                        <c:when test="${userDetails.enabled}">
                                            <button id="disable-account-btn"
                                                    class="btn bg-gradient-danger col-md-3">Vô hiệu tài khoản
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button id="enable-account-btn"
                                                    class="btn bg-gradient-success col-md-3">Kích hoạt tài khoản
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.card -->
    </section>
    <!-- /.content -->
</div>
<script src="<c:url value="/static/custom/js/adminUserProfile.js"/>"></script>
</body>
</html>