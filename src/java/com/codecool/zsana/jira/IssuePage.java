package com.codecool.zsana.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssuePage extends BasePage {

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='aui-button aui-button-primary search-button']")
    private WebElement searchBtn;

    @FindBy(xpath = "//textarea[@id='advanced-search']")
    private WebElement searchBar;

    public boolean searchForIssues(String issueName) {
        String previousUrl = driver.getCurrentUrl();
        waitForElementToBeClickable(searchBar);
        searchBar.clear();
        searchBar.sendKeys("issuekey =  " + issueName);
        waitForElementToBeClickable(searchBtn);
        searchBtn.click();
        String currentUrl = driver.getCurrentUrl();
        if (!previousUrl.equals(currentUrl)) {
            String elementText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='" + issueName + "']"))).getText();
            return issueName.equals(elementText);
        }
        return false;
    }

}
