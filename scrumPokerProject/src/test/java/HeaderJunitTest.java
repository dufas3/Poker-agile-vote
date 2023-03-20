import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeaderJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void pressUserIconPositiveTest() {
        Header.clickUserIcon();
        String expectedResults = "Welcome back";
        Assert.assertEquals(Header.getHeaderIconResults(), expectedResults);
    }

    @Test
    public void pressUserIconNegativeTest() {
        Header.clickUserIcon();
        String expectedResults = "Something";
        Assert.assertNotEquals(Header.getHeaderIconResults(), expectedResults);
    }

    @Test
    public void pressFestoLogoPositiveTest() {
        Header.clickFestoLogo();
        String expectedResults = "Discover your Essentials";
        Assert.assertEquals(Header.getFestoLogoResults(), expectedResults);
    }

    @Test
    public void pressFestoLogoNegativeTest() {
        Header.clickFestoLogo();
        String expectedResults = "Something";
        Assert.assertNotEquals(Header.getFestoLogoResults(), expectedResults);
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}
