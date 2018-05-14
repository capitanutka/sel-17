package by.cherdakk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);

  }

  public void open() {
    driver.get("http://localhost/litecart/");
  }

  @FindBy(css = "li.product a.link")
  public WebElement product;

}
