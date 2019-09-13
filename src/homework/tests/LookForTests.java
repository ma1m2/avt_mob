package homework.tests;

import homework.lib.CoreTests;
import homework.lib.ui.SearchPageOb;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LookForTests extends CoreTests {
    @Test//Find text “Search…” in field and compare with expected result
    public void testEx2(){
        SearchPageOb searchPageOb = new SearchPageOb(driver);

        searchPageOb.startSearchInput();
        searchPageOb.assertValueOfSearchAttributeIsTrue();

        System.out.println("Well done! The testEx2 has been passed successfully!");
    }

    @Test//Find text “Search…” in field and compare with expected result
    public void testEx3(){
        SearchPageOb searchPageOb = new SearchPageOb(driver);
        String searchLine = "Apple";

        searchPageOb.initSearchInput();
        searchPageOb.typeSearchLine(searchLine);

        //be sure that 3 articles are found
        String textTitle = searchPageOb.waitArticleAndGetValueOfAtribut("Apple");
        assertEquals("We see unexpected text in title item","Apple",textTitle);
        System.out.println("Well done! " + textTitle);

        textTitle = searchPageOb.waitArticleAndGetValueOfAtribut("Apple Inc.");
        assertTrue("We see unexpected text in title item_1 'Apple Inc.'",textTitle.equals("Apple Inc."));
        System.out.println("Well done! " + textTitle);

        textTitle = searchPageOb.waitArticleAndGetValueOfAtribut("Apple TV");
        assertTrue("We see unexpected text in title item_1 'Apple Inc.'",textTitle.equals("Apple TV"));
        System.out.println("Well done! " + textTitle);

        //cancel search
        searchPageOb.clickCancelSearch();
        System.out.println("Well done! Search has been canceled");

        //be sure that result of search is disappeared
        searchPageOb.waitToDisappearResultOfSearch();
        System.out.println("Well done! The testEx3 has been passed successfully!");
    }

    @Test//Find list of elements by some word and assert that all results have this word
    public void testEx4(){
        List<WebElement> elements = new ArrayList<>();
        SearchPageOb searchPageOb = new SearchPageOb(driver);
        String searchLine = "Apple";
        String expectedResult = "APPLE";

        searchPageOb.initSearchInput();
        searchPageOb.typeSearchLine(searchLine);
        //search some word
        searchPageOb.waitForSearchResultByID(searchLine);
        //pick up all found elements and compare in loop
        searchPageOb.assertSearchWordIsInEveryResult(expectedResult);
    }
}
