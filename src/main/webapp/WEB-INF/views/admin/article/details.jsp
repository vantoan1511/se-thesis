<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="${article.title}"/>
<c:if test="${empty article.title}">
    <c:set var="pageTitle" value="Thêm mới bài viết"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Iframe Preview container -->
    <div id="iframe-container" class="hidden">
        <button id="close-button"
                onclick="handlePreviewCloseButtonClick(event)"
                class="btn btn-default"><i class="ri-close-line text-danger"></i> Đóng
        </button>
        <iframe class="preview-frame" src=""></iframe>
    </div>
    <!-- Content Header (Page header) -->
    <%@ include file="../../components/admin/contentHeader.jsp" %>

    <!-- Main content -->
    <section class="content">
        <%--@elvariable id="article" type=""--%>
        <form:form modelAttribute="article" id="form">
            <form:hidden path="id"/>
            <sec:authorize access="hasRole('ADMIN')">
                <%@ include file="../../components/admin/detailsFunctionalities.jsp" %>
            </sec:authorize>
            <sec:authorize access="hasRole('WRITER')">
                <%@ include file="../../components/author/detailsFunctionalities.jsp" %>
            </sec:authorize>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="title">Tiêu đề</label>
                            <form:input path="title" cssClass="form-control form-control-border"
                                        cssErrorClass="is-invalid"
                                        placeholder="VD: Bài viết 1"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="alias">Alias</label>
                            <form:input path="alias" id="alias"
                                        class="form-control form-control-border"
                                        readonly="false"
                                        placeholder="Được khởi tạo tự động dựa trên tiêu đề"/>
                            <p class="text-gray">Alias sẽ được sử dụng làm một phần cho URL bài viết</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card card-outline card-tabs">
                <div class="card-header">
                    <ul class="nav nav-tabs" id="content-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="content-general-tab" data-toggle="pill"
                               href="#general" role="tab" aria-controls="content-tab"
                               aria-selected="true">Thông tin chung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="article-content-tab" data-toggle="pill"
                               href="#article-content" role="tab"
                               aria-controls="content-tab" aria-selected="false">Nội dung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="media-and-links-tab" data-toggle="pill"
                               href="#media-and-links" role="tab"
                               aria-controls="media-and-links" aria-selected="false">Hình ảnh & Đa phương
                                tiện</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="details-tab" data-toggle="pill"
                               href="#details" role="tab"
                               aria-controls="details" aria-selected="false">Chi tiết</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <div class="tab-content" id="tab-content">
                        <div class="tab-pane fade active show" id="general" role="tabpanel"
                             aria-labelledby="content-general-tab">
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <form:label path="stateCode">Trạng thái</form:label>
                                        <form:select path="stateCode"
                                                     cssClass="form-control"
                                                     disabled="true">
                                            <form:options items="${states}"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="categoryCode">Chuyên mục</label>
                                        <form:select path="categoryAlias" id="categoryCode"
                                                     cssClass="wide">
                                            <form:option value="" label="--Chọn chuyên mục--"/>
                                            <form:options items="${categories}"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col">
                                                <label for="featured">Nổi bật</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <form:checkbox path="featured"
                                                               id="featured"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8 col-sm-12">
                                    <div class="form-group">
                                        <label>Mô tả ngắn</label>
                                        <form:textarea id="description"
                                                       path="description"
                                                       cssClass="form-control form-control-border"
                                                       rows="5"
                                                       placeholder="Mô tả bài viết"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="article-content" role="tabpanel"
                             aria-labelledby="article-content-tab">
                            <form:textarea path="text" cssClass="form-control"/>
                        </div>
                        <div class="tab-pane fade" id="media-and-links" role="tabpanel"
                             aria-labelledby="media-and-links-tab">
                            <div class="form-group row">
                                <div class="col-md-2">
                                    <label for="thumbnailUrl">Ảnh bìa</label>
                                </div>
                                <div class="col-md-10 row">
                                    <div class="col-md-auto">
                                        <a id="preview-thumbnail-btn"
                                           class="btn btn-default"> <i class="ri-search-line text-success"></i>Xem trước</a>
                                    </div>
                                    <div class="col-md-10">
                                        <form:input path="thumbnailUrl"
                                                    cssClass="form-control"
                                                    value="${article.thumbnailUrl}"
                                                    placeholder="Dán đường dẫn vào đây"/>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${article.createdBy eq loggedUser.username}">
                                <div class="form-group row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-10">
                                        <div class="gallery row">
                                            <div class="image-options">
                                                <a id="inspect-btn"
                                                   class="dropdown-item btn btn-default btn-sm">
                                                    <i class="ri-search-line text-success"></i> Xem trước
                                                </a>
                                                <a id="set-thumbnail-btn"
                                                   class="dropdown-item btn btn-sm">
                                                    <i class="ri-paint-line text-success"></i> Đặt làm ảnh bìa
                                                </a>
                                                <a id="delete-image-btn"
                                                   class="dropdown-item btn btn-sm">
                                                    <i class="ri-delete-bin-line text-danger"></i> Xóa
                                                </a>
                                            </div>
                                            <c:forEach var="image"
                                                       items="${uploadedImages.content}">
                                                <div class="image-item col-md-auto">
                                                    <img style="object-fit: cover"
                                                         data-image-id="${image.id}"
                                                         src="${image.url}"
                                                         height="100"
                                                         width="100">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-10">
                                        <a id="upload-btn"
                                           class="btn bg-gradient-success"><i class="ri-arrow-up-line"></i> Tải lên</a>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="tab-pane fade" id="details" role="tabpanel"
                             aria-labelledby="details-tab">
                            <div>
                                <h3>Chi tiết</h3>
                                <p>Đã tạo: <fmt:formatDate value="${article.createdAt}"
                                                           pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                <p>Bởi: <c:out value="${article.createdBy}"/></p>
                                <p>Lần sửa đổi cuối: <fmt:formatDate value="${article.lastModifiedAt}"
                                                                     pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                <p>Bởi: <c:out value="${article.lastModifiedBy}"/></p>
                                <p>
                                    Đăng tải: <c:if test="${not empty article.publishedAt}"><fmt:formatDate
                                        value="${article.publishedAt}"
                                        pattern="dd/MM/yyyy HH:mm:ss"/></c:if><c:if
                                        test="${empty article.publishedAt}">Chưa đăng tải</c:if>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </section>
</div>
<script src="<c:url value="/static/ckeditor/ckeditor.js"/>"></script>
<script src="<c:url value="/static/custom/js/details.js"/>"></script>
</body>
</html>