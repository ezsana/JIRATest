package com.codecool.zsana.jira;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BrowseIssueTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private IssuePage issuePage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        issuePage = new IssuePage(getDriver());
        loginPage.successfulLogin();
    }

    @BeforeEach
    void goToSearchIssuePage() {
        dashboardPage.goToSearchIssuesPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProject.csv", numLinesToSkip = 1)
    void isIssueListAvailableOnProjectPage(String project) {
        Assertions.assertTrue(projectPage.checkIssueListIsAvailable(project));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseIssue.csv", numLinesToSkip = 1)
    void areSpecificIssuesAvailable(String issueName) {
        Assertions.assertTrue(issuePage.searchForIssues(issueName));
    }

    @AfterAll
    void CloseTests() {
        dashboardPage.logOut();
        shutDown();
    }
}
