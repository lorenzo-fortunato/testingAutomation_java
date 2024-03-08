package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomeOut {
    WebDriver driver;

    public HomeOut(WebDriver driver) {
        this.driver = driver;
    }


    @CacheLookup
    @FindBy( xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/nav[1]/a[1]" )
    WebElement signIn;

    public void goToSignInPage() {
        signIn.click();
    }
}
