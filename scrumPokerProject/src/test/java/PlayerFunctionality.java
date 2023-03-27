import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PlayerFunctionality {

    public static void clickCardPlayer(String cardNumber) {
        WebElement playerCard = Setup.browser.findElement(By.className("number"));
        System.out.println("" + playerCard);

        List<WebElement> playerCards = Setup.browser.findElements(By.className("card-css"));
        System.out.println("Total cards: " + playerCards.size());

        for (WebElement card : playerCards) {
            WebElement cardTag = card.findElement(By.tagName("h2"));
            if (cardTag.getText().equals(cardNumber)) {
                card.click();
                break;
            }
        }
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

    public static String getVotingResultAfterFlipCards(String playerName) {
        List<WebElement> playersList = Setup.browser.findElements(By.className("align-center-start"));
        String votingResultText = "";

        for (WebElement player : playersList) {
            WebElement playerTag = player.findElement(By.tagName("h6"));
            if (playerTag.getText().equals(playerName)) {
                votingResultText = playerTag.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/h5")).getAttribute("class");
            }
        }
        System.out.println("text: " + votingResultText);
        return votingResultText;
    }

    public static ArrayList<String> getPossiblePayerVotingAreaCardsList() {
        ArrayList<String> playerCards = new ArrayList<>();
        for (WebElement cardElement : Setup.browser.findElements(By.className("number"))) {
            playerCards.add(cardElement.getText());
        }
        return playerCards;
    }

    public static String getPlayerVisibleInTheList(String playerName) {
        WebElement playerInList = Setup.browser.findElement(By.xpath("//*[text()='" + playerName + "']"));
        String playerNameText = playerInList.getText();
        System.out.println(playerNameText);
        return playerNameText;
    }

//TODO uzkomentuota, nes dar bus koreguojama ir naudojama funkcija ateityje
    public static String getCardColor(){
        String cardColor = Setup.browser.findElement(By.className("login-button")).getCssValue("background");
        System.out.println("Button color: " + cardColor);
        return cardColor;
    }
}

