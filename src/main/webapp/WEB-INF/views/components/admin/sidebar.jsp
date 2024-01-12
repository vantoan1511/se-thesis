<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="com.vtoan1517.utils.SecurityUtils" %>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.request.contextPath}/admin/home" class="brand-link"> <img
            src="<c:url value="/static/admin/dist/img/AdminLTELogo.png"/>" alt="AdminLTE Logo"
            class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">Hệ thống quản lý</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="<c:url value="/static/admin/dist/img/user2-160x160.jpg"/>" class="img-circle elevation-2"
                     alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block">
                    <%= SecurityUtils.getPrincipal().getFullName()%>
                </a>
            </div>
        </div>

        <!-- SidebarSearch Form -->
        <div class="form-inline">
            <div class="input-group" data-widget="sidebar-search">
                <input class="form-control form-control-sidebar" type="search"
                       placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-sidebar">
                        <i class="fas fa-search fa-fw"></i>
                    </button>
                </div>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column"
                data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <span class="nav-header">QUẢN LÝ BÀI VIẾT</span>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/articles"/>" class="nav-link">
                        <i class="ri-list-check-3 nav-icon"></i>
                        <p>Tất cả bài viết</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/articles?tab=published"/>" class="nav-link">
                        <i class="ri-upload-2-line nav-icon"></i>
                        <p>Đã đăng tải</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/articles?tab=pending"/>" class="nav-link">
                        <i class="ri-time-line nav-icon"></i>
                        <p>Chờ duyệt </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/articles?tab=featured"/>" class="nav-link">
                        <i class="ri-star-line nav-icon"></i>
                        <p>Nổi bật</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/articles?tab=trash"/>" class="nav-link">
                        <i class="ri-delete-bin-line nav-icon"></i>
                        <p>Thùng rác</p>
                    </a>
                </li>
                <li class="nav-item">
                    <span class="nav-header"> QUẢN LÝ DANH MỤC </span>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/category"/>" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Chuyên mục</p>
                    </a>
                </li>
                <li class="nav-item">
                    <span class="nav-header"> QUẢN LÝ NGƯỜI DÙNG </span>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/users"/>" class="nav-link">
                        <i class="nav-icon fas fa-user"></i>
                        <p>Tất cả người dùng</p>
                    </a>
                </li>
                <li class="nav-item">
                    <span class="nav-header"> QUẢN LÝ TÀI NGUYÊN </span>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/admin/storage"/>" class="nav-link">
                        <i class="ri-hard-drive-3-fill nav-icon"></i>
                        <p>Kho lưu trữ</p>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</aside>
