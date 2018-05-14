package by.cherdakk.app;

import by.cherdakk.pages.CartPage;
import by.cherdakk.pages.MainPage;
import by.cherdakk.pages.ProductPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Application {
  private WebDriver driver;
  private WebDriverWait wait;

  private MainPage mainPage;
  private ProductPage productPage;
  private CartPage cartPage;

  public Application() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 3);
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    mainPage = new MainPage(driver);
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);
  }

  public void quit() {
    driver.quit();
    driver = null;
  }

  public void addItem() {
    mainPage.open();
    mainPage.product.click();
    productPage.selectSizeIfNeccesary();
    Integer currentQuantityOfItems = productPage.getCurrentQuantityofItems();
    productPage.addToCartButton.click();
    wait.until(textToBePresentInElement(productPage.itemsCounter, String.valueOf(currentQuantityOfItems + 1)));
  }

  public void removeAllItems() {
    cartPage.open();
    Integer numberOfUniqueItems = 1;
    if (cartPage.shortcuts.size() > 0) {
      numberOfUniqueItems = cartPage.shortcuts.size();
      cartPage.shortcuts.get(0).click();
    }
    for (int i = 0; i < numberOfUniqueItems; i++) {
      cartPage.removeButton.click();
      wait.until(stalenessOf(cartPage.tableOfGoods()));
    }
  }

  public void isCartEmpty() {
    mainPage.open();
    Assert.assertEquals(0,productPage.getCurrentQuantityofItems());
  }

}
