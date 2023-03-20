import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginLogout {

    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL_CORRECT = "testemail@gmail.com";
    public static final String EMAIL_INCORRECT = "testemailgmail.com";

    public static final String PASSWORD_CORRCT = "testpassword123";
    public static final String PASSWORD_INCORRCT = "testpass";


    public static void loginPlayer(String name) {
        WebElement nameField = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/body/div/input"));
        Assert.assertTrue("Player name field is inactive and/or invisible", nameField.isEnabled() && nameField.isDisplayed());
        nameField.sendKeys(name);
    }

    public static void clickEnterPlayerButton() {
        WebElement enterButton = Setup.browser.findElement(By.className("join-button"));
        enterButton.click();

    }

    public static WebElement findNameInput() {
        WebElement nameInputPlayer = Setup.browser.findElement(By.xpath("//input[@placeholder=\"Enter your name\"]"));
        return nameInputPlayer;
    }

    public static void clickIconModeratorLogin() {
        WebElement moderatorLoginLink = Setup.browser.findElement(By.className("login-button"));
        moderatorLoginLink.click();

        Setup.browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
        loginButton.click();
    }

    //patikrinam headerio elementa ir patikrinam (paasertinam Logintest)ar tiktai tas userio name'as yra rodomas
    public static String getLoginResults() {
        By loginResult = By.id("dropdown-basic"); //pasiimam elementa userio name
        new WebDriverWait(Setup.browser, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(loginResult));
        WebElement loginName = Setup.browser.findElement(By.id("dropdown-basic"));
        String resultsText = loginName.getText();
        return resultsText;

    }

    public static String getUnsuccessfulModeratorLoginMessage() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-text.text-danger")));
        WebElement loginErrorMessage = Setup.browser.findElement(By.cssSelector(".error-text.text-danger"));
        String errorMessageText = loginErrorMessage.getText();
        System.out.println(errorMessageText);
        return errorMessageText;
    }

    public static void logoutUser() {
        By logoutLink = By.partialLinkText("Logout");
        new WebDriverWait(Setup.browser, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logoutLink));
        WebElement logoutButton = Setup.browser.findElement(By.partialLinkText("Logout"));
        logoutButton.click();
    }

}
