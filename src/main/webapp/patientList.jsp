<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="patientList" scope="session" />
<c:set var="activePage" value="patientList" scope="session" />
<c:set var="user" value='${requestScope["user"]}' />
<c:set var="title" value="Patient List | RVU Tracker" scope="session"/>
<c:set var="statuses" value="${['In Progress', 'Final Review', 'Signed']}" scope="application" />

<c:import url="head.jsp"/>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#patientTable').DataTable();
    });
</script>

<html>
    <body>
        <c:import url="navBar.jsp"/>

        <main id="patientList" class="content-wrapper-lg">
            <h1>Hello ${user.firstName}!</h1>
            <div class="kpis">
                <c:forEach var="status" items="${statuses}">
                    <div class="kpi">
                        <h5 class="kpiTitle">${status}</h5>
                        <div class="kpiValue">${reportStatusCount[status]}</div>
                    </div>
                </c:forEach>
            </div>

            <a class="button" href="addNewPatient.jsp">Add Patient</a>

            <table id="patientTable" class="display" cellspacing="0" width="100%">
                <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Interview</th>
                    <th>Report Status</th>
                </thead>
                <tbody>
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td>
                            <c:set var="editPatient">
                                <c:url value="/displayPatientInfoServlet">
                                    <c:param name="patientId" value="${patient.id}"/>
                                </c:url>
                            </c:set>
                            <a href="${editPatient}">${patient.firstName}</a>
                        </td>
                        <td><a href="${editPatient}">${patient.lastName}</a></td>
                        <td><a href="${editPatient}">${patient.interviewDate}</a></td>
                        <td><a href="${editPatient}">${patient.reportStatus}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
</html>

