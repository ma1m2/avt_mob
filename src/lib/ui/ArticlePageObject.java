package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.MyListPageObjectFactory;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
  protected static String
          TITLE,
          TITLE_2,
          FOOTER_ELEMENT,
          OPTION_BUTTON,
          OPTION_ADD_TO_MY_LIST_BUTTON,
          ADD_TO_MY_LIST_OVERLAY,
          MY_LIST_INPUT,
          MY_LIST_OK_BUTTON,
          CLOSE_ARTICLE_BUTTON;

  public ArticlePageObject(AppiumDriver driver) {
    super(driver);
  }

  public WebElement waitForTitleElement() {
    return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
  }
  public WebElement waitForTitleSecondArticle() {
    return this.waitForElementPresent(TITLE_2, "Cannot find second article title on page", 15);
  }

  public String getArticleTitle() {
    WebElement element = waitForTitleElement();
    if (Platform.getInstance().isAndroid()) {
      return element.getAttribute("text");
    } else {
      return element.getAttribute("name");
    }
  }

  public void swipeToFooter() {
    if (Platform.getInstance().isAndroid()) {
      this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
    } else {
      this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
    }
  }

  public void addArticleToMyList(String nameOfFolder) {
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
  public void addArticleToMySaved(){
    this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list",5);
  }
  public void closeArticle() {
    this.waitForElementAndClick(
            CLOSE_ARTICLE_BUTTON,//"//android.widget.ImageButton[@content-desc='Navigate up']"
            "Cannot close article, cannot find X-link",
            5
    );
  }
  public void addSecondArticleToMyList() {
    this.waitForElementAndClick(
            OPTION_BUTTON,
            "Cannot find button to open article options",
            5
    );
    this.waitForElementAndClick(
            OPTION_ADD_TO_MY_LIST_BUTTON,//"//*[@text='Add to reading list']"
            "Cannot find option to add article to reading list",
            5
    );
  }
}
