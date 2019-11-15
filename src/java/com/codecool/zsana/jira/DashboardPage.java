package com.codecool.zsana.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    private WebElement avatarPicture;

    @FindBy(xpath = "//a[@id='view_profile']")
    private WebElement viewProfile;

    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@id='browse_link']")
    private WebElement projectLink;

    @FindBy(xpath = "//a[.='Private Project 4 (PP4)']")
    private WebElement privateProject4Link;

    @FindBy(xpath = "//a[@id='project_view_all_link_lnk']")
    private WebElement searchAllProjects;

    @FindBy(xpath = "//a[@id='find_link']")
    private WebElement issuesLink;

    @FindBy(xpath = "//a[@id='issues_new_search_link_lnk']")
    private WebElement searchAllIssues;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void openProfilePage() {
        waitForElementToBeClickable(avatarPicture);
        avatarPicture.click();
        waitForElementToBeClickable(viewProfile);
        viewProfile.click();

    }

    public void logOut() {
        waitForElementToBeClickable(avatarPicture);
        avatarPicture.click();
        waitForElementToBeClickable(logoutLink);
        logoutLink.click();
    }

    public WebElement getProjectLink() {
        return projectLink;
    }

    public WebElement getSearchAllProjects() {
        return searchAllProjects;
    }

    public void goToAllProjectSearchPage() {
        waitForElementToBeClickable(projectLink);
        projectLink.click();
        waitForElementToBeClickable(searchAllProjects);
        searchAllProjects.click();
    }

    public void goToSearchIssuesPage() {
        waitForElementToBeClickable(issuesLink);
        issuesLink.click();
        waitForElementToBeClickable(searchAllIssues);
        searchAllIssues.click();
    }

    public void goToPrivateProject() {
        waitForElementToBeClickable(projectLink);
        projectLink.click();
        waitForElementToBeClickable(privateProject4Link);
        privateProject4Link.click();
    }

}
