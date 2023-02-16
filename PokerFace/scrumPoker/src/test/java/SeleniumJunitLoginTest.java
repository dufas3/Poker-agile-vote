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

    public void loginModeratorPositiveTest() {
        Selenium.loginPlayer();
        String expectedResults = "ModeratorName";
        Assert.assertEquals(Selenium.getLoginResults(), expectedResults);

    }

    @Test
    public void loginModeratorNegativeTest() {
        Selenium.loginPlayer();
        String expectedResults = "Business";
        Assert.assertNotEquals(Selenium.getLoginResults(), expectedResults);

    }


}