<%@ page import="com.newswebsite.main.security.SecurityUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="loggedUsername" value="<%=SecurityUtil.username()%>"/>
<c:set var="loggedUser" value="${pageContext.request.userPrincipal.principal}"/>

<!DOCTYPE html>
<html>
<body>
<section class="page first">
    <div class="container">
        <div class="page-description">
            <div>
                <div>
                    <h5>Bình luận</h5>
                    <select name="size">
                        <option value="2">2</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                    </select>
                </div>

                <c:if test="${not empty message}">
                    <%@ include file="../components/web/alert.jsp" %>
                </c:if>

                <div>
                    <table class="table table-striped table-hover">
                        <tbody>
                        <c:forEach var="review" items="${pagedReviews.content}">
                            <tr>
                                <td>
                                    <c:if test="${not empty review.parentText }">
                                        Phản hồi "${review.parentText}":
                                    </c:if>
                                    <a class="review-text"
                                       target="_blank"
                                       href="/${review.articleAlias}">${review.text}</a>
                                </td>
                                <td class="review-time"><fmt:formatDate value="${review.createdAt}"/></td>
                                <td>
                                    <c:if test="${username eq loggedUsername}">
                                        <a href="#"
                                           data-review-id="${review.id}"
                                           class="btn btn-sm btn-magz edit-review-btn"><i class="ion-edit"></i> Sửa</a>
                                        <a href="/reviews/${review.id}/delete"
                                           class="delete-review-btn btn btn-sm btn-primary ">
                                            <i class="ion-android-delete"></i> Xóa</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="text-center">
                    <ul class="pagination"></ul>
                    <div class="pagination-help-text">
                        Hiển thị ${pagedReviews.numberOfElements}/${pagedReviews.totalElements} &dash;
                        Trang ${pagedReviews.number+1}
                    </div>
                </div>
                <form id="page-request-form"
                      data-total-pages="${pagedReviews.totalPages}">
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="size" value="${pagedReviews.size}">
                </form>
            </div>
        </div>
    </div>
</section>
<div class="modal-box">
    <div class="modal-inner">
        <div class="inner-title">
            <h5>Chỉnh sửa bình luận</h5>
        </div>
        <div class="inner-body">
            <div class="row">
                <div class="col-md-3">
                    <label>${loggedUser.fullName()}</label>
                    <p class="review-time"></p>
                </div>
                <div class="col-md-9">
                    <form id="review-submit-form">
                        <input type="hidden" name="id" value="">
                        <textarea class="form-control review-text" name="text"></textarea>
                    </form>
                </div>
            </div>
        </div>
        <div class="inner-footer">
            <button class="btn btn-rounded btn-primary close-modal-btn">Hủy</button>
            <button id="update-review-btn" class="btn btn-rounded btn-primary">Lưu</button>
        </div>
    </div>
</div>
<script>
    $(function () {
        const $deleteReviewBtn = $('.delete-review-btn');
        const $editReviewBtn = $('.edit-review-btn');
        const $closeModalBtn = $('.close-modal-btn');
        const $updateReviewBtn = $('#update-review-btn');

        $deleteReviewBtn.click(e => handleDeleteReviewButton(e));
        $editReviewBtn.click(e => handleEditReviewButton(e));
        $closeModalBtn.click(e => handleCloseModalButton(e));
        $updateReviewBtn.click(e => handleUpdateReviewButton(e));

        paginationFunc();
    });

    function handleUpdateReviewButton(e) {
        e.preventDefault();
        const $reviewSubmitForm = $('#review-submit-form');
        let data = {};
        let formData = $reviewSubmitForm.serializeArray().forEach((i, e) => {
            data["" + i.name + ""] = i.value;
        });
        console.log(data);
        handlePutRequest('/api/v1/reviews', data, () => {
            location.reload()
        }, xhr => errorCallback(xhr));
    }

    function handleCloseModalButton(e) {
        e.preventDefault();
        let $modal = $('.modal-box');
        $modal.hide();
    }

    function handleEditReviewButton(e) {
        e.preventDefault();
        let $modal = $('.modal-box');
        let $currentReview = $(e.target).closest('tr');
        let currentReviewText = $currentReview.find('.review-text').text();
        let reviewTime = $currentReview.find('.review-time').text();
        let reviewId = $(e.target).data("review-id");
        $modal.find('.review-time').text(reviewTime);
        $modal.find('.review-text').text(currentReviewText);
        $modal.find('[name=id]').val(reviewId);
        $modal.show();
    }

    function handleDeleteReviewButton(e) {
        e.preventDefault();
        showWarningAlert('Xóa bình luận này?', result => {
            if (result.isConfirmed) {
                let redirectUrl = location.href;
                location.replace(e.target.href + '?redirectUrl=' + redirectUrl);
            }
        });
    }

    function paginationFunc() {
        const $sizeSelect = $('select[name=size]');
        const $pageRequestForm = $('#page-request-form');

        let totalPages = ${pagedReviews.totalPages};
        let currentPage = ${pagedReviews.number+1};
        let currentSize = $('input[name=size]').val();

        $sizeSelect.find('option').map((i, e) => {
            $(e).attr('selected', $(e).val() === currentSize);
        })

        $sizeSelect.change(function () {
            $('input[name=size]').val($(this).val());
            $pageRequestForm.submit();
        });

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
                        $pageRequestForm.submit();
                    }
                }
            });
        }
    }
</script>
</body>
</html>
