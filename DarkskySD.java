package stepdefinition;

import cucumber.api.java.cs.A;
import framework.DarkskyHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DarkskySD {

    private DarkskyHomePage darkskyHomePage = new DarkskyHomePage();

    @Given("^I am on Darksky home page$")
    public void navigateToDarkskyHomePage() throws Throwable {
        String darkSkyTitle = "Dark Sky - 260 Broadway, New York City, NY";
        Assert.assertEquals(SharedSD.getDriver().getTitle(), darkSkyTitle, "Not correct page");

    }

    @And("^I verify timeline current time (.+) is displayed$")
    public void verifyCurrentTime(String currentTimeText) {
        Assert.assertEquals(darkskyHomePage.getCurrentTimeStringText(), currentTimeText);

    }

    @Then("^I verify timeline is displayed with two hours incremented$")
    public void verifyTimeIncrementedByTwoHours() {
        List<WebElement> timesDarksky = darkskyHomePage.getTimesDarksky();
        List<String> expectedTimesDarksky = new ArrayList<>();
        List<String> actualTimesDarksky = new ArrayList<>();
        String hour = darkskyHomePage.getHour(0);

        for (WebElement times : timesDarksky) {
            expectedTimesDarksky.add(times.getText());
        }

        String currentHour = "Now";
        actualTimesDarksky.add(0, currentHour);
        int number = 2;
        for (int i = 1; i < timesDarksky.size(); i++) {
            actualTimesDarksky.add(i, darkskyHomePage.getHour(number).toLowerCase());
            number = number + 2;
        }

        Assert.assertEquals(expectedTimesDarksky, actualTimesDarksky, "Actual and Expected times not equal");


    }

    @When("^I expand todays timeline$")
    public void expandTodayTimeline() throws InterruptedException {
        darkskyHomePage.getTempTimeline();

    }

    @Then("^I verify lowest and highest temp is displayed correctly$")
    public void verifyLowestHighestTemp() {
        boolean low = darkskyHomePage.lowTempDisplayed();
        boolean high = darkskyHomePage.highTempDisplayed();

        Assert.assertTrue(low, "Low Temp Not Displayed. Low temp: " + darkskyHomePage.getCurrentHighTempStringText());
        Assert.assertTrue(high, "High Temp Not Displayed. High temp: " + darkskyHomePage.getCurrentLowTempStringText());
    }

    @Then("^I verify current temp is not greater or less then temps from daily timeline$")
    public void verifyCurrentTempGreaterLesserThanDaily() {

        List<WebElement> currentTemps = darkskyHomePage.getTempsDarksky();
        int[] tempArray = new int[currentTemps.size()];

        String currentTemperature = darkskyHomePage.getCurrentTempText();
        currentTemperature = currentTemperature.replace("Clear.", "").trim();
        currentTemperature = currentTemperature.substring(0, 2);
        int currentTemp = Integer.parseInt(currentTemperature);

        for (int i = 0; i < currentTemps.size(); i++) {
            WebElement temp1 = currentTemps.get(i);
            String tempString1 = temp1.getText().substring(0, 2);
            int tempInt1 = Integer.parseInt(tempString1);
            tempArray[i] = tempInt1;
        }


        int maxT = tempArray[0];
        int minT = tempArray[0];

        for (int i = 0; i < currentTemps.size(); i++) {

            if (tempArray[i] > maxT) {
                maxT = tempArray[i];

            }
        }
        for (int i = 0; i < currentTemps.size(); i++) {

            if (tempArray[i] < minT) {
                minT = tempArray[i];

            }
        }
        System.out.println("Max temp in Daily Temp Values:" + maxT);
        System.out.println("Min temp in Daily Temp Values:" + minT);

        if ((currentTemp > maxT) || (currentTemp < minT)) {
            Assert.assertTrue(darkskyHomePage.currentTempDisplayed(), "Current Temp " + currentTemperature + " is less than or greater than daily Temp values");
        } else {
            System.out.println("Current Temp " + currentTemperature + " is not less than or greater than daily Temp values");
            System.out.println("Daily Temp Values");
            for (WebElement t : currentTemps) {

                System.out.println(t.getText());
            }
        }

    }

}






