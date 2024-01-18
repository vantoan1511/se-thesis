<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="card">
    <div class="card-body row">
        <div class="col-md-3 col-sm-auto">
            <button onclick="handleSaveButtonClick(event, '#form', false, false)"
                    id="saveBtn"
                    class="btn btn-block bg-gradient-success">
                <i class="ri-save-line"></i> Lưu
            </button>
        </div>
        <div class="col-auto">
            <button onclick="handleSaveButtonClick(event, '#form', true, false)"
                    id="saveAndCloseBtn"
                    class="btn btn-block btn-default">
                <i class="ri-check-double-line text-success"></i> Lưu & Đóng
            </button>
        </div>
        <div class="col-auto">
            <button onclick="handleSaveButtonClick(event, '#form', false, true)"
                    id="saveAndNewBtn"
                    class="btn btn-block btn-default">
                <i class="ri-file-add-line text-success"></i> Lưu & Mới
            </button>
        </div>
        <div class="col-auto">
            <a href="<c:url value="/admin/articles"/>"
               class="btn btn-block btn-default"><i class="ri-close-line text-danger"></i> Huỷ
            </a>
        </div>
        <c:if test="${model.id ne 0}">
            <div class="col-sm-auto">
                <button onclick="handlePreviewButtonClick(event)"
                        class="btn btn-block btn-default"><i class="ri-slideshow-2-line"></i> Xem trước
                </button>
            </div>
            <c:choose>
                <c:when test="${model.stateCode eq 'draft'}">
                    <div class="col-sm-auto">
                        <button onclick="handlePublishButtonClick(event, ${model.id})"
                                id="publish-btn"
                                class="btn btn-block bg-gradient-success">
                            <i class="ri-send-plane-line"></i> Đăng tải
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-sm-auto">
                        <button onclick="handlePublishButtonClick(event, ${model.id}, false)"
                                id="unpublish-btn"
                                class="btn btn-block btn-default">
                            <i class="ri-close-circle-line"></i> Hủy đăng tải
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
