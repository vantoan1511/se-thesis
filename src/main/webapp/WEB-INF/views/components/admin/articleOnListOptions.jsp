<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<c:if test="${article.stateCode ne 'draft' and article.stateCode ne 'trash'}">
    <div class="btn-group">
        <button type="button"
                class="btn btn-default dropdown-toggle dropdown-icon"
                data-toggle="dropdown"
                aria-expanded="false"></button>
        <div class="dropdown-menu dropdown-menu-right" role="menu" style>
            <c:if test="${article.stateCode eq 'pending'}">
                <button onclick="handleApproveButtonClick(event, ${article.id})"
                        class="dropdown-item btn btn-default btn-sm"><i class="fas fa-check text-success"></i> Chấp nhận
                </button>
                <button onclick="handleRefuseButtonClick(event, ${article.id})"
                        class="dropdown-item btn btn-default btn-sm"><i class="fas fa-times text-danger"></i> Từ chối
                </button>
            </c:if>
            <c:if test="${article.stateCode eq 'published'}">
                <button onclick="handlePublishButtonClick(event, ${article.id}, false)"
                        class="dropdown-item btn btn-default btn-sm"><i class="fas fa-times text-danger"></i> Hủy đăng
                    tải
                </button>
            </c:if>
        </div>
    </div>
</c:if>
