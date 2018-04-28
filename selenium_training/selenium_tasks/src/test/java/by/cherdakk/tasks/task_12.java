package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;

public class task_12 {
  private WebDriver driver;
  private WebDriverWait wait;
  File image = new File("images/new_duck.jpg");

  //getAbsolutePath();

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
    driver.get("http://localhost/litecart/admin/");

    driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
    driver.findElement(By.cssSelector("button[name=login]")).click();

    driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)")).click();

    driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();

    //Fill in fields on General tab
    driver.findElement(By.cssSelector("label:nth-child(3) input[type=radio]")).click();
    driver.findElement(By.cssSelector("input[name*=name]")).sendKeys("New Product");
    driver.findElement(By.cssSelector("input[name=code]")).sendKeys("New Code");
    driver.findElements(By.cssSelector("input[name*=product_groups]")).get(2).click();
    driver.findElement(By.cssSelector("input[name=quantity]")).sendKeys("10");
    driver.findElement(By.cssSelector("input[name*=new_images]")).sendKeys(image.getAbsolutePath());
    driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("01042018");
    driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("31052018");

    //Fill in fields on Information tab
    driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("select[name=manufacturer_id]"))));
    Select manufacturer = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]")));
    manufacturer.selectByValue("1");
    driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys("new duck");
    driver.findElement(By.cssSelector("input[name*=short_description]")).sendKeys("new duck");
    driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("new duck");
    driver.findElement(By.cssSelector("input[name*=head_title]")).sendKeys("new duck");
    driver.findElement(By.cssSelector("input[name*=meta_description]")).sendKeys("new duck");

    //Fill in fields on Prices tab
    driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name=purchase_price]"))));
    driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("13");
    Select currency = new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
    currency.selectByValue("USD");
    driver.findElement(By.cssSelector("input[name*=USD]")).sendKeys("19");
    driver.findElement(By.cssSelector("input[name*=EUR]")).sendKeys("22");

    //Save
    driver.findElement(By.cssSelector("button[name=save]")).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.notice.success"))));
  }
}
