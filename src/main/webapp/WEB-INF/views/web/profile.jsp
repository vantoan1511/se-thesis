<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<body>
<section class="page first">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="line thin"></div>
                <div class="page-description">
                    <div class="element-block">
                        <div class="element-body">

                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="d-flex flex-column align-items-center text-center">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                     alt="Admin"
                                                     class="img-circle p-1 bg-primary" width="110">
                                                <div class="mt-3">
                                                    <h4><c:out value="${user.firstName}"/> <c:out
                                                            value="${user.lastName}"/></h4>
                                                    <p class="text-muted font-size-sm">@<c:out
                                                            value="${user.username}"/></p>
                                                </div>
                                            </div>
                                            <hr class="my-4">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                                    <h6 class="mb-0">Đã tham gia </h6>
                                                    <span class="text-secondary"><fmt:formatDate
                                                            value="${user.createdAt}" pattern="dd MMM yyyy"/> </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <div class="card">
                                        <div class="card-body">
                                            <%--@elvariable id="user" type=""--%>
                                            <form:form modelAttribute="user" method="post" acceptCharset="UTF-8">
                                                <c:if test="${not empty message}">
                                                    <div class="row">
                                                        <div class="alert alert-${message.type} show" role="alert">
                                                            <c:out value="${message.message}"/>
                                                            <button type="button" class="close" data-dismiss="alert"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <div class="row mb-3">
                                                    <div class="col-sm-3">
                                                        <h6 class="mb-0">Họ</h6>
                                                    </div>
                                                    <div class="col-sm-9 form-group floating text-secondary">
                                                        <form:input path="firstName"
                                                                    cssClass="form-control"
                                                                    cssErrorClass="error"/>
                                                    </div>
                                                </div>
                                                <div class="row mb-3">
                                                    <div class="col-sm-3">
                                                        <h6 class="mb-0">Tên</h6>
                                                    </div>
                                                    <div class="col-sm-9 form-group floating text-secondary">
                                                        <form:input path="lastName"
                                                                    cssClass="form-control"
                                                                    cssErrorClass="error"/>
                                                    </div>
                                                </div>
                                                <div class="row mb-3">
                                                    <div class="col-sm-3">
                                                        <h6 class="mb-0">Email</h6>
                                                    </div>
                                                    <div class="col-sm-9 form-group floating text-secondary">
                                                        <form:input path="email"
                                                                    cssClass="form-control"
                                                                    cssErrorClass="error"/>
                                                    </div>
                                                </div>
                                                <div class="row mb-3">
                                                    <div class="col-sm-3">
                                                        <h6 class="mb-0">Mật khẩu</h6>
                                                    </div>
                                                    <div class="col-sm-9 form-group floating text-secondary">
                                                        <p>
                                                            <i>Đã ẩn </i>
                                                            <a href="/change-password?nextUrl=http://localhost:8080/my-profile">
                                                                <i class="ion-edit"></i> Đổi mật khẩu
                                                            </a>
                                                        </p>
                                                    </div>
                                                </div>
                                                <c:if test="${pageContext.request.userPrincipal.name eq user.username}">
                                                    <div class="row">
                                                        <div class="col-sm-3"></div>
                                                        <div class="col-sm-9 text-secondary">
                                                            <button type="submit"
                                                                    class="btn btn-primary btn-rounded px-4">
                                                                Lưu
                                                            </button>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </form:form>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="d-flex align-items-center mb-3">Recent</h5>
                                                    <article class="article-mini">
                                                        <div class="inner">
                                                            <figure>
                                                                <a href="single.html">
                                                                    <img src="/static/web/images/news/img09.jpg"
                                                                         alt="Sample Article">
                                                                </a>
                                                            </figure>
                                                            <div class="padding">
                                                                <h1><a href="single.html">Duis aute irure dolor in
                                                                    reprehenderit in voluptate velit</a></h1>
                                                                <div class="detail">
                                                                    <div class="category"><a href="category.html">Lifestyle</a>
                                                                    </div>
                                                                    <div class="time">December 22, 2016</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </article>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
