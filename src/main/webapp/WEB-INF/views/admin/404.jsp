<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Lỗi 404"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <%@ include file="../components/admin/contentHeader.jsp" %>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
            <h2 class="headline text-warning"> 404</h2>

            <div class="error-content">
                <h3><i class="fas fa-exclamation-triangle text-warning"></i> Không tìm thấy</h3>

                <p>
                    Chúng tôi đã vô cùng nỗ lực trong việc tìm kiếm tài nguyên bạn yêu cầu, nhưng rất tiếc...
                    Bạn có thể <a href="/admin/home">về trang chủ</a> hoặc thử lại
                </p>

                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="query" class="form-control" placeholder="Search">

                        <div class="input-group-append">
                            <button type="submit" name="submit" class="btn btn-warning"><i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.input-group -->
                </form>
            </div>
            <!-- /.error-content -->
        </div>
        <!-- /.error-page -->
    </section>
    <!-- /.content -->
</div>
</body>
</html>
