package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {
  static {
    FOLDER_BY_NAME_TMP = "xpath://*[@text='{FOLDER_NAME}']";
    ARTICLE_BY_TITLE_TMP = "xpath://*[@text='{TITLE}']";//Java (programming language)
  }

  public AndroidMyListPageObject(AppiumDriver driver) {
    super(driver);
  }
}
