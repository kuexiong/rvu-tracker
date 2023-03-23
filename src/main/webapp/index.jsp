<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>

<%--Sign in page--%>

<html>
    <body>

    <%
        response.sendRedirect(request.getContextPath() + "/logIn");
    %>

    </body>
</html>
