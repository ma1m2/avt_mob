package homework.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageOb extends MainPageObj {
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            TITLE_XPATH_TMP = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@text='{TITLE}']",//Java (programming language)
            OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            FOLDER_XPATH_TMP = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']";//ReadLater

    public ArticlePageOb(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpath(String substring) {
        return FOLDER_XPATH_TMP.replace("{FOLDER_NAME}", substring);
    }
    private static String getTitleXpath(String substring) {
        return TITLE_XPATH_TMP.replace("{TITLE}", substring);
    }
    /*TEMPLATES METHODS*/

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public void assertTitle() {
        String titleXpath = getTitleXpath("Java (programming language)");
        this.assertElementPresent(By.xpath(titleXpath), "Cannot find title by request ");
    }

    public void addFirstArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),//"//android.widget.ImageView[@content-desc='More options']"
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),//"//*[@text='Add to reading list']"
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),//"org.wikipedia:id/onboarding_button"
                "Cannot find 'GOT IT' tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_INPUT),//org.wikipedia:id/text_input
                "Cannot find input to set name of article folder",
                5
        );
        this.waitForElementAndSendKey(
                By.id(MY_LIST_INPUT),//"org.wikipedia:id/text_input"
                nameOfFolder,//"Learning programming"
                "Cannot find input to set name 'Learning programming'",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),//"//*[@text='OK']"
                "Cannot press 'OK' button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),//"//android.widget.ImageButton[@content-desc='Navigate up']"
                "Cannot close article, cannot find X-link",
                5
        );
    }

    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),//"//*[@text='Add to reading list']"
                "Cannot find option to add article to reading list",
                5
        );
        String folderXpath = getFolderXpath("ReadLater");//"//*[@resource-id='org.wikipedia:id/item_title'][@text='ReadLater']";
        this.waitForElementAndClick(
                By.xpath(folderXpath),
                "Cannot find 'ReadLater' folder by '" + folderXpath + "'",
                5
        );
    }
}
