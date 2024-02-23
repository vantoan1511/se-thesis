<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Tìm kiếm bài viết"/>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<section class="search">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <form id="search-form" method="get">
                    <aside>
                        <h2 class="aside-title">Tìm kiếm</h2>
                        <div class="aside-body">
                            <p>Nhập từ khóa tìm kiếm vào ô bên dưới. </p>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text"
                                           name="q"
                                           class="form-control"
                                           value="${param.q}"
                                           placeholder="Nhập từ khóa tìm kiếm ...">
                                    <div class="input-group-btn">
                                        <button class="btn btn-primary">
                                            <i class="ion-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </aside>
                    <aside>
                        <h2 class="aside-title">Filter</h2>
                        <div class="aside-body">
                            <div class="checkbox-group">
                                <div class="group-title">Chuyên mục</div>
                                <div class="form-group icheck-material-red">
                                    <input id="select-all"
                                           onclick="handleSelectAllCheckboxClick(this, '.check-box')"
                                           type="checkbox">
                                    <label for="select-all">Tất cả</label>
                                </div>
                                <c:forEach var="category" items="${categories}">
                                    <div class="form-group icheck-material-red">
                                        <input id="${category.id}"
                                               type="checkbox"
                                               onclick="handleSingleCheckboxClick('#select-all', '.check-box')"
                                               class="check-box"
                                               name="categoryId"
                                               <c:if test="${categoryIds.contains(category.id)}">checked</c:if>
                                               value="${category.id}">
                                        <label for="${category.id}">${category.title}</label>
                                    </div>
                                </c:forEach>
                                <div class="group-title">Ngày đăng tải</div>
                                <div class="form-group icheck-material-red">
                                    <input id="anytime"
                                           type="radio"
                                           name="date_format"
                                           value="anytime" checked>
                                    <label for="anytime">Tất cả</label>
                                </div>
                                <div class="form-group icheck-material-red">
                                    <input id="day"
                                           type="radio"
                                           name="date_format"
                                           value="day">
                                    <label for="day">1 ngày qua</label>
                                </div>
                                <div class="form-group icheck-material-red">
                                    <input id="week"
                                           type="radio"
                                           name="date_format"
                                           value="week">
                                    <label for="week">1 Tuần qua</label>
                                </div>
                                <div class="form-group icheck-material-red">
                                    <input id="month"
                                           type="radio"
                                           name="date_format"
                                           value="month">
                                    <label for="month">1 Tháng qua</label>
                                </div>

                            </div>
                        </div>
                    </aside>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="size" value="${articles.size}">
                </form>
            </div>
            <div class="col-md-9">
                <div class="nav-tabs-group">
                    <ul class="nav-tabs-list">
                        <li class="active"><a href="#">Tất cả</a></li>
                    </ul>
                    <div class="nav-tabs-right">
                        <select id="limit-select"
                                class="form-control">
                            <option>Dòng</option>
                            <option>2</option>
                            <option>5</option>
                            <option>10</option>
                            <option>20</option>
                            <option>50</option>
                            <option>100</option>
                        </select>
                    </div>
                </div>
                <div class="search-result">
                    Tìm thấy ${articles.totalElements} kết quả cho từ khóa "${param.q}"
                </div>
                <div class="row">
                    <c:forEach items="${articles.content}" var="article">
                        <article class="col-md-12 article-list">
                            <div class="inner">
                                <figure>
                                    <a href="/${article.alias}">
                                        <img src="${article.thumbnailUrl}">
                                    </a>
                                </figure>
                                <div class="details">
                                    <div class="detail">
                                        <div class="category">
                                            <a href="/categories/${article.categoryAlias}">${article.categoryTitle}</a>
                                        </div>
                                        <time style="margin: 0 .5rem">
                                            <fmt:formatDate value="${article.publishedAt}"/>
                                        </time>
                                    </div>
                                    <h1><a href="/${article.alias}">${article.title}</a></h1>
                                    <p>${article.description}</p>
                                    <footer>
                                        <a href="#" class="love"><i class="ion-android-favorite-outline"></i>
                                            <div>${article.traffic}</div>
                                        </a>
                                        <a class="btn btn-primary more" href="/${article.alias}">
                                            <div>Đọc tiếp</div>
                                            <div><i class="ion-ios-arrow-thin-right"></i></div>
                                        </a>
                                    </footer>
                                </div>
                            </div>
                        </article>
                    </c:forEach>
                    <div class="col-md-12 text-center">
                        <ul class="pagination"></ul>
                        <c:if test="${articles.totalPages > 0}">
                            <div class="pagination-help-text">
                                Trang ${articles.number+1}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(function () {
        let dateFormat = `${date_format}`;
        let currentPage = ${articles.number+1};
        let totalPages = ${articles.totalPages};
        let $searchForm = $('#search-form');

        $('input[name=date_format]').map((i, e) => {
            if (e.value === dateFormat) {
                e.checked = true;
            }
        });
        if ($('.check-box').length === $('.check-box[checked]').length) {
            $('#select-all').prop('checked', true);
        }

        $('#limit-select').change(function (e) {
            $('input[name=size]').val($(this).val());
            $searchForm.submit();
        })
        if (totalPages > 0) {
            $('.pagination').twbsPagination({
                startPage: currentPage,
                totalPages: totalPages,
                visiblePages: 7,
                first: 'Đầu',
                last: 'Cuối',
                next: 'Tiếp',
                prev: 'Lùi',
                onPageClick: (event, page) => {
                    if (currentPage !== page) {
                        $('input[name=page]').val(page);
                        $searchForm.submit();
                    }
                }
            });
        }
    })
</script>
</body>
</html>
