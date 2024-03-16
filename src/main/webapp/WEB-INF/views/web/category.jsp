<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chuyên mục: ${category.title}</title>
</head>
<body>
<section class="category">
    <div class="container">
        <div class="row">
            <div class="col-md-8 text-left">
                <div class="row">
                    <div class="col-md-12">
                        <ol class="breadcrumb">
                            <li><a href="/home">Trang chủ</a></li>
                            <c:if test="${category.parentAlias != null}">
                                <li><a href="/categories/${category.parentAlias}">${category.parentTitle}</a></li>
                            </c:if>
                            <li class="active">${category.title}</li>
                        </ol>
                        <h1 class="page-title">Chuyên mục: ${category.title}</h1>
                        <c:if test="${category.subCategories != null}">
                            <p class="page-subtitle">
                                <c:forEach var="item" items="${category.subCategories}">
                                    <a href="/categories/${item.alias}">${item.title}</a>
                                </c:forEach>
                            </p>
                        </c:if>
                    </div>
                </div>
                <div class="line"></div>
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
                                        <div class="time"><fmt:formatDate value="${article.publishedAt}"
                                                                          pattern="HH:mm dd/MM/yyyy"/>
                                        </div>
                                    </div>
                                    <h1><a href="/${article.alias}">${article.title}</a>
                                    </h1>
                                    <p>${article.description}</p>
                                    <footer>
                                        <a class="love"><i class="ion-eye"></i>
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
                        <div class="pagination-help-text">
                            <c:if test="${articles.totalElements > 0}">Trang ${articles.number+1}</c:if>
                        </div>
                    </div>

                    <form id="page-request-form" method="get">
                        <input type="hidden" name="page" value="1">
                        <input type="hidden" name="size" value="${articles.size}">
                    </form>
                </div>
            </div>

            <%@ include file="../components/web/recent.jsp" %>
        </div>
    </div>
</section>
<script>
    $(function () {
        let totalPages = ${articles.totalPages};
        let currentPage = ${articles.number+1};

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
                        $('#page-request-form').submit();
                    }
                }
            });
        }
    })
</script>
</body>
</html>
