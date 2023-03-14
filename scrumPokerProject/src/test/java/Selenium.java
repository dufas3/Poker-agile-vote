import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium {
    public static WebDriver browser;
    public static final String POKER_URL = "pokerface-test.azurewebsites.net";
    //"https://pokerfaceapp-dev.azurewebsites.net/?room=1ff6ae59-c5b2-4c55-bf6b-114dbc83a30b"//statinis, i interneta patalpintas linkas
    public static final String FIELD_NAME = "Antanukas";
    public static final String EMAIL = "testemail@gmail.com";
    public static final String PASSWORD = "testpassword123";

    public static void main(String[] args) {
        System.out.println("Scrum Poker");
        setup(POKER_URL);
        //setupChrome(POKER_URL);
        //LOGIN/LOGOUT
        //loginPlayer();
        loginModerator();
        //logoutUser();


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
        //clickFooterCloudLink();
        //acceptFestoCookies();

        //MODERATOR_CARD_SETTINGS
        //flipCard();
        //clearVotes();
        //finishVoting();
        //clickModeratorSettings();
        //waitForCardCheckboxOptions();
        //selectCardCheckbox(1);
        //selectCardCheckbox(2);
        //selectCardCheckbox(3);
        //selectCardCheckbox(0);
        //unselectCardCheckbox(1);
        //unselectCardCheckbox(2);
        //unselectCardCheckbox(3);
        //getPossibleCardCheckboxValuesList();


        //PLAYER_LIST_SECTION
        //getPossiblePayerCardsList();
        //getPossibleModeratorCardsList();
        //getPlayerListMessageResults();
        //waitForCardVoteOptions();
        //clickCardPlayer("20");
        //getCheckMarkElement("Antanukas");

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
        //Broweser is being opened and URL is being uploaded
        browser.get(url);

        //simuliacija pascrollinti zemyn puslapi
        //JavascriptExecutor js = (JavascriptExecutor) browser;
        //js.executeScript("window.scrollBy(0,4500)");


        //browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void setupChrome(String url) {
        //Susiejamas Chrome narsykles driveris su kodu. Narsykle privalo buti idiegta i OS
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver_110.exe");

        ChromeOptions options = new ChromeOptions();
        //SSL sertifikatu tikrinimo ignoravimas
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");//arba sitas isdidinimui
        //Sukuriamas Chrome Driver objektas (reikia, kad butu importuotos bibliotekos)
        browser = new ChromeDriver(options);

        //Narsykles lango ispelitimas (maximaizinimas) -  arba sitas
        //browser.manage().window().maximize();

        //Atidaroma narsykle ir uzkraunamas nurodytas URL adresas
        browser.get(url);


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

    //-------------------------------------------------LOGIN/LOGOUT------------------------------------------------------------
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

    public static String getUnsuccessfulLoginMessage() {
        WebElement loginMessage = browser.findElement(By.className("error-text text-danger"));
        String messageText = loginMessage.getText();
        return messageText;
    }

    public static void logoutUser(){
        By logoutLink = By.partialLinkText("Logout");
        new WebDriverWait(browser, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(logoutLink));
        WebElement logoutButton = browser.findElement(By.partialLinkText("Logout"));
        logoutButton.click();
    }

//-------------------------------------------------MODERATOR_CARD_SETTINGS--------------------------------------------------------------

    public static void flipCard() {
        WebElement flipCardButton = browser.findElement(By.className("flip-cards btn btn-outline-primary"));
        flipCardButton.click();
    }

    public static void clearVotes() {
        WebElement clearCardButton = browser.findElement(By.className("clear-votes btn btn-outline-primary"));
        clearCardButton.click();
    }

    public static void finishVoting() {
        WebElement finishVotingButton = browser.findElement(By.className("finish-voting btn btn-primary"));
        finishVotingButton.click();
    }

    public static void clickModeratorSettings() {
//click settings icon
        WebElement moderatorSettingsButton = browser.findElement(By.cssSelector("#root > div.App > div > div.voting > div.voting-container.border.rounded.bg-light > button"));
        moderatorSettingsButton.click();

        //browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public static void waitForCardCheckboxOptions() {
        new WebDriverWait(browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("form-check-input"), 1));
    }

    public static void selectCardCheckbox(int index) {

        List<WebElement> checkbox = browser.findElements(By.className("form-check-input"));
        int size = checkbox.size();
        System.out.println("Dydis " + size);
        Assert.assertTrue("Index out of range", size > index);

        Assert.assertFalse("Card box is selected", checkbox.get(index).isSelected()); //patikrini ar nera paselectintas ir ar is vis galima clickint, ar yra clikinimo funkcionalumas

        checkbox.get(index).click();

        Assert.assertTrue("Card box is not selected", checkbox.get(index).isSelected()); //patikrini ar packlikino

        //click save button
        WebElement saveButton = browser.findElement(By.className("w-25 btn btn-primary"));
        saveButton.click();
    }


    public static void unselectCardCheckbox(int index) {

        List<WebElement> checkbox = browser.findElements(By.className("form-check-input"));
        int size = checkbox.size();

        Assert.assertTrue("Index out of range", size > index);

        Assert.assertTrue("Card box is not selected", checkbox.get(index).isSelected()); //patikrini ar yra jau paselectinta

        checkbox.get(index).click();

        Assert.assertFalse("Card box is selected", checkbox.get(index).isSelected()); //patikrini ar nusiselectino

        //click save button
        WebElement saveButton = browser.findElement(By.className("w-25 btn btn-primary"));
        saveButton.click();


    }

    public static ArrayList<String> getPossibleCardCheckboxValuesList() {
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement element : browser.findElements(By.className("form-check-label"))) {
            actual.add(element.getText());
        }

        return actual;
    }

