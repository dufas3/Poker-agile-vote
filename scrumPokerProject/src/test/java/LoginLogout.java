import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginLogout {

    public static final String FIELD_NAME = "saule";
    public static final String EMAIL_CORRECT = "testemail@gmail.com";
    public static final String EMAIL_INCORRECT = "testemailgmail.com";

    public static final String PASSWORD_CORRECT = "testpassword123";
    public static final String PASSWORD_INCORRECT = "testpass";

    public static void loginPlayer(String name) {
        WebElement nameField = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/body/div/input"));
        Assert.assertTrue("Player name field is inactive and/or invisible", nameField.isEnabled() && nameField.isDisplayed());
        nameField.sendKeys(name);
        Setup.browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void clickEnterPlayerButton() {
        WebElement enterButton = Setup.browser.findElement(By.id("joinbutton"));
        Assert.assertTrue("Enter button is invisible", enterButton.isDisplayed());
        enterButton.click();
    }

    public static void enterEmail(String email) {
        WebElement emailField = Setup.browser.findElement(By.id("emailenter"));
        Assert.assertTrue("Email field is inactive and/or invisible", emailField.isEnabled() && emailField.isDisplayed());
        emailField.sendKeys(email);
    }

    public static void enterPassword(String password) {
        WebElement passwordField = Setup.browser.findElement(By.id("passwordenter"));
        Assert.assertTrue("Password field is inactive and/or invisible", passwordField.isEnabled() && passwordField.isDisplayed());
        passwordField.sendKeys(password);
    }

    public static void submitLoginForm() {
        WebElement loginButton = Setup.browser.findElement(By.className("login-button"));
        Assert.assertTrue("Login button is invisible", loginButton.isDisplayed());
        loginButton.click();
    }

    public static void waitForLoginResults() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(By.id("dropdown-basic")));
    }

    public static void waitForNameResults(String text) {
        WebElement name = Setup.browser.findElement(By.id("dropdown-basic"));
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElement(name,text));
    }

    public static String getLoginResults() {
        WebElement loginName = Setup.browser.findElement(By.id("dropdown-basic"));
        String resultsText = loginName.getText();
        return resultsText;
    }

    public static String getUnsuccessfulLoginMessage() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-text.text-danger")));
        WebElement loginErrorMessage = Setup.browser.findElement(By.cssSelector(".error-text.text-danger"));
        String errorMessageText = loginErrorMessage.getText();
        System.out.println(errorMessageText);
        return errorMessageText;
    }

    public static void pressLoginNameButton() {
        By loginResult = By.id("dropdown-basic");
        new WebDriverWait(Setup.browser, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(loginResult));
        WebElement loginNameButton = Setup.browser.findElement(By.id("dropdown-basic"));
        Assert.assertTrue("Login User name button located in Header is invisible", loginNameButton.isDisplayed());
        loginNameButton.click();
    }

    public static void pressLogoutButton() {
        WebElement logoutButton = Setup.browser.findElement(By.id("logoutbutton"));
        Assert.assertTrue("Logout button located in Header is invisible", logoutButton.isDisplayed());
        logoutButton.click();
    }

    public static String getButtonColor(By elementSelector){
        String buttonColor = Setup.browser.findElement(elementSelector).getCssValue("background");
        System.out.println("Button color: " + buttonColor);
        return buttonColor;
    }
}
