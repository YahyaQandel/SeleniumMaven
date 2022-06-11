import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMyStore {

    public static final String ACCOUNT_ICON_HEADER = "MY ACCOUNT";
    private static final String MYSTORE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private WebDriver driver;
    private final String accountEmail = System.getenv("ACCOUNT_EMAIL");
    private final String accountPassword = System.getenv("ACCOUNT_PASSWORD");
    @Before
    public void prepare()  {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(MYSTORE_URL);
    }
    @Test
    public void testAccountLogin()  {
        loginToAccount();
        assertUserDetailsExistsInPage();
        assertMyAccountPageHeaderExists();
    }

    private void assertMyAccountPageHeaderExists() {
        WebElement myProfileIconInlineText = waitForElementsToBeVisible(By.className("page-heading")).get(0);
        String myProfileIconString = myProfileIconInlineText.getText();
        assertEquals(myProfileIconString,ACCOUNT_ICON_HEADER);
    }

    private void assertUserDetailsExistsInPage() {
        WebElement accountNumberTitle = waitForElementToBeVisible(By.className("account"));
        String AccountHeadingTitle = accountNumberTitle.getText();
        assertTrue(AccountHeadingTitle.contains("Test User"));
    }

    private void loginToAccount() {
        WebElement submitButton = waitForElementToBeVisible(By.id("SubmitLogin"));
        boolean isSubmitButtonDisplayed = submitButton.isDisplayed();
        if (isSubmitButtonDisplayed) {
            WebElement loginNumberField = waitForElementToBeVisible(By.id("email"));
            loginNumberField.sendKeys(accountEmail);
            WebElement loginPasswordField = waitForElementToBeVisible(By.id("passwd"));
            loginPasswordField.sendKeys(accountPassword);
            loginPasswordField.sendKeys(Keys.RETURN);
        }
    }


    private WebElement waitForElementToBeVisible(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selector));
    }

    private List<WebElement> waitForElementsToBeVisible(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    @After
    public void teardown() throws IOException {
        driver.quit();
    }

}
