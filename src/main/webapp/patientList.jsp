<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="patientList" scope="session"/>
<c:set var="user" value='${requestScope["user"]}' />
<c:set var="title" value="Patient List | RVU Tracker" scope="session"/>
<c:import url="head.jsp"/>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#patientTable').DataTable();
    } );
</script>

<html>
    <body>
        <c:import url="navBar.jsp"/>

        <h1>Hello ${user.firstName}!</h1>

        <table>
            <thead>
            <th><h5>In Progress</h5></th>
            <th><h5>Final Review</h5></th>
            <th><h5>Signed</h5></th>
            </thead>
            <tbody>
            <tr>
                <td>${reportStatusCount['In Progress']}</td>
                <td>${reportStatusCount['Final Review']}</td>
                <td>${reportStatusCount['Signed']}</td>
            </tr>
            </tbody>
        </table>

        <h2>Patients </h2>
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

        <a href="addNewPatient.jsp">Add new patient!</a>

    </body>
</html>

