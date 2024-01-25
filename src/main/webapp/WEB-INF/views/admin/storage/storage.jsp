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
            <div class="card-body">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>
                            <div class="form-check icheck-material-red">
                                <input onclick="handleSelectAllCheckboxClick(this, '.item-select')"
                                       type="checkbox"
                                       id="select-all">
                                <label for="select-all"></label>
                            </div>
                        </th>
                        <th>Id</th>
                        <th>Tiêu đề</th>
                        <th>Ngày tạo</th>
                        <th>Chủ sở hữu</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="file" items="${files.content}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input onclick="handleSingleCheckboxClick('#select-all', '.item-select')"
                                           class="item-select"
                                           type="checkbox"
                                           id="${file.id}"
                                           value="${file.id}">
                                    <label for="${file.id}"></label>
                                </div>
                            </td>
                            <td>${file.id}</td>
                            <td>
                                <a title="Click để xem"
                                   href="/admin/my-storage/${file.alias}">${file.title} <i class="ri-edit-box-line"></i>
                                </a>
                                <p style="font-size: 85%">
                                    URL: <a onclick="handleCopyToClipboard(event, '${file.url}')"
                                            title="Click để copy url"
                                            href="#">${file.url} <i class="ri-clipboard-line"></i>
                                </a>
                                </p>
                            </td>
                            <td>
                                <fmt:formatDate value="${file.createdAt}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td>
                                <c:out value="${file.createdBy}"/>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown"
                                            aria-expanded="false"></button>
                                    <div class="dropdown-menu dropdown-menu-right" role="menu" style>
                                        <button onclick="handleImageInspect(event, `${file.url}`)"
                                                class="dropdown-item btn btn-default btn-sm">
                                            <i class="ri-search-line text-success"></i> Xem
                                        </button>
                                        <button onclick="handleImageDeleteButton(event, `${file.id}`)"
                                                class="dropdown-item btn btn-default btn-sm">
                                            <i class="ri-delete-bin-line text-danger"></i> Xóa
                                        </button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${files.numberOfElements lt 1}">
                        <tr>
                            <td class="text-center" colspan="8">(Trống)</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="card-footer"></div>
        </div>
    </section>
</div>
<script src="<c:url value="/static/custom/js/storage/main.js"/>"></script>
</body>
</html>
