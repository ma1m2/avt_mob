package homework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class HomeWork {
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

    //@Test//Find text “Search…” in field and compare with expected result
    public void testEx2(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        WebElement searchElement =  waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search input",
                15
        );
        String textSearch = searchElement.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected text in Search input",
                "Search…",
                textSearch
        );
        System.out.println("Well done! The testEx2 has been passed successfully!");

    }

    @Test//Find text “Search…” in field and compare with expected result
    public void testEx3(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        //search some word
        waitForElementAndSendKey(
                By.id("org.wikipedia:id/search_src_text"),
                "Apple",
                "Cannot find Search input",
                5
        );

        //be sure that 3 articles are found
        WebElement titleItem =  waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Apple']"),
                "Cannot find a title item 'Apple'",
                10
        );
        String textTitle = titleItem.getAttribute("text");
        Assert.assertTrue("We see unexpected text in title item",textTitle.equals("Apple"));
        System.out.println("Well done! " + textTitle);

        WebElement titleItem1 =  waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Apple Inc.']"),
                "Cannot find a title item_1 'Apple Inc.' ",
                10
        );
        String textTitle1 = titleItem1.getAttribute("text");
        Assert.assertTrue("We see unexpected text in title item_1 'Apple Inc.'",textTitle1.equals("Apple Inc."));
        System.out.println("Well done! " + textTitle1);

        WebElement titleItem2 =  waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Apple TV']"),
                "Cannot find a title item_2 'Apple TV'",
                10
        );
        String textTitle2 = titleItem2.getAttribute("text");
        Assert.assertTrue("We see unexpected text in title item_2 'Apple TV'",textTitle2.equals("Apple TV"));
        System.out.println("Well done! " + textTitle2);

        //cancel search
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );
        System.out.println("Well done! Search has been canceled");

        //be sure that result of search is disappeared
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Result of search by ID is still present on the page",
                5
        );
        System.out.println("Well done! The testEx3 has been passed successfully!");
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSecond);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}
