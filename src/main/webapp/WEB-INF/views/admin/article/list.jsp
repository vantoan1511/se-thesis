<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Danh sách bài viết</h1>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <sec:authorize access="hasRole('ADMIN')">
            <%@include file="../../components/admin/listFunctionalities.jsp" %>
        </sec:authorize>
        <sec:authorize access="hasRole('WRITER')">
            <%@include file="../../components/author/listFunctionalities.jsp" %>
        </sec:authorize>
        <div class="card">
            <div class="card-header">
                <div class="row">
                    <div class="col-md-2">
                        <div class="row">
                            <select name="sort-by" id="sort-by" class="col form-control custom-select">
                                <option value="title">Tiêu đề</option>
                                <option value="categoryId">Chuyên mục</option>
                                <option value="modifiedDate">Ngày sửa đổi</option>
                                <option value="createdBy">Tác giả</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="row">
                            <select name="sort-order" id="sort-order" class="col form-control custom-select">
                                <option value="ASC">Tăng dần</option>
                                <option value="DESC">Giảm dần</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="row">
                            <select id="limit-select" class="col form-control custom-select">
                                <option value="1">1</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="30">30</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </div>
                    </div>
                </div>
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
                        <th class="text-center">Nổi bật</th>
                        <th>Trạng thái</th>
                        <th>Tiêu đề</th>
                        <th>Truy cập</th>
                        <th>Đã tạo</th>
                        <th>Tác giả</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="article" items="${model.content}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input onchange="handleSingleCheckboxClick('#select-all', '.check-box')"
                                           class="form-check-input check-box"
                                           type="checkbox"
                                           id="${article.id}"
                                           value="${article.id}">
                                    <label for="${article.id}"></label>
                                </div>
                            </td>
                            <td>${article.id}</td>
                            <td class="text-center" style="font-size: 1.5rem">
                                <a href="#">
                                    <c:choose>
                                        <c:when test="${article.featured}">
                                            <i class="ri-star-fill text-yellow"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="ri-star-line"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </td>
                            <td class="status-cell text-center"><c:out value="${article.stateName}"/></td>
                            <td>
                                <a class="text-truncate"
                                   href="/admin/articles/${article.id}">
                                        ${article.title} <i class="ri-edit-box-line"></i>
                                </a>
                                <p style="font-size: 85%">
                                    Alias: ${article.alias}<br>
                                    Category: <a href="#">${article.categoryName}</a>
                                </p>
                            </td>
                            <td>ACCESS_NAME</td>
                            <td>
                                <fmt:formatDate value="${article.createdAt}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td>
                                <c:catch var="error">
                                    <c:out value="${article.createdBy}"/>
                                </c:catch>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.name eq 'admin'}">
                                        <%@ include file="../../components/admin/articleOnListOptions.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <%@ include file="../../components/author/articleOnListOptions.jsp" %>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer clear-fix row">
                <div class="col-auto">
                    <span>Trang ${model.number + 1} - Hiển thị ${model.numberOfElements} trong số ${model.totalElements} mục.</span>
                </div>
                <div class="col-auto">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </section>
    <!---->
    <form id="form-submit" action="<c:url value="/admin/articles"/>" method="get">
        <input type="hidden" name="tab" value="${param.tab}">
        <input type="hidden" value="${model.number+1}" name="page" id="page">
        <input type="hidden" value="${model.size}" name="limit" id="limit">
        <input type="hidden" value="${sortBy}" name="sortBy" id="sortBy">
        <input type="hidden" value="${sortOrder}" name="sortOrder" id="sortOrder">
    </form>
</div>
<script>
    $(document).ready(() => {
        var currentPage = ${model.number+1};
        var totalPages = ${model.totalPages};
        var sortBy = '${sortBy}';
        var sortOrder = '${sortOrder}';
        var limit = ${model.size};
        var totalItems = ${model.totalElements};

        $('#sort-by option').each(function () {
            var value = $(this).attr('value');
            if (sortBy === value) {
                $(this).attr('selected', true);
            }
        });

        $('#sort-order option').each(function () {
            if (sortOrder === $(this).attr('value')) {
                $(this).attr('selected', true);
            }
        });

        $('#sort-by').on('change', function () {
            $('#sortBy').val($(this).val());
            $('#form-submit').submit();
        });

        $('#sort-order').on('change', function () {
            $('#sortOrder').val($(this).val());
            $('#form-submit').submit();

        });

        $('#limit-select option[value="' + limit + '"]').prop('selected', true);

        $('#limit-select').on('change', function () {
            $('#page').val(1);
            $('#limit').val($(this).val());
            $('#form-submit').submit();
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
                        $('#page').val(page);
                        $('#form-submit').submit();
                    }
                }
            });
        }
    });
</script>
</body>
</html>