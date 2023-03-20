import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ModeratorSettings {

    //TODO dar neparasyti testai siai funkcijai, del to neaktyvi, testai bus parasyti
    public static void flipCard() {
        WebElement flipCardButton = Setup.browser.findElement(By.className("flip-cards btn btn-outline-primary"));
        flipCardButton.click();
    }
    //TODO dar neparasyti testai siai funkcijai, del to neaktyvi, testai bus parasyti
    public static void clearVotes() {
        WebElement clearCardButton = Setup.browser.findElement(By.className("clear-votes btn btn-outline-primary"));
        clearCardButton.click();
    }
    //TODO dar neparasyti testai siai funkcijai, del to neaktyvi, testai bus parasyti
    public static void finishVoting() {
        WebElement finishVotingButton = Setup.browser.findElement(By.className("finish-voting btn btn-primary"));
        finishVotingButton.click();
    }

    public static void clickModeratorSettings() {
        WebElement moderatorSettingsButton = Setup.browser.findElement(By.cssSelector("#root > div.App > div > div.voting > div.voting-container.border.rounded.bg-light > button"));
        moderatorSettingsButton.click();

    }

    public static void waitForCardCheckboxOptions() {
        new WebDriverWait(Setup.browser, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("form-check-input"), 1));
    }

    public static void selectCardCheckbox(int index) {
        List<WebElement> checkbox = Setup.browser.findElements(By.className("form-check-input"));
        int size = checkbox.size();
        System.out.println("Dydis " + size);

        Assert.assertTrue("Index out of range", size > index);
        Assert.assertFalse("Card box is selected", checkbox.get(index).isSelected()); //patikrini ar nera paselectintas ir ar is vis galima clickint, ar yra clikinimo funkcionalumas

        checkbox.get(index).click();

        Assert.assertTrue("Card box is not selected", checkbox.get(index).isSelected()); //patikrini ar packlikino
    }

    public static void clickSaveButton() {
        WebElement saveButton = Setup.browser.findElement(By.className("w-25 btn btn-primary"));
        saveButton.click();
    }


    public static void unselectCardCheckbox(int index) {
        List<WebElement> checkbox = Setup.browser.findElements(By.className("form-check-input"));
        int size = checkbox.size();

        Assert.assertTrue("Index out of range", size > index);
        Assert.assertTrue("Card box is not selected", checkbox.get(index).isSelected()); //patikrini ar yra jau paselectinta

        checkbox.get(index).click();

        Assert.assertFalse("Card box is selected", checkbox.get(index).isSelected()); //patikrini ar nusiselectino
    }

    public static ArrayList<String> getPossibleCardCheckboxValuesList() {
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement element : Setup.browser.findElements(By.className("form-check-label"))) {
            actual.add(element.getText());
        }

        return actual;
    }

    public static ArrayList<String> getPossibleModeratorCardsList() {
        ArrayList<String> moderatorCards = new ArrayList<>();
        for (WebElement cardsElement : Setup.browser.findElements(By.className("number"))) {
            moderatorCards.add(cardsElement.getText());
        }
        return moderatorCards;
    }
}