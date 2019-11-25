package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
    protected static String MY_LISTS_LINK ;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    public void clickMyLists(){
        this.waitForElementAndClick(
                MY_LISTS_LINK,//"//android.widget.FrameLayout[@content-desc='My lists']"
                "Cannot find navigation button to my list",
                10
        );
    }
}
