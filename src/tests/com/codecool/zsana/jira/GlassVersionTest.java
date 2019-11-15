package com.codecool.zsana.jira;

import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassVersionTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private GlassDocumentationPage glassDocumentationPage;
    private ProjectPage projectPage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        loginPage.successfulLogin();
        dashboardPage.goToPrivateProject();
        projectPage.clickOnGlassLink();
    }

    @Test
    void isGlassVersionUpToDate() {
        List<String> glassVersion = glassDocumentationPage.getGlassVersionAttributes();
        projectPage.clickOnReleaseLink();
        List<String> version = projectPage.getVersionAttributes();
        Assertions.assertArrayEquals(glassVersion.toArray(), version.toArray());
    }

    @AfterAll
    void closeTest() {
        dashboardPage.logOut();
        shutDown();
    }

}

