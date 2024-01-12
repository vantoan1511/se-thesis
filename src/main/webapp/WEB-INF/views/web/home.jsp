<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<section class="home">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-sm-12 col-xs-12">

                <%@include file="../components/web/headline.jsp" %>

                <%@include file="../components/web/featured.jsp" %>

                <div class="line">
                    <div>Latest News</div>
                </div>

                <%@include file="../components/web/latest.jsp" %>

                <div class="line top">
                    <div>Related News</div>
                </div>

                <%@include file="../components/web/related.jsp" %>
            </div>

            <%@include file="../components/web/sidebar.jsp" %>

        </div>
    </div>
</section>

<%@ include file="../components/web/bestOfTheWeek.jsp" %>
</body>
</html>
