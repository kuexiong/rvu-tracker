<%@include file="head.jsp"%>

<html>
    <body>

        <h3>Create Account</h3>

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

                <input type="submit" name="" value="Submit" />
            </form>

    </body>
</html>
