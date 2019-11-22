package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
        SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",//Object-oriented programming language
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /*TEMPLATES METHODS*/

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input after clicking search input element");
    }
    public void typeSearchLine(String search){
        this.waitForElementAndSendKey(SEARCH_INPUT,search,"Cannot find and type into search input",5);
    }
    public void waitForSearchResult(String substring){//String substring
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath,"Cannot find search result with substring ",15);
    }
    public void waitForCancelButtonToAppear(){//4_04_video
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button",5);
    }
    public void waitForCancelButtonToDisappear(){//4_04_video
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present",5);
    }
    public void clickCancelSearch(){//4_04_video
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",5);
    }
    public void clickArticleBySubstring(String substring){//4_05_video
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath,"Cannot find and click search result with substring " + substring,10);
    }
    public int getAmountOfFoundArticles(String searchLine){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find cannot find anything by request: '" + searchLine + "'", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultLabel(String searchLine){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label by the request: " + searchLine,
                15
        );
    }
    public void assertThereIsNoResultSearch(String searchLine){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We've found some results by the request: " + searchLine);
    }
    public void clearInput(){
        this.waitForElementAndClear(SEARCH_INPUT_ID,"Cannot find Search input after clearing",15);
    }
}
