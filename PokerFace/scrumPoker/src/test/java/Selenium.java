import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class Selenium {
    private static WebDriver browser;
    public static final String POKER_URL = "http://localhost:3000/";
    public static final String FESTO_URL = "https://www.festo.com/us/en/";
    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL = "antanukas@ant.com";
    public static final String PASSWORD = "Paswword23";

    public static void main(String[] args) {
        System.out.println("Scrum Poker");
        setup(POKER_URL);
        //setup(FESTO_URL);
        //loginPlayer();
        //loginModerator();
        clickFestoFooterLink();
    }


    public static void setup(String url) {
        //Susiejamas Edge narsykles driveris su kodu. Narsykle privalo buti idiegta i OS
        //Linking Edge browser driver with code. The browser must be installed in the OS
        System.setProperty("webdriver.edge.driver", "libs/msedgedriver_109.exe");

        EdgeOptions options = new EdgeOptions();
        //SSL sertifikatu tikrinimo ignoravimas
        //Ignoring SSL certificate verification
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");

        //Sukuriamas Edge Driver objektas (reikia, kad butu importuotos bibliotekos)
        //Creating an Edge Driver object (requires imported libraries)
        browser = new EdgeDriver(options);
        //Atidaroma narsykle ir uzkraunamas nurodytas URL adresas
        browser.get(url);
    }

    public static void clickFestoFooterLink() {
        WebElement festoImprintLink = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/footer/ul/li[1]/a"));
        Assert.assertTrue("Festo link is inactive and/or invisible", festoImprintLink.isEnabled() && festoImprintLink.isDisplayed());
        festoImprintLink.click();
    }


    public static void loginPlayer() {
        //press icon for Player login
        WebElement playerLink = browser.findElement(By.className("login"));
        playerLink.click();
        browser.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //write Player name
        WebElement nameField = browser.findElement(By.xpath("/html/body/div/input"));
        Assert.assertTrue("Player name field is inactive and/or invisible", nameField.isEnabled() && nameField.isDisplayed());
        nameField.sendKeys(FIELD_NAME);

        //click enter button
        WebElement enterButton = browser.findElement(By.className("join-button"));
        enterButton.click();

    }

    public static void loginModerator() {
        //click icon for Moderator login
        WebElement moderatorLoginLink = browser.findElement(By.className("login-button"));
        moderatorLoginLink.click();

        //write email
        WebElement emailField = browser.findElement(By.xpath("/html/body/div/input[1]"));
        Assert.assertTrue("Email field is inactive and/or invisible", emailField.isEnabled() && emailField.isDisplayed());
        emailField.sendKeys(EMAIL);

        //write password
        WebElement passwordField = browser.findElement(By.xpath("/html/body/div/input[2]"));
        Assert.assertTrue("Password field is inactive and/or invisible", passwordField.isEnabled() && passwordField.isDisplayed());
        passwordField.sendKeys(PASSWORD);

        //click login button
        WebElement loginButton = browser.findElement(By.className("join-button"));
        loginButton.click();


       /* // click on "don't have account" (NEZINAU AR TAIP TURETU BUT :DDDDD)
        WebElement dontHaveAccount = browser.findElement(By.className("reset-button"));
        dontHaveAccount.click();*/
    }

    //patikrinam headerio elementa ir patikrinam (paasertinam Logintest)ar tiktai tas userio name'as yra rodomas
    public static String getLoginResults() {
        WebElement loginResult = browser.findElement(By.cssSelector("header.page-header h2 user name")); //pasiimam elementa userio name
        String resultsText = loginResult.getText();
        return resultsText;

    }

    public static String getFooterOutputResults() {
        WebElement getFooterResults = browser.findElement(By.cssSelector("header.page-header h2")); //pasiimam elementa userio name
        String resultsText = getFooterResults.getText();
        return resultsText;
    }
}
