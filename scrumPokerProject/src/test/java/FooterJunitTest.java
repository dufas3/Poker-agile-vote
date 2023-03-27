import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FooterJunitTest {

    @Before
    public void setup() {
        Setup.launchMainBrowser();
    }

    @Test
    public void pressImprintLinkPositiveTest() {
        Footer.clickFooterLink("Imprint");
        Setup.acceptFestoCookies();
        String expectedResults = "Imprint";
        Assert.assertEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressImprintLinkNegativeTest() {
        Footer.clickFooterLink("Imprint");
        Setup.acceptFestoCookies();
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressPrivacyLinkPositiveTest() {
        Footer.clickFooterLink("privacy");
        Setup.acceptFestoCookies();
        String expectedResults = "Festo Data Privacy Statement";
        Assert.assertEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressPrivacyLinkNegativeTest() {
        Footer.clickFooterLink("privacy");
        Setup.acceptFestoCookies();
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressTermsLinkPositiveTest() {
        Footer.clickFooterLink("Terms");
        Setup.acceptFestoCookies();
        String expectedResults = "Terms and Conditions of Sale";
        Assert.assertEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressTermsLinkNegativeTest() {
        Footer.clickFooterLink("Terms");
        Setup.acceptFestoCookies();
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressCloudLinkPositiveTest() {
        Footer.clickFooterLink("Cloud");
        Setup.acceptFestoCookies();
        String expectedResults = "Cloud Services";
        Assert.assertEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @Test
    public void pressCloudLinkNegativeTest() {
        Footer.clickFooterLink("Cloud");
        Setup.acceptFestoCookies();
        String expectedResults = "Something";
        Assert.assertNotEquals(expectedResults, Footer.getFooterLinkResults());
    }

    @After
    public void finish() {
        Setup.closePage();
    }
}
