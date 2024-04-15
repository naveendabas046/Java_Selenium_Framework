package commonFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonMethods {

    public RemoteWebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public void connectDriver() {
        js = driver;
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    public WebElement getElementByXpath(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void commonButtonClick(String button) {
        List<WebElement> buttons = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        while (buttons.isEmpty() && (System.currentTimeMillis() - startTime < 10000)) {
            buttons.addAll(driver.findElements(By.xpath("//button[normalize-space()='" + button + "']")));
            buttons.addAll(driver.findElements(By.xpath("//span[@alt='" + button + "']")));
            buttons.addAll(driver.findElements(By.xpath("//span[@img='" + button + "']")));
            buttons.addAll(driver.findElements(By.partialLinkText(button)));
            buttons.addAll(driver.findElements(By.xpath("//span[normalize-space()='" + button + "']")));
        }
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) btn.click();
            break;
        }
    }
}
