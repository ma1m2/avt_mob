package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(By by, String errorMessage){
        return waitForElementPresent(by, errorMessage, 5);
    }
    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSecond);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int startY = (int) (size.height*0.8);//at the bottom of screen
        int endY = (int) (size.height*0.2);//at the top of screen
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }
    public void swipeUpQuick(){
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipe){
        int alreadySwipe = 0;
        while (driver.findElements(by).size()==0){
            if(alreadySwipe > maxSwipe){
                waitForElementPresent(by, "Cannot find element by swiping Up. \n" + errorMessage,0);
                System.out.println("Cannot find element by swiping Up. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            alreadySwipe++;
        }
    }
    public void swipeElementToLeft(By by, String errorMessage){
        WebElement element = waitForElementPresent(by, errorMessage,10);
        int leftX = element.getLocation().getX();
        int rigthX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rigthX, middleY)
                .waitAction(1000)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }
    public int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementNotPresent(By by, String errorMessage){
        int amountOfElement = getAmountOfElements(by);
        System.out.println("Number of elements on a page = " + amountOfElement);
        if(amountOfElement > 0){
            String defaultMessage = "An element '" + by.toString() + "' suppose to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }
    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,errorMessage,timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
