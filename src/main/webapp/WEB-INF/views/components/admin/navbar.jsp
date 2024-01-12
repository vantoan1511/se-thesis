<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button">
                <i class="fas fa-bars"></i>
            </a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/admin/home" class="nav-link">Home</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/home" target="_blank" class="nav-link">Mở trang người dùng</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/logout" class="nav-link">Đăng xuất</a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <%--<ul class="navbar-nav ml-auto">

        <li class="nav-item dropdown user-menu">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                <img src="<c:url value="/template/admin/dist/img/avatar.png"/>"
                     class="user-image img-circle elevation-2"
                     alt="User Image">
                <span class="d-none d-md-inline">${session.user.fullname}</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <!-- User image -->
                <li class="user-header bg-primary">
                    <img src="${session.user.avatar}" class="img-circle elevation-2"
                         alt="User Image">

                    <p>
                        ${session.user.fullname} - ${session.user.role.name}
                    </p>
                </li>
                <!-- Menu Body -->
                <li class="user-body">
                    <!-- /.row -->
                </li>
                <!-- Menu Footer-->
                <li class="user-footer">
                    <a href="<c:url value="/admin/users?tab=edit&id=${session.user.id}"/>"
                       class="btn btn-default btn-flat">Profile</a>
                    <a href="<c:url value="/logout"/>" class="btn btn-default btn-flat float-right">Đăng xuất</a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-widget="fullscreen" href="#" role="button">
                <i class="fas fa-expand-arrows-alt"></i>
            </a>
        </li>
    </ul>--%>
</nav>