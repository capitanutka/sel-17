package by.cherdakk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends Page {
  public ProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void selectSizeIfNeccesary() {
    if (this.options.size() > 0 ) {
      Select size = new Select(this.sizeDropDown);
      size.selectByValue("Medium");
    }
  }

  public int getCurrentQuantityofItems() {
    return Integer.parseInt(this.itemsCounter.getText());
  }

  @FindBy(css = "select[name='options[Size]']")
  public WebElement sizeDropDown;

  @FindBy(css = "span.quantity")
  public WebElement itemsCounter;

  @FindBy(css = "button[name=add_cart_product]")
  public WebElement addToCartButton;

  @FindBy(css = "select[name='options[Size]']")
  public List<WebElement> options;

}
