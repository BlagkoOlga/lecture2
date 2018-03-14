import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.Properties;
import utils.WebEventListener;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by olgaliutsko on 3/12/18.
 */
public class BaseScript {
    protected static WebEventListener eventListener;
    protected static GeneralActions actions;
    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
            // TODO prepare required WebDriver instance according to browser type
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseScript.class.getResource("drivers/chromedriver").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        // TODO configure browser window (set timeouts, browser pindow position) and connect loggers.
        return new EventFiringWebDriver(driver);
    }

    static void filterByCategoryName(EventFiringWebDriver driver, String categoryName) {
        WebElement categoryFilterInputField = driver.findElement(By.cssSelector("[name='categoryFilter_name']"));
        categoryFilterInputField.sendKeys(categoryName);

        WebElement filterButton = driver.findElement(By.id("submitFilterButtoncategory"));
        filterButton.click();
    }
}
