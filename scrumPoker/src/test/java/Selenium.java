import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Selenium {
    private static WebDriver browser;
    public static final String POKER_URL = "http://localhost:3000/";
    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL = "testemail@gmail.com";
    public static final String PASSWORD = "testpassword123";

    public static void main(String[] args) {
        System.out.println("Scrum Poker");
        setup(POKER_URL);

        //LOGIN
        //loginPlayer();
        loginModerator();

        //HEADER
        //clickUserIcon();
        //clickFestoLogo();

        //FOOTER
        //clickFooterImprintLink();
        //acceptFestoCookies();
        //clickFooterPrivacyLink();
        //clickFooterTermsLink();
        //clickFooterCloudLink();

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

        //simuliacija pascrollinti zemyn puslapi
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,4500)");


        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //---------------------------------------------HEADER--------------------------------------------------------------
    public static void clickUserIcon() {

        WebElement userIcon = browser.findElement(By.className("black"));
        userIcon.click();

        browser.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static void clickFestoLogo() {


        WebElement festoLogo = browser.findElement(By.className("logo"));
        festoLogo.click();

        //browser.get("https://www.festo.com/us/en/");
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String test1 = browser.getCurrentUrl();

        By buttonSelector = By.xpath("/html/body/dialog/div/div/menu/button[2]");
        new WebDriverWait(browser, Duration.ofSeconds(3)).until(
                ExpectedConditions.presenceOfElementLocated(buttonSelector));
        browser.findElement(buttonSelector).click();

    }

    public static String getHeaderIconResults() {
        WebElement getHeaderResults = browser.findElement(By.cssSelector("body > div > div.info > h2"));
        String resultsHeading2Text = getHeaderResults.getText();
        return resultsHeading2Text;
    }

    public static String getFestoLogoResults() {
        WebElement getLogoResults = browser.findElement(By.className("headline--YLOqq"));
        String resultsHeadlineText = getLogoResults.getText();
        return resultsHeadlineText;
    }

    //----------------------------------------------FOOTER-------------------------------------------------------------
    public static void clickFooterImprintLink() {
        WebElement footerImprintLink = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/footer/ul/li[1]/a"));
        Assert.assertTrue("Festo Imprint link is inactive and/or invisible", footerImprintLink.isEnabled() && footerImprintLink.isDisplayed());
        footerImprintLink.click();
    }

    public static void clickFooterPrivacyLink() {
        WebElement footerPrivacyLink = browser.findElement(By.cssSelector("#root > div.container > footer > ul > li:nth-child(2) > a"));
        Assert.assertTrue("Festo Data Privacy link is inactive and/or invisible", footerPrivacyLink.isEnabled() && footerPrivacyLink.isDisplayed());
        footerPrivacyLink.click();
    }

    public static void clickFooterTermsLink() {
        WebElement footerTermsLink = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/footer/ul/li[3]/a"));
        Assert.assertTrue("Festo Imprint link is inactive and/or invisible", footerTermsLink.isEnabled() && footerTermsLink.isDisplayed());
        footerTermsLink.click();
    }

    public static void clickFooterCloudLink() {
        WebElement footerCloudLink = browser.findElement(By.xpath("//*[@id=\"root\"]/div[2]/footer/ul/li[4]/a"));
        Assert.assertTrue("Festo Imprint link is inactive and/or invisible", footerCloudLink.isEnabled() && footerCloudLink.isDisplayed());
        footerCloudLink.click();
    }

    public static void acceptFestoCookies() {


        //Accept Cookies
        WebElement cookiePolicy = browser.findElement(By.cssSelector("body > dialog > div > div > menu > button._Button_primary__d121ae37._Button_medium__d121ae37.accept-button--g96zu"));
        cookiePolicy.click();

        //simuliacija pascrollinti zemyn puslapi
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,4500)");

        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static String getFooterLinkResults() {
        WebElement getFooterResults = browser.findElement(By.className("main-headline")); //pasiimam Imprint elementa
        String resultsHeadingText = getFooterResults.getText();
        return resultsHeadingText;
    }

    //-------------------------------------------------LOGIN------------------------------------------------------------
    public static void loginPlayer() {

        //write Player name
        WebElement nameField = browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/body/div/input"));
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

        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //write email
        WebElement emailField = browser.findElement(By.id("emailenter"));
        Assert.assertTrue("Email field is inactive and/or invisible", emailField.isEnabled() && emailField.isDisplayed());
        emailField.sendKeys(EMAIL);

        //write password
        WebElement passwordField = browser.findElement(By.id("passwordenter"));
        Assert.assertTrue("Password field is inactive and/or invisible", passwordField.isEnabled() && passwordField.isDisplayed());
        passwordField.sendKeys(PASSWORD);

        //click login button
        WebElement loginButton = browser.findElement(By.className("login-button"));
        loginButton.click();


    }

    //patikrinam headerio elementa ir patikrinam (paasertinam Logintest)ar tiktai tas userio name'as yra rodomas
    public static String getLoginResults() {
        WebElement loginResult = browser.findElement(By.id("dropdown-basic")); //pasiimam elementa userio name
        String resultsText = loginResult.getText();
        return resultsText;

    }


}
