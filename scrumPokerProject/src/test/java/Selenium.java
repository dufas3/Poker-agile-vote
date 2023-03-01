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
    public static final String POKER_URL = "https://pokerfaceapp-17528.azurewebsites.net/";
    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL = "testemail@gmail.com";
    public static final String PASSWORD = "testpassword123";

    public static void main(String[] args) {
        System.out.println("Scrum Poker");
        setup(POKER_URL);
        //LOGIN
        loginPlayer();
        //loginModerator();
        //moderatorFlipCard();
        //moderatorClearVotes();
        //moderatorFinishVoting();

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
        WebElement footerPrivacyLink = browser.findElement(By.id("F2"));
        Assert.assertTrue("Festo Data Privacy link is inactive and/or invisible", footerPrivacyLink.isEnabled() && footerPrivacyLink.isDisplayed());
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

//-------------------------------------------------MODERATOR_CARD_SETTINGS--------------------------------------------------------------

    public static void flipCard() {
        WebElement flipCardButton = browser.findElement(By.id(""));
        flipCardButton.click();
    }

    public static void clearVotes() {
        WebElement clearCardButton = browser.findElement(By.id(""));
        clearCardButton.click();
    }

    public static void finishVoting() {
        WebElement finishVotingButton = browser.findElement(By.id(""));
        finishVotingButton.click();
    }

    public static void clickModeratorSettings() {
//click settings icon
        WebElement moderatorSettingsButton = browser.findElement(By.cssSelector("#root > div.App > div > div.voting > div.voting-container.border.rounded.bg-light > button"));
        moderatorSettingsButton.click();

        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public static void checkUseAllCardsBox() {
//check use all cards box
        WebElement useAllCardsButton = browser.findElement(By.xpath("//*[@id=\"flexCheckDefault\"]"));
        useAllCardsButton.click();

//click save button
        WebElement savebutton = browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div[2]/button[1]/h6"));
        savebutton.click();
    }

    public static void selectCards() {
//check card 0 box
        WebElement card0Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 0 box is inactive and/or invisible", card0Box.isEnabled() && card0Box.isDisplayed());
        card0Box.click();

//check card 1/2 box
        WebElement cardHalfBox = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 1/2 box is inactive and/or invisible", cardHalfBox.isEnabled() && cardHalfBox.isDisplayed());
        cardHalfBox.click();

//check card 1 box
        WebElement card1Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 1 box is inactive and/or invisible", card1Box.isEnabled() && card1Box.isDisplayed());
        card1Box.click();

//check card 2 box
        WebElement card2Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 2 box is inactive and/or invisible", card2Box.isEnabled() && card2Box.isDisplayed());
        card2Box.click();

//check card 3 box
        WebElement card3Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 3 box is inactive and/or invisible", card3Box.isEnabled() && card3Box.isDisplayed());
        card3Box.click();

//check card 5 box
        WebElement card5Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 5 box is inactive and/or invisible", card5Box.isEnabled() && card5Box.isDisplayed());
        card5Box.click();

//check card 8 box
        WebElement card8Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 8 box is inactive and/or invisible", card8Box.isEnabled() && card8Box.isDisplayed());
        card8Box.click();

//check card 13 box
        WebElement card13Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 13 box is inactive and/or invisible", card13Box.isEnabled() && card13Box.isDisplayed());
        card13Box.click();

//check card 20 box
        WebElement card20Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 20 box is inactive and/or invisible", card20Box.isEnabled() && card20Box.isDisplayed());
        card20Box.click();

//check card 40 box
        WebElement card40Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 40 box is inactive and/or invisible", card40Box.isEnabled() && card40Box.isDisplayed());
        card40Box.click();

//check card 100 box
        WebElement card100Box = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card 100 box is inactive and/or invisible", card100Box.isEnabled() && card100Box.isDisplayed());
        card100Box.click();

//check card ? box
        WebElement cardQuestionBox = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card ? box is inactive and/or invisible", cardQuestionBox.isEnabled() && cardQuestionBox.isDisplayed());
        cardQuestionBox.click();

//check card Coffee box
        WebElement cardCoffeeBox = browser.findElement(By.xpath (""));
        Assert.assertTrue("Card Coffee box is inactive and/or invisible", cardCoffeeBox.isEnabled() && cardCoffeeBox.isDisplayed());
        cardCoffeeBox.click();

//click save button
        WebElement saveButton = browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div[2]/button[1]/h6"));
        saveButton.click();
    }

/* public static void changeCards() {
//uncheck card 13 box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//uncheck card 20 box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//uncheck card 40 box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//uncheck card 100 box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//uncheck card ? box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//uncheck card Coffee box
WebElement checkCard0 = browser.findElement(By.(""));
checkCard0.click();

//click save button
WebElement savebutton = browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div[2]/button[1]/h6"));
savebutton.click();
}*/


}
