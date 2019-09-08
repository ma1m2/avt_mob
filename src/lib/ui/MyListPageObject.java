package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
        FOLDER_BY_NAME_TMP = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TMP = "//*[@text='{TITLE}']";//Java (programming language)

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES*/
    private String getFolderByNameTmp(String nameOfFolder){
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", nameOfFolder);
    }
    private String getArticleTitleByXpath(String articleTitle) {
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", articleTitle);
    }
    /*TEMPLATES*/

    public void openFolderByName(String nameFolder){
        String folderNameXpath = getFolderByNameTmp(nameFolder);
        this.waitForElementAndClick(
                //By.id("org.wikipedia:id/item_image_1"),//passed always
                By.xpath(folderNameXpath),//failed sometimes
                "Cannot find folder by name " + nameFolder,
                5
        );
    }
    public void waitForArticleToAppearByTitle(String articleTitle){
        String articleXpath = getArticleTitleByXpath(articleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Cannot find saved article by title " + articleTitle, 15
        );
    }
    public void waitForArticleToDisappearByTitle(String articleTitle){
        String articleXpath = getArticleTitleByXpath(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article still disappeared by title " + articleTitle, 15
        );
    }
    public void swipeArticleToDelete(String articleTitle){
        String articleXpath = getArticleTitleByXpath(articleTitle);
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(
                By.xpath(articleXpath),//"//*[@text='Java (programming language)']"
                "Cannot find saved article " + articleTitle
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }


}
