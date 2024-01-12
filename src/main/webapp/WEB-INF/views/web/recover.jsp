<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đổi mật khẩu</title>
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
                <div class="alert alert-default-${type}" role="alert">
                        ${message}
                </div>
            </c:if>
            <form action="/reset" method="post">
                <input type="hidden" name="token" value="${param.token}">
                <div class="input-group mb-3">
                    <input type="password"
                           name="password"
                           class="form-control"
                           placeholder="Mật khẩu mới"/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="ri-lock-password-line"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" name="re-password" placeholder="Nhập lại mật khẩu"/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="ri-lock-password-line"></span></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit"
                                class="btn bg-gradient-primary btn-block">
                            Xác nhận <i class="ri-loop-right-line"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
</body>
</html>
