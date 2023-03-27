import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Header {

    public static void clickUserIcon(String text) {
        Setup.waitForElementToAppear(By.partialLinkText(text));
        WebElement userIconLogin = Setup.browser.findElement(By.partialLinkText(text));
        Setup.clickJavaScript(userIconLogin);
    }

    public static void clickFestoLogo() {
        Setup.waitForElementToAppear(By.id("logolink"));
        WebElement festoLogo = Setup.browser.findElement(By.id("logolink"));
        Assert.assertTrue("Festo logo is invisible", festoLogo.isDisplayed());
        Setup.clickJavaScript(festoLogo);
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
