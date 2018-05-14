package by.cherdakk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
  public CartPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.findElement(By.cssSelector("#cart")).click();
  }

  @FindBy(css = "li.shortcut")
  public List<WebElement> shortcuts;

  @FindBy(css = "button[name=remove_cart_item]")
  public WebElement removeButton;

  public WebElement tableOfGoods() {
    return driver.findElement(By.cssSelector("table.dataTable"));
  }
}
