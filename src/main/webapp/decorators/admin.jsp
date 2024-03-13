<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><dec:title default="Trang chủ"/></title>
    <link rel="shortcut icon" href="/static/public/favicon.ico" type="image/x-icon">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="<c:url value="/static/admin/plugins/fontawesome-free/css/all.min.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <%--Remix Icon--%>
    <link rel="stylesheet" href="<c:url value="/static/remixicon/remixicon.css"/>">
    <!-- iCheck -->
    <link rel="stylesheet"
          href="<c:url value="/static/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/icheck-material@1.0.1/icheck-material.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/static/admin/dist/css/adminlte.min.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.min.css" rel="stylesheet">
    <%--Select2--%>
    <link rel="stylesheet" href="<c:url value="/static/admin/plugins/select2/css/select2.min.css"/>">
    <%--Custom style--%>
    <link rel="stylesheet" href="<c:url value="/static/custom/css/styles.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/niceselect/css/nice-select2.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/static/admin/plugins/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css"/>">
    <!-- Jquery -->
    <script src="<c:url value="/static/admin/plugins/jquery/jquery.min.js"/>"></script>

</head>
<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">

    <!-- Navbar -->
    <%@ include file="/WEB-INF/views/components/admin/navbar.jsp" %>

    <!-- Main Sidebar Container -->
    <%@ include file="/WEB-INF/views/components/admin/sidebar.jsp" %>

    <dec:body/>

    <!-- Footer -->
    <%@ include file="/WEB-INF/views/components/admin/footer.jsp" %>

</div>
<!-- pagination -->
<script
        src="<c:url value="/static/twbspagination/jquery.twbsPagination.min.js"/>"></script>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap 4 -->
<script
        src="<c:url value="/static/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/static/admin/dist/js/adminlte.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.all.min.js"></script>
<!-- Validation -->
<script src="<c:url value="/static/admin/plugins/jquery-validation/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/static/admin/plugins/jquery-validation/additional-methods.min.js"/>"></script>
<!-- Custom -->
<script src="<c:url value="/static/admin/plugins/bootstrap-switch/js/bootstrap-switch.min.js"/>"></script>
<script src="<c:url value="/static/custom/js/utils.js"/>"></script>
<script src="<c:url value="/static/custom/js/main.js"/>"></script>
<script src="<c:url value="/static/niceselect/js/nice-select2.js"/>"></script>
</body>
</html>