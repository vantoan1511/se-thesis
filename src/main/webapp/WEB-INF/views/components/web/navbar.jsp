<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!-- Start nav -->
<nav class="menu">
    <div class="container">
        <div class="brand">
            <a href="#">
                <img src="<c:url value="/static/web/images/toansnewslogo.png"/>" alt="Magz Logo">
            </a>
        </div>
        <div class="mobile-toggle">
            <a href="#" data-toggle="menu" data-target="#menu-list"><i class="ion-navicon-round"></i></a>
        </div>
        <div class="mobile-toggle">
            <a href="#" data-toggle="sidebar" data-target="#sidebar"><i class="ion-ios-arrow-left"></i></a>
        </div>
        <div id="menu-list">
            <ul class="nav-list">
                <li class="for-tablet nav-title"><a>Menu</a></li>
                <sec:authorize access="isAnonymous()">
                    <li class="for-tablet"><a href="/login">Login</a></li>
                    <li class="for-tablet"><a href="/register">Register</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="for-tablet">
                        <a href="#"><%=SecurityUtil.username()%>>
                        </a>
                    </li>
                    <li class="for-tablet"><a href="/logout">Logout</a></li>
                </sec:authorize>
                <li><a href="/home"><i class="ion-android-home"></i></a></li>
                <li class="dropdown magz-dropdown magz-dropdown-megamenu">
                    <a href="#"><i class="ion-android-menu"></i><i class="ion-ios-arrow-right"></i></a>
                    <div class="dropdown-menu megamenu">
                        <div class="megamenu-inner">
                            <div id="list-categories" class="row">
                                <%--<div class="col-md-3">
                                    <ul class="vertical-menu">
                                        <li><a href="<c:url value="/categories/the-thao"/>">Thể thao</a></li>
                                        <li><a href="<c:url value="/categories/bong-da"/>">Bóng đá</a></li>
                                    </ul>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- End nav -->