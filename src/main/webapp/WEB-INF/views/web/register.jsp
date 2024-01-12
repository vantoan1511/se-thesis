<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<div class="register-box">
    <div class="register-logo">
        <a href="<c:url value="/home"/>">
            <img src="<c:url value="/static/web/images/toansnewslogo.png"/>" alt="logo">
        </a>
    </div>

    <div class="card card-outline card-success">
        <div class="card-body register-card-body">

            <form:form action="/register" modelAttribute="user" method="post" accept-charset="UTF-8">
                <form:errors path="firstName" cssClass="error"/>
                <form:errors path="lastName" cssClass="error"/>
                <div class="input-group mb-3">
                    <form:input path="firstName"
                                maxlength="12"
                                cssClass="form-control"
                                placeholder="Họ"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-user-line"></span>
                        </div>
                    </div>
                    <form:input path="lastName"
                                cssClass="form-control"
                                placeholder="Tên"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-user-line"></span>
                        </div>
                    </div>
                </div>
                <form:errors path="email" cssClass="error"/>
                <div class="input-group mb-3">
                    <form:input path="email" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Email"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-mail-line"></span>
                        </div>
                    </div>
                </div>
                <form:errors path="username" cssClass="error"/>
                <div class="input-group mb-3">
                    <form:input path="username"
                                cssClass="form-control"
                                cssErrorClass="form-control is-invalid"
                                placeholder="Username"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-user-add-line"></span>
                        </div>
                    </div>
                </div>
                <form:errors path="password" cssClass="error"/>
                <div class="input-group mb-3">
                    <form:password path="password"
                                   cssClass="form-control"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Password"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-lock-password-line"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="icheck-success">
                            <input type="checkbox" id="agreeTerms" name="terms" value="agree">
                            <label for="agreeTerms">
                                Tôi đã đọc và đồng ý với <a href="#">các điều khoản</a>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- /.col -->
                    <div class="col">
                        <button type="submit"
                                class="btn bg-gradient-success btn-block">
                            Đăng ký <i class="ri-user-add-line"></i></button>
                    </div>
                    <!-- /.col -->
                </div>
            </form:form>

            <div class="social-auth-links text-center">
                <p>- hoặc -</p>
                <a href="<c:url value="/login"/>"
                   class="btn btn-block btn-outline-primary">
                    Đã có tài khoản <i class="ri-login-circle-line"></i>
                </a>
            </div>
        </div>
        <!-- /.form-box -->
    </div><!-- /.card -->
</div>
</body>
</html>
