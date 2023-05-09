<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="reporting" scope="session" />
<c:set var="activePage" value="reporting" scope="session" />
<c:set var="user" value='${requestScope["user"]}' />
<c:set var="title" value="Reporting | RVU Tracker" scope="session"/>
<c:import url="head.jsp"/>

<%--<script type="text/javascript" class="init">--%>
<%--    $(document).ready( function () {--%>
<%--        $('#rvuTable').DataTable();--%>
<%--    } );--%>
<%--</script>--%>

<html>
  <body>

    <c:import url="navBar.jsp"/>

    <main id="reporting" class="content-wrapper-lg">
        <div class="sectionHeader">
            <h6>CURRENT MONTH</h6>
            <h2>${currentMonth} ${currentYear}</h2>
        </div>

        <div class="kpis">
            <div class="kpi">
                <h5 class="kpiTitle">Current RVU</h5>
                <div class="kpiValue">${currentMonthlyTotal['Total']}</div>
            </div>
            <div class="kpi">
                <h5 class="kpiTitle">Target RVU</h5>
                <div class="kpiValue">300</div>
            </div>
            <div class="kpi">
                <h5 class="kpiTitle">Progress</h5>
                <div class="kpiValue">
                    <img src="${progressBar}" width="100%">
                </div>
            </div>
        </div>

        <div class="sectionHeader">
            <h6>FISCAL YTD</h6>
            <h2>Previous Months</h2>
        </div>

        <table id="rvuTable" class="display" cellspacing="0">
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
    </main>

  </body>
</html>
