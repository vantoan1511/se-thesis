<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="card">
    <div class="card-body row">
        <c:if test="${article.stateCode eq 'PENDING'}">
            <div class="col-sm-auto">
                <button onclick="handleApproveButtonClick(event, ${article.id})"
                        class="btn btn-block bg-gradient-success">
                    <i class="fas fa-paper-plane" aria-hidden="true"></i> Chấp nhận
                </button>
            </div>
        </c:if>
        <c:if test="${article.stateCode eq 'PUBLISHED'}">
            <div class="col-sm-auto">
                <button onclick="handleArticlePublishButton(event, ${article.id}, false)"
                        class="btn btn-block bg-gradient-success">
                    <i class="fas fa-paper-plane" aria-hidden="true"></i> Gỡ xuống
                </button>
            </div>
        </c:if>
        <div class="col-sm-auto">
            <button onclick="handlePreviewButtonClick(event, '${article.alias}')"
                    class="btn btn-block btn-default"><i class="ri-slideshow-2-line"></i> Chế độ xem
            </button>
        </div>
        <div class="col-auto">
            <a href="<c:url value="/admin/articles"/>" class="btn btn-block btn-default">
                <i class="fas fa-undo text-success"></i> Quay lại
            </a>
        </div>
    </div>
</div>
