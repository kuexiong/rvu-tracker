<%@include file="taglib.jsp"%>
<c:set var="pageStyle" value="patientList" />
<c:set var="user" value='${requestScope["user"]}' />
<%@include file="head.jsp"%>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#patientTable').DataTable();
    } );
</script>

<html>
    <body>
        <c:import url="navBar.jsp"/>

        <h1>Hello ${user.firstName}!</h1>

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
                    <td><a href="editPatient.jsp">${patient.firstName}</a></td>
                    <td><a href="editPatient.jsp">${patient.lastName}</a></td>
                    <td><a href="editPatient.jsp">${patient.interviewDate}</a></td>
                    <td><a href="editPatient.jsp">${patient.reportStatus}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="addNewPatient.jsp">Add new patient!</a>
    </body>
</html>

