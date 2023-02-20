<%@include file="head.jsp"%>

<%--Sign in page--%>

<html>
    <body>
        <h3>Sign In</h3>

        <form action="signInServlet" method="get">

            <label for="email">Email Address</label><br/>
            <input id="email" type="text" name="email" required/>
            <br/><br/>

            <label for="password">Password</label><br/>
            <input id="password" type="text" name="password" required/>
            <br/><br/>

            <input type="submit" name="" value="Submit" />
        </form>

        <a href="signUp.jsp">Create an account!</a>
    </body>
</html>
