<%@ page import="com.newswebsite.main.security.SecurityUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="loggedUsername" value="<%=SecurityUtil.username()%>"/>

<!DOCTYPE html>
<html>
<body>
<section class="page first">
    <div class="container">
        <div class="page-description">
            <div class="row">
                <div class="col-md-4">
                    <div class="featured-author">
                        <div class="featured-author-inner">
                            <div class="featured-author-cover">
                                <div class="featured-author-center">
                                    <figure class="featured-author-picture">
                                        <img class="avatar"
                                             src="${profile.avatarUrl}"
                                             onerror="this.src='/static/public/images/default-avatar.png'"
                                             alt="${profile.username}-avatar"
                                             width="80"
                                             height="80">
                                    </figure>
                                    <div class="featured-author-info">
                                        <h2 class="name">${profile.getFullName()}</h2>
                                        <div class="">@${profile.username}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="featured-author-body">
                                <div class="featured-author-count">
                                    <div class="item">
                                        <a>
                                            <div class="name">Tham gia</div>
                                            <div class="value">
                                                <fmt:formatDate value="${profile.createdAt}"
                                                                pattern="MMM yyyy"/>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="item">
                                        <a>
                                            <div class="name">Bình luận</div>
                                            <div class="value">${recentReviews.totalElements}</div>
                                        </a>
                                    </div>
                                    <sec:authorize access="hasRole('WRITER')">
                                        <div class="item">
                                            <a>
                                                <div class="name">Bài viết</div>
                                                <div class="value">0</div>
                                            </a>
                                        </div>
                                    </sec:authorize>
                                </div>
                                <div class="featured-author-quote">

                                </div>
                                <div class="block">
                                    <h2 class="block-title">Hình ảnh</h2>
                                    <div class="block-body">
                                        <ul class="item-list-round" data-magnific="gallery">
                                            <c:forEach var="image"
                                                       items="${uploadedImages.content}">
                                                <li>
                                                    <a href="${image.url}"
                                                       style="background-image: url('${image.url}');">
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <br>
                                <div class="block">
                                    <h2 class="block-title">Bình luận gần đây</h2>
                                    <div class="block-body">
                                        <c:forEach var="review" items="${recentReviews.content}">
                                            <p><a href="/${review.articleAlias}">"${review.text}"</a></p>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="featured-author-footer">
                                    <c:if test="${profile.username eq loggedUsername}">
                                        <a id="show-all-reviews-btn"
                                           href="/users/${loggedUsername}/reviews">Tất cả bình luận</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 profile-details-tab">
                    <div class="card">
                        <div class="card-header">
                            <h5>Thông tin chung</h5>
                        </div>
                        <div class="card-body">
                            <%--@elvariable id="profile" type=""--%>
                            <form:form id="user-profile-form"
                                       modelAttribute="profile"
                                       method="post"
                                       acceptCharset="UTF-8">

                                <c:if test="${not empty message}">
                                    <div class="row">
                                        <%@ include file="../components/web/alert.jsp" %>
                                    </div>
                                </c:if>

                                <div class="row mb-3">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Họ</h6>
                                    </div>
                                    <div class="col-sm-9 form-group floating text-secondary">
                                        <form:input path="firstName"
                                                    cssClass="form-control"
                                                    cssErrorClass="error"/>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Tên</h6>
                                    </div>
                                    <div class="col-sm-9 form-group floating text-secondary">
                                        <form:input path="lastName"
                                                    cssClass="form-control"
                                                    cssErrorClass="error"/>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Email</h6>
                                    </div>
                                    <div class="col-sm-9 form-group floating text-secondary">
                                        <form:input path="email"
                                                    cssClass="form-control"
                                                    cssErrorClass="error"/>
                                    </div>
                                </div>
                                <c:if test="${loggedUsername eq profile.username}">
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Mật khẩu</h6>
                                        </div>
                                        <div class="col-sm-9 form-group floating text-secondary">
                                            <p>
                                                <i>Đã ẩn </i>
                                                <a href="<c:url value="/change-password"/>">
                                                    <i class="ion-edit"></i> Đổi mật khẩu
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Ảnh đại diện</h6>
                                            <p style="font-size: 85%"><i>Chọn ảnh có sẵn trong thư viện
                                                hoặc tải lên</i></p>
                                        </div>
                                        <div class="col-sm-9 form-group floating text-secondary">
                                            <form:hidden path="avatarUrl"/>
                                            <div class="block">
                                                <ul class="item-list-round gallery">
                                                    <div class="image-options">
                                                        <button id="inspect-btn"
                                                                class="btn btn-sm btn-primary">Xem
                                                        </button>
                                                        <button id="set-avatar-btn"
                                                                class="btn btn-sm btn-primary">Đặt làm
                                                            avatar
                                                        </button>
                                                        <button id="delete-image-btn"
                                                                class="btn btn-sm btn-primary">Xóa
                                                        </button>
                                                    </div>
                                                    <c:forEach var="image"
                                                               items="${uploadedImages.content}">
                                                        <li>
                                                            <a data-image-id="${image.id}"
                                                               href="${image.url}"
                                                               style="background-image: url('${image.url}')"></a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <button id="upload-btn"
                                                    type="button"
                                                    class="btn btn-primary btn-rounded">
                                                <i class="ion-android-arrow-up"></i> Tải lên
                                            </button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9 text-secondary">
                                            <button type="submit"
                                                    class="btn btn-primary btn-rounded px-4">
                                                <i class="ion-android-done"></i> Lưu
                                            </button>
                                        </div>
                                    </div>
                                </c:if>
                            </form:form>
                        </div>
                    </div>
                    <c:if test="${loggedUsername eq profile.username}">
                        <div class="card">
                            <div class="card-header">
                                <h5>Thiết lập</h5>
                            </div>
                            <div class="card-body">
                                <div class="row mb-3">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Xóa tài khoản</h6>
                                        <p style="font-size: 85%"><i>Xóa vĩnh viễn tài khoản</i></p>
                                    </div>
                                    <div class="col-sm-9">
                                        <button type="button"
                                                id="delete-my-account-btn"
                                                class="btn btn-primary btn-rounded px-4">
                                            <i class="ion-android-delete"></i> Xóa tài khoản
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value="/static/custom/js/webProfile.js"/>"></script>
</body>
</html>
