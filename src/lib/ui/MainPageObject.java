package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
  protected AppiumDriver driver;

  public MainPageObject(AppiumDriver driver) {
    this.driver = driver;
  }

  public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
  }

  public WebElement waitForElementPresent(String locator, String errorMessage) {
    return waitForElementPresent(locator, errorMessage, 5);
  }

  public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSecond) {
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSecond);
    element.click();
    return element;
  }

  public WebElement waitForElementAndSendKey(String locator, String value, String errorMessage, long timeoutInSecond) {
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSecond);
    element.sendKeys(value);
    return element;
  }

  public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
    By by = this.getLocatorByString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(errorMessage + "\n");
    return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
    );
  }

  public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    element.clear();
    return element;
  }

  public void swipeUp(int timeOfSwipe) {
    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize();
    int x = size.width / 2;
    int startY = (int) (size.height * 0.8);//at the bottom of screen
    int endY = (int) (size.height * 0.2);//at the top of screen
    action
            .press(x, startY)
            .waitAction(timeOfSwipe)
            .moveTo(x, endY)
            .release()
            .perform();
  }

  public void swipeUpQuick() {
    swipeUp(200);
  }

  public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipe) {
    By by = this.getLocatorByString(locator);
    int alreadySwipe = 0;
    while (driver.findElements(by).size() == 0) {
      if (alreadySwipe > maxSwipe) {
        waitForElementPresent(locator, "Cannot find element by swiping Up. \n" + errorMessage, 0);
        System.out.println("Cannot find element by swiping Up. \n" + errorMessage);
        return;
      }
      swipeUpQuick();
      alreadySwipe++;
    }
  }

  public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipe) {//for iOS 6_04
    int alreadySwipe = 0;
    while (!this.isElementLocatedOnTheScreen(locator)) {
      if (alreadySwipe > maxSwipe) {
        Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
      }
      swipeUpQuick();
      ++alreadySwipe;
    }
  }

  public boolean isElementLocatedOnTheScreen(String locator) {//for iOS 6_04
    int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator '" + locator + "'", 1).getLocation().getY();
    int screenSizeByY = driver.manage().window().getSize().getHeight();
    return elementLocationByY < screenSizeByY;
  }
  public void clickElementToTheRightUpperCorner(String locator, String errorMessage){
    WebElement element= this.waitForElementPresent(locator + "/..",errorMessage);
    int leftX = element.getLocation().getX();
    int upperY = element.getLocation().getY();
    int lowerY = upperY + element.getSize().getHeight();
    int middleY = (upperY + lowerY) / 2;
    int width = element.getSize().getWidth();

    int pointToClickX = (leftX + width)-3;
    int pointToClickY = middleY;
    TouchAction action = new TouchAction(driver);
    action.tap(pointToClickX,pointToClickY).perform();

  }
  public void swipeElementToLeft(String locator, String errorMessage) {
    WebElement element = waitForElementPresent(locator, errorMessage, 10);
    int leftX = element.getLocation().getX();
    int rigthX = leftX + element.getSize().getWidth();
    int upperY = element.getLocation().getY();
    int lowerY = upperY + element.getSize().getHeight();
    int middleY = (upperY + lowerY) / 2;

    TouchAction action = new TouchAction(driver);
    action.press(rigthX, middleY);
    action.waitAction(1000);
    if(Platform.getInstance().isAndroid()){
      action.moveTo(leftX, middleY);
    }else{
      int offsetX = -1 * element.getSize().getWidth();
      action.moveTo(offsetX,0);
    }
    action.release();
    action.perform();
  }

  public int getAmountOfElements(String locator) {
    By by = this.getLocatorByString(locator);
    List elements = driver.findElements(by);
    return elements.size();
  }

  public void assertElementNotPresent(String locator, String errorMessage) {
    int amountOfElement = getAmountOfElements(locator);
    System.out.println("Number of elements on a page = " + amountOfElement);
    if (amountOfElement > 0) {
      String defaultMessage = "An element by '" + locator + "' suppose to be not present";
      throw new AssertionError(defaultMessage + " " + errorMessage);
    }
  }

  public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
    return element.getAttribute(attribute);
  }

  private By getLocatorByString(String locatorWithType) {
    String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
    String byType = explodedLocator[0];
    String locator = explodedLocator[1];
    if (byType.equals("xpath")) {
      return By.xpath(locator);
    } else if (byType.equals("id")) {
      return By.id(locator);
    } else {
      throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
    }
  }
  public void tapAnywhe(int x, int y){
    TouchAction action = new TouchAction(driver);
    action.tap(x,y).perform();
  }
}
