<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Danh sách chuyên mục bài viết"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <%@ include file="../components/admin/contentHeader.jsp" %>
    <!-- Main content -->
    <section class="content">
        <div class="card">
            <div class="card-body row">
                <div class="col-md-3 col-sm-6">
                    <a href="<c:url value="/admin/categories/new"/>" class="btn btn-block bg-gradient-success">
                        <i class="ri-add-circle-line"></i> Mới
                    </a>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <form id="page-request-form"
                      method="get">
                    <div class="row">
                        <div class="col-md-auto">
                            <select name="by"
                                    id="sort-by"
                                    class="col form-control custom-select">
                                <option value="title">Tiêu đề</option>
                                <option value="createdAt">Ngày tạo</option>
                                <option value="lastModifiedAt">Lần sửa đổi cuối</option>
                                <option value="createdBy">Tác giả</option>
                            </select>
                        </div>
                        <div class="col-md-auto">
                            <select name="order"
                                    id="sort-order"
                                    class="col form-control custom-select">
                                <option value="ASC">Tăng dần</option>
                                <option value="DESC">Giảm dần</option>
                            </select>
                        </div>
                        <div class="col-md-auto">
                            <select id="limit-select"
                                    name="size"
                                    class="col form-control custom-select">
                                <option value="2">2</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="page" value="1">
                    <%--<input type="hidden" name="size" value="${pagedCategories.size}">--%>
                </form>
            </div>
            <div class="card-body p-0">
                <table id="myTable" class="table table-bordered table-hover projects">
                    <thead>
                    <tr>
                        <th>
                            <div class="form-check icheck-material-red">
                                <input onchange="handleSelectAllCheckboxClick(this, '.check-box')"
                                       id="select-all"
                                       class="form-check-input check-all"
                                       type="checkbox">
                                <label for="select-all"></label>
                            </div>
                        </th>
                        <th>Id</th>
                        <th>Tiêu đề</th>
                        <th>Đã tạo</th>
                        <th>Tác giả</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="category" items="${pagedCategories.content}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input onchange="handleSingleCheckboxClick('#select-all', '.check-box')"
                                           class="form-check-input check-box"
                                           type="checkbox"
                                           id="${category.id}"
                                           value="${category.id}">
                                    <label for="${category.id}"></label>
                                </div>
                            </td>
                            <td>${category.id}</td>
                            <td>
                                <a class="text-truncate"
                                   href="/admin/categories/${category.alias}">
                                        ${category.title} <i class="ri-edit-box-line"></i>
                                </a>
                                <p style="font-size: 85%">
                                    Alias: <a target="_blank"
                                              href="<c:url value="/categories/${category.alias}"/>">${category.alias}</a><br>
                                    Parent: ${category.parentTitle}
                                </p>
                            </td>
                            <td>
                                <fmt:formatDate value="${category.createdAt}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td><c:out value="${category.createdBy}"/></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown"
                                            aria-expanded="false"></button>
                                    <div class="dropdown-menu dropdown-menu-right" role="menu" style>
                                        <c:if test="${category.createdBy eq pageContext.request.userPrincipal.name}">
                                            <button onclick="handleCategoryDeleteButton(event, ${category.id})"
                                                    class="dropdown-item btn btn-default btn-sm">
                                                <i class="ri-delete-bin-line text-danger"></i> Xóa
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${pagedCategories.totalElements < 1}">
                        <tr>
                            <td colspan="8" class="text-center">Trống</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="card-footer clear-fix row">
                <div class="col-auto">
                    <span>Trang ${pagedCategories.number + 1} - Hiển thị ${pagedCategories.numberOfElements} trong số ${pagedCategories.totalElements} mục.</span>
                </div>
                <div class="col-auto">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </section>
    <!---->
</div>
<script src="<c:url value="/static/custom/js/category/main.js"/>"></script>
<script>
    $(function () {
        const sortBy = `${sortBy}`;
        const sortOrder = `${sortOrder}`;
        const size = `${pagedCategories.size}`;
        const $sortBySelect = $('#sort-by');
        const $sortOrderSelect = $('#sort-order');
        const $sizeSelect = $('#limit-select');
        const $pageRequest = $('#page-request-form');
        const $curPage = $('input[name=page]');
        const $curSize = $('input[name=size]');

        const totalItems = ${pagedCategories.totalElements};
        const currentPage = ${pagedCategories.number+1};
        const totalPages = ${pagedCategories.totalPages};

        $sortBySelect.find('option').each(function () {
            if (sortBy === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sortOrderSelect.find('option').each(function () {
            if (sortOrder === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sizeSelect.find('option').each(function () {
            if (size === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sortBySelect.change(function () {
            $pageRequest.submit();
        })

        $sortOrderSelect.change(function () {
            $pageRequest.submit();
        })

        $sizeSelect.change(function () {
            $pageRequest.submit();
        })

        if (totalItems > 0) {
            $('#pagination').twbsPagination({
                startPage: currentPage,
                totalPages: totalPages,
                visiblePages: 7,
                first: 'Đầu',
                last: 'Cuối',
                next: 'Tiếp',
                prev: 'Lùi',
                onPageClick: (event, page) => {
                    if (currentPage !== page) {
                        $curPage.val(page);
                        $pageRequest.submit();
                    }
                }
            });
        }
    })
</script>
</body>
</html>