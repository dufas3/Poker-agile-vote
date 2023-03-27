import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class PlayerFunctionalityJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void getCheckMarkPlayerVotedTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/button/i"));
        ModeratorSettings.clickVotingConfigurationButton();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.clickCardCheckbox("0");
        ModeratorSettings.clickCardCheckbox("3");
        ModeratorSettings.clickCardCheckbox("8");
        ModeratorSettings.clickCardCheckbox("20");
        ModeratorSettings.clickCardCheckbox("Coffee");
        ModeratorSettings.clickSaveButton();
        ModeratorSettings.waitForCardsOptions();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        ModeratorSettings.waitForCardsOptions();
        PlayerFunctionality.clickCardPlayer("20");
        PlayerFunctionality.getCheckMarkElement("saule");
        String expectedResults = "fa-regular fa-circle-check text-primary";
        Assert.assertEquals(PlayerFunctionality.getCheckMarkElement("saule"), expectedResults);
    }

    @Test
    public void comparePlayerCardsWithModeratorCardsPositiveTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/button/i"));
        ModeratorSettings.clickVotingConfigurationButton();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.clickCardCheckbox("0");
        ModeratorSettings.clickCardCheckbox("3");
        ModeratorSettings.clickCardCheckbox("8");
        ModeratorSettings.clickCardCheckbox("20");
        ModeratorSettings.clickCardCheckbox("Coffee");
        ModeratorSettings.clickSaveButton();
        ModeratorSettings.waitForCardsOptions();
        List<String> actualListOfModeratorCards = ModeratorSettings.getPossibleModeratorVotingAreaCardsList();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        ModeratorSettings.waitForCardsOptions();
        PlayerFunctionality.getPossiblePayerVotingAreaCardsList();
        System.out.println("Moderator cards list: " + ModeratorSettings.getPossibleModeratorVotingAreaCardsList());
        System.out.println("Player cards list: " + PlayerFunctionality.getPossiblePayerVotingAreaCardsList());
        ArrayList<String> actualListPlayer = PlayerFunctionality.getPossiblePayerVotingAreaCardsList();
        Assert.assertEquals(actualListOfModeratorCards, actualListPlayer);
    }

    @Test
    public void getPlayerNameInTheListTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        LoginLogout.waitForNameResults(LoginLogout.FIELD_NAME);
        PlayerFunctionality.getPlayerVisibleInTheList(LoginLogout.FIELD_NAME);
        String expectedPlayerName = "saule";
        String actualPlayerName = PlayerFunctionality.getPlayerVisibleInTheList(LoginLogout.FIELD_NAME);
        Assert.assertEquals(expectedPlayerName, actualPlayerName);
    }

    @Test
    public void getQuestionMarkPlayerNotVotedTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/button/i"));
        ModeratorSettings.clickVotingConfigurationButton();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.clickCardCheckbox("0");
        ModeratorSettings.clickCardCheckbox("3");
        ModeratorSettings.clickCardCheckbox("8");
        ModeratorSettings.clickCardCheckbox("20");
        ModeratorSettings.clickCardCheckbox("Coffee");
        ModeratorSettings.clickSaveButton();
        ModeratorSettings.waitForCardsOptions();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("Justas");
        LoginLogout.clickEnterPlayerButton();
        Setup.launchThirdBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        ModeratorSettings.waitForCardsOptions();
        PlayerFunctionality.clickCardPlayer("20");
        Setup.launchMainBrowser();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div/div/button[1]/h6"));
        ModeratorSettings.clickFlipCardsButton();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/h5"));
        PlayerFunctionality.getVotingResultAfterFlipCards("Justas");
        String expectedSymbolResult = "vote-icon";
        String actualSymbolResult = PlayerFunctionality.getVotingResultAfterFlipCards("Justas");
        Assert.assertEquals(actualSymbolResult, expectedSymbolResult);
    }

    @Test
    public void getVotingResultNextToPlayerNameTest() {
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRECT);
        LoginLogout.submitLoginForm();
        LoginLogout.waitForLoginResults();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/button/i"));
        ModeratorSettings.clickVotingConfigurationButton();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.clickCardCheckbox("0");
        ModeratorSettings.clickCardCheckbox("3");
        ModeratorSettings.clickCardCheckbox("8");
        ModeratorSettings.clickCardCheckbox("20");
        ModeratorSettings.clickCardCheckbox("Coffee");
        ModeratorSettings.clickSaveButton();
        ModeratorSettings.waitForCardsOptions();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer("Justas");
        LoginLogout.clickEnterPlayerButton();
        ModeratorSettings.waitForCardsOptions();
        PlayerFunctionality.clickCardPlayer("8");
        Setup.launchMainBrowser();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div/div/button[1]/h6"));
        ModeratorSettings.clickFlipCardsButton();
        Setup.waitForElementToAppear(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/h5"));
        PlayerFunctionality.getVotingResultAfterFlipCards("Justas");
        String expectedSymbolResult = "vote-icon";
        String actualSymbolResult = PlayerFunctionality.getVotingResultAfterFlipCards("Justas");
        Assert.assertEquals(actualSymbolResult, expectedSymbolResult);
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}
