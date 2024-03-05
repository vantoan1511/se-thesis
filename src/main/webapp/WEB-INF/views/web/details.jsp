<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<section class="single">
    <div class="container">
        <div class="row">

            <%@ include file="../components/web/recent.jsp" %>

            <div class="col-md-8">
                <ol class="breadcrumb">
                    <li><a href="#">Trang chủ</a></li>
                    <li class="active">${article.categoryTitle}</li>
                </ol>
                <article class="article main-article">
                    <header>
                        <h1>${article.title}</h1>
                        <ul class="details">
                            <li>Đã đăng lúc <fmt:formatDate value="${article.publishedAt}"
                                                            pattern="HH:mm dd MMM, yyyy"/>
                            </li>
                            <li><a href="/categories/${article.categoryAlias}">${article.categoryTitle}</a></li>
                            <li>Bởi <a href="/users/${article.createdBy}">${article.createdBy}</a></li>
                        </ul>
                    </header>
                    <div class="main">${article.text}</div>
                    <footer>
                        <div class="col"></div>
                        <div class="col">
                            <a class="love"><i class="ion-eye"></i>
                                <div>${article.traffic}</div>
                            </a>
                        </div>
                    </footer>
                </article>

                <%-- <div class="line">
                     <div>Tác giả</div>
                 </div>

                 <%@ include file="../components/web/author.jsp" %>--%>

                <%--<div class="line">
                    <div>Bạn có thể thích</div>
                </div>

                <%@ include file="../components/web/youMayAlsoLike.jsp" %>--%>

                <div class="line thin"></div>

                <%@ include file="../components/web/comments.jsp" %>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value="/static/custom/js/single.js"/>"></script>
</body>
</html>
