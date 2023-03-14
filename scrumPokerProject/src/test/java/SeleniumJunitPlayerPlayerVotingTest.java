import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SeleniumJunitPlayerPlayerVotingTest {
    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);
    }

        @Test

        public void getCheckMarkPlayerVotedTest () {
            Selenium.loginPlayer();
            Selenium.waitForCardVoteOptions();
            Selenium.clickCardPlayer("20");
            Selenium.getCheckMarkElement("Antanukas");
            String expectedResults = "fa-regular fa-circle-check text-primary";
            Assert.assertEquals(Selenium.getCheckMarkElement("Antanukas"), expectedResults);
        }

    @Test

    public void comparePlayerCardsWithModeratorCardsPositiveTest(){
        Selenium.loginModerator();
        Selenium.setupChrome(Selenium.POKER_URL); //kodel pajungus ir moderatoriu ir playeri grazina tuscius sarasus, o ukomentavus setupChrome ir login player grazina playerio ir moderatoriaus grazius sarasus???abiem atvejais testas passina
        Selenium.loginPlayer();
        Selenium.getPossibleModeratorCardsList();
        Selenium.getPossiblePayerCardsList();
        System.out.println("Moderator cards list: " + Selenium.getPossibleModeratorCardsList());
        System.out.println("Player cards list: " + Selenium.getPossiblePayerCardsList());
        ArrayList<String> actualListPlayer = Selenium.getPossiblePayerCardsList();
        ArrayList<String> expectedListModerator = Selenium.getPossibleModeratorCardsList();
        Assert.assertEquals(expectedListModerator, actualListPlayer);
    }
    }
