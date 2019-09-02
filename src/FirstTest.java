import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //@Test //Find locator 'Search Wikipedia' and click on it. Type "Java" and wait result 'Object-oriented programming language'.
    public void firstTestByXpath(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        System.out.println("Well done firstTest! Excellent!");
    }
    //@Test //Open app, click 'Search', click X, and check that we return on previous screen.
    public void testCancelSearchById(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X-button by ID is still present on the page",
                5
        );
        System.out.println("Well done testCancelSearch!");
    }

    //@Test //Clear element from the information that was filled in before
    public void testClearSearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input by xpath",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search input after clearing",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X-button by ID is still present on the page",
                5
        );
        System.out.println("Well done! The testClearSearch has been passed successfully!");
    }

    //@Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        WebElement titleElement =  waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find a title of the article",
                15
        );
        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle
        );
        System.out.println("Well done! The testCompareArticleTitle has been passed successfully!");
    }
    //@Test
    public void testSwipeArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find Search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                15
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find a title of the article",
                15
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article 'Appium'",
                20
        );
        System.out.println("Well done! The testSwipeArticleTitle has been passed successfully!");
    }

    //@Test
    public void testSaveArticleToMyList(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find a title of the article",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );
        waitForElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot find input to set name 'Learning programming'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X-link",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find created folder",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
        System.out.println("Well done! The testSaveArticleToMyList has been passed successfully!");
    }

    //@Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Linkin Park discography";
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find cannot find anything by request: '" + searchLine + "'",
                15
        );
        int amountOfSearchResult = getAmountOfElements(By.xpath(searchResultLocator));
        System.out.println("Number of elements on a page = " + amountOfSearchResult);
        Assert.assertTrue(
                "We didn't find any result",
                amountOfSearchResult > 0
        );

        System.out.println("Well done! The testAmountOfNotEmptySearch has been passed successfully!");
    }
    //@Test
    public void testAmountOfEmptySearch() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "zxcvbnmasd";//zxcvbnmasd
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";
        TimeUnit.SECONDS.sleep(10);
        assertElementNotPresent(
                By.xpath(searchResultLocator),
                "We've found some results by the request: " + searchLine
        );
        waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Cannot find empty result label by the request: " + searchLine,
                15
        );

        System.out.println("Well done! The testAmountOfEmptySearch has been passed successfully!");
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Java";
        waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15
        );
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find a title of the article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find a title of the article",
                15
        );
        Assert.assertEquals(
                "Article title has been exchange after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find a title of the article",
                15
        );
        Assert.assertEquals(
                "Article title has been exchange after second rotation",
                titleAfterRotation,
                titleAfterSecondRotation
        );
        System.out.println("Well done! The testChangeScreenOrientationOnSearchResult has been passed successfully!");
    }
    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String errorMessage){
        return waitForElementPresent(by, errorMessage, 5);
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
    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
    protected void swipeUp(int timeOfSwipe){
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
    protected void swipeUpQuick(){
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipe){
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
    protected void swipeElementToLeft(By by, String errorMessage){
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
    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    private void assertElementNotPresent(By by, String errorMessage){
        int amountOfElement = getAmountOfElements(by);
        System.out.println("Number of elements on a page = " + amountOfElement);
        if(amountOfElement > 0){
            String defaultMessage = "An element '" + by.toString() + "' support to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }
    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,errorMessage,timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}
