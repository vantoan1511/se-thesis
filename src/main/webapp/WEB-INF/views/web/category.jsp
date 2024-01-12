<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chuyên mục: ${category.name}</title>
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
                            <c:if test="${category.parentCode != null}">
                                <li><a href="/categories/${category.parentCode}">${category.parentName}</a></li>
                            </c:if>
                            <li class="active">${category.name}</li>
                        </ol>
                        <h1 class="page-title">Chuyên mục: ${category.name}</h1>
                        <c:if test="${category.subCategories != null}">
                            <p class="page-subtitle">
                                <c:forEach var="item" items="${category.subCategories}">
                                    <a href="/categories/${item.code}">${item.name}</a>
                                </c:forEach>
                            </p>
                        </c:if>
                    </div>
                </div>
                <div class="line"></div>
                <div class="row">
                    <c:forEach items="${articles}" var="article">
                        <article class="col-md-12 article-list">
                            <div class="inner">
                                <figure>
                                    <a href="/${article.slug}">
                                        <img src="${article.thumbnailUrl}">
                                    </a>
                                </figure>
                                <div class="details">
                                    <div class="detail">
                                        <div class="category">
                                            <a href="/categories/${article.categoryCode}">${article.categoryName}</a>
                                        </div>
                                        <div class="time"><fmt:formatDate value="${article.publishedDate}"
                                                                          pattern="HH:mm dd/MM/yyyy"/>
                                        </div>
                                    </div>
                                    <h1><a href="/${article.slug}">${article.title}</a>
                                    </h1>
                                    <p>${article.description}</p>
                                    <footer>
                                        <a href="#" class="love""><i class="ion-ios-eye-outline"></i>
                                        <div>${article.traffic}</div>
                                        </a>
                                        <a class="btn btn-primary more" href="/${article.slug}">
                                            <div>Đọc tiếp</div>
                                            <div><i class="ion-ios-arrow-thin-right"></i></div>
                                        </a>
                                    </footer>
                                </div>
                            </div>
                        </article>
                    </c:forEach>

                    <div class="col-md-12 text-center">
                        <ul class="pagination">
                        </ul>
                        <div class="pagination-help-text">
                            Showing 8 results of 776 &mdash; Page 1
                        </div>
                    </div>

                    <form action="/categories/${category.code}">
                        <input id="page" type="hidden" name="page" value="page">
                    </form>
                </div>
            </div>

            <%@ include file="../components/web/recent.jsp" %>
        </div>
    </div>
</section>
</body>
</html>
