<%@ page import="com.newswebsite.main.security.SecurityUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="loggedUsername" value="<%=SecurityUtil.username()%>"/>

<!DOCTYPE html>
<html>
<body>
<section class="page first">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
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
                                                    <h4><c:out value="${profile.firstName}"/> <c:out
                                                            value="${profile.lastName}"/></h4>
                                                    <p class="text-muted font-size-sm">@<c:out
                                                            value="${profile.username}"/></p>
                                                </div>
                                            </div>
                                            <hr class="my-4">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                                    <h6 class="mb-0">Đã tham gia </h6>
                                                    <span class="text-secondary"><fmt:formatDate
                                                            value="${profile.createdAt}" pattern="dd MMM yyyy"/> </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <div class="card">
                                        <div class="card-body">
                                            <%--@elvariable id="profile" type=""--%>
                                            <form:form id="user-profile-form"
                                                       modelAttribute="profile"
                                                       method="post"
                                                       acceptCharset="UTF-8">

                                                <c:if test="${not empty message}">
                                                    <div class="row">
                                                        <%@ include file="../components/utils/alert.jsp" %>
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
                                                <c:if test="${loggedUsername eq profile.username}">
                                                    <div class="row mb-3">
                                                        <div class="col-sm-3">
                                                            <h6 class="mb-0">Mật khẩu</h6>
                                                        </div>
                                                        <div class="col-sm-9 form-group floating text-secondary">
                                                            <p>
                                                                <i>Đã ẩn </i>
                                                                <a href="<c:url value="/change-password"/>">
                                                                    <i class="ion-edit"></i> Đổi mật khẩu
                                                                </a>
                                                            </p>
                                                        </div>
                                                    </div>
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
<script>
    $(function () {
        $('#user-profile-form').validate({
            rules: {
                firstName: {
                    required: true
                },
                lastName: {
                    required: true
                },
                email: {
                    email: true,
                    required: true
                },
            },
            messages: {
                firstName: {
                    required: "Vui lòng nhập họ"
                },
                lastName: {
                    required: "Vui lòng nhập tên"
                },
                email: {
                    required: "Vui lòng nhập địa chỉ email",
                    email: "Vui lòng nhập một địa chỉ email hợp lệ"
                }
            },
            errorElement: 'span',
            errorClass: 'help-block',
            highlight: function (element, errorClass, validClass) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).closest('.form-group').removeClass('has-error');
            }
        });
    });
</script>
</body>
</html>
