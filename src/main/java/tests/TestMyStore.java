package tests;
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

import com.config.parser.*;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMyStore {

    public static final String ACCOUNT_ICON_HEADER = "MY ACCOUNT";
    private static final String MYSTORE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private WebDriver driver;
    ConfigParser props;
    private String accountEmail = "test@automationclass.com";
    private String accountPassword = "T7Qy5E$Bt!a4P!!";
    @Before
    public void prepare() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(MYSTORE_URL);
    }
    @Test
    public void testAccountLogin() throws IOException, InterruptedException {
        // write your tests here
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
