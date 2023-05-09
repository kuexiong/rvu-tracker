<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="errorPages" scope="session" />
<c:set var="title" value="Error 500 | RVU Tracker" scope="session"/>
<c:import url="head.jsp"/>

<html>

    <body>

        <main>
            <div class="errorMessage">
                <h1>500</h1>
                <h3>Oh no! Something went wrong!</h3>
            </div>
        </main>
    </body>
</html>

