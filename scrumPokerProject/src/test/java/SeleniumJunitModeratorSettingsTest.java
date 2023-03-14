import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SeleniumJunitModeratorSettingsTest {

    ArrayList<String> valuesExpected;

    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);

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
        Selenium.loginModerator();
        Selenium.clickModeratorSettings();
        Selenium.waitForCardCheckboxOptions();
        Selenium.getPossibleCardCheckboxValuesList();
        ArrayList<String> actualList = Selenium.getPossibleCardCheckboxValuesList();
        System.out.println("actual list: " + Selenium.getPossibleCardCheckboxValuesList());
        Assert.assertEquals(actualList, valuesExpected);
    }

    @Test

    public void selectAllCardsPositiveTest() { //tikrinam, ar moderatorius gali parikti all cards(paclickint all cards checkboxa)
        Selenium.loginModerator();
        Selenium.clickModeratorSettings();
        Selenium.waitForCardCheckboxOptions();
        Selenium.selectCardCheckbox(0);
        List<String> actualListOfModeratorCards = Selenium.getPossibleModeratorCardsList();
        Assert.assertEquals(actualListOfModeratorCards, valuesExpected);
    }

    @Test

    public void selectSpecificCardsPositiveTest() { ///nebaigta, clikint ne paga index, nes kaip sulyginsi su voting area kortelem???
        Selenium.loginModerator();
        Selenium.clickModeratorSettings();
        Selenium.waitForCardCheckboxOptions();
        Selenium.selectCardCheckbox(0);
        Selenium.unselectCardCheckbox(0);
        Selenium.selectCardCheckbox(1);
        Selenium.selectCardCheckbox(2);
        Selenium.selectCardCheckbox(3);

    }
}
