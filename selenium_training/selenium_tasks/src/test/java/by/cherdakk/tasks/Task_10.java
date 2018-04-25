package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task_10 {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.get("http://localhost/litecart/");
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void task_10() {
    List<String> failedItems = new ArrayList<>();

    WebElement duck = driver.findElement(By.cssSelector("div#box-campaigns li.product"));

    //Is Regular price crossed on the Main page?
    String styleRegularPriceOnMainPage = duck.findElement(By.cssSelector("s[class=regular-price]")).getCssValue("text-decoration-line");
    if (!styleRegularPriceOnMainPage.equals("line-through")) {
      failedItems.add("Regular price is not crossed out on the Main page");
    }

    //Is Regular price grey on the Main page?
    String colorRegularPriceOnMainPage = duck.findElement(By.cssSelector("s[class=regular-price]")).getCssValue("color");
    List<Integer> rgb = parseColor(colorRegularPriceOnMainPage);
    if ((rgb.get(0) != rgb.get(1)) || (rgb.get(0) != rgb.get(2))) {
      failedItems.add("Regular price is not grey on the Main page");
    }

    //Is Campaign price bold on the Main page?
    String styleCampaignPriceOnMainPage = duck.findElement(By.cssSelector("strong[class=campaign-price]")).getCssValue("font-weight");
    if (!styleCampaignPriceOnMainPage.equals("bold") && !styleCampaignPriceOnMainPage.equals("bolder") && !(Integer.parseInt(styleCampaignPriceOnMainPage) >= 700)) {
      failedItems.add("Campaign price is not bold on the Main page");
    }

    //Is Campaign price red on the Main page?
    String colorCampaignPriceOnMainPage = duck.findElement(By.cssSelector("strong[class=campaign-price]")).getCssValue("color");
    rgb = parseColor(colorCampaignPriceOnMainPage);
    if ((rgb.get(1) != 0) || (rgb.get(2) != 0)) {
      failedItems.add("Campaign price is not red on the Main page");
    }

    //Is Campaign price bigger than Regular price on the Main page?
    Dimension sizeRegularPriceString = duck.findElement(By.cssSelector("s[class=regular-price]")).getSize();
    Dimension sizeCampaignPriceString = duck.findElement(By.cssSelector("strong[class=campaign-price]")).getSize();
    if ((sizeRegularPriceString.getHeight() >= sizeCampaignPriceString.getHeight()) || (sizeRegularPriceString.getWidth() >= sizeCampaignPriceString.getWidth())) {
      failedItems.add("Campaign price is not bigger than Regular price on the Main page");
    }

    //Save some info from the Main page for future checking
    String nameOnMainPage = duck.findElement(By.cssSelector("div[class=name]")).getAttribute("textContent");
    String regularPriceOnMainPage = duck.findElement(By.cssSelector("s[class=regular-price]")).getAttribute("textContent");
    String campaignPriceOnMainPage = duck.findElement(By.cssSelector("strong[class=campaign-price]")).getAttribute("textContent");

    //Go to the product own page
    duck.click();

    //Are product tittles on Main and Own pages are not equal?
    String nameOnOwnPage = driver.findElement(By.cssSelector("h1[class=title]")).getAttribute("textContent");
    if (!nameOnMainPage.equals(nameOnOwnPage)) {
      failedItems.add("Product tittles on Main and Own pages are not equal");
    }

    //Are Regular prices on Main and Own pages are not equal?
    String regularPriceOnOwnPage = driver.findElement(By.cssSelector("s[class=regular-price]")).getAttribute("textContent");
    if (!regularPriceOnMainPage.equals(regularPriceOnOwnPage)) {
      failedItems.add("Regular prices on Main and Own pages are not equal");
    }

    //Are Campaign prices on Main and Own pages are not equal?
    String campaignPriceOnOwnPage = driver.findElement(By.cssSelector("strong[class=campaign-price]")).getAttribute("textContent");
    if (!campaignPriceOnMainPage.equals(campaignPriceOnOwnPage)) {
      failedItems.add("Campaign prices on Main and Own pages are not equal");
    }

    //Is Regular price crossed on the Own page?
    String styleRegularPrice = driver.findElement(By.cssSelector("s[class=regular-price]")).getCssValue("text-decoration-line");
    if (!styleRegularPrice.equals("line-through")) {
      failedItems.add("Regular price is not crossed out on the Main page");
    }

    //Is Regular price grey on the Own page?
    String colorRegularPrice = driver.findElement(By.cssSelector("s[class=regular-price]")).getCssValue("color");
    rgb = parseColor(colorRegularPrice);
    if ((rgb.get(0) != rgb.get(1)) || (rgb.get(0) != rgb.get(2))) {
      failedItems.add("Regular price is not grey on the Own page");
    }

    //Is Campaign price bold on the Own page?
    String styleCampaignPrice = driver.findElement(By.cssSelector("strong[class=campaign-price]")).getCssValue("font-weight");
    if (!styleCampaignPriceOnMainPage.equals("bold") && !styleCampaignPriceOnMainPage.equals("bolder") && !(Integer.parseInt(styleCampaignPriceOnMainPage) >= 700)) {
      failedItems.add("Campaign price is not bold on the Own page");
    }

    //Is Campaign price red on the Own page?
    String colorCampaignPrice = driver.findElement(By.cssSelector("strong[class=campaign-price]")).getCssValue("color");
    rgb = parseColor(colorCampaignPrice);
    if ((rgb.get(1) != 0) || (rgb.get(2) != 0)) {
      failedItems.add("Campaign price is not red on the Own page");
    }

    //Is Campaign price bigger than Regular price on the Own page?
    Dimension sizeRegularPrice = driver.findElement(By.cssSelector("s[class=regular-price]")).getSize();
    Dimension sizeCampaignPrice = driver.findElement(By.cssSelector("strong[class=campaign-price]")).getSize();
    if ((sizeRegularPrice.getHeight() >= sizeCampaignPriceString.getHeight()) || (sizeRegularPrice.getWidth() >= sizeCampaignPriceString.getWidth())) {
      failedItems.add("Campaign price is not bigger than Regular price on the Own page");
    }

  }

  private List<Integer> parseColor(String color)  {
    List<Integer> rgb = new ArrayList<>();
    String[] numbers = color.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");
    rgb.add(Integer.parseInt(numbers[0].trim()));
    rgb.add(Integer.parseInt(numbers[1].trim()));
    rgb.add(Integer.parseInt(numbers[1].trim()));

    return rgb;
  }


}

