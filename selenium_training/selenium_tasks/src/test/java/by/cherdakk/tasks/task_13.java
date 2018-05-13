package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class task_13 {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 5);
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void task_13() {
    driver.get("http://localhost/litecart/");
    for (int i = 1; i <= 3; i++) {
      addItem(i);
    }

    //remove all items
    driver.findElement(By.cssSelector("div#cart-wrapper a.link")).click();
    List<WebElement> items = driver.findElements(By.cssSelector("a.inact"));
    for (int i = 0; i < (items.size() - 1); i++) {
      items.get(i).click();
      WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
      driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
      wait.until(stalenessOf(table));
    }
  }

  private void addItem(Integer expectedQuantityOfItems) {
    driver.findElement(By.cssSelector("li.product a.link")).click();
    if (isElementPresent(driver,By.cssSelector("select[name='options[Size]']"))) {
      Select size = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
      size.selectByValue("Medium");
    }
    WebElement itemsCounter = driver.findElement(By.cssSelector("span.quantity"));
    driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
    wait.until(textToBePresentInElement(itemsCounter, String.valueOf(expectedQuantityOfItems)));
    driver.findElement(By.cssSelector("img[title='My Store']")).click();
  }

  private boolean isElementPresent(WebDriver driver, By locator) {
    return driver.findElements(locator).size() > 0;
  }

}
