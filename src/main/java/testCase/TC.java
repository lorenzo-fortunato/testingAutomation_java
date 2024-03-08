package testCase;

import activity.*;
import fail.Fail;
import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomeIn;
import pages.HomeOut;
import utilities.Constants;
import utilities.ExcelUtils;
import utilities.TestLogger;

import java.io.IOException;
import java.time.Duration;

public class TC {
    public WebDriver driver;
    public String user;
    public String pwd;
    public String browser;
    public String product;
    public String address;
    public String vendor;
    public String firstName;
    public String lastName;
    public String filePath;
    public String state;
    public String postCode;
    public int currentRow;
    public ExcelUtils excelUtils = new ExcelUtils();
    private static Logger Log;

    @BeforeTest
    public void beforeTest() throws IOException {

        Log = TestLogger.setLogger();

        filePath = Constants.Path_testData + Constants.File_testData;
        excelUtils.setExcelFile(filePath, "ordersToTest");
        Log.info("File excel selected");

        currentRow = 1;
    }

    @Test
    public void test_case() throws IOException {

        int rowCount = excelUtils.getRowCountInSheet();
        for (int i = 1; i <= rowCount; i++) {

            getData();
            chooseBrowser(browser);

            HomeIn homeIn = PageFactory.initElements(driver, HomeIn.class);
            HomeOut homeOut = PageFactory.initElements(driver, HomeOut.class);
            Login_action login_action = PageFactory.initElements(driver, Login_action.class);
            Logout_action logout_action = PageFactory.initElements(driver, Logout_action.class);
            Checkout_action checkout_action = PageFactory.initElements(driver, Checkout_action.class);

            homeOut.goToSignInPage();
            Log.info("Navigate to signin page");

            login_action.executeLogin(user, pwd);

            Log.info("Trying login with user: " + user + " and password: " + pwd);

            if (VerifyObjectPresent.verify(driver, "//h3[@class='api-error']")) {
                Fail.fail(Log, "Wasn't able to login", currentRow, filePath);
            } else {
                Log.info("Logged in with user: " + user + " and password: " + pwd);

                addProductToCart(checkout_action, homeIn);
                goFromCartToCheckout(checkout_action);
                verifyOrder();

                driver.navigate().to(Constants.Url);
                Log.info("Navigate to homepage to execute logout");
                logout_action.executeLogout();
            }

            driver.quit();
            Log.info("Driver closed correctly");
            currentRow++;
        }
    }

    private void getData() {
        browser = excelUtils.getCellData(currentRow, Constants.Col_browser);
        user = excelUtils.getCellData(currentRow, Constants.Col_username);
        pwd = excelUtils.getCellData(currentRow, Constants.Col_password);
        vendor = excelUtils.getCellData(currentRow, Constants.Col_vendor);
        product = excelUtils.getCellData(currentRow, Constants.Col_product);
        address = excelUtils.getCellData(currentRow, Constants.Col_address);
        firstName = excelUtils.getCellData(currentRow, Constants.Col_firstname);
        lastName = excelUtils.getCellData(currentRow, Constants.Col_lastname);
        state = excelUtils.getCellData(currentRow, Constants.Col_state);
        postCode = excelUtils.getCellData(currentRow, Constants.Col_postalcode);
    }

    private void chooseBrowser(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
            Log.info("Using Chrome driver for this test");
            OpenBrowser_activity.openSelectedBrowser(driver, Log);
        } else if (browser.equalsIgnoreCase("Mozilla")) {
            driver = new FirefoxDriver();
            Log.info("Using Firefox driver for this test");
            OpenBrowser_activity.openSelectedBrowser(driver, Log);
        }
    }

    private void addProductToCart(Checkout_action actionCheckout, HomeIn home) throws IOException {
        selectProductsByVendor(vendor, home);

        driver.findElement(By.xpath("//p[normalize-space()='" + product + "']/following-sibling::div[2]")).click();

        String cartItem = driver.findElement(By.xpath("//p[@class='title']")).getText();
        if (!cartItem.equalsIgnoreCase(product)) {
            Fail.fail(Log, "Wrong product in the chart", currentRow, filePath);
        } else {
            actionCheckout.goToCheckout();
            Log.info("Going to checkout page");
        }
    }

    private void selectProductsByVendor(String vendor, HomeIn home) {
        if (vendor.equalsIgnoreCase("Google")) {
            home.selectGoogleProducts();
            Log.info("Selected Google products");
        } else if (vendor.equalsIgnoreCase("OnPlus")) {
            home.selectOnePlusProducts();
            Log.info("Selected OnePlus products");
        }
    }

    private void goFromCartToCheckout(Checkout_action actionCheckout) throws IOException {
        WebElement elementInCart = driver.findElement(By.xpath("//h5[@class='product-title optimizedCheckout-contentPrimary']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(elementInCart));
        String productName = elementInCart.getText();
        if (productName.equalsIgnoreCase(product)) {
            actionCheckout.executeCheckout(firstName, lastName, address, state, postCode);
        } else {
            Fail.fail(Log, "Wrong product in the cart", currentRow, filePath);
        }
    }

    private void verifyOrder() throws IOException {
        if (VerifyObjectPresent.verify(driver, "//legend[@id='confirmation-message']")) {
            excelUtils.setCellValue(currentRow, Constants.Col_result, "PASS", filePath);
            Log.info("Written PASS in result cell");
        } else {
            Fail.fail(Log, "No confirmation page", currentRow, filePath);
        }
    }

    @AfterTest
    public void afterTest() {
        Log.info("Tests correctly executed");
        System.out.println("------------------------------------------------------");
        int rowCount = excelUtils.getRowCountInSheet();
        for (int i = 1; i <= rowCount; i++) {
            System.out.println("In row " + i + " the result cell value is: " + excelUtils.getCellData(i, Constants.Col_result));
        }
    }
}
