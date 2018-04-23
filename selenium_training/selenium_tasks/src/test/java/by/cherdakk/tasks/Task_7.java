package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Task_7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void task_7() {
        List<String> failedItems = new ArrayList<>();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
        for (int i = 1; i <= menuItems.size(); i++) {
            WebElement menuItem = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ")"));
            String menuItemName = menuItem.getText();
            menuItem.click();

            if (driver.findElements(By.cssSelector("h1")).size() == 0) {
                failedItems.add(menuItemName);
            }

            List<WebElement> menuSubItems = driver.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li"));
            for (int j = 2; j <= menuSubItems.size(); j++) {
                WebElement menuSubItem = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li:nth-child(" + j + ")"));
                String menuSubItemName = menuSubItem.getText();
                menuSubItem.click();

                if (driver.findElements(By.cssSelector("h1")).size() == 0) {
                    failedItems.add(menuItemName + " - " + menuSubItemName);
                }
            }
        }

        Assert.assertTrue("Menu items without header: " + failedItems.toString(), failedItems.size() == 0);
    }

}