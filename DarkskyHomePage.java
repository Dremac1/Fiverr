package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import stepdefinition.SharedSD;

import java.util.List;

public class DarkskyHomePage extends BasePage {
    private By currentTime = By.xpath("//span[@class='Now']");
    private By times = By.xpath("//span[contains(@class,'hour')]/span");
    private By temps = By.xpath("//span[contains(@style,'opacity')]");
    private By timeLine = By.xpath("//span[contains(text(),'Today')]");
    private By highTemp = By.xpath("//span[@class='maxTemp'][contains(text(),'49˚')]");
    private By lowTemp = By.xpath("//span[@class='minTemp'][contains(text(),'43˚')]");
    private By currentTemp = By.xpath("//span[@class='summary swap']");
    private int num = 0;


    public String getCurrentTimeStringText() {

        return getTextFromElement(currentTime);
    }

    public String getCurrentTempText() {

        return getTextFromElement(currentTemp);
    }

    public List<WebElement> getTimesDarksky() {

        return getTimesIntoArray(times);
    }

    public String getHour(int num) {

        return getCurrentHour(num);
    }

    public List<WebElement> getTempsDarksky() {

        return getTimesIntoArray(temps);
    }

    public boolean lowTempDisplayed() {

        return isElementDisplayed(lowTemp);
    }

    public boolean highTempDisplayed() {
        return isElementDisplayed(highTemp);
    }

    public boolean currentTempDisplayed() {
        return isElementDisplayed(currentTemp);
    }

    public void getTempTimeline() throws InterruptedException {
        tempTimeline(timeLine);
    }

    public String getCurrentHighTempStringText() {

        return getTextFromElement(highTemp);
    }

    public String getCurrentLowTempStringText() {

        return getTextFromElement(lowTemp);
    }

}
