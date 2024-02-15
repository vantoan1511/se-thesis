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
            <form id="change-password-form" action="/reset" method="post">
                <input type="hidden" name="token" value="${param.token}">
                <div class="form-group mb-3">
                    <input id="password"
                           type="password"
                           name="password"
                           class="form-control"
                           placeholder="Mật khẩu mới"/>
                </div>
                <div class="form-group mb-3">
                    <input type="password"
                           class="form-control"
                           name="confirmedPassword"
                           placeholder="Nhập lại mật khẩu"/>
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
<script>
    $(function () {
        $('#change-password-form').validate({
            rules: {
                password: {
                    required: true,
                },
                confirmedPassword: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                password: {
                    required: "Vui lòng nhập mật khẩu mới",
                },
                confirmedPassword: {
                    required: "Vui lòng xác nhận mật khẩu mới",
                    equalTo: "Mật khẩu xác nhận không khớp"
                }
            },
            errorElement: 'span',
            errorClass: 'error invalid-feedback mt-2',
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        })
    });
</script>
</body>
</html>
