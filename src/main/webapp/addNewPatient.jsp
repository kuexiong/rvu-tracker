<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="taglib.jsp"/>
<c:set var="title" value="Add New Patient | RVU Tracker" scope="session"/>
<c:set var="pageStyle" value="patient" scope="session"/>
<c:import url="head.jsp"/>

<html>
    <body>
    <c:import url="navBar.jsp"/>

        <main id="patient-info">
            <h1>New Patient</h1>

            <form id="patientForm" action="addPatientServlet" method="post">
                <div id="patient-details" class="box">
                    <div class="box-header">
                        <img src="images/user.svg" alt="User icon">
                        <h5>Patient Details</h5>
                    </div>

                    <div class="box-content">
                        <div class="field">
                            <label for="firstName">First Name</label>
                            <input id="firstName" type="text" name="firstName" required>
                        </div>
                        <div class="field">
                            <label for="lastName">Last Name</label>
                            <input id="lastName" type="text" name="lastName" required>
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
                            <input id="dateOfInterview" type="date" name="dateOfInterview" required>
                        </div>
                        <div class="field">
                            <label for="reportStatus">Report Status</label>
                            <select id="reportStatus" name="reportStatus">
                                <option value="">Please choose an option</option>
                                <option value="In Progress">In Progress</option>
                                <option value="Final Review">Final Review</option>
                                <option value="Signed">Signed</option>
                            </select>
                        </div>

                        <div class="field">
                            <label for="referralQuestion">Referral Question</label>
                            <textarea id="referralQuestion" name="referralQuestion" required></textarea>
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
                                        <td><input id="quantity96116" type="text" name="quantity96116"></td>
                                        <td>96116</td>
                                        <td>Interview and chart review (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="quantity96121" type="text" name="quantity96121"></td>
                                        <td>96121</td>
                                        <td>Interview and chart review (Each additional hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="quantity96132" type="text" name="quantity96132"></td>
                                        <td>96132</td>
                                        <td>Testing and report writing (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="quantity96133" type="text" name="quantity96133"></td>
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
                    <button type="submit" class="button" name="" value="">Create Patient</button>
                    <button onclick="window.location.href='patientListServlet';" class="button secondary" name="" value="">Cancel</button>
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

        </main>
    </body>
</html>
