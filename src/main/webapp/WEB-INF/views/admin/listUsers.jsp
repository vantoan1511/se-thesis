<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Danh sách người dùng"/>
<c:set var="breadcrumbs" value="${['Trang chủ', 'Danh sách người dùng']}"/>

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
            <div class="card-header">
                <c:if test="${not empty message}">
                    <div class="row">
                        <%@ include file="../components/admin/alert.jsp" %>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-md-auto">
                        <select name="sort-by" id="sort-by" class="col form-control custom-select">
                            <option value="lastName">Tên</option>
                            <option value="firstName">Họ</option>
                            <option value="createdAt">Ngày tham gia</option>
                        </select>
                    </div>
                    <div class="col-md-auto">
                        <select name="sort-order" id="sort-order" class="col form-control custom-select">
                            <option value="ASC">Tăng dần</option>
                            <option value="DESC">Giảm dần</option>
                        </select>
                    </div>
                    <div class="col-md-auto">
                        <select id="limit-select" class="col form-control custom-select">
                            <option value="2">2</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>
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
                        <th>Trạng thái</th>
                        <th>Tên</th>
                        <th>Đã tham gia</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="user" items="${userPage.content}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input onchange="handleSingleCheckboxClick('#select-all', '.check-box')"
                                           class="form-check-input check-box"
                                           type="checkbox"
                                           id="${user.id}"
                                           value="${user.id}">
                                    <label for="${user.id}"></label>
                                </div>
                            </td>
                            <td>${user.id}</td>

                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${user.enabled}">
                                        <b><i class="ri-circle-fill text-success"></i></b>
                                    </c:when>
                                    <c:otherwise>
                                        <b><i class="ri-circle-fill text-danger"></i></b>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td class="post">
                                <div class="user-block">
                                    <img class="avatar img-circle img-bordered-sm"
                                         src="${user.avatarUrl}"
                                         alt="${user.username}-avatar">
                                    <span class="username">
                                        <a href="/admin/users/${user.username}">${user.fullName()}</a>
                                    </span>
                                    <span class="description">
                                        #${user.username} <br>
                                        Vai trò:
                                        <c:forEach var="role" items="${user.authorities}" varStatus="index">
                                            ${role.description}<c:if test="${not index.last}">, </c:if>
                                        </c:forEach>
                                    </span>
                                </div>
                            </td>
                            <td><fmt:formatDate value="${user.createdAt}" pattern="dd MMM yyyy"/></td>
                            <td>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <%@ include file="../components/admin/articleOnListOptions.jsp" %>
                                </sec:authorize>
                                <sec:authorize access="hasRole('WRITER')">
                                    <%@ include file="../components/author/articleOnListOptions.jsp" %>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${userPage.numberOfElements lt 1}">
                        <tr>
                            <td colspan="8" class="text-center">Trống</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="card-footer clear-fix row">
                <div class="col-auto">
                    <span>Trang ${userPage.number + 1} - Hiển thị ${userPage.numberOfElements} trong số ${userPage.totalElements} mục.</span>
                </div>
                <div class="col-auto">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </section>
    <!---->
    <form id="form-submit" action="<c:url value="/admin/users"/>" method="get">
        <input type="hidden" name="tab" value="${param.tab}">
        <input type="hidden" value="${userPage.number+1}" name="page" id="page">
        <input type="hidden" value="${userPage.size}" name="limit" id="limit">
        <input type="hidden" value="${sortBy}" name="by" id="sortBy">
        <input type="hidden" value="${sortOrder}" name="order" id="sortOrder">
    </form>
</div>
<script>
    $(function () {
        paginationFunc();
        avatarFallbackFunc();
    });

    function avatarFallbackFunc() {
        const $avatar = $('.avatar');
        $avatar.each(function () {
            if ($(this).attr('src') === '') {
                $(this).attr('src', '/static/public/images/avatar.png');
            } else {
                $(this).on('error', function () {
                    $(this).attr('src', '/static/public/images/avatar.png');
                })
            }
        })
    }

    function paginationFunc() {
        let currentPage = ${userPage.number+1};
        let totalPages = ${userPage.totalPages};
        let sortBy = '${sortBy}';
        let sortOrder = '${sortOrder}';
        let limit = ${userPage.size};
        let totalItems = ${userPage.totalElements};

        $('#sort-by option').each(function () {
            let value = $(this).attr('value');
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
    }
</script>
</body>
</html>
