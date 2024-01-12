<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><dec:title default="Đăng nhập"/></title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/static/admin/plugins/fontawesome-free/css/all.min.css"/>">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="<c:url value="/static/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/static/admin/dist/css/adminlte.min.css"/>">
    <!-- Remix Icon -->
    <link rel="stylesheet" href="<c:url value="/static/remixicon/remixicon.css"/>">

    <link rel="stylesheet" href="<c:url value="/static/custom/css/styles.css"/>">
</head>
<body class="hold-transition login-page">

<dec:body/>

<!-- jQuery -->
<script src="<c:url value="/static/admin/plugins/jquery/jquery.min.js"/>"></script>
<!-- Bootstrap 4 -->
<script src="<c:url value="/static/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/static/admin/dist/js/adminlte.min.js"/>"></script>
</body>
</html>
