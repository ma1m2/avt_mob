package homework.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigateByUI extends MainPageObj {
    private static final String
            MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']";

    public NavigateByUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists(){
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),//"//android.widget.FrameLayout[@content-desc='My lists']"
                "Cannot find navigation button to my list",
                10
        );
    }
}
