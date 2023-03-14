import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeleniumJunitLoginTest {

    //aprasomi veiksmai, kurie bus atliekami pries kiekvino testo paleidima
    //describing actions, which will be performed before every test launch
    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);
    }

    @Test

    public void loginPlayerPositiveTest() {
        Selenium.loginPlayer();
        String expectedResults = "Antanukas";
        Assert.assertEquals(Selenium.getLoginResults(), expectedResults);

    }

    @Test
    public void loginPlayerNegativeTest() {
        Selenium.loginPlayer();
        String expectedResults = "Business";
        Assert.assertNotEquals(Selenium.getLoginResults(), expectedResults);

    }

    @Test
    public void loginPlayerUnsuccessfulMessageTest() {
        Selenium.loginPlayer();
        Selenium.getUnsuccessfulLoginMessage();
        String expectedResults = "Please enter username!";
        Assert.assertNotEquals(Selenium.getUnsuccessfulLoginMessage(), expectedResults);

    }

    @Test
    public void loginModeratorPositiveTest() {
        Selenium.loginModerator();
        String expectedResults = "testemail@gmail.com";
        Assert.assertEquals(Selenium.getLoginResults(), expectedResults);

    }

    @Test
    public void loginModeratorNegativeTest() {
        Selenium.loginModerator();
        String expectedResults = "Business";
        Assert.assertNotEquals(Selenium.getLoginResults(), expectedResults);

    }

    @Test
    public void logoutUserPlayerPositiveTest() {
        Selenium.loginPlayer();
        Selenium.logoutUser();
        String currentUrl = Selenium.browser.getCurrentUrl();
        Assert.assertEquals(Selenium.POKER_URL, currentUrl);
    }
    @Test
    public void logoutUserPlayerNegativeTest() {
        Selenium.loginPlayer();
        String currentUrl = Selenium.browser.getCurrentUrl();
        Selenium.logoutUser();
        Assert.assertNotEquals(Selenium.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserModeratorPositiveTest() {
        Selenium.loginModerator();
        Selenium.logoutUser();
        String currentUrl = Selenium.browser.getCurrentUrl();
        Assert.assertEquals(Selenium.POKER_URL, currentUrl);
    }
    @Test
    public void logoutUserModeratorNegativeTest() {
        Selenium.loginModerator();
        String currentUrl = Selenium.browser.getCurrentUrl();
        Selenium.logoutUser();
        Assert.assertNotEquals(Selenium.POKER_URL, currentUrl);
    }


}