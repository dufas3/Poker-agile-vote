import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Header {
    public static void clickUserIcon() {
        WebElement userIcon = Setup.browser.findElement(By.id("H1"));
        userIcon.click();

        Setup.browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void clickFestoLogo() {
        WebElement festoLogo = Setup.browser.findElement(By.className("logo"));
        festoLogo.click();

        Setup.browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        By buttonSelector = By.xpath("/html/body/dialog/div/div/menu/button[2]");
        new WebDriverWait(Setup.browser, Duration.ofSeconds(3)).until(
                ExpectedConditions.presenceOfElementLocated(buttonSelector));
        Setup.browser.findElement(buttonSelector).click();
    }

    public static String getHeaderIconResults() {
        WebElement getHeaderResults = Setup.browser.findElement(By.cssSelector("#root > div.App > div > div > div.info > h2"));
        return getHeaderResults.getText();
    }

    public static String getFestoLogoResults() {
        WebElement getLogoResults = Setup.browser.findElement(By.className("headline--YLOqq"));
        String resultsHeadlineText = getLogoResults.getText();
        return resultsHeadlineText;
    }
}
