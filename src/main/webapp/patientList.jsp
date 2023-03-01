<%@include file="taglib.jsp"%>
<c:set var="pageStyle" value="patientList" />
<%@include file="head.jsp"%>

<html>
    <body>
        <c:import url="navBar.jsp"/>

        <h2>Patients </h2>
        <table id="userTable" class="display" cellspacing="0" width="100%">
            <thead>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Interview</th>
            <th>Report Status</th>
            </thead>
            <tbody>
            <c:forEach var="patient" items="${patients}">
                <tr>
                    <td>${patient.firstName}</td>
                    <td>${patient.lastName}</td>
                    <td>${patient.interviewDate}</td>
                    <td>${patient.reportStatus}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="addNewPatient.jsp">Add new patient!</a>
    </body>
</html>

