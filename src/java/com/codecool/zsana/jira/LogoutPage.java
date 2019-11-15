package com.codecool.zsana.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {

    @FindBy(xpath = "//a[.='Log In']")
    private WebElement loginLink;

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIfUserIsLoggedOut() {
        try {
            waitForElementToAppear(loginLink);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
