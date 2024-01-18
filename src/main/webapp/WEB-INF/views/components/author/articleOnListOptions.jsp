<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="/common/taglib.jsp" %>

<div class="btn-group">
    <button type="button"
            class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown"
            aria-expanded="false"></button>
    <div class="dropdown-menu dropdown-menu-right" role="menu" style>
        <c:catch var="error">
            <c:choose>
                <c:when test="${article.statusCode eq 'trash'}">
                    <button onclick="handleDeleteButtonClick(event, ${article.id})"
                            class="dropdown-item btn btn-default btn-sm">
                        <i class="ri-delete-bin-line text-danger"></i> Xóa
                    </button>
                    <button onclick="handleRestoreButtonClick(event, ${article.id})"
                            class="dropdown-item btn btn-default btn-sm">
                        <i class="ri-arrow-go-back-line text-success"></i> Khôi phục
                    </button>
                </c:when>
                <c:otherwise>
                    <button onclick="handleTrashButtonClick(event, ${article.id})"
                            class="dropdown-item btn btn-default btn-sm">
                        <i class="ri-delete-bin-line text-danger"></i> Xóa
                    </button>
                </c:otherwise>
            </c:choose>
        </c:catch>
    </div>
</div>
