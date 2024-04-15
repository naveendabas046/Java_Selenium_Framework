package stepDefinations;

import commonFiles.CommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class MyStepdefs extends CommonMethods {


    @Given("I am on {string} home page")
    public void iAmOnHomePage(String website) {
        connectDriver();
        switch (website.toLowerCase()) {
            case "flipkart" -> driver.get("https://www.flipkart.com/");
            case "amazon" -> driver.get("https://www.amazon.in/");
            default ->
                    throw new SkipException(website + " is not a valid selection, please select from 'flipkart' or 'amazon'");
        }
    }

    @When("I navigate to {string} section")
    public void iNavigateToSection(String section) {
        getElementByXpath("//img[@alt='" + section + "']").click();
    }

    @And("I click on {string} smartphone view all button")
    public void iClickOnSmartphoneViewAllButton(String company) {
        getElementByXpath("//h2[normalize-space()='" + company + " smartphones']/../..//span").click();
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        commonButtonClick(button);
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("I scroll to the {string} of the page")
    public void iScrollToTheOfThePage(String position) {
        switch (position.toLowerCase()) {
            case "bottom" -> js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            case "top" -> js.executeScript("window.scrollTo(0, 0)");
            default ->
                    throw new SkipException(position + " - is not a valid selection, please select 'top' or 'bottom'");
        }
    }

    @Then("I print all devices names and prices")
    public void iPrintAllDevicesNamesAndPrices() {
        SoftAssert softAssert = new SoftAssert();
        String price;
        String name = null;
        List<WebElement> products = driver.findElements(By.xpath("//img[@class='s-image']"));
        for (int i = 1; i <= products.size(); i++) {
            WebElement productData = driver.findElement(By.xpath("(//div[@data-cy='title-recipe']/..//div[@data-cy='title-recipe'])[" + i + "]"));
            try {
                price = productData.findElement(By.xpath("..//span[@class='a-price-whole']")).getText();
                int index = productData.getText().indexOf('(');
                try {
                    name = productData.getText();
                    name = name.substring(0, index).trim();
                } catch (StringIndexOutOfBoundsException ignored) {
                }
                System.out.println("product - " + i + "\n" + name + " price - " + price);

            } catch (NoSuchElementException e) {
                softAssert.fail(name + " (product " + i + ") - price was not displayed on the webpage");
            }
        }
        softAssert.assertAll();
    }
}
