package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @CacheLookup
    @FindBy( how = How.ID, using = "react-select-2-input")
    WebElement username;

    @CacheLookup
    @FindBy( how = How.ID, using = "react-select-3-input")
    WebElement password;

    public void enterUsername(String user_name) {
        try {
            username.sendKeys(user_name);
            username.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Element not interactable: " + e.getMessage());
        }
    }

    public void enterPassword(String pwd) {
        try {
            password.sendKeys(pwd);
            password.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Element not interactable: " + e.getMessage());
        }
    }

    public void tryLogin() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('login-btn').click();");
    }

}
