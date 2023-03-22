<%@include file="taglib.jsp"%>
<c:set var="pageStyle" value="auth" />
<%@include file="head.jsp"%>

<%--Sign in page--%>

<html>
    <body>

        <main id="auth">
            <img class="auth-logo" src="images/RVU-logo.svg" alt="RVU logo">
            <h1>Sign In</h1>

            <div id="auth-form">
                <form action="signInServlet" method="get">

                    <label for="email">Email</label><br/>
                    <input id="email" type="text" name="email" required/>
                    <br/><br/>

                    <label for="password">Password</label><br/>
                    <input id="password" type="text" name="password" required/>
                    <br/><br/>

                    <div class="auth-form-actions">
                        <button type="submit" class="button" name="" value="">Sign In</button>
                        <button type="submit" class="button secondary" name="" value="">Forgot Password</button>
                    </div>
                </form>

                <p>Don't have any account yet? <a href="signUp.jsp">Create an account</a></p>
                <p><a href="patientListServlet">See list of patients</a></p>
            </div>
        </main>
    </body>
</html>
