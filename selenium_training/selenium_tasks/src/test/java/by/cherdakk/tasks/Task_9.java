package by.cherdakk.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task_9 {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
    driver.findElement(By.cssSelector("[name=login]")).click();
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void test_1A_countriesAreSorted() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    List<WebElement> nameCells = driver.findElements(By.cssSelector("form[name=countries_form] tr.row td:nth-child(5) a"));

    Assert.assertTrue("Countries are not sorted", isColumnSorted(nameCells));

  }

  @Test
  public void test_1B_zonesAreSorted() {
    List<String> failedItems = new ArrayList<>();

    List<String> links = new ArrayList<>();
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    List<WebElement> countryNameCells = driver.findElements(By.xpath("//form//tr[@class='row']/td[6][not(.='0')]/../td[5]/a"));
    for (WebElement countryNameCell : countryNameCells) {
      links.add(countryNameCell.getAttribute("href"));
    }

    for (String link : links) {
      List<String> zones = new ArrayList<>();
      driver.get(link);
      List<WebElement> zoneNameCells = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3)"));
      zoneNameCells.remove(zoneNameCells.size() - 1);

      if (!isColumnSorted(zoneNameCells)) {
        failedItems.add(link);
      }
    }
    Assert.assertTrue("Links to countries with unsorted zones: " + failedItems.toString(), failedItems.size() == 0);
  }

  @Test
  public void test_2_geoZonesAreSorted() {
    List<String> failedItems = new ArrayList<>();

    List<String> links = new ArrayList<>();
    driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    List<WebElement> countryNameCells = driver.findElements(By.cssSelector("form[name=geo_zones_form] tr.row td:nth-child(3) a"));
    for (WebElement countryNameCell : countryNameCells) {
      links.add(countryNameCell.getAttribute("href"));
    }

    for (String link : links) {
      List<String> zones = new ArrayList<>();
      driver.get(link);
      List<WebElement> zoneNameCells = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3) option[selected]"));
      if (!isColumnSorted(zoneNameCells)) {
        failedItems.add(link);
      }
    }
    Assert.assertTrue("Links to countries with unsorted zones: " + failedItems.toString(), failedItems.size() == 0);
  }


  private boolean isColumnSorted(List<WebElement> column) {
    List<String> values = new ArrayList<>();
    for (WebElement cell : column) {
      values.add(cell.getText());
    }

    List<String> valuesSorted = new ArrayList<>(values);
    valuesSorted.sort(Comparator.naturalOrder());

    return values.equals(valuesSorted);
  }

}
