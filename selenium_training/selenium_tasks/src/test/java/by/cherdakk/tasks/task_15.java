package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class task_15 {
  WebDriver driver1, driver2, driver3;
  WebDriverWait wait;

  @Before
  public void start() {
    try {
      driver1 = new RemoteWebDriver(new URL("http://192.168.43.8:4444/wd/hub"), DesiredCapabilities.chrome());
      driver2 = new RemoteWebDriver(new URL("http://192.168.43.8:4444/wd/hub"), DesiredCapabilities.internetExplorer());
      driver3 = new RemoteWebDriver(new URL("http://192.168.43.8:4444/wd/hub"), DesiredCapabilities.chrome());

    }
    catch (MalformedURLException ex){
      System.out.println("Invalid URL");
    }
    //wait = new WebDriverWait(driver, 10);
    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

//  @After
//  public void stop() {
//    driver.quit();
//    driver = null;
//  }

  @Test
  public void task_15() throws InterruptedException {
    driver1.get("https://www.google.com/");
    driver2.get("https://www.google.com/");
    driver3.get("https://www.google.com/");
  }
}