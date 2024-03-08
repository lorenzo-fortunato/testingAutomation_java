package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeIn {

    WebDriver driver;

    public HomeIn(WebDriver driver) {
        this.driver = driver;
    }

    @CacheLookup
    @FindBy( how = How.ID, using = "logout")
    WebElement logoutBtn;

    @CacheLookup
    @FindBy( xpath = "//span[normalize-space()='Google']")
    WebElement googleSelection;

    @CacheLookup
    @FindBy( xpath = "//span[normalize-space()='OnePlus']")
    WebElement onePlusSelection;

    public void tryLogout() {
        logoutBtn.click();
    }

    public void selectGoogleProducts() {
        googleSelection.click();
    }

    public void selectOnePlusProducts() {
        onePlusSelection.click();
    }
}
