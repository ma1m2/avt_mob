import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\x1y1z\\Documents\\GitHub\\avt_mob\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    //@Test
    public void firstTest(){
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' on page",
                5
        );
        waitForElementByXpathAndSendKey(
                "//*[contains(@text,'Search…')]",
                "Java",
                "Cannot find Search input",
                5
        );
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic searching by java",
                15
        );
        System.out.println("Well done! Excellent!");
    }
    @Test
    public void testCancelSearchByXpath(){
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' on page",
                5
        );
        waitForElementByXpathAndClick(
                "//*[@resource-id='org.wikipedia:id/search_close_btn']",
                "Cannot find X-button by id to cancel Search",
                5
        );
        waitForElementNotPresentByXpath(
                //"org.wikipedia:id/search_close_btn",
                "//*[@resource-id='org.wikipedia:id/search_close_btn']",
                "X-button by id is still present on the page",
                5
        );

        System.out.println("Well done!");
    }
    //@Test
    public void testCancelSearchById(){
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X-button by id to cancel Search",
                5
        );
    }

    private WebElement waitForElementPresentByXpath(String xPath, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xPath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresentByXpath(String xPath, String errorMessage){
        return waitForElementPresentByXpath(xPath, errorMessage, 5);
    }
    private WebElement waitForElementByXpathAndClick(String xPath, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresentByXpath(xPath, errorMessage, timeoutInSecond);
        element.click();
        return element;
    }
    private WebElement waitForElementByXpathAndSendKey(String xPath, String value, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresentByXpath(xPath, errorMessage, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementPresentById(String id, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementByIdAndClick(String id, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresentById(id, errorMessage, timeoutInSecond);
        element.click();
        return element;
    }
    private boolean waitForElementNotPresentById(String id, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private boolean waitForElementNotPresentByXpath(String xPath, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xPath);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
