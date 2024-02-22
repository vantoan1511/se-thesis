<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Kho lưu trữ"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
<div class="content-wrapper">
    <%@ include file="../../components/admin/contentHeader.jsp" %>

    <section class="content">
        <!-- Functionality bar -->
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <button onclick="handleUploadButtonClick(event)"
                                class="btn btn-block bg-gradient-success"><i class="ri-upload-line"></i> Tải lên
                        </button>
                    </div>
                    <div class="col-md-auto">
                        <button onclick="handleImageTrashButtonClick(event, $('.item-select:checked'))"
                                class="btn btn-block btn-default"><i class="ri-delete-bin-line text-danger"></i> Xóa
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Tải lên</h3>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <c:if test="${files.numberOfElements lt 1}">
                        <i>Trống</i>
                    </c:if>
                    <c:forEach var="image" items="${files.content}">
                        <div class="col-md-3 col-sm-6">
                            <div class="card my-2">
                                <div class="card-body">
                                    <img style=";height: 250px; object-fit: cover"
                                         class="img-fluid"
                                         src="<c:url value="${image.url}"/>" alt="${image.alias}">
                                </div>
                                <div class="card-footer">
                                    <div class="text-right">
                                        <a class="btn btn-sm bg-gradient-success" title="Click để xem chi tiết"
                                           href="/admin/my-storage/${image.alias}">
                                            <i class="ri-edit-box-line"></i> Chi tiết
                                        </a>
                                        <a onclick="handleCopyToClipboard(event, '${image.url}')"
                                           title="Click để copy url"
                                           class="btn btn-sm btn-default"
                                           href="#"><i class="ri-clipboard-line"></i> Copy Url
                                        </a>
                                        <div class="btn-group">
                                            <button type="button"
                                                    class="btn btn-default dropdown-toggle dropdown-icon"
                                                    data-toggle="dropdown"
                                                    aria-expanded="false">
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right"
                                                 role="menu">
                                                <button onclick="handleImageInspect(event, `${image.url}`)"
                                                        class="dropdown-item btn btn-default btn-sm">
                                                    <i class="ri-search-line text-success"></i> Xem trước
                                                </button>
                                                <button onclick="handleImageDeleteButton(event, `${image.id}`)"
                                                        class="dropdown-item btn btn-default btn-sm">
                                                    <i class="ri-delete-bin-line text-danger"></i> Xóa
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="card-footer">
                Hiển thị ${files.numberOfElements} mục trong tổng số ${files.totalElements} mục
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Hệ thống</h3>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <div class="col-md-3 col-sm-6">
                        <img class="img-thumbnail img-fluid my-2"
                             src="<c:url value="/static/public/images/avatar.png"/>" alt="default-avatar">
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <img class="img-thumbnail img-fluid my-2"
                             src="<c:url value="/static/public/images/avatar2.png"/>" alt="default-avatar2">
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <img class="img-thumbnail img-fluid my-2"
                             src="<c:url value="/static/public/images/avatar3.png"/>" alt="default-avatar3">
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <img class="img-thumbnail img-fluid my-2"
                             src="<c:url value="/static/public/images/avatar4.png"/>" alt="default-avatar4">
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <img class="img-thumbnail img-fluid my-2"
                             src="<c:url value="/static/public/images/avatar5.png"/>" alt="default-avatar5">
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<script src="<c:url value="/static/custom/js/storage/main.js"/>"></script>
</body>
</html>
