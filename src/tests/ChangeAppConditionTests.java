package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test//3_07; 4_08
    public void testChangeScreenOrientationOnSearchResult(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickArticleBySubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

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
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        System.out.println("Well done! The testCheckSearchArticleInBackground has been passed successfully!");
    }
}
