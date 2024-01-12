<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title><dec:title default="Toan's News - Mang tin tức đến cho mọi nhà"/></title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value="/static/web/scripts/bootstrap/bootstrap.min.css"/>">
    <!-- IonIcons -->
    <link rel="stylesheet" href="<c:url value="/static/web/scripts/ionicons/css/ionicons.min.css"/>">
    <!-- Toast -->
    <link rel="stylesheet" href="<c:url value="/static/web/scripts/toast/jquery.toast.min.css"/>">
    <!-- OwlCarousel -->
    <link rel="stylesheet" href="<c:url value="/static/web/scripts/owlcarousel/dist/assets/owl.carousel.min.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/static/web/scripts/owlcarousel/dist/assets/owl.theme.default.min.css"/>">
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="<c:url value="/static/web/scripts/magnific-popup/dist/magnific-popup.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.min.css" rel="stylesheet">

    <!-- Custom style -->
    <link rel="stylesheet" href="<c:url value="/static/web/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/web/css/skins/all.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/web/css/demo.css"/>">
    <link rel="stylesheet" href="../static/remixicon/remixicon.css">
    <link rel="stylesheet" href="../static/custom/css/styles.css">
    <script src="<c:url value="/static/web/js/jquery.js"/>"></script>

</head>
<body class="skin-orange">

<%@include file="../WEB-INF/views/components/web/header.jsp" %>

<dec:body/>

<%@include file="../WEB-INF/views/components/web/footer.jsp" %>

<!-- Back to Top Button -->
<button id="back-to-top" onclick="scrollToTop()"><i class="ion-android-arrow-up"></i></button>

<!-- JS -->
<script src="<c:url value="/static/web/scripts/bootstrap/bootstrap.min.js"/>"></script>
<script>var $target_end = $(".best-of-the-week");</script>
<script src="<c:url value="/static/web/scripts/jquery-number/jquery.number.min.js"/>"></script>
<script src="<c:url value="/static/web/scripts/owlcarousel/dist/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/static/web/scripts/magnific-popup/dist/jquery.magnific-popup.min.js"/>"></script>
<script src="<c:url value="/static/web/scripts/easescroll/jquery.easeScroll.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.all.min.js"></script>
<script src="<c:url value="/static/web/scripts/toast/jquery.toast.min.js"/>"></script>
<script src="<c:url value="/static/web/js/demo.js"/>"></script>
<script src="<c:url value="/static/web/js/e-magz.js"/>"></script>
<script src="../static/custom/js/utils.js"></script>
<script src="../static/custom/js/main.js"></script>
</body>
</html>
