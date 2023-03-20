import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PlayerFunctionality {


    //TODO uzkomentuota, nes dar bus koreguojama ir naudojama funkcija ateityje
    public static WebElement findPlayerInList(String player) {

        return Setup.browser.findElement(By.xpath("//div[contains(@class, 'player-list')]//h6[text()[contains(., '" + player + "')]]"));
    }

    //TODO uzkomentuota, nes dar bus koreguojama ir naudojama funkcija ateityje
   /* public static void waitForPlayerAppearInList(String player) {

        Selenium.waitForElement(By.xpath("//div[contains(@class, 'player-list')]//h6[text()[contains(., '" + player + "')]]"));
    }*/

    //TODO uzkomentuota, nes dar bus koreguojama ir naudojama funkcija ateityje
    public static String getPlayerListMessageResults() {
        WebElement getPlayerListMessage = Setup.browser.findElement(By.className("bg-primary border border-primary rounded-top w-100 p-4 text-center text-white"));
        String messageText = getPlayerListMessage.getText();
        return messageText;
    }
    //TODO uzkomentuota, nes dar bus koreguojama ir naudojama funkcija ateityje
   /* public static String getModeratorNumberInList() {
        List<WebElement> listElements = browser.findElements(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[1]/div/h6")); //elementas kuri issitraukiam yra spalva, moderatorius yra kitokios spalvos ir pirmas
        WebElement firstListElement = listElements.get(0);
        String firstListElement = listElements.getText;
        //return firstListElement;

    }*/

    public static void clickCardPlayer(String cardNumber) {
        WebElement playerCard = Setup.browser.findElement(By.className("number"));
        //new Actions(browser).moveToElement(playerCard).build().perform();

        System.out.println("" + playerCard);
        List<WebElement> playerCards = Setup.browser.findElements(By.className("card-css"));
        System.out.println("Total cards: " + playerCards.size());
        //Assert.assertTrue("Player card is not displayed and/or enabled", playerCard.isDisplayed() && playerCard.isEnabled());
        for (WebElement card : playerCards) {
            WebElement cardTag = card.findElement(By.tagName("h2"));
            if (cardTag.getText().equals(cardNumber)) {
                //System.out.println("Card: " + card.getText().equals("3"));
                card.click();
                //card.click();
                break;
            }
        }
    }

    public static void waitForCardVoteOptions() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("card-css"), 1));
    }

    public static String getCheckMarkElement(String playerName) {
        List<WebElement> playersList = Setup.browser.findElements(By.className("align-center-start"));
        String checkMarkText = "";
        for (WebElement player : playersList) {
            WebElement playerTag = player.findElement(By.tagName("h6"));
            if (playerTag.getText().equals(playerName)) {
                checkMarkText = playerTag.findElement(By.tagName("i")).getAttribute("class");
                System.out.println("text: " + checkMarkText);
            }
        }
        return checkMarkText;
    }

    public static ArrayList<String> getPossiblePayerCardsList() {
        ArrayList<String> playerCards = new ArrayList<>();
        for (WebElement cardElement : Setup.browser.findElements(By.className("number"))) {
            playerCards.add(cardElement.getText());
        }
        return playerCards;
    }
}


