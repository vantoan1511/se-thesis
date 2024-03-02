<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<c:set var="loggedUsername" value="${pageContext.request.userPrincipal.name}"/>
<c:set var="pageTitle" value="Đổi mật khẩu"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>${pageTitle}</title>
</head>
<body>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <%@ include file="../components/admin/contentHeader.jsp" %>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col"></div>
                <div class="col">

                    <c:if test="${not empty message}">
                        <%@ include file="../components/admin/alert.jsp" %>
                    </c:if>

                    <div class="card">
                        <div class="card-header bg-gradient-success">
                            <h3 class="card-title">Tài khoản</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <form id="change-password-form"
                                  method="post">
                                <div class="input-group mb-3">
                                    <input type="password"
                                           class="form-control"
                                           name="currentPassword"
                                           placeholder="Mật khẩu hiện tại"
                                           autofocus/>
                                </div>
                                <div class="input-group mb-3">
                                    <input id="newPassword"
                                           type="password"
                                           class="form-control"
                                           name="newPassword"
                                           placeholder="Mật khẩu mới"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input id="confirmPassword"
                                           type="password"
                                           class="form-control"
                                           name="confirmPassword"
                                           placeholder="Nhập lại mật khẩu mới"/>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <button type="submit"
                                                class="btn bg-gradient-success btn-block">Lưu <i
                                                class="ri-login-box-line"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="row text-center">
                                    <a href="/admin/users/${loggedUsername}">Quay lại <i
                                            class="ri-arrow-go-back-line"></i></a>
                                </div>
                            </form>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.card-body -->
                </div>
                <div class="col"></div>
            </div>
        </div>
        <!-- /.card -->
    </section>
    <!-- /.content -->
</div>
<script>
    $(function () {
        $('#change-password-form').validate({
            rules: {
                currentPassword: {
                    required: true
                },
                newPassword: {
                    required: true
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                currentPassword: {
                    required: "Vui lòng nhập mật khẩu hiện tại"
                },
                newPassword: {
                    required: "Vui lòng nhập mật khẩu mới"
                },
                confirmPassword: {
                    required: "Vui lòng lại nhập mật khẩu mới",
                    equalTo: "Mật khẩu mới nhập lại không khớp"
                }
            },
            errorElement: 'span',
            errorClass: 'error invalid-feedback',
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    })
</script>
</body>
</html>
