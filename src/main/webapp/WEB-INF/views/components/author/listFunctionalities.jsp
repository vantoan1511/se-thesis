<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="/common/taglib.jsp" %>

<div class="card">
    <div class="card-body row">
        <div class="col-md-3 col-sm-6">
            <a href="<c:url value="/admin/articles/new"/>" class="btn btn-block bg-gradient-success">
                <i class="ri-add-circle-line"></i> Mới
            </a>
        </div>
        <c:choose>
            <c:when test="${param.tab eq 'trash'}">
                <div class="col-md-auto col-sm-3">
                    <button onclick="handleMultipleRestoreButton(event, '.check-box:checked')"
                            class="btn btn-block btn-default"
                            title="Khôi phục">
                        <i class="ri-arrow-go-back-line text-success"></i> Khôi phục
                    </button>
                </div>
                <div class="col-md-auto col-sm-3">
                    <button onclick="handleMultipleDeleteButtonClick(event, '.check-box:checked')"
                            class="btn btn-block btn-default"
                            title="Xóa">
                        <i class="ri-delete-bin-2-line text-danger"></i> Xóa
                    </button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-auto col-sm-3">
                    <button onclick="handleMultipleTrashButtonClick(event, '.check-box:checked')"
                            type="button"
                            class="btn btn-block btn-default"
                            title="Chuyển vào thùng rác">
                        <i class="ri-delete-bin-line text-danger"></i> Chuyển vào thùng rác
                    </button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
