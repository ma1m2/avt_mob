package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
  @Test//2_; 4_05
  public void testCompareArticleTitle() {
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickArticleBySubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

    String articleTitle = articlePageObject.getArticleTitle();
    assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
    );
    System.out.println("Well done! The testCompareArticleTitle has been passed successfully!");
  }

  @Test//3_01_02; 4_05
  public void testSwipeArticleTitle() {
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");//("Appium");
    searchPageObject.clickArticleBySubstring("Object-oriented programming language");//("Appium");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    articlePageObject.swipeToFooter();

    System.out.println("Well done! The testSwipeArticleTitle has been passed successfully!");
  }
}
