package com.codecool.zsana.jira;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassPermissionsTest extends BasePageTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPermissionsPage projectPermissionsPage;
    ProjectPage projectPage;
    GlassDocumentationPage glassDocumentationPage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectPermissionsPage = new ProjectPermissionsPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        loginPage.successfulLogin();
        dashboardPage.goToPrivateProject();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*
    @Test
    void areGlassPermissionsParallelWithProjectPermissions() {
        loginPage.connectGlassVersionAndProjectVersion(projectPage, projectPermissionsPage, glassDocumentationPage);
    }
     */

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        super.shutDown();
    }
}

