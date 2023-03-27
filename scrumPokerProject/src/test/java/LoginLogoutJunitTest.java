import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginLogoutJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void loginPlayerPositiveTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("Justas");
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.waitForNameResults("Justas");
        LoginLogout.getLoginResults();
        String actualPlayerName = LoginLogout.getLoginResults();
        System.out.println("result" + LoginLogout.getLoginResults());
        String expectedPlayerName = "Justas";
        Assert.assertEquals(expectedPlayerName, actualPlayerName);
    }

    @Test
    public void loginPlayerNegativeTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        String expectedResults = "Business";
        Assert.assertNotEquals(expectedResults, LoginLogout.getLoginResults());
    }

    @Test
    public void loginPlayerEmptyNameUnsuccessfulMessageTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("");
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.getUnsuccessfulLoginMessage();
        String expectedResults = "Please enter username!";
        Assert.assertEquals(expectedResults, LoginLogout.getUnsuccessfulLoginMessage());
    }

    @Test
    public void loginPlayerNameMaxSymbolsTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("Wsnklksjeiwocms01#&laqoksm");
        LoginLogout.clickEnterPlayerButton();
        String expectedNameResult = "Wsnklksjeiwocms01#&laqoksm";
        String actualNameResult = LoginLogout.getLoginResults();
        Assert.assertNotEquals(expectedNameResult, actualNameResult);
    }

    @Test
    public void loginPlayerSameNameTwiceTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("Antanas");
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.waitForLoginResults();
        Setup.launchThirdBrowser();
        LoginLogout.loginPlayer("Antanas");
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.getUnsuccessfulLoginMessage();
        String actualMessage = LoginLogout.getUnsuccessfulLoginMessage();
        String expectedMessage = "This username is taken!";
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void loginModeratorPositiveTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        LoginLogout.waitForNameResults("testemail@gmail.com");
        String actualModeratorResults = LoginLogout.getLoginResults();
        String expectedModeratorResults = "testemail@gmail.com";
        Assert.assertEquals(expectedModeratorResults, actualModeratorResults);
    }

    @Test
    public void loginModeratorNegativeTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        LoginLogout.getLoginResults();
        String expectedResults = "Business";
        Assert.assertNotEquals(expectedResults, LoginLogout.getLoginResults());
    }

    @Test
    public void loginModeratorIncorrectPasswordTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_INCORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.getUnsuccessfulLoginMessage();
        String actualErrorMessage = LoginLogout.getUnsuccessfulLoginMessage();
        String expectedErrorMessage = "Wrong username or password!";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void loginModeratorIncorrectEmailTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_INCORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.getUnsuccessfulLoginMessage();
        String actualErrorMessage = LoginLogout.getUnsuccessfulLoginMessage();
        String expectedErrorMessage = "Wrong username or password!";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void logoutUserPlayerPositiveTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.pressLoginNameButton();
        LoginLogout.pressLogoutButton();
        String currentUrl = Setup.browser.getCurrentUrl();
        Assert.assertEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserPlayerNegativeTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.waitForLoginResults();
        String currentUrl = Setup.browser.getCurrentUrl();
        LoginLogout.pressLoginNameButton();
        LoginLogout.pressLogoutButton();
        Assert.assertNotEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserModeratorPositiveTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        Setup.waitForElementToAppear(By.id("dropdown-basic"));
        LoginLogout.pressLoginNameButton();
        LoginLogout.pressLogoutButton();
        String currentUrl = Setup.browser.getCurrentUrl();
        Assert.assertEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserModeratorNegativeTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        String currentUrlLoggedIn = Setup.browser.getCurrentUrl();
        LoginLogout.pressLoginNameButton();
        LoginLogout.pressLogoutButton();
        Assert.assertNotEquals(Setup.POKER_URL, currentUrlLoggedIn);
    }

    @Test
    public void getLoginButtonColorBlueTest() {
        String actualColor = LoginLogout.getButtonColor(By.className("login-button"));
        String expectedColor = "rgb(100, 149, 237) none repeat scroll 0% 0% / auto padding-box border-box";
        Assert.assertEquals(expectedColor, actualColor);
    }

    @Test
    public void getEnterButtonColorBlueTest() {
        Setup.launchAlternativeBrowser();
        String actualColor = LoginLogout.getButtonColor(By.className("join-button"));
        String expectedColor = "rgb(240, 240, 240) none repeat scroll 0% 0% / auto padding-box border-box";
        Assert.assertEquals(expectedColor, actualColor);
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}