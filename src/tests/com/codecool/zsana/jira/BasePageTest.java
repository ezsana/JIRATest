package com.codecool.zsana.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

class BasePageTest {

    private static String driverPath = System.getenv("DRIVERPATH");
    private WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void login() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.successfulLogin();
    }

    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.logOut();
    }

    public void shutDown() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

}