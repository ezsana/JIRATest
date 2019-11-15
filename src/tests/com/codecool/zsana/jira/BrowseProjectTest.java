package com.codecool.zsana.jira;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BrowseProjectTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private SearchProjectPage searchProjectPage;
    private ProjectPage projectPage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        searchProjectPage = new SearchProjectPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        loginPage.successfulLogin();
    }

    @BeforeEach
    void goToSearchProjectsPage() {
        dashboardPage.goToAllProjectSearchPage();
    }

    @Test
    void isAllProjectsListDisplayed() {
        Assertions.assertTrue(projectPage.isAllProjectsListDisplayed());
    }

    // it's all tests, including TOUCAN/JETI/COALA
    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProject.csv", numLinesToSkip = 1)
    void IsBrowsingProjectAvailable(String title) {
        searchProjectPage.waitForProjectNameToShowAndClick(title);
        implicitlyWait();
        switch (title) {
            case "Main Testing Project":
                Assertions.assertTrue(projectPage.IsProjectHeadingDisplayed(title, projectPage.getMainTestingProjectHeading()));
                break;
            case "TOUCAN projekt":
                Assertions.assertTrue(projectPage.IsProjectHeadingDisplayed(title, projectPage.getToucanHeadingIcon()));
                break;
            case "JETI Project":
                Assertions.assertTrue(projectPage.IsProjectHeadingDisplayed(title, projectPage.getJetiHeadingIcon()));
                break;
            case "COALA Project":
                Assertions.assertTrue(projectPage.IsProjectHeadingDisplayed(title, projectPage.getCoalaHeading()));
                break;
            default:
                System.out.println("No heading found.");
        }
    }

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        shutDown();
    }
}
