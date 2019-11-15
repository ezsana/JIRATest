package com.codecool.zsana.jira;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LogoutPageTest extends BasePageTest {
    private LogoutPage logoutPage;

    @BeforeEach
    public void setup() {
        super.setUp();
        login();
        logout();
        logoutPage = new LogoutPage(getDriver());
    }

    @AfterEach
    public void closeTests() {
        super.shutDown();
    }

    @Test
    public void loginLinkAppearsIfUserLogsOut() {
        Assertions.assertTrue(logoutPage.checkIfUserIsLoggedOut());
    }

}