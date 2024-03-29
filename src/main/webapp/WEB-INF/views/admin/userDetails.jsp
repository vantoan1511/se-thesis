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
    <%@ include file="../components/admin/contentHeader.jsp" %>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <!-- Profile Image -->
                    <div class="card card-success card-outline">
                        <div class="card-body box-profile">
                            <div class="text-center">
                                <img class="avatar img-circle"
                                     src="${userDetails.avatarUrl}"
                                     alt="/static/public/images/default-avatar.png"
                                     width="110"
                                     height="110">

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

                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                    <!-- About Me Box -->
                    <div class="card card-outline card-success">
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
                        <%@ include file="../components/admin/alert.jsp" %>
                    </c:if>

                    <div class="card card-outline card-success">
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
                                        <label class="col-sm-2">Ảnh đại diện</label>
                                        <div class="col-sm-10">
                                            <form:hidden path="avatarUrl"/>
                                            <div class="gallery row">
                                                <div class="image-options">
                                                    <a id="inspect-btn"
                                                       class="dropdown-item btn btn-default btn-sm">
                                                        <i class="ri-search-line text-success"></i> Xem trước
                                                    </a>
                                                    <a id="set-avatar-btn"
                                                       class="dropdown-item btn btn-sm">
                                                        <i class="ri-paint-line text-success"></i> Đặt làm avatar
                                                    </a>
                                                    <a id="delete-image-btn"
                                                       class="dropdown-item btn btn-sm">
                                                        <i class="ri-delete-bin-line text-danger"></i> Xóa
                                                    </a>
                                                </div>
                                                <c:forEach var="image"
                                                           items="${uploadedImages.content}">
                                                    <div class="image-item col-md-auto">
                                                        <img style="object-fit: cover"
                                                             data-image-id="${image.id}"
                                                             src="${image.url}"
                                                             height="100"
                                                             width="100">
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2"></label>
                                        <div class="col-sm-10">
                                            <button id="upload-btn"
                                                    type="button"
                                                    class="btn bg-gradient-success">
                                                <i class="ri-arrow-up-line"></i> Tải lên
                                            </button>
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

                    <sec:authorize access="hasRole('ADMIN')">
                        <div class="card card-outline card-success">
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
                                            class="btn bg-gradient-success"><i class="ri-send-plane-line"></i> Lưu
                                    </button>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>

                    <div class="card card-outline card-success">
                        <div class=" card-header">
                            <h3 class="card-title">Tài khoản</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-2">
                                    <label>Xóa tài khoản</label>
                                    <p style="font-size: 85%">
                                        <i>
                                            Xóa vĩnh viễn tài khoản cùng các tài nguyên liên quan: ảnh, bài viết, bình
                                            luận,...
                                        </i>
                                    </p>
                                </div>
                                <div class="col-md-10">
                                    <button id="delete-account-btn"
                                            class="btn bg-gradient-danger">
                                        <i class="ri-delete-bin-line"></i> Xóa tài khoản
                                    </button>
                                </div>
                            </div>
                            <sec:authorize access="hasRole('ADMIN')">
                                <div class="row">
                                    <div class="col-md-2">
                                        <label>Vô hiệu tài khoản</label>
                                        <p style="font-size: 85%">
                                            <i>
                                                Vô hiệu hóa tài khoản. Tài khoản bị vô hiệu không thể đăng nhập
                                            </i>
                                        </p>
                                    </div>
                                    <div class="col-md-10">
                                        <c:choose>
                                            <c:when test="${userDetails.enabled}">
                                                <button id="disable-account-btn"
                                                        class="btn bg-gradient-danger">
                                                    <i class="ri-lock-line"></i> Vô hiệu tài khoản
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button id="enable-account-btn"
                                                        class="btn bg-gradient-success">
                                                    <i class="ri-lock-unlock-line"></i> Kích hoạt tài khoản
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
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
<script src="<c:url value="/static/custom/js/adminUserDetails.js"/>"></script>
</body>
</html>