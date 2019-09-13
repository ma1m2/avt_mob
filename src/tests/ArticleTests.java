package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
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
}