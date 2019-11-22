package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE,"Cannot article title on page",15);
    }
    public String getArticleTitle(){
        WebElement element = waitForTitleElement();
        return element.getAttribute("text");
    }
    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of article",20);
    }
    public void addArticleToMyList(String nameOfFolder){
        this.waitForElementAndClick(
                OPTION_BUTTON,//"//android.widget.ImageView[@content-desc='More options']"
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,//"//*[@text='Add to reading list']"
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,//"org.wikipedia:id/onboarding_button"
                "Cannot find 'GOT IT' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_INPUT,//org.wikipedia:id/text_input
                "Cannot find input to set name of article folder",
                5
        );
        this.waitForElementAndSendKey(
                MY_LIST_INPUT,//"org.wikipedia:id/text_input"
                nameOfFolder,//"Learning programming"
                "Cannot find input to set name 'Learning programming'",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,//"//*[@text='OK']"
                "Cannot press 'OK' button",
                5
        );
    }
    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,//"//android.widget.ImageButton[@content-desc='Navigate up']"
                "Cannot close article, cannot find X-link",
                5
        );
    }
}
