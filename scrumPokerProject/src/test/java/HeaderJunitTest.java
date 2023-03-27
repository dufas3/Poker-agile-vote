import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeaderJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void pressUserIconPositiveTest() {
        Setup.launchAlternativeBrowser();
        Header.clickUserIcon("Login");
        String expectedResults = "Welcome back";
        Assert.assertEquals(expectedResults,Header.getHeaderIconResults());
    }

    @Test
    public void pressUserIconNegativeTest() {
        Setup.launchAlternativeBrowser();
        Header.clickUserIcon("Login");
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Header.getHeaderIconResults());
    }

    @Test
    public void pressFestoLogoPositiveTest() {
        Header.clickFestoLogo();
        Setup.acceptFestoCookies();
        String currentUrlFesto = Setup.browser.getCurrentUrl();
        String expectedUrlFesto = Setup.URL_FESTO;
        Assert.assertEquals(expectedUrlFesto, currentUrlFesto);
    }

    @Test
    public void pressFestoLogoNegativeTest() {
        Header.clickFestoLogo();
        Setup.acceptFestoCookies();
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Header.getFestoLogoResults());
    }

   @After
    public void finish() {
        Setup.closePage();
    }
}
