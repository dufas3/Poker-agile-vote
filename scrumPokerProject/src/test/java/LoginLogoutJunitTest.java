import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LoginLogoutJunitTest {


    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void loginPlayerPositiveTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        String expectedResults = "Antanukas";
        Assert.assertEquals(LoginLogout.getLoginResults(), expectedResults);
    }

    @Test
    public void loginPlayerNegativeTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        String expectedResults = "Business";
        Assert.assertNotEquals(LoginLogout.getLoginResults(), expectedResults);
    }

    @Test
    public void loginPlayerUnsuccessfulMessageTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.getUnsuccessfulModeratorLoginMessage();
        String expectedResults = "Please enter username!";
        Assert.assertNotEquals(LoginLogout.getUnsuccessfulModeratorLoginMessage(), expectedResults);
    }

    @Test
    public void loginPlayerNameMaxSymbolsTest() {
        LoginLogout.loginPlayer("Wsnklksjeiwocms01#&laqoksm");
        LoginLogout.clickEnterPlayerButton();
        String expectedNameResult = "Wsnklksjeiwocms01#&laqoksm";
        String actualNameResult = LoginLogout.getLoginResults();
        Assert.assertNotEquals(expectedNameResult, actualNameResult);
    }

    @Test
    public void loginPlayerNameMinSymbolTest() {
        LoginLogout.loginPlayer("");
        LoginLogout.clickEnterPlayerButton();
        String expectedNameResult = "";
        String actualNameResult = LoginLogout.getLoginResults();
        Assert.assertNotEquals(expectedNameResult, actualNameResult);
    }

    @Test
    public void loginPlayerNameEmptyTest() {
        LoginLogout.loginPlayer("");
        WebElement nameInput = LoginLogout.findNameInput();
        String expectedResult = "true";
        Assert.assertEquals(expectedResult, nameInput.getAttribute("required"));
    }

    @Test
    public void loginModeratorPositiveTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        LoginLogout.submitLoginForm();
        String expectedResults = "testemail@gmail.com";
        Assert.assertEquals(LoginLogout.getLoginResults(), expectedResults);
    }

    @Test
    public void loginModeratorNegativeTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        LoginLogout.submitLoginForm();
        String expectedResults = "Business";
        Assert.assertNotEquals(LoginLogout.getLoginResults(), expectedResults);
    }

    @Test
    public void loginModeratorIncorrectPasswordTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_INCORRCT);
        LoginLogout.submitLoginForm();
        LoginLogout.getUnsuccessfulModeratorLoginMessage();
        String actualErrorMessage = LoginLogout.getUnsuccessfulModeratorLoginMessage();
        String expectedErrorMessage = "Wrong username or password!";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void loginModeratorIncorrectEmailTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_INCORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        LoginLogout.submitLoginForm();
        LoginLogout.getUnsuccessfulModeratorLoginMessage();
        String actualErrorMessage = LoginLogout.getUnsuccessfulModeratorLoginMessage();
        String expectedErrorMessage = "Wrong username or password!";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void logoutUserPlayerPositiveTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.logoutUser();
        String currentUrl = Setup.browser.getCurrentUrl();
        Assert.assertEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserPlayerNegativeTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        String currentUrl = Setup.browser.getCurrentUrl();
        LoginLogout.logoutUser();
        Assert.assertNotEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserModeratorPositiveTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        LoginLogout.submitLoginForm();
        LoginLogout.logoutUser();
        String currentUrl = Setup.browser.getCurrentUrl();
        Assert.assertEquals(Setup.POKER_URL, currentUrl);
    }

    @Test
    public void logoutUserModeratorNegativeTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        LoginLogout.submitLoginForm();
        String currentUrl = Setup.browser.getCurrentUrl();
        LoginLogout.logoutUser();
        Assert.assertNotEquals(Setup.POKER_URL, currentUrl);
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}