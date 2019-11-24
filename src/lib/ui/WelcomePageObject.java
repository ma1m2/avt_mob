package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
  private static final String
          STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
          STEP_NEW_WAY_TO_EXPLORE_TEXT = "id:New ways to explore",
          STEP_ADD_OR_EDIT_PREFERRED_LANG = "id:Add or edit preferred languages",
          STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
          NEXT_BUTTON = "id:Next",
          GET_STARTED_BUTTON = "id:Get started",
          SKIP = "id:Skip";


  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForLearnMoreLink() {
    this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
  }

  public void waitForNewWayToExploreText() {
    this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 10);
  }

  public void waitForAddOrEditPreferredLangLink() {
    this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG, "Cannot find 'Add or edit preferred languages' link", 10);
  }

  public void waitForLearnMoreAboutDataCollectedLink() {
    this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
  }

  public void clickNextButton() {
    this.waitForElementAndClick(NEXT_BUTTON, "Cannot find 'Next' button", 10);
  }

  public void clickGetStartedtButton() {
    this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find 'Get started' button", 10);
  }

  public void clickSkip() {
    this.waitForElementAndClick(SKIP, "Cannot find 'Skip' button", 10);
  }
}
