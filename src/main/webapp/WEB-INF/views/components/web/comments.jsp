<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div id="comments-section"
     class="comments">
    <form accept-charset="UTF-8"
          <sec:authorize access="isAnonymous()">class="hidden"</sec:authorize>
          class="row"
          id="review-form">
        <div id="leave-review" class="col-md-12">
            <h3 class="title">Suy nghĩ của bạn</h3>
        </div>
        <div class="form-group col-md-12">
            <label for="text">Bình luận <span class="required"></span></label>
            <div id="reply-to-text"
                 class="hidden">
                <blockquote id="reply-text"></blockquote>
            </div>
            <textarea class="form-control"
                      id="text"
                      name="text"
                      placeholder="Viết bình luận của bạn tại đây ..."></textarea>
        </div>
        <div class="form-group col-md-12">
            <button onclick="handleReviewSubmitButton(event, '#review-form')"
                    id="review-submit-btn"
                    class="btn btn-primary">Gửi
            </button>
            <a onclick="handleReplyButton(this, false)"
               id="cancel-btn"
               href="#leave-review"
               class="btn btn-danger">Hủy
            </a>
        </div>
        <c:set var="user" value="${pageContext.request.userPrincipal.principal}"/>
        <input type="hidden" name="id" value="">
        <input type="hidden" name="articleId" value="${article.id}">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="parentId" value="">
    </form>
    <h2 class="title">${comments.getTotalElements()} Bình luận
        <sec:authorize access="isAnonymous()">
            <a onclick="handleLoginButton(event, this)"
               href="#">Đăng nhập để bình luận</a>
        </sec:authorize>
    </h2>
    <div id="comment-list" class="comment-list"></div>
    <div class="row">
        <div class="col text-center">
            <button onclick="loadReviews()"
                    id="load-more-reviews-button"
                    class="btn btn-magz">Xem thêm <i class="ion-android-arrow-down"></i></button>
        </div>
    </div>
</div>
<div id="id" data-article-id="${article.id}" class="hidden"></div>
<div id="logged-user" data-username="${user.username}" class="hidden"></div>