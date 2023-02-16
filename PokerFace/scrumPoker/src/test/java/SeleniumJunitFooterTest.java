import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeleniumJunitFooterTest {

    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);
    }

    @Test

    public void pressFooterLinkPositiveTest() {
        Selenium.loginPlayer();
        String expectedResults = "Antanukas";
        Assert.assertEquals(Selenium.getLoginResults(), expectedResults);

    }
}
