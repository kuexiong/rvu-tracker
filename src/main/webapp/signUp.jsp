<%@include file="taglib.jsp"%>
<c:set var="pageStyle" value="auth" />
<%@include file="head.jsp"%>

<html>
    <body>
        <main id="auth">
            <img class="auth-logo" src="images/RVU-logo.svg" alt="RVU logo">
            <h1>Create Account</h1>

            <div id="auth-form">
                <form action="signUpServlet" method="post">

                    <label for="firstName">First Name</label><br/>
                    <input id="firstName" type="text" name="firstName" required/>
                    <br/><br/>

                    <label for="lastName">Last Name</label><br/>
                    <input id="lastName" type="text" name="lastName" required/>
                    <br/><br/>

                    <label for="email">Email Address</label><br/>
                    <input id="email" type="text" name="email" required/>
                    <br/><br/>

                    <label for="password">Password</label><br/>
                    <input id="password" type="text" name="password" required/>
                    <br/><br/>
                    <div class="auth-form-actions">
                        <button type="submit" name="" value="Submit">Submit</button>
                    </div>
                </form>

                <p>Already have an account? <a href="index.jsp">Sign In</a></p>
            </div>
        </main>
    </body>
</html>
