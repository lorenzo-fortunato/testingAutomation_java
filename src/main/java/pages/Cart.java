package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {

    WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    @CacheLookup
    @FindBy( xpath = "//div[@class='buy-btn']" )
    WebElement checkoutBtn;

    public void goToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();
    }
}
