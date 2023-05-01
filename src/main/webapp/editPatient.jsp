<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="pageStyle" value="patient" />
<c:import url="head.jsp"/>

<html>
    <body>
        <main id="patient-info">
            <h1>New Patient</h1>

            <form action="updatePatientServlet" method="post" id="update-form">
                <div id="patient-details" class="box">
                    <div class="box-header">
                        <img src="images/user.svg" alt="User icon">
                        <h5>Patient Details</h5>
                    </div>

                    <div class="box-content">
                        <div class="field">
                            <label for="firstName">First Name</label>
                            <input id="firstName" type="text" name="firstName" required value="${patient.firstName}">
                        </div>
                        <div class="field">
                            <label for="lastName">Last Name</label>
                            <input id="lastName" type="text" name="lastName" required value="${patient.lastName}">
                        </div>
                    </div>
                </div>

                <div id="encounter-details" class="box">
                    <div class="box-header">
                        <img src="images/document.svg">
                        <h5>Encounter Details</h5>
                    </div>

                    <div class="box-content">
                        <div class="field">
                            <label for="dateOfInterview">Date of Interview</label>
                            <input id="dateOfInterview" type="date" name="dateOfInterview" required value="${patient.interviewDate}">
                        </div>
                        <div class="field">
                            <label for="reportStatus">Report Status</label>
                            <input list="reportStatus" name="reportStatus" required value="${patient.reportStatus}">
                            <datalist id="reportStatus">
                                <option value="In Progress">
                                <option value="Final Review">
                                <option value="Signed">
                            </datalist>
                        </div>

                        <div class="field">
                            <label for="referralQuestion">Referral Question</label>
                            <textarea id="referralQuestion" name="referralQuestion" required>${patient.referralQuestion}</textarea>
                        </div>
                    </div>
                </div>

                <div id="charges" class="box">
                    <div class="box-header">
                        <img src="images/charges.svg">
                        <h5>Charges</h5>
                    </div>

                    <div class="box-content">
                        <div class="field">
                            <table id="cptCodes">
                                <thead>
                                    <th>Quantity</th>
                                    <th>Code</th>
                                    <th>Description</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input id="96116quantity" type="text" name="96116quantity" value="${quantity96116}"></td>
                                        <td>96116</td>
                                        <td>Interview and chart review (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96121quantity" type="text" name="96121quantity" value="${quantity96121}"></td>
                                        <td>96121</td>
                                        <td>Interview and chart review (Each additional hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96132quantity" type="text" name="96132quantity" value="${quantity96132}"></td>
                                        <td>96132</td>
                                        <td>Testing and report writing (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96133quantity" type="text" name="96133quantity" value="${quantity96133}"></td>
                                        <td>96133</td>
                                        <td>Testing and report writing (Each additional hour)</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


                <input id="timestamp" name="timestamp" value="" type="hidden">

                <div class="patient-form-actions">
                    <button type="submit" class="button" name="patientId" value="${patient.id}">Save Changes</button>
                    <button onclick="window.location.href='patientListServlet';" class="button secondary" name="" value="">Cancel</button>
                    <button type="submit" class="button secondary" name="delete">Delete Patient</button>
                </div>
            </form>

            <script>
                let field = document.querySelector('#timestamp');
                let date = new Date();

                let dateString =
                    date.getUTCFullYear() + "-" +
                    ("0" + (date.getUTCMonth() + 1)).slice(-2) + "-" +
                    ("0" + date.getUTCDate()).slice(-2) + " " +
                    ("0" + date.getUTCHours()).slice(-2) + ":" +
                    ("0" + date.getUTCMinutes()).slice(-2) + ":" +
                    ("0" + date.getUTCSeconds()).slice(-2);

                // Set the date
                field.value = dateString;
            </script>

            <script>
                $(document).ready(function () {
                    $("#update-form").submit(function () {
                        if ($("#96116quantity").val()=="") {
                            $("#96116quantity").val('0');
                        }
                        if ($("#96121quantity").val()=="") {
                            $("#96121quantity").val('0');
                        }
                        if ($("#96132quantity").val()=="") {
                            $("#96132quantity").val('0');
                        }
                        if ($("#96133quantity").val()=="") {
                            $("#96133quantity").val('0');
                        }
                    });
                });
            </script>

        </main>
    </body>
</html>
