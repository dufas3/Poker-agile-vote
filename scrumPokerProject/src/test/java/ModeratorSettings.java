import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ModeratorSettings {

    public static void clickFlipCardsButton() {
        WebElement flipCardsButton = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div/div/button[1]/h6"));
        Assert.assertTrue("Clear votes button is invisible", flipCardsButton.isDisplayed());
        flipCardsButton.click();
    }

    public static void clickClearVotesButton() {
        WebElement clearVotesButton = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div/div/button[2]/h6"));
        Assert.assertTrue("Clear votes button is invisible", clearVotesButton.isDisplayed());
        clearVotesButton.click();
    }

    public static void clickFinishVotingButton() {
        WebElement finishVotingButton = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div/button/h6"));
        Assert.assertTrue("Finish voting button is invisible", finishVotingButton.isDisplayed());
        finishVotingButton.click();
    }

    public static void clickVotingConfigurationButton() {
        WebElement votingConfigurationButton = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/button/i"));
        Assert.assertTrue("Voting configuration button is invisible", votingConfigurationButton.isDisplayed());
        votingConfigurationButton.click();
    }

    public static void waitForCardCheckboxOptions() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("form-check-input"), 2));
    }

    public static void waitForCardsOptions() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("card-css"), 1));
    }

    public static void clickCardCheckbox(String text) {
        WebElement cardBoxOption = Setup.browser.findElement(By.xpath("//label[contains(.,'" + text + "')]"));
        cardBoxOption.click();
    }

    public static Boolean checkIfCardCheckboxIsMarked(String text) {
        WebElement checkbox = Setup.browser.findElement(By.xpath("//label[contains(.,'" + text + "')]"));
        if(checkbox.isSelected()) {
            System.out.println("Checkbox is selected");
        }
        else {
            checkbox.click();
            checkbox.click();
        }
        return true;
    }

    public static void clickAndValidateCardCheckbox(String text) {
        Setup.waitForElementToAppear(By.xpath("//label[contains(.,'" + text + "')]"));
        WebElement cardBoxOption = Setup.browser.findElement(By.xpath("//label[contains(.,'" + text + "')]"));
        cardBoxOption.click();
        if(cardBoxOption.isSelected()) {
            System.out.println("Checkbox is selected");
        }
        else {
            cardBoxOption.click();
            cardBoxOption.click();
        }
    }

    public static void clickSaveButton() {
        WebElement saveButton = Setup.browser.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div[1]/div[2]/div[3]/button[1]/h6"));
        Assert.assertTrue("Save button is invisible", saveButton.isDisplayed());
        saveButton.click();
    }

    public static ArrayList<String> getPossibleCardCheckboxValuesList() {
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement element : Setup.browser.findElements(By.className("form-check-label"))) {
            actual.add(element.getText());
        }
        return actual;
    }

    public static ArrayList<String> getPossibleModeratorVotingAreaCardsList() {
        ArrayList<String> moderatorCards = new ArrayList<>();
        for (WebElement cardsElement : Setup.browser.findElements(By.className("number"))) {
            moderatorCards.add(cardsElement.getText());
        }
        System.out.println(moderatorCards);
        return moderatorCards;
    }

    public static String getCircleText() {
        WebElement circleText = Setup.browser.findElement(By.className("circle"));
        String resultsText = circleText.getText();
        System.out.println(resultsText);
        return resultsText;
    }

    public static boolean waitForNumberOfCheckmarksToBe(int number) {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(6)).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//i[@class='fa-regular fa-circle-check text-primary']"), number));
        return true;
    }

    public static String getFirstUserInTheList() {
        ArrayList<String> actualUsers = new ArrayList<>();
        String firstElement = null;
        for (WebElement element : Setup.browser.findElements(By.className("align-center-start"))) {
            actualUsers.add(element.getText());
            firstElement = actualUsers.get(0);
            if (firstElement.equals("testemail@gmail.com")) {
                break;
            }
        }
        System.out.println(firstElement);
        return firstElement;
    }

    public static String getPhaseMessageTopOffList() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > div.poker > div.player-list.border.rounded.bg-light > h6")));
        WebElement phaseMessage = Setup.browser.findElement(By.cssSelector("#root > div > div.poker > div.player-list.border.rounded.bg-light > h6"));
        String phaseMessageText = phaseMessage.getText();
        System.out.println(phaseMessageText);
        return phaseMessageText;
    }

    public static void waitPhaseMessageResults(String text) {
        WebElement name = Setup.browser.findElement(By.cssSelector("#root > div > div.poker > div.player-list.border.rounded.bg-light > h6"));
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElement(name, text));
    }
}