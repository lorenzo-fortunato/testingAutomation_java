package activity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyObjectPresent {

    public static boolean verify(WebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
