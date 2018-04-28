package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import org.openqa.selenium.support.ui.WebDriverWait;

public class task_11 {

  private WebDriver driver;
  private WebDriverWait wait;
  private Random randNumber = new Random();

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
  public void task_11() {
    driver.get("http://localhost/litecart/");

    //Creating a new account
    driver.findElement(By.cssSelector("form[name=login_form] a")).click();

    driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Charles");
    driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("Bukowski");
    driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("5124 De Longpre Ave");
    driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("90027");
    driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Los Angeles");

    WebElement dropdown = driver.findElement(By.cssSelector("select[name=country_code]"));
    Select country = new Select(dropdown);
    country.selectByVisibleText("United States");

    dropdown = driver.findElement(By.cssSelector("select[name=zone_code]"));
    Select zone = new Select(dropdown);
    zone.selectByValue("CA");

    String email = randomEmail();
    driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
    driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+123456789");

    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("password");
    driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys("password");

    driver.findElement(By.cssSelector("button[name=create_account]")).click();

    //Log out
    driver.findElement(By.cssSelector("div#box-account li:nth-child(4) a")).click();

    //Log in
    driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("password");
    driver.findElement(By.cssSelector("button[name=login]")).click();

    //Have we logged in successfully?
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.notice.success"))));
    Assert.assertTrue((driver.findElement(By.cssSelector("div.notice.success")).getText().contains("You are now logged in as Charles Bukowski")));
  }

  private String randomEmail() {
    String alphanum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      sb.append(alphanum.charAt(randNumber.nextInt(alphanum.length())));
    }
    return sb.append("@test.by").toString();
  }

}
