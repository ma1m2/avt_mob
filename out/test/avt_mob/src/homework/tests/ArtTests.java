package homework.tests;

import homework.lib.CoreTests;
import homework.lib.ui.ArticlePageOb;
import homework.lib.ui.SearchPageOb;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ArtTests extends CoreTests {
    @Test//Ex6
    public void testAssertTitle() throws InterruptedException {
        SearchPageOb searchPageOb = new SearchPageOb(driver);
        String searchLine = "Java";
        searchPageOb.initSearchInput();
        searchPageOb.typeSearchLine(searchLine);
        searchPageOb.clickArticleBySubstring("Object-oriented programming language");

        TimeUnit.SECONDS.sleep(5);
        ArticlePageOb articlePageOb = new ArticlePageOb(driver);
        articlePageOb.assertTitle();

        System.out.println("Well done! The testAssertTitle has been passed successfully!");
    }
}
