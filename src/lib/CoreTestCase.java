package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase extends TestCase {
  public static final String PLATFORM_IOS = "ios";
  public static final String PLATFORM_ANDROID = "android";

  protected AppiumDriver driver;
  protected DesiredCapabilities capabilities;
  private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
  private static String platform = System.getenv("PLATFORM");

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    capabilities = this.getCapabilitiesByEnv();
    driver = this.getAppiumDriver();
    //driver = new AndroidDriver(new URL(appiumURL), capabilities);
    this.rotateScreenPortrait();
  }

  @Override
  protected void tearDown() throws Exception {
    driver.quit();
    super.tearDown();
  }

  protected void rotateScreenPortrait() {
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  protected void rotateScreenLandscape() {
    driver.rotate(ScreenOrientation.LANDSCAPE);
  }

  protected void backgroundApp(int seconds) {
    driver.runAppInBackground(seconds);
  }

  private DesiredCapabilities getCapabilitiesByEnv() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();

    if (platform.equals(PLATFORM_ANDROID)) {
      capabilities.setCapability("platformName", "Android");
      capabilities.setCapability("deviceName", "AndroidTestDevice");
      capabilities.setCapability("platformVersion", "8.0");
      capabilities.setCapability("automationName", "Appium");
      capabilities.setCapability("appPackage", "org.wikipedia");
      capabilities.setCapability("appActivity", ".main.MainActivity");
      capabilities.setCapability("app", "/Users/home/Documents/GitHub/avt_mob/apks/org.wikipedia.apk");
      //"C:\\Users\\x1y1z\\Documents\\GitHub\\avt_mob\\apks\\org.wikipedia.apk");
    } else if (platform.equals(PLATFORM_IOS)) {
      capabilities.setCapability("platformName", "iOS");
      capabilities.setCapability("deviceName", "iPhone SE");
      capabilities.setCapability("platformVersion", "11.0");
      capabilities.setCapability("app", "/Users/home/Documents/GitHub/avt_mob/apks/Wikipedia.app");
    } else {
      throw new Exception("Cannot get run platform from env variable. Platform value: " + platform);
    }
    return capabilities;
  }

  private AppiumDriver getAppiumDriver() throws Exception {
      if(platform.equals(PLATFORM_IOS)){
          driver = new IOSDriver(new URL(appiumURL), capabilities);
      }else if(platform.equals(PLATFORM_ANDROID)){
          driver = new AndroidDriver(new URL(appiumURL), capabilities);
      }else {
          throw new Exception("Cannot get run driver from env variable. Platform value: " + platform);
      }
    return driver;
  }
}
