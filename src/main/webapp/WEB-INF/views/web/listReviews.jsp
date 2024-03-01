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
                                    <a target="_blank"
                                       href="/${review.articleAlias}">"${review.text}"</a>
                                </td>
                                <td><fmt:formatDate value="${review.createdAt}"/></td>
                                <td>
                                    <c:if test="${username eq loggedUsername}">
                                        <a href="#"
                                           class="btn btn-sm btn-magz "><i class="ion-edit"></i> Sửa</a>
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
<script>
    $(function () {
        const $deleteReviewBtn = $('.delete-review-btn');

        $deleteReviewBtn.click(e => handleDeleteReviewButton(e));

        paginationFunc();
    });

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
