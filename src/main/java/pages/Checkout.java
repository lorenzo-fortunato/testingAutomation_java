package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Checkout {
    WebDriver driver;

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    @CacheLookup
    @FindBy( how = How.ID, using = "firstNameInput")
    WebElement firstNameInput;

    @CacheLookup
    @FindBy( how = How.ID, using = "lastNameInput")
    WebElement lastNameInput;

    @CacheLookup
    @FindBy( how = How.ID, using = "addressLine1Input")
    WebElement addressLine1Input;

    @CacheLookup
    @FindBy( how = How.ID, using = "provinceInput")
    WebElement provinceInput;

    @CacheLookup
    @FindBy( how = How.ID, using = "postCodeInput")
    WebElement postCodeInput;

    @CacheLookup
    @FindBy( xpath = "//button[@id='checkout-shipping-continue']")
    WebElement submitBtn;

    public void insertFirstName(String name) {
        firstNameInput.sendKeys(name);
    }

    public void insertLastName(String LastName) {
        lastNameInput.sendKeys(LastName);
    }

    public void insertAddress(String address) {
        addressLine1Input.sendKeys(address);
    }

    public void insertProvince(String province) {
        provinceInput.sendKeys(province);
    }

    public void insertPostCode(String postCode) {
        postCodeInput.sendKeys(postCode);
    }

    public void submitCheckout() {
        submitBtn.click();
    }
}
