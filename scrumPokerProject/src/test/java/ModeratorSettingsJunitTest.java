import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModeratorSettingsJunitTest {

    ArrayList<String> valuesExpected;

    @Before
    public void setup() {
        Setup.launchMainBrowser();

        valuesExpected = new ArrayList<>();
        valuesExpected.add("Use all cards");
        valuesExpected.add("0");
        valuesExpected.add("1/2");
        valuesExpected.add("1");
        valuesExpected.add("2");
        valuesExpected.add("3");
        valuesExpected.add("5");
        valuesExpected.add("8");
        valuesExpected.add("13");
        valuesExpected.add("20");
        valuesExpected.add("40");
        valuesExpected.add("100");
        valuesExpected.add("?");
        valuesExpected.add("Coffee");
    }

    @Test
    public void getPossibleValuesPositiveTest() { //tikrinam, ar moderatorius mato visus checkboxus pas save settingsuose, lyginam su reikalavimais
        LoginLogout.clickIconModeratorLogin();
        ModeratorSettings.clickModeratorSettings();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.getPossibleCardCheckboxValuesList();
        ArrayList<String> actualList = ModeratorSettings.getPossibleCardCheckboxValuesList();
        System.out.println("actual list: " + ModeratorSettings.getPossibleCardCheckboxValuesList());
        Assert.assertEquals(actualList, valuesExpected);
    }

    @Test
    public void selectAllCardsPositiveTest() { //tikrinam, ar moderatorius gali parikti all cards(paclickint all cards checkboxa)
        LoginLogout.clickIconModeratorLogin();
        ModeratorSettings.clickModeratorSettings();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.selectCardCheckbox(0);
        ModeratorSettings.clickSaveButton();
        List<String> actualListOfModeratorCards = ModeratorSettings.getPossibleModeratorCardsList();
        Assert.assertEquals(actualListOfModeratorCards, valuesExpected);
    }

    @Test
    public void selectSpecificCardsPositiveTest() { ///nebaigta, clikint ne paga index, nes kaip sulyginsi su voting area kortelem???
        LoginLogout.clickIconModeratorLogin();
        ModeratorSettings.clickModeratorSettings();
        ModeratorSettings.waitForCardCheckboxOptions();
        ModeratorSettings.selectCardCheckbox(0);
        ModeratorSettings.unselectCardCheckbox(0);
        ModeratorSettings.selectCardCheckbox(1);
        ModeratorSettings.selectCardCheckbox(2);
        ModeratorSettings.selectCardCheckbox(3);
        ModeratorSettings.clickSaveButton();
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}
