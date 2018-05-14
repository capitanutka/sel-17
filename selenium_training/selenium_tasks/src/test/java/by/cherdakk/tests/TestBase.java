package by.cherdakk.tests;

import org.junit.After;
import org.junit.Before;
import by.cherdakk.app.Application;

public class TestBase {
  public Application app;

  @Before
  public void start() {
    app = new Application();
  }

  @After
  public void stop() {
    app.quit();
  }

}
