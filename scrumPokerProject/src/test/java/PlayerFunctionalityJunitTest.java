import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerFunctionalityJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void getCheckMarkPlayerVotedTest() {
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        PlayerFunctionality.waitForCardVoteOptions();
        PlayerFunctionality.clickCardPlayer("20");
        PlayerFunctionality.getCheckMarkElement("Antanukas");
        String expectedResults = "fa-regular fa-circle-check text-primary";
        Assert.assertEquals(PlayerFunctionality.getCheckMarkElement("Antanukas"), expectedResults);
    }

    @Test
    public void comparePlayerCardsWithModeratorCardsPositiveTest() {
        LoginLogout.clickIconModeratorLogin();
        LoginLogout.enterEmail(LoginLogout.EMAIL_CORRECT);
        LoginLogout.enterPassword(LoginLogout.PASSWORD_CORRCT);
        ModeratorSettings.getPossibleModeratorCardsList();
        Setup.launchAlternativeBrowser();
        LoginLogout.loginPlayer(LoginLogout.FIELD_NAME);
        LoginLogout.clickEnterPlayerButton();
        PlayerFunctionality.getPossiblePayerCardsList();
        System.out.println("Moderator cards list: " + ModeratorSettings.getPossibleModeratorCardsList());
        System.out.println("Player cards list: " + PlayerFunctionality.getPossiblePayerCardsList());
        ArrayList<String> actualListPlayer = PlayerFunctionality.getPossiblePayerCardsList();
        ArrayList<String> expectedListModerator = ModeratorSettings.getPossibleModeratorCardsList();
        Assert.assertEquals(expectedListModerator, actualListPlayer);
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}
