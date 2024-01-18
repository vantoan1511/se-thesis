<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="card">
    <div class="card-body row">
        <div class="col-auto">
            <a href="<c:url value="/admin/articles"/>" class="btn btn-block btn-default">
                <i class="fas fa-undo text-success"></i> Quay lại
            </a>
        </div>
        <c:if test="${model.id ne 0}">
            <div class="col-sm-auto">
                <button onclick="handlePreviewButtonClick(event)"
                        class="btn btn-block btn-default"><i class="ri-slideshow-2-line"></i> Xem trước
                </button>
            </div>
            <c:if test="${model.stateCode eq 'published'}">
                <div class="col-sm-auto">
                    <button onclick="handlePublishButtonClick(event, ${model.id}, false)"
                            class="btn btn-block bg-gradient-success">
                        <i class="fas fa-paper-plane" aria-hidden="true"></i> Hủy đăng tải
                    </button>
                </div>
            </c:if>
            <c:if test="${model.stateCode eq 'pending'}">
                <div class="col-sm-auto">
                    <button onclick="handleApproveButtonClick(event, ${model.id})"
                            class="btn btn-block bg-gradient-success">
                        <i class="fas fa-paper-plane" aria-hidden="true"></i> Chấp nhận
                    </button>
                </div>
            </c:if>
        </c:if>
    </div>
</div>
