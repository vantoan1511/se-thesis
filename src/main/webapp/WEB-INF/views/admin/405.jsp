<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lỗi hệ thống</title>
</head>
<body>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Lỗi 500</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Lỗi hệ thống</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
            <h2 class="headline text-danger">500</h2>

            <div class="error-content">
                <h3><i class="fas fa-exclamation-triangle text-danger"></i> Đã xảy ra sự cố ngoài ý muốn!</h3>

                <p>
                    Có gì đó không đúng đã xảy ra, chúng tôi đang cố gắng sửa chúng nhanh nhất có thể
                    Bạn có thể <a href="/admin/home">về trang chủ</a> hoặc thử lại
                </p>

                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="query" class="form-control" placeholder="Search">

                        <div class="input-group-append">
                            <button type="submit" name="submit" class="btn btn-danger"><i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.input-group -->
                </form>
            </div>
        </div>
        <!-- /.error-page -->

    </section>
    <!-- /.content -->
</div>
</body>
</html>
