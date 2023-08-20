import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import support.Constants;

public class SearchTest {

    private WebDriver driver;
    private final By SEARCH_FIELD_LOCATOR = By.xpath("//input[@name='keyword']");
    private final By SEARCH_BUTTON = By.xpath("//input[@value='Search']");
    private final String EMPTY_SEARCH_WARNING_TEXT = "Oops, error. No keyword is provided";
    private final String NO_BOOKS_FOUND_SEARCH_WARNING_TEXT = "Nothing is found :(";
    private final String WORD_WITH_LESS_THAN_MIN_LIMIT_CHARACTERS_WARNING_TEXT
            = "Please, note that current MySQL settings don't allow searches for words containing less than 4 chars";

    @BeforeSuite
    private void beforeSuite() {
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeTest
    private void beforeTest() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterSuite
    public void afterTest() {
        driver.close();
    }

    @Test(testName = "Search by one-word book name")
    public void searchByOneWordBookName() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Gitanjali");
        driver.findElement(SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='Gitanjali ']")).isDisplayed();
    }

    @Test(testName = "Search by multiple-word book name")
    public void searchByMultipleWordBookName() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("The Power of Positive Thinking");
        driver.findElement(SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='The Power of Positive Thinking ']")).isDisplayed();
    }

    @Test(testName = "Search by one word from multiple-word book name")
    public void searchByOneWordFromMultipleWordBookName() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Power");
        driver.findElement(SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='The Power of Positive Thinking ']")).isDisplayed();
    }

    @Test(testName = "Search by a part of word from book name")
    public void searchByPartOfWordFromBookName() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Powe");
        driver.findElement(SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), NO_BOOKS_FOUND_SEARCH_WARNING_TEXT);
    }

    @Test(testName = "Search by word with less than minimal limit characters")
    public void searchByWordWithLessThanMinLimitCharacters() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Pow");
        driver.findElement(SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), WORD_WITH_LESS_THAN_MIN_LIMIT_CHARACTERS_WARNING_TEXT);
    }

    @Test(testName = "Search by empty string")
    public void searchByEmptyString() {
        driver.findElement(SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR)
                .getText(), EMPTY_SEARCH_WARNING_TEXT);
    }

    @Test(testName = "Search by author first name")
    public void searchByAuthorFirstName() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Twain");
        driver.findElement(SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), NO_BOOKS_FOUND_SEARCH_WARNING_TEXT);
    }

    @Test(testName = "Search by author last name")
    public void searchByAuthorLastname() {
        driver.findElement(SEARCH_FIELD_LOCATOR).sendKeys("Mark");
        driver.findElement(SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), NO_BOOKS_FOUND_SEARCH_WARNING_TEXT);
    }
}
