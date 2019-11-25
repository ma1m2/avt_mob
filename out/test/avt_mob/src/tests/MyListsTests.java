package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String nameOfFolder = "Learning programming";
  @Test//3_03; 4_06
  public void testSaveArticleToMyList() {
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickArticleBySubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    String articleTitle = articlePageObject.getArticleTitle();

    if(Platform.getInstance().isAndroid()){
        articlePageObject.addArticleToMyList(nameOfFolder);
    }else {
        articlePageObject.addArticleToMySaved();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyLists();

    MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
    if(Platform.getInstance().isAndroid()){
        myListPageObject.openFolderByName("Learning programming");
    }
    myListPageObject.removeOverlay();
    myListPageObject.swipeArticleToDelete(articleTitle);

    System.out.println("Well done! The testSaveArticleToMyList has been passed successfully!");
  }

}
