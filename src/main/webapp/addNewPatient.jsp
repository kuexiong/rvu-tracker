<%@include file="taglib.jsp"%>
<c:set var="pageStyle" value="patient" />
<%@include file="head.jsp"%>

<html>
    <body>
        <main id="patient-info">
            <h1>New Patient</h1>

            <form action="addPatientServlet" method="post">
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
                            <input list="reportStatus" name="reportStatus" required>
                            <datalist id="reportStatus">
                                <option value="In Progress">
                                <option value="Final Review">
                                <option value="Signed">
                            </datalist>
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
                                        <td><input id="96116quantity" type="text" name="96116quantity"></td>
                                        <td>96116</td>
                                        <td>Interview and chart review (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96121quantity" type="text" name="96121quantity"></td>
                                        <td>96121</td>
                                        <td>Interview and chart review (Each additional hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96132quantity" type="text" name="96132quantity"></td>
                                        <td>96132</td>
                                        <td>Testing and report writing (First hour)</td>
                                    </tr>
                                    <tr>
                                        <td><input id="96133quantity" type="text" name="96133quantity"></td>
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
        </main>
    </body>
</html>
