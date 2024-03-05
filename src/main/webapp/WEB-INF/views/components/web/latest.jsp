<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="row">
    <div class="col-md-6">
        <c:forEach items="${latest.content}" var="article" begin="0" step="2">
            <article class="article">
                <div class="inner">
                    <figure>
                        <a href="/${article.alias}">
                            <img src="${article.thumbnailUrl}" alt="${article.title}">
                        </a>
                    </figure>
                    <div class="padding">
                        <div class="detail">
                            <div class="time"><fmt:formatDate value="${article.publishedAt}"/></div>
                            <div class="category">
                                <a href="/categories/${article.categoryAlias}">${article.categoryTitle}</a>
                            </div>
                        </div>
                        <h2>
                            <a href="/${article.alias}">${article.title}</a>
                        </h2>
                        <p>
                                ${article.description}
                        </p>
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
    </div>
    <div class="col-md-6">
        <c:forEach items="${latest.content}" var="article" begin="1" step="2">
            <article class="article">
                <div class="inner">
                    <figure>
                        <a href="/${article.alias}">
                            <img src="${article.thumbnailUrl}" alt="${article.title}">
                        </a>
                    </figure>
                    <div class="padding">
                        <div class="detail">
                            <div class="time"><fmt:formatDate value="${article.publishedAt}"/></div>
                            <div class="category">
                                <a href="/categories/${article.categoryAlias}">${article.categoryTitle}</a>
                            </div>
                        </div>
                        <h2>
                            <a href="/${article.alias}">${article.title}</a>
                        </h2>
                        <p>
                                ${article.description}
                        </p>
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
    </div>
</div>