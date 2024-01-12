<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quên mật khẩu</title>
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
            <form action="/forgot" method="post">
                <div class="input-group mb-3">
                    <input type="email" class="form-control" name="email" placeholder="Email"/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="ri-mail-line"></span></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit"
                                class="btn bg-gradient-primary btn-block">
                            Reset mật khẩu <i class="ri-loop-right-line"></i>
                        </button>
                    </div>
                </div>
            </form>
            <p class="mt-3 mb-1">
                <a href="/login">Đăng nhập <i class="ri-login-circle-line"></i></a>
            </p>
            <p class="mb-0">
                <a href="/register">Tạo tài khoản mới <i class="ri-user-add-line"></i></a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
</body>
</html>
