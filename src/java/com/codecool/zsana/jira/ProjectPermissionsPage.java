package com.codecool.zsana.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class ProjectPermissionsPage extends BasePage {

    public ProjectPermissionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='project-config-panel-permissions']")
    private WebElement permissionTable;

    // with this method we get all the data we need from the permission table as a list of list of strings. Maybe it should be Hashmap.
    List<List<String>> getTableDataOfTableRows() {
        waitForElementToBeVisible(permissionTable);
        System.out.println(permissionTable);
        List<WebElement> tableRows = permissionTable.findElements(By.cssSelector("tr[data-permission-key]"));
        try {
            Thread.sleep(10);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
        List<List<String>> permissionRowsWithData = new ArrayList<>();
        for (WebElement row : tableRows) {
            List<String> data = new ArrayList<>();
            // add permission name to list:
            data.add(row.findElement(By.cssSelector("td[data-headers='project-permissions'] > p.title")).getText());
            // add user permissions to list
            for (WebElement we: row.findElements(By.cssSelector("dl.types"))) {
                data.add(we.getText());
            }
            permissionRowsWithData.add(data);
        }
        System.out.println(Arrays.toString(permissionRowsWithData.toArray()));
        return permissionRowsWithData;
    }

    //this is the hashmap variate from the project page permissions:
    Map<String, List<Boolean>> createMapFromProjectPagePermissions() {
        List<List<String>> permissions = getTableDataOfTableRows();
        Map<String, List<Boolean>> permissionsMap = new HashMap<>();
        List<Boolean> booleans = new ArrayList<>();
        for (List<String> permRow : permissions) {
            for (int i = 1; i < permRow.size(); i++) {
                if (permRow.get(i).contains("Application access")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Project Role")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Assignee")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Reporter")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
            }
        }
        return permissionsMap;
    }


}
