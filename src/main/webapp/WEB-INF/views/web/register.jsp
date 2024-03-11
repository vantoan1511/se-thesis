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
            <%--@elvariable id="user" type=""--%>
            <form:form id="register-user-form"
                       modelAttribute="user"
                       method="post"
                       accept-charset="UTF-8">

                <c:if test="${not empty message}">
                    <%@ include file="../components/admin/alert.jsp" %>
                </c:if>

                <div class="form-group">
                    <form:input path="firstName"
                                cssClass="form-control"
                                placeholder="Họ"/>
                </div>
                <div class="form-group">
                    <form:input path="lastName"
                                cssClass="form-control"
                                placeholder="Tên"/>
                </div>
                <div class="form-group">
                    <form:input path="email"
                                cssClass="form-control"
                                cssErrorClass="form-control"
                                placeholder="Email"/>
                </div>
                <div class="form-group">
                    <form:input path="username"
                                cssClass="form-control"
                                cssErrorClass="form-control"
                                placeholder="Tên đăng nhập"/>
                </div>
                <div class="form-group">
                    <form:password path="password"
                                   cssClass="form-control"
                                   placeholder="Mật khẩu"/>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="icheck-success">
                            <input type="checkbox"
                                   id="agree-term"
                                   name="terms"
                                   value="agree">
                            <label for="agree-term">
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
<script>
    $(function () {
        $('#register-user-form').validate({
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
                username: {
                    required: true
                },
                password: {
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
                },
                username: {
                    required: "Vui lòng nhập tên đăng nhập"
                },
                password: {
                    required: "Vui lòng nhập mật khẩu"
                },
            },
            errorElement: 'span',
            errorClass: 'error invalid-feedback',
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid')
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        })
    })
</script>
</body>
</html>