//---------------------------------------------------PLAYER_LIST_SECTION--------------------------------------------------------------

    public static String getPlayerListMessageResults() {
        WebElement getPlayerListMessage = browser.findElement(By.className("bg-primary border border-primary rounded-top w-100 p-4 text-center text-white"));
        String messageText = getPlayerListMessage.getText();
        return messageText;
    }

   /* public static String getModeratorNumberInList() {
        List<WebElement> listElements = browser.findElements(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[1]/div/h6")); //elementas kuri issitraukiam yra spalva, moderatorius yra kitokios spalvos ir pirmas
        WebElement firstListElement = listElements.get(0);
        String firstListElement = listElements.getText;
        //return firstListElement;

    }*/

    public static void clickCardPlayer(String cardNumber) {
        WebElement playerCard = browser.findElement(By.className("number"));
        //new Actions(browser).moveToElement(playerCard).build().perform();

        System.out.println("" + playerCard);
        List<WebElement> playerCards = browser.findElements(By.className("card-css"));
        System.out.println("Total cards: " + playerCards.size());
        //Assert.assertTrue("Player card is not displayed and/or enabled", playerCard.isDisplayed() && playerCard.isEnabled());
        for (WebElement card : playerCards) {
            WebElement cardTag = card.findElement(By.tagName("h2"));
            if (cardTag.getText().equals(cardNumber)) {
                //System.out.println("Card: " + card.getText().equals("3"));
                card.click();
                //card.click();
                break;
            }
        }
    }


    public static void waitForCardVoteOptions() {
        new WebDriverWait(browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("card-css"), 1));
    }


    public static String getCheckMarkElement(String playerName) {
        List<WebElement> playersList = browser.findElements(By.className("align-center-start"));
        String checkMarkText = "";
        for (WebElement player : playersList) {
            WebElement playerTag = player.findElement(By.tagName("h6"));
            if (playerTag.getText().equals(playerName)) {
                checkMarkText = playerTag.findElement(By.tagName("i")).getAttribute("class");
                System.out.println("text: " + checkMarkText);
            }
        }
        return checkMarkText;
    }
    public static ArrayList<String> getPossiblePayerCardsList() {
        ArrayList<String> playerCards = new ArrayList<>();
        for (WebElement cardElement : browser.findElements(By.className("number"))) {
            playerCards.add(cardElement.getText());
        }
        return playerCards;
    }
    public static ArrayList<String> getPossibleModeratorCardsList(){
        ArrayList<String> moderatorCards = new ArrayList<>();
        for (WebElement cardsElement : browser.findElements(By.className("number"))) {
            moderatorCards.add(cardsElement.getText());
        }
        return moderatorCards;
    }

}
