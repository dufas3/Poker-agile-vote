import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SeleniumJunitModeratorSettingsTest {

    ArrayList<String> valuesExpected;

    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);

        ArrayList<String> valuesExpected = new ArrayList<>();
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

    public void getPossibleValuesPositiveTest() {
        Selenium.loginModerator();
        Selenium.clickModeratorSettings();
        Selenium.waitForCardOptions();
        Selenium.getPossibleValuesList();
        Assert.assertArrayEquals(Selenium.getPossibleValuesList(), valuesExpected.toArray());
    }
}