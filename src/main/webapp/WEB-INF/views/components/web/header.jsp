<%@ page import="com.newswebsite.main.security.SecurityUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="username" value="<%=SecurityUtil.username()%>"/>

<header class="primary">
    <div class="firstbar">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-12">
                    <div class="brand">
                        <a href="/home">
                            <img src="<c:url value="/static/web/images/toansnewslogo.png"/>" alt="logo">
                        </a>
                    </div>
                </div>
                <div class="col-md-6 col-sm-12">
                    <form action="/search/" method="get" class="search" autocomplete="off">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" name="q" class="form-control" placeholder="Nhập từ khóa tìm kiếm...">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary"><i class="ion-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-3 col-sm-12 text-right">
                    <ul class="nav-icons">
                        <sec:authorize access="isAnonymous()">
                            <li>
                                <a href="/register"><i class="ion-person-add"></i>
                                    <div>Đăng ký</div>
                                </a>
                            </li>
                            <li>
                                <a href="/login"><i class="ion-person"></i>
                                    <div>Đăng nhập</div>
                                </a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li>
                                <a href="<c:url value="/my-profile"/>"><i class="ion-android-person"></i>
                                    <div>
                                        <c:out value="${username}"/>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="/logout"><i class="ion-log-out"></i>
                                    <div>Đăng xuất</div>
                                </a>
                            </li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <%@include file="navbar.jsp" %>
</header>