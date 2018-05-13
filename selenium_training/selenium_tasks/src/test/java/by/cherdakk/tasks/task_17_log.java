package by.cherdakk.tasks;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class task_17_log {
  private EventFiringWebDriver driver;
  private WebDriverWait wait;

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by +  " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen.png");
      try {
        Files.copy(tempFile, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen.getAbsolutePath());
    }
  }

  @Before
  public void start() {
    driver = new EventFiringWebDriver(new ChromeDriver());
    driver.register(new MyListener());
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void task_17_log() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
    driver.findElement(By.cssSelector("button[name=login]")).click();

    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]")).click();

    driver.findElement(By.xpath("//i[@class='fa fa-folder']/../a")).click();
    driver.findElement(By.xpath("//i[@class='fa fa-folder']/../a")).click();

    List<WebElement> products = driver.findElements(By.xpath("//td/img/../a"));
    for (int i = 1; i <= (products.size() + 4) ; i++){
      WebElement product = driver.findElement(By.xpath("//tr[" + i + "]//a"));
      product.click();
      driver.findElement(By.xpath("//button[@name='cancel']")).click();
    }
  }
}