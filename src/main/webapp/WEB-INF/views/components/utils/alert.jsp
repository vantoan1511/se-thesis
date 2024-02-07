<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="alert alert-${message.type} show" role="alert">
    <c:out value="${message.message}"/>
    <button type="button" class="close" data-dismiss="alert"
            aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>