package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase{
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

}
