package homework.tests;

import homework.lib.CoreTests;
import homework.lib.ui.ArticlePageOb;
import homework.lib.ui.MyListPageOb;
import homework.lib.ui.NavigateByUI;
import homework.lib.ui.SearchPageOb;
import org.junit.Test;

public class FolderTests extends CoreTests {
    @Test//Ex5
    public void testSaveTwoArticleIntoOneFolder() throws InterruptedException {
        SearchPageOb searchPageOb = new SearchPageOb(driver);
        String searchLine = "Android";
        searchPageOb.initSearchInput();
        searchPageOb.typeSearchLine(searchLine);
        searchPageOb.clickArticleBySubstring("Android (operating system)");
                //("Open-source operating system for mobile devices created by Google");

        ArticlePageOb articlePageOb = new ArticlePageOb(driver);
        articlePageOb.waitForTitleElement();
        articlePageOb.addFirstArticleToMyList("ReadLater");
        articlePageOb.closeArticle();

        //1.Save the second article into the same folder 'ReadLater'
        searchPageOb.initSearchInput();
        searchPageOb.typeSearchLine(searchLine);
        searchPageOb.clickArticleBySubstring("Android version history");
        articlePageOb.waitForTitleElement();
        articlePageOb.addSecondArticleToMyList();
        articlePageOb.closeArticle();
        NavigateByUI navigateByUI = new NavigateByUI(driver);
        navigateByUI.clickMyLists();
        MyListPageOb myListPageOb = new MyListPageOb(driver);
        //myListPageOb.openFolderByName("ReadLater");
        myListPageOb.openFolderByImageID();

        //2.Delete the first article
        myListPageOb.swipeArticleToDelete("Android (operating system)");
        //3.To be sure that the second article is present 4.Go into it
        myListPageOb.openArticle();
        //4.Be sure that the title is matched
        String articleTitle = articlePageOb.waitForTitleElement().getAttribute("text");
        assertEquals("We see unexpected title", "Android version history", articleTitle);

        System.out.println("Well done! The testSaveTwoArticleIntoOneFolder has been passed successfully!");
    }
}
