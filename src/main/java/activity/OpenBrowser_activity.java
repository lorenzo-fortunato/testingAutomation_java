package activity;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.Constants;

import java.time.Duration;

public class OpenBrowser_activity {
    public static void openSelectedBrowser(WebDriver driver, Logger log) {
        driver.manage().window().maximize();
        driver.get(Constants.Url);
        log.info("Web application launched");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        log.info("Set implicit wait of 2 seconds");
    }
}
