package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {
  static {
    ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";//Java (programming language)
    CLOSE_OVERLAY = "xpath://XCUIElementTypeButton[@name='close']";//not work
  }
  public iOSMyListPageObject(AppiumDriver driver) {
    super(driver);
  }
}
