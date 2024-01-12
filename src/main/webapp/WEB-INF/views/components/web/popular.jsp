<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<aside>
    <h1 class="aside-title">Phổ biến <a href="#" class="all">Tất cả <i class="ion-ios-arrow-right"></i></a></h1>
    <div class="aside-body">
        <c:forEach items="${popular}" var="article">
            <article class="article-mini">
                <div class="inner">
                    <figure>
                        <a href="/${article.slug}">
                            <img src="${article.thumbnailUrl}" alt="${article.title}">
                        </a>
                    </figure>
                    <div class="padding">
                        <h1><a href="/${article.slug}">${article.title}</a>
                        </h1>
                    </div>
                </div>
            </article>
        </c:forEach>
    </div>
</aside>