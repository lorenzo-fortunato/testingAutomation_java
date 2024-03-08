package activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import java.io.IOException;

public class Login_action {
    WebDriver driver;
    LoginPage loginPage;

    public Login_action(WebDriver driver) {
        this.driver = driver;
        this.loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    public void executeLogin(String user, String password) throws IOException {
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.tryLogin();
    }
}
