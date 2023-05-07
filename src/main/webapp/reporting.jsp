<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="reporting" />
<c:set var="user" value='${requestScope["user"]}' />
<c:import url="head.jsp"/>

<%--<script type="text/javascript" class="init">--%>
<%--    $(document).ready( function () {--%>
<%--        $('#rvuTable').DataTable();--%>
<%--    } );--%>
<%--</script>--%>

<html>
  <body>

    <c:import url="navBar.jsp"/>

    <h6>CURRENT MONTH</h6>

    <h2>${currentMonth} ${currentYear}</h2>

        <table>
            <thead>
            <th><h5>Current RVU</h5></th>
            <th><h5>Target RVU</h5></th>
            </thead>
            <tbody>
                <tr>
                    <td>${currentMonthlyTotal['Total']}</td>
                    <td>300</td>
                </tr>
            </tbody>
        </table>
    
    <img src="${progressBar}">

    <h6>FISCAL YTD</h6>

    <h2>Previous Months</h2>

        <table id="rvuTable" class="display" cellspacing="0" width="75%">
            <thead>
            <th>Month</th>
            <th>Year</th>
            <th>Total RVU</th>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${monthlyTotals}">

                <tr>
                    <td>
                        ${entry.key}
                    </td>
                    <td>
                            <c:set var="month" value="${entry.key}"/>
                            <c:out value="${monthYear[month]}"/>
                    </td>
                    <td>
                        ${monthlyTotals[entry.key]['Total']}
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

  </body>
</html>
