import lib.CoreTestCase;
import lib.ui.*;
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
    public void testSearchByXpath(){//4_03
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        System.out.println("Well done! The testSearchByXpath has been passed successfully!");
    }
    @Test //Open app, click 'Search', click X, and check that we return on previous screen.
    public void testCancelSearchById(){//2_07; 4_04
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Well done! The testCancelSearchById has been passed successfully!");
    }

    @Test //Clear element from the information that was filled in before
    public void testClearSearch(){//2_09; 4-05
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

    @Test//2_; 4_05
    public void testCompareArticleTitle(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickArticleBySubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String articleTitle = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle
        );
        System.out.println("Well done! The testCompareArticleTitle has been passed successfully!");
    }
    @Test//3_01_02; 4_05
    public void testSwipeArticleTitle(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickArticleBySubstring("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

        System.out.println("Well done! The testSwipeArticleTitle has been passed successfully!");
    }

    @Test//3_03; 4_06
    public void testSaveArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickArticleBySubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName("Learning programming");
        myListPageObject.swipeArticleToDelete(articleTitle);
        //myListPageObject.waitForArticleToDisappearByTitle("Java (programming language)");

        System.out.println("Well done! The testSaveArticleToMyList has been passed successfully!");
    }

    @Test//3_05; 4_07
    public void testAmountOfNotEmptySearch(){

        String searchLine = "Linkin Park discography";
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        mainPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find cannot find anything by request: '" + searchLine + "'",
                15
        );
        int amountOfSearchResult = searchPageObject.getAmountOfFoundArticles(searchLine);
        System.out.println("Number of elements on a page = " + amountOfSearchResult);
        Assert.assertTrue(
                "We didn't find any result",
                amountOfSearchResult > 0
        );

        System.out.println("Well done! The testAmountOfNotEmptySearch has been passed successfully!");
    }
    @Test//3_06; 4_07
    public void testAmountOfEmptySearch() throws InterruptedException {
        String searchLine = "jzxcvbnmasd";//zxcvbnmasd//java
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        TimeUnit.SECONDS.sleep(5);

        searchPageObject.assertThereIsNoResultSearch(searchLine);
        searchPageObject.waitForEmptyResultLabel(searchLine);

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
