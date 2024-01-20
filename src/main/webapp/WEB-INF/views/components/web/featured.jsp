<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<div class="owl-carousel owl-theme slide" id="featured">
    <c:forEach items="${featured}" var="article">
        <div class="item">
            <article class="featured">
                <div class="overlay"></div>
                <figure>
                    <img src="${article.thumbnailUrl}" alt="${article.title}">
                </figure>
                <div class="details">
                    <div class="category">
                        <a href="/categories/${article.categoryCode}">${article.categoryName}</a>
                    </div>
                    <h1>
                        <a href="/${article.alias}">
                                ${article.title}
                        </a>
                    </h1>
                    <div class="time"><fmt:formatDate value="${article.publishedAt}" pattern="HH:mm dd/MM/yyyy"/></div>
                </div>
            </article>
        </div>
    </c:forEach>
</div>
