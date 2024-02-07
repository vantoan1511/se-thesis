<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="/common/taglib.jsp" %>

<c:set value="Đổi mật khẩu" var="pageTitle"/>
<c:set var="loggedUsername" value="${pageContext.request.userPrincipal.name}"/>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>${pageTitle}</title>
</head>
<body>
<section class="login first grey">
    <div class="container">
        <div class="box-wrapper">
            <div class="box box-border">
                <div class="box-body">
                    <h4>${pageTitle}</h4>
                    <form id="change-password-form"
                          method="post">

                        <c:if test="${not empty message}">
                            <div class="row">
                                <%@ include file="../components/utils/alert.jsp" %>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <label>Mật khẩu cũ</label>
                            <input type="password" name="currentPassword" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu mới</label>
                            <input id="newPassword" type="password" name="newPassword" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nhập lại mật khẩu mới</label>
                            <input type="password" name="confirmPassword" class="form-control">
                        </div>
                        <div class="form-group text-right">
                            <button class="btn btn-primary btn-block">Xác nhận</button>
                        </div>
                        <div class="form-group text-center">
                            <span class="text-muted"><a href="/profiles/${loggedUsername}">Hủy</a></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(function () {
        $('#change-password-form').validate({
            rules: {
                currentPassword: {
                    required: true,
                },
                newPassword: {
                    required: true,
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                currentPassword: {
                    required: "Vui lòng nhập mật khẩu hiện tại",
                },
                newPassword: {
                    required: "Vui lòng nhập mật khẩu mới",
                },
                confirmPassword: {
                    required: "Vui lòng xác nhận mật khẩu mới",
                    equalTo: "Mật khẩu xác nhận không khớp"
                }
            },
            errorElement: 'span',
            errorClass: 'help-block mt-2',
            highlight: function (element, errorClass, validClass) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).closest('.form-group').removeClass('has-error');
            }
        })
    });
</script>
</body>
</html>
