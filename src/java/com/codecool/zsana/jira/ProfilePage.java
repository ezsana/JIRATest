package com.codecool.zsana.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement profilePageUsername;

    @FindBy(xpath = "//dd[@id='up-d-email']")
    private WebElement profilePageEmail;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getUserData(WebElement element) {
        waitForElementToAppear(element);
        return element.getText();
    }

    public String checkUsername() {
        return getUserData(profilePageUsername);
    }

    public String checkEmail() {
        return getUserData(profilePageEmail);
    }

}
