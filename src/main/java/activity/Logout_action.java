package activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomeIn;

public class Logout_action {
    WebDriver driver;
    HomeIn homeIn;

    public Logout_action(WebDriver driver) {
        this.driver = driver;
        this.homeIn = PageFactory.initElements(driver, HomeIn.class);
    }

    public void executeLogout() {
        homeIn.tryLogout();
    }
}
