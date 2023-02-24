import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeleniumJunitHeaderTest {
    //aprasomi veiksmai, kurie bus atliekami pries kiekvino testo paleidima
    //describing actions, which will be performed before every test launch
    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);
    }

    @Test
    public void pressUserIconPositiveTest() {
        Selenium.clickUserIcon();
        String expectedResults = "Welcome back";
        Assert.assertEquals(Selenium.getHeaderIconResults(), expectedResults);

    }

    @Test

    public void pressUserIconNegativeTest() {
        Selenium.clickUserIcon();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getHeaderIconResults(), expectedResults);

    }
    @Test
    public void pressFestoLogoPositiveTest() {
        Selenium.clickUserIcon();
        String expectedResults = "Discover your Essentials";
        Assert.assertEquals(Selenium.getFestoLogoResults(), expectedResults);

    }

    @Test

    public void pressFestoLogoNegativeTest() {
        Selenium.clickUserIcon();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getFestoLogoResults(), expectedResults);

    }
}
