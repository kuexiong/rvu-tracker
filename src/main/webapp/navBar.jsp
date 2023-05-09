<nav id="navBar">
    <div class="nav-links">
        <img src="images/Logo.svg" alt="RVU logo">
        <ul>
            <li><a href="patientListServlet" class="button secondary ${activePage == "patientList" ? 'active' : ''}">Patient List</a></li>
            <li><a href="reportingServlet" class="button secondary ${activePage == "reporting" ? 'active' : ''}">Reporting</a></li>
        </ul>
    </div>

    <button onclick="window.location.href='logoutServlet';" class="secondary">
        <img src="images/log-out.svg" alt="log out icon" />
        Log Out
    </button>
</nav>