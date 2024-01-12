<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm bài viết</title>
</head>
<body>
<section class="search">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <aside>
                    <h2 class="aside-title">Tìm kiếm</h2>
                    <div class="aside-body">
                        <p>Nhập từ khóa tìm kiếm vào ô bên dưới. </p>
                        <form action="/search/" method="get">
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
                        </form>
                    </div>
                </aside>
                <aside>
                    <h2 class="aside-title">Lọc kết quả</h2>
                    <div class="aside-body">
                        <form class="checkbox-group">
                            <div class="group-title">Ngày đăng tải</div>
                            <div class="form-group">
                                <label><input type="radio" name="date" checked> Bất kỳ</label>
                            </div>
                            <div class="form-group">
                                <label><input type="radio" name="date"> Hôm nay</label>
                            </div>
                            <div class="form-group">
                                <label><input type="radio" name="date"> Tuần vừa rồi</label>
                            </div>
                            <div class="form-group">
                                <label><input type="radio" name="date"> Tháng vừa rồi</label>
                            </div>
                            <br>
                            <div class="group-title">Chuyên mục</div>
                            <div class="form-group">
                                <label><input type="checkbox" name="category" checked> Tất cả</label>
                            </div>
                            <div class="form-group">
                                <label><input type="checkbox" name="category"> Lifestyle</label>
                            </div>
                        </form>
                    </div>
                </aside>
            </div>
            <div class="col-md-9">
                <div class="nav-tabs-group">
                    <ul class="nav-tabs-list">
                        <li class="active"><a href="#">Tất cả</a></li>
                        <li><a href="#">Mới nhất</a></li>
                        <li><a href="#">Cũ nhất</a></li>
                        <li><a href="#">Phổ biến</a></li>
                    </ul>
                    <div class="nav-tabs-right">
                        <select class="form-control">
                            <option>Dòng</option>
                            <option>10</option>
                            <option>20</option>
                            <option>50</option>
                            <option>100</option>
                        </select>
                    </div>
                </div>
                <div class="search-result">
                    Tìm thấy ${model.totalItems} kết quả cho từ khóa "${param.q}"
                </div>
                <div class="row">
                    <c:forEach items="${model.data}" var="article">
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
                                        <time style="margin: 0 .5rem">
                                            <fmt:formatDate value="${article.modifiedDate}"/>
                                        </time>
                                    </div>
                                    <h1><a href="/${article.slug}">${article.title}</a></h1>
                                    <p>${article.description}</p>
                                    <footer>
                                        <a href="#" class="love"><i class="ion-android-favorite-outline"></i>
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
                            <li class="prev"><a href="#"><i class="ion-ios-arrow-left"></i></a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">...</a></li>
                            <li><a href="#">97</a></li>
                            <li class="next"><a href="#"><i class="ion-ios-arrow-right"></i></a></li>
                        </ul>
                        <div class="pagination-help-text">
                            Showing 8 results of 776 &mdash; Page 1
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
