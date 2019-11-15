package com.codecool.zsana.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProjectPage extends BasePage {

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='COALA Project']")
    private WebElement coalaHeading;

    @FindBy(xpath = "//a[.='Main Testing Project']")
    private WebElement mainTestingProjectHeading;

    @FindBy(xpath = "//a[.='TOUCAN projekt']")
    private WebElement toucanHeading;

    @FindBy(xpath = "//a[.='JETI Project']")
    private WebElement jetiHeading;

    @FindBy(xpath = "//span[.='Issues']")
    private WebElement issueLink;

    @FindBy(xpath = "//*[@id='content']/div[@class='aui-page-panel']//div[@class='list-content']/ol[@class='issue-list']")
    private WebElement issueLinksList;

    @FindBy(xpath = "//span[.='Releases']")
    private WebElement releaseLink;

    @FindBy(xpath = "//span[@class='aui-icon aui-icon-large glass-project-icon']")
    private WebElement glassLink;

    @FindBy(xpath = "//*[@id='projects']")
    private WebElement allProjectsList;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div/div[2]")
    private WebElement projectSettingsLinkIcon;

    @FindBy(xpath = "//a[@id='view_project_permissions']")
    private WebElement projectPermissionsLink;

    private String mainTestingProjectUrl = "https://jira.codecool.codecanvas.hu/projects/MTP/issues/MTP-154?filter=allopenissues";

    private String coalaProjectUrl = "https://jira.codecool.codecanvas.hu/projects/JETI/issues/JETI-234?filter=allopenissues";

    private String jetiProjectUrl = "https://jira.codecool.codecanvas.hu/projects/JETI/issues/JETI-234?filter=allopenissues";

    private String toucanProjectUrl = "https://jira.codecool.codecanvas.hu/projects/TOUCAN/issues/TOUCAN-52?filter=allopenissues";

    public WebElement getCoalaHeading() {
        return coalaHeading;
    }

    public WebElement getMainTestingProjectHeading() {
        return mainTestingProjectHeading;
    }

    public WebElement getToucanHeadingIcon() {
        return toucanHeading;
    }

    public WebElement getJetiHeadingIcon() {
        return jetiHeading;
    }

    public boolean IsProjectHeadingDisplayed(String title, WebElement element) {
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        return element.getText().equals(title);
    }

    public boolean checkIssueListIsAvailable(String project) {
        switch (project) {
            case "Main Testing Project":
                navigateToPage(mainTestingProjectUrl);
                break;
            case "TOUCAN projekt":
                navigateToPage(toucanProjectUrl);
                break;
            case "JETI Project":
                navigateToPage(jetiProjectUrl);
                break;
            case "COALA Project":
                navigateToPage(coalaProjectUrl);
                break;
            default:
                System.out.println("No such project found.");
        }
        waitForElementToBeClickable(issueLink);
        issueLink.click();
        waitForElementToAppear(issueLinksList);
        return isElementVisible(issueLinksList);
    }

    void changeCheckedStatusOfReleaseButtons() {
        WebElement released = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Released']")));
        WebElement unreleased = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Unreleased']")));
        WebElement archived = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Archived']")));
        if (released.getAttribute("class").equals("version-filter-status-pressed")) {
            released.click();
        }
        if (unreleased.getAttribute("class").equals("version-filter-status-pressed")) {
            unreleased.click();
        }
        if (archived.getAttribute("class").equals("version-filter-status-pressed")) {
            archived.click();
        }
    }

    List<String> getVersionAttributes() {
        List<String> versionAttributes = new ArrayList<>();
        changeCheckedStatusOfReleaseButtons();
        WebElement versionNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='5.0']")));
        WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class='items ui-sortable']//span[@class='aui-lozenge aui-lozenge-current aui-lozenge-subtle']")));
        WebElement date = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//time[.='22/Oct/19']")));
        WebElement description = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='Check this version in glass']")));
        versionAttributes.add(versionNumber.getText());
        versionAttributes.add(status.getText());
        versionAttributes.add(date.getText());
        versionAttributes.add(description.getText());
        return versionAttributes;
    }

    void clickOnReleaseLink() {
        waitForElementToBeClickable(releaseLink);
        releaseLink.click();
    }

    void clickOnGlassLink() {
        waitForElementToBeClickable(glassLink);
        glassLink.click();
    }

    boolean isAllProjectsListDisplayed() {
        waitForElementToBeVisible(allProjectsList);
        return allProjectsList.isDisplayed();
    }

    void goToPermissions() {
        try {
            fluentlyWait(projectSettingsLinkIcon);
            projectSettingsLinkIcon.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            projectSettingsLinkIcon.click();
        }
        waitForElementToBeClickable(projectPermissionsLink);
        projectPermissionsLink.click();
    }

}
