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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium {
    private static WebDriver browser;
    public static final String POKER_URL = "https://pokerfaceapptest.azurewebsites.net";
    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL = "testemail@gmail.com";
    public static final String PASSWORD = "testpassword123";

    public static void main(String[] args) {
        System.out.println("Scrum Poker");
        setup(POKER_URL);
        //LOGIN
        //loginPlayer();
        //loginModerator();


        //HEADER ---VEIKIA
        //clickUserIcon();
        //clickFestoLogo();

        //FOOTER ---VEIKIA
        //clickFooterImprintLink();
        //acceptFestoCookies();
        //clickFooterPrivacyLink();
        //acceptFestoCookies();
        //clickFooterTermsLink();
        //acceptFestoCookies();
        clickFooterCloudLink();
        acceptFestoCookies();

        //MODERATOR_CARD_SETTINGS
        //flipCard();
        //clearVotes();
        //finishVoting();
        //clickModeratorSettings();
        //checkUseAllCardsBox();
        //selectCards(1);
        //selectCards(5);
        //selectCards(8);
        //unselectCards();
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
        //JavascriptExecutor js = (JavascriptExecutor) browser;
        //js.executeScript("window.scrollBy(0,4500)");


        //browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //---------------------------------------------HEADER--------------------------------------------------------------
    public static void clickUserIcon() {

        WebElement userIcon = browser.findElement(By.id("H1"));
        userIcon.click();

        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
        WebElement getHeaderResults = browser.findElement(By.cssSelector("#root > div.App > div > div > div.info > h2"));
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
        new WebDriverWait(browser, Duration.ofSeconds(3));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("document.getElementById('F1').scrollIntoView()");
        new WebDriverWait(browser, Duration.ofSeconds(3));
        WebElement footerImprintLink = browser.findElement(By.id("F1"));
        Assert.assertTrue("Festo Imprint link is inactive and/or invisible", footerImprintLink.isEnabled() && footerImprintLink.isDisplayed());
        js.executeScript("document.getElementById('F1').click()");
        //footerPrivacyLink.click();
    }

    public static void clickFooterPrivacyLink() {
        new WebDriverWait(browser, Duration.ofSeconds(3));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("document.getElementById('F2').scrollIntoView()");
        new WebDriverWait(browser, Duration.ofSeconds(3));
        WebElement footerPrivacyLink = browser.findElement(By.id("F2"));
        Assert.assertTrue("Festo Data Privacy link is inactive and/or invisible", footerPrivacyLink.isEnabled() && footerPrivacyLink.isDisplayed());
        js.executeScript("document.getElementById('F2').click()");
        //footerPrivacyLink.click();
    }

    public static void clickFooterTermsLink() {
        new WebDriverWait(browser, Duration.ofSeconds(3));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("document.getElementById('F3').scrollIntoView()");
        new WebDriverWait(browser, Duration.ofSeconds(3));
        WebElement footerTermsLink = browser.findElement(By.id("F3"));
        Assert.assertTrue("Festo Terms and Conditions of Sale link is inactive and/or invisible", footerTermsLink.isEnabled() && footerTermsLink.isDisplayed());
        js.executeScript("document.getElementById('F3').click()");
        //footerPrivacyLink.click();
    }

    public static void clickFooterCloudLink() {
        new WebDriverWait(browser, Duration.ofSeconds(3));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("document.getElementById('F4').scrollIntoView()");
        new WebDriverWait(browser, Duration.ofSeconds(3));
        WebElement footerCloudLink = browser.findElement(By.id("F4"));
        Assert.assertTrue("Festo Terms and Conditions of Sale link is inactive and/or invisible", footerCloudLink.isEnabled() && footerCloudLink.isDisplayed());
        js.executeScript("document.getElementById('F4').click()");
        //footerPrivacyLink.click();
    }

    public static void acceptFestoCookies() {
        //Accept Cookies
        By buttonSelector = By.xpath("//button[contains(text(),'Accept All Cookies')]");
        new WebDriverWait(browser, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(buttonSelector));
        browser.findElement(buttonSelector).click();

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
        WebElement saveButton = browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div[2]/button[1]/h6"));
        saveButton.click();
    }

    public static void selectCards(int index) {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("document.getElementById('flexCheckDefault').scrollIntoView()");
        new WebDriverWait(browser, Duration.ofSeconds(3));


        List<WebElement> checkbox = browser.findElements(By.id("flexCheckDefault"));
        int Size = checkbox.size();
        Assert.assertTrue("Index out of range", Size > index);
        for (int i = 0; i < Size; i++) {
            if (i == index) {
                checkbox.get(index).click();
                break;
            }
        }
        Assert.assertTrue("Card box is not selected", checkbox.get(index).isSelected());
    }


    public static void unselectCards() {
        List<WebElement> cardBoxOptions2 = browser.findElements(By.className("form-check-input")); //cardBox - pavadinimas visu card boxu
        System.out.println("Total card box options: " + cardBoxOptions2.size());

        for (WebElement cardBoxOption2 : cardBoxOptions2) {
            // issitraukiame elementa pgala reiksme (value)
            if (cardBoxOption2.getAttribute("Value").equals("1")) { //paselectina Coffe, nes tik jis turi Value, devai uzdes ant kitu Value irgi
                cardBoxOption2.click();
                // kad kitu nebetikrintu, jeigu jau rado korteles 1 box'a
                // break - iseina is ciklo
                // continue - toliau tesia
                break;
            }
        }
    }
}