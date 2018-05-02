package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class task_14 {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void task_12() throws InterruptedException {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
    driver.findElement(By.cssSelector("button[name=login]")).click();

    driver.findElement(By.cssSelector("a[title=Edit]")).click();

    List<WebElement> linksToExternalPages = driver.findElements(By.cssSelector("i.fa-external-link"));

    String mainWindow = driver.getWindowHandle();
    Set<String> oldWindows = driver.getWindowHandles();

    for (WebElement link : linksToExternalPages) {
      link.click();

      wait.until(ExpectedConditions.numberOfWindowsToBe(2));
      Set<String> handles = driver.getWindowHandles();
      handles.removeAll(oldWindows);
      String newWindow = handles.iterator().next();

      driver.switchTo().window(newWindow);

      driver.close();
      driver.switchTo().window(mainWindow);
    }
  }
}