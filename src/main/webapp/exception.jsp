<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="errorPages" scope="session" />
<c:set var="title" value="Exception Error | RVU Tracker" scope="session"/>
<c:import url="head.jsp"/>

<html>

    <body>

        <main>
            <div class="errorMessage">
                <h1>Oh no! Something went wrong!</h1>
            </div>
        </main>

    </body>
</html>
