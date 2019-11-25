package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

  protected static String
          FOLDER_BY_NAME_TMP,
          CLOSE_OVERLAY,
          ARTICLE_BY_TITLE_TMP;//Java (programming language)

  public MyListPageObject(AppiumDriver driver) {
    super(driver);
  }

  /*TEMPLATES*/
  private String getFolderByNameTmp(String nameOfFolder) {
    return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", nameOfFolder);
  }

  private String getArticleTitleByXpath(String articleTitle) {
    return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", articleTitle);
  }
  /*TEMPLATES*/

  public void openFolderByName(String nameFolder) {
    String folderNameXpath = getFolderByNameTmp(nameFolder);
    this.waitForElementAndClick(
            //By.id("org.wikipedia:id/item_image_1"),//passed always
            folderNameXpath,//failed sometimes
            "Cannot find folder by name " + nameFolder,
            5
    );
  }

  public void waitForArticleToAppearByTitle(String articleTitle) {
    String articleXpath = getArticleTitleByXpath(articleTitle);
    this.waitForElementPresent(articleXpath, "Cannot find saved article by title " + articleTitle, 15
    );
  }

  public void waitForArticleToDisappearByTitle(String articleTitle) {
    String articleXpath = getArticleTitleByXpath(articleTitle);
    this.waitForElementNotPresent(articleXpath, "Saved article still disappeared by title " + articleTitle, 15
    );
  }

  public void swipeArticleToDelete(String articleTitle) {
    String articleXpath = getArticleTitleByXpath(articleTitle);
    this.waitForArticleToAppearByTitle(articleTitle);
    this.swipeElementToLeft(
            articleXpath,//"//*[@text='Java (programming language)']"
            "Cannot find saved article " + articleTitle
    );
    if(Platform.getInstance().isIOS()){
        this.clickElementToTheRightUpperCorner(articleXpath,"Cannot find Red Bin");
    }
    this.waitForArticleToDisappearByTitle(articleTitle);
  }

  public void removeOverlay(){
      this.tabAnywher(150,500);
  }
  public void closeOverlay() {//not work
    this.waitForElementAndClick(CLOSE_OVERLAY, "Cannot find Overlay to close by xpath: " + CLOSE_OVERLAY, 5);
  }
}
