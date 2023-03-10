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

                <div class="patient-form-actions">
                    <button type="submit" class="button" name="" value="">Create Patient</button>
                    <button onclick="window.location.href='patientListServlet';" class="button secondary" name="" value="">Cancel</button>
                </div>
            </form>
        </main>
    </body>
</html>
