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

    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToMyList(nameOfFolder);
    } else {
      articlePageObject.addArticleToMySaved();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyLists();

    MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      myListPageObject.openFolderByName(nameOfFolder);
    }

    myListPageObject.removeOverlay();
    myListPageObject.swipeArticleToDelete(articleTitle);

    System.out.println("Well done! The testSaveArticleToMyList has been passed successfully!");
  }

  @Test//6_lesson_HW_Ex11
  public void testSaveTwoArticleIntoOneFolder(){
    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine("Java");
    searchPageObject.clickArticleBySubstring("Object-oriented programming language");

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
    articlePageObject.waitForTitleElement();
    String articleTitle1 = articlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addArticleToMyList(nameOfFolder);
    } else {
      articlePageObject.addArticleToMySaved();
    }
    articlePageObject.closeArticle();

    //1.Save the second article into the same folder 'ReadLater'
    MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
    if (Platform.getInstance().isAndroid()) {
      searchPageObject.initSearchInput();
      searchPageObject.typeSearchLine("Java");
    } else {
      searchPageObject.repeatedSearchInput();
    }
    searchPageObject.clickArticleBySubstring("Java (software platform)");
    articlePageObject.waitForTitleSecondArticle();
    String articleTitle2 = "Java (software platform)";
    if (Platform.getInstance().isAndroid()) {
      articlePageObject.addSecondArticleToMyList();
      myListPageObject.clickFolderByName(nameOfFolder);
    } else {
      articlePageObject.addArticleToMySaved();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyLists();
    if (Platform.getInstance().isAndroid()) {
      myListPageObject.openFolderByName(nameOfFolder); //work sometimes
      //myListPageOb.openFolderByImageID(); always work
    }

    //2.Delete the first article     //Java (programming language)
    myListPageObject.removeOverlay();
    myListPageObject.swipeArticleToDelete(articleTitle1);

    //3.To be sure that the second article is present 4.Go into it
    myListPageObject.openSecondArticle(articleTitle2);
    //4.Be sure that the title is matched
    assertEquals("We see unexpected title", "Java (software platform)", articleTitle2);

    System.out.println("Well done! The testSaveTwoArticleIntoOneFolder has been passed successfully!");
  }
}
