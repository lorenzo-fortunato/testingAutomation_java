package activity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.Cart;
import pages.Checkout;

public class Checkout_action {

    WebDriver driver;
    Checkout checkout;

    public Checkout_action(WebDriver driver) {
        this.driver = driver;
        this.checkout = PageFactory.initElements(driver, Checkout.class);
    }

    public void goToCheckout() {
        Cart cart = PageFactory.initElements(driver, Cart.class);
        cart.goToCheckout();
    }

    public void executeCheckout(String name, String lastName, String address, String province, String postCode) {
        checkout.insertFirstName(name);
        checkout.insertLastName(lastName);
        checkout.insertAddress(address);
        checkout.insertProvince(province);
        checkout.insertPostCode(postCode);
        checkout.submitCheckout();
    }
}
