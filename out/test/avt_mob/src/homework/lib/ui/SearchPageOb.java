package homework.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageOb extends MainPageObj {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_INPUT_ID = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",//Object-oriented programming language
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_ID = "org.wikipedia:id/page_list_item_title";//org.wikipedia:id/page_list_item_title

    public SearchPageOb(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search input element", 5);
    }
    public void startSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }
    public void typeSearchLine(String search) {
        this.waitForElementAndSendKey(By.xpath(SEARCH_INPUT), search, "Cannot find and type into search input", 5);
    }
    public WebElement getSearchInputElement() {
        return this.waitForElementPresent(By.id(SEARCH_INPUT_ID), "Cannot find Search input", 15);
    }
    public void assertValueOfSearchAttributeIsTrue() {//Ex2
        String textSearch = this.getSearchInputElement().getAttribute("text");
        Assert.assertEquals("We see unexpected text in Search input", "Search…", textSearch);
    }
    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath),
                "Cannot find search result with substring ", 15);
    }
    public void waitForSearchResultByID(String searchLine){
        this.waitForElementPresent(By.id(SEARCH_RESULT_BY_ID),
                "Cannot find search result by ID " + searchLine, 15);
    }
    public String waitArticleAndGetValueOfAtribut(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        WebElement titleItem = this.waitForElementPresent(By.xpath(searchResultXpath),
                "Cannot find search result with substring '" + substring + "'", 10);
        return titleItem.getAttribute("text");
    }
    public void clickCancelSearch(){//4_04_video
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click search cancel button",5);
    }
    public void waitToDisappearResultOfSearch(){
        waitForElementNotPresent(By.id(SEARCH_RESULT_BY_ID), "Result of search by ID is still present on the page", 5);
    }
    public void clickArticleBySubstring(String substring){//4_05_video
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath),"Cannot find and click search result with substring " + substring,10);
    }
    public void assertSearchWordIsInEveryResult(String expectedResult){//"APPLE"
        List<WebElement> elements = new ArrayList<>();
        elements = driver.findElements(By.id(SEARCH_RESULT_BY_ID));
        System.out.println("Number of elements on a page = " + elements.size());
        for(WebElement element:elements){
            String str = element.getAttribute("text").toUpperCase();
            System.out.println(str);
            Assert.assertEquals("We see unexpected text on a page",expectedResult,str.substring(0,5));
        }
    }
}
