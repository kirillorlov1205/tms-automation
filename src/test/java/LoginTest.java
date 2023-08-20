import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private WebDriver driver;
    private final By LOGIN_FIELD_LOCATOR = By.name("email");
    private final By PASSWORD_FIELD_LOCATOR = By.name("password");
    private final By LOGIN_BUTTON_LOCATOR = By.xpath("//input[@value='Login']");
    private final By INVALID_CREDENTIALS_VALIDATION_LOCATOR = By.cssSelector("span.error_message");
    private final String INVALID_CREDENTIALS_VALIDATION_MESSAGE = "Oops, error. Email and/or password don't match our records";

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeTest
    public void beforeTest() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterSuite
    public void afterSuite() {
        driver.close();
    }

    @Test(testName = "Login with invalid email")
    public void checkForValidationErrorWhileProvidingInvalidEmail() {
        driver.findElement(LOGIN_FIELD_LOCATOR).sendKeys("test");
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(INVALID_CREDENTIALS_VALIDATION_LOCATOR).getText(), INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with invalid password")
    public void checkForValidationErrorWhileProvidingInvalidPassword() {
        driver.findElement(LOGIN_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys("1");
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(INVALID_CREDENTIALS_VALIDATION_LOCATOR).getText(), INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with empty email")
    public void checkForValidationErrorWhileProvidingEmptyEmail() {
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(INVALID_CREDENTIALS_VALIDATION_LOCATOR).getText(), INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with empty password")
    public void checkForValidationErrorWhileProvidingEmptyPassword() {
        driver.findElement(LOGIN_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(INVALID_CREDENTIALS_VALIDATION_LOCATOR).getText(), INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }
}
