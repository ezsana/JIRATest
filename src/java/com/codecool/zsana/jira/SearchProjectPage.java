package com.codecool.zsana.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchProjectPage extends BasePage {

    public SearchProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='project-filter-text']")
    private WebElement searchbar;

    @FindBy(xpath = "//a[.='Main Testing Project']")
    private WebElement mainTestingProjectLink;

    @FindBy(xpath = "//a[.='TOUCAN projekt']")
    private WebElement toucanProjectLink;

    @FindBy(xpath = "//a[.='COALA Project']")
    private WebElement coalaProjectLink;

    @FindBy(xpath = "//a[.='JETI Project']")
    private WebElement jetiProjectLink;

    void waitForProjectNameToShowAndClick(String title) {
        waitForElementToBeClickable(searchbar);
        searchbar.sendKeys(title);
        WebElement element;
        switch (title) {
            case "Main Testing Project":
                element = mainTestingProjectLink;
                break;
            case "TOUCAN projekt":
                element = toucanProjectLink;
                break;
            case "JETI Project":
                element = jetiProjectLink;
                break;
            case "COALA Project":
                element = coalaProjectLink;
                break;
            default:
                element = null;
        }
        waitForElementToBeClickable(element);
        element.click();
    }

}
