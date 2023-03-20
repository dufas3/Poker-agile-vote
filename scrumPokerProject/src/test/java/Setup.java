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

public class Setup {
    public static WebDriver browser;
    public static WebDriver mainBrowser;
    public static WebDriver alternativeBrowser;
    public static final String POKER_URL = "https://pokerface-test.azurewebsites.net/";


    public static WebDriver setup() { //leidzia per chrome by default, jeigu settingsu lentelej nurodai BROWSER=edge, tada leis edge
        String browserType = System.getenv("BROWSER");

        WebDriver browser;
        if ("edge".equals(browserType)) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            browser = new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            browser = new ChromeDriver(options);
        }
        return browser;
    }

    public static void waitForElementToAppear(By element) {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(20)).until(e -> e.findElement(element).isDisplayed());
    }

    public static void clickJavaScript(WebElement element) {
        ((JavascriptExecutor) browser).executeScript("arguments[0].click()", element);
    }

    public static void acceptFestoCookies() {
        By buttonSelector = By.xpath("//button[contains(text(),'Accept All Cookies')]");
        new WebDriverWait(Setup.browser, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(buttonSelector));
        Setup.browser.findElement(buttonSelector).click();
    }

    public static void launchMainBrowser() {
        if (mainBrowser == null) {
            mainBrowser = setup();
            mainBrowser.get(POKER_URL);
        }
        browser = mainBrowser;
    }

    public static void launchAlternativeBrowser() {
        if (alternativeBrowser == null) {
            alternativeBrowser = setup();
            alternativeBrowser.get(POKER_URL);
        }
        browser = alternativeBrowser;
    }

    public static void closePage() {
        if (mainBrowser != null) mainBrowser.close();
        if (alternativeBrowser != null) alternativeBrowser.close();
        mainBrowser = null;
        alternativeBrowser = null;
    }
}




