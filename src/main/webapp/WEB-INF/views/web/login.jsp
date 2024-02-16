<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đăng nhập</title>
</head>
<body>
<div class="login-box">
    <div class="login-logo">
        <a href="<c:url value="/home"/>">
            <img src="<c:url value="/static/web/images/toansnewslogo.png"/>" alt="logo">
        </a>
    </div>
    <!-- /.login-logo -->
    <div class="card card-outline card-primary">
        <div class="card-body login-card-body">
            <c:if test="${message != null}">
                <div class="alert alert-default-${message.type}" role="alert">
                        ${message.message}
                </div>
            </c:if>
            <form action="/perform-login" method="post">
                <input type="hidden" name="nextUrl" value="${param.nextUrl}">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="username" placeholder="Tên đăng nhập/Email"
                           autofocus/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="ri-user-line"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" name="password" placeholder="Mật khẩu đăng nhập"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="ri-lock-password-line"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember-me" name="remember-me">
                            <label for="remember-me">Ghi nhớ tôi</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit"
                                class="btn bg-gradient-primary btn-block">Đăng nhập <i class="ri-login-box-line"></i>
                        </button>
                    </div>
                </div>
            </form>
            <div class="social-auth-links text-center mb-3">
                <p>- hoặc -</p>
                <a href="<c:url value="/register"/>" class="btn btn-block btn-outline-success">
                    Tạo tài khoản mới <i class="ri-user-add-line"></i>
                </a>
            </div>
            <p class="mb-1">
                <a href="/forgot">Quên mật khẩu <i class="ri-question-line"></i></a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
