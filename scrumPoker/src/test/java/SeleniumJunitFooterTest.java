import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeleniumJunitFooterTest {

    //aprasomi veiksmai, kurie bus atliekami pries kiekvino testo paleidima
    //describing actions, which will be performed before every test launch
    @Before
    public void setup() {
        Selenium.setup(Selenium.POKER_URL);
    }

    @Test

    public void pressImprintLinkPositiveTest() {
        Selenium.clickFooterImprintLink();
        String expectedResults = "Imprint";
        Assert.assertEquals(Selenium.getFooterLinkResults(), expectedResults);

    }

    @Test

    public void pressImprintLinkNegativeTest() {
        Selenium.clickFooterImprintLink();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getFooterLinkResults(), expectedResults);

    }

    @Test

    public void pressPrivacyLinkPositiveTest() {
        Selenium.clickFooterPrivacyLink();
        String expectedResults = "Festo Data Privacy Statement";
        Assert.assertEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
    @Test

    public void pressPrivacyLinkNegativeTest() {
        Selenium.clickFooterPrivacyLink();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
    @Test

    public void pressTermsLinkPositiveTest() {
        Selenium.clickFooterTermsLink();
        String expectedResults = "Terms and Conditions of Sale";
        Assert.assertEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
    @Test

    public void pressTermsLinkNegativeTest() {
        Selenium.clickFooterTermsLink();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
    @Test

    public void pressCloudLinkPositiveTest() {
        Selenium.clickFooterCloudLink();
        String expectedResults = "Cloud Services";
        Assert.assertEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
    @Test

    public void pressCloudLinkNegativeTest() {
        Selenium.clickFooterCloudLink();
        String expectedResults = "Something";
        Assert.assertNotEquals(Selenium.getFooterLinkResults(), expectedResults);

    }
}
