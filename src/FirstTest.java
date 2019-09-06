import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class FirstTest extends CoreTestCase{
    private MainPageObject mainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test //Find locator 'Search Wikipedia' and click on it. Type "Java" and wait result 'Object-oriented programming language'.
    public void testSearchByXpath(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        System.out.println("Well done firstTest! Excellent!");
    }
    @Test //Open app, click 'Search', click X, and check that we return on previous screen.
    public void testCancelSearchById(){
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );
        mainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X-button by ID is still present on the page",
                5
        );
        System.out.println("Well done testCancelSearch!");
    }

    @Test //Clear element from the information that was filled in before
    public void testClearSearch(){
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input by xpath",
                5
        );
        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search input after clearing",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );
        mainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X-button by ID is still present on the page",
                5
        );
        System.out.println("Well done! The testClearSearch has been passed successfully!");
    }

    @Test//2_
    public void testCompareArticleTitle(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        WebElement titleElement =  mainPageObject.waitForElementPresent(
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
    @Test//3_01_02
    public void testSwipeArticleTitle(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search",
                15
        );
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find a title of the article",
                15
        );
        mainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article 'Appium'",
                20
        );
        System.out.println("Well done! The testSwipeArticleTitle has been passed successfully!");
    }

    @Test//3_03
    public void testSaveArticleToMyList() throws InterruptedException {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find a title of the article",
                15
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' tip overlay",
                5
        );
        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot find input to set name 'Learning programming'",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X-link",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                10
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_image_1"),
                //By.xpath("//*[@text='Learning programming']"),-failed sometimes
                "Cannot find created folder",
                5
        );
        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
        System.out.println("Well done! The testSaveArticleToMyList has been passed successfully!");
    }

    @Test//3_05
    public void testAmountOfNotEmptySearch(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Linkin Park discography";
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        mainPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find cannot find anything by request: '" + searchLine + "'",
                15
        );
        int amountOfSearchResult = mainPageObject.getAmountOfElements(By.xpath(searchResultLocator));
        System.out.println("Number of elements on a page = " + amountOfSearchResult);
        Assert.assertTrue(
                "We didn't find any result",
                amountOfSearchResult > 0
        );

        System.out.println("Well done! The testAmountOfNotEmptySearch has been passed successfully!");
    }
    @Test//3_06
    public void testAmountOfEmptySearch() throws InterruptedException {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "zxcvbnmasd";//zxcvbnmasd
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";
        TimeUnit.SECONDS.sleep(10);
        mainPageObject.assertElementNotPresent(
                By.xpath(searchResultLocator),
                "We've found some results by the request: " + searchLine
        );
        mainPageObject.waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Cannot find empty result label by the request: " + searchLine,
                15
        );

        System.out.println("Well done! The testAmountOfEmptySearch has been passed successfully!");
    }

    @Test//3_07
    public void testChangeScreenOrientationOnSearchResult(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Java";
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15
        );
        String titleBeforeRotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find a title of the article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = mainPageObject.waitForElementAndGetAttribute(
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
        String titleAfterSecondRotation = mainPageObject.waitForElementAndGetAttribute(
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

    @Test//3_08
    public void testCheckSearchArticleInBackground(){
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5
        );
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
        driver.runAppInBackground(2);
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after returning from background",
                15
        );

        System.out.println("Well done! The testCheckSearchArticleInBackground has been passed successfully!");
    }
}
