package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class task_17 {
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
  public void task_17() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
    driver.findElement(By.cssSelector("button[name=login]")).click();

    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]")).click();

    driver.findElement(By.xpath("//i[@class='fa fa-folder']/../a")).click();
    driver.findElement(By.xpath("//i[@class='fa fa-folder']/../a")).click();

    List<WebElement> products = driver.findElements(By.xpath("//td/img/../a"));
    for (int i = 5; i <= (products.size() + 4) ; i++){
      WebElement product = driver.findElement(By.xpath("//tr[" + i + "]//a"));
      product.click();

      driver.findElement(By.xpath("//button[@name='cancel']")).click();
    }

    List<LogEntry> BrowserLog = driver.manage().logs().get("browser").getAll();
    Assert.assertTrue(BrowserLog.toString(), BrowserLog.size() == 0);
  }
}