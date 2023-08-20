package support;

import org.openqa.selenium.By;

public class Constants {

    public static final By WARNING_MESSAGE_LOCATOR = By.cssSelector("span.error_message");
    public static final By CONFORMATION_MESSAGE_LOCATOR = By.cssSelector("span.confirmation_message");

    public static final By ZIP_CODE_FIELD_LOCATOR = By.xpath("//input[@name='zip_code']");
    public static final By ZIP_CODE_SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@value='Continue']");
    public static final String INVALID_ZIP_CODE_WARNING_MESSAGE
            = "Oops, error on page. ZIP code should have 5 digits";
    public static final By SIGN_UP_BUTTON_LOCATOR = By.xpath("//a[text()='Sign up']");
    public static final By FIRST_NAME_FIELD_LOCATOR = By.name("first_name");
    public static final By LAST_NAME_FIELD_LOCATOR = By.name("last_name");
    public static final By EMAIL_FIELD_LOCATOR = By.name("email");
    public static final By PASSWORD_FIELD_LOCATOR = By.name("password1");
    public static final By CONFIRM_PASSWORD_FIELD_LOCATOR = By.name("password2");
    public static final By SIGN_UP_SUBMIT_BUTTON_LOCATOR = By.cssSelector("input[value='Register']");
    public static final String SIGN_UP_INVALID_DATA_WARNING_MESSAGE
            = "Oops, error on page. Some of your fields have invalid data or email was previously used";
    public static final String SUCCESSFUL_SIGN_UP_MESSAGE = "Account is created!";


}
