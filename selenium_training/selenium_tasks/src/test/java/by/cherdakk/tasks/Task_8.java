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

public class Task_8 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void task_8() {
        List<String> failedItems = new ArrayList<>();

        driver.get("http://localhost/litecart/");

        List<WebElement> ducks = driver.findElements(By.cssSelector("ul.listing-wrapper.products li"));
        for (WebElement duck : ducks) {
            List<WebElement> stickers = duck.findElements(By.cssSelector("div.sticker"));

            if (stickers.size() != 1) {
                failedItems.add(duck.findElement(By.cssSelector("div.name")).getText());
            }
        }

        Assert.assertTrue("Ducks with stickers issues: " + failedItems.toString(), failedItems.size() == 0);
    }

}