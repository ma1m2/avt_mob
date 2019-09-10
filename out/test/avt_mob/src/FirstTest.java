import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class FirstTest extends CoreTestCase{
/*    private MainPageObject mainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }*/

    @Test //Find locator 'Search Wikipedia' and click on it. Type "Java" and wait result 'Object-oriented programming language'.
    public void testSearchByXpath(){//4_03
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        System.out.println("Well done! The testSearchByXpath has been passed successfully!");
    }
    @Test //Open app, click 'Search', click X, and check that we return on previous screen.
    public void testCancelSearch(){//2_07; 4_04
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Well done! The testCancelSearchById has been passed successfully!");
    }

    @Test //Clear element from the information that was filled in before
    public void testClearSearch(){//2_09;
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clearInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

/*        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' by id",
                5
        );
        mainPageObject.waitForElementAndSendKey(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input by xpath",
                5
        );*/
/*        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search input after clearing",
                5
        );*/
/*        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X-button by id to cancel Search",
                5
        );*/
/*        mainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X-button by ID is still present on the page",
                5
        );*/
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
        assertEquals(
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

/*        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        searchPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find cannot find anything by request: '" + searchLine + "'",
                15
        );*/
        int amountOfSearchResult = searchPageObject.getAmountOfFoundArticles(searchLine);
        System.out.println("Number of elements on a page = " + amountOfSearchResult);
        assertTrue(
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

    @Test//3_07; 4_08
    public void testChangeScreenOrientationOnSearchResult(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickArticleBySubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        System.out.println("Screen orientation is " + driver.getOrientation().toString());

        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals("Article title has been exchange after rotation", titleBeforeRotation, titleAfterRotation);
        this.rotateScreenPortrait();
        System.out.println("Screen orientation is " + driver.getOrientation().toString());

        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals("Article title has been exchange after second rotation", titleAfterRotation, titleAfterSecondRotation);
        System.out.println("Well done! The testChangeScreenOrientationOnSearchResult has been passed successfully!");
    }

    @Test//3_08; 4_08
    public void testCheckSearchArticleInBackground(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        System.out.println("Well done! The testCheckSearchArticleInBackground has been passed successfully!");
    }
}
