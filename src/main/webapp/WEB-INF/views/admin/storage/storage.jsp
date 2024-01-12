<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kho lưu trữ</title>
</head>
<body>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <h1>Kho lưu trữ</h1>
            </div>
        </div>
    </section>

    <section class="content">
        <!-- Functionality bar -->
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-auto">
                        <button class="btn btn-block bg-gradient-success"
                                onclick="handleUploadButtonClick(event)"><i class="ri-upload-line"></i> Tải lên
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
                        <th>URL</th>
                        <th>Tải lên bởi</th>
                        <th>Ngày tạo</th>
                        <th>Lần sửa cuối</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${images.size() gt 0}">
                            <c:forEach var="image" items="${images}">
                                <tr>
                                    <td>
                                        <div class="form-check icheck-material-red">
                                            <input onclick="handleSingleCheckboxClick('#select-all', '.item-select')"
                                                   class="item-select"
                                                   type="checkbox"
                                                   id="${image.id}"
                                                   value="${image.id}">
                                            <label for="${image.id}"></label>
                                        </div>
                                    </td>
                                    <td>${image.id}</td>
                                    <td>
                                        <a onclick="handleImageInspect(event, '${image.url}')"
                                           title="Click để xem"
                                           href="#">${image.title} <i class="ri-eye-line"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a onclick="handleCopyToClipboard(event, '${image.url}')"
                                           title="Click để copy url"
                                           href="#">${image.url} <i class="ri-clipboard-line"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${image.createdBy eq pageContext.request.userPrincipal.name}">
                                                Tôi
                                            </c:when>
                                            <c:otherwise>${image.createdBy}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${image.createdDate}" pattern="HH:mm dd/MM/yyyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${image.modifiedDate}" pattern="HH:mm dd/MM/yyyy"/>
                                    </td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="text-center" colspan="8">(Trống)</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
</body>
</html>
