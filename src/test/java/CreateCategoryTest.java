import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.WebEventListener;


import java.sql.Timestamp;

import static utils.Properties.getAdminLogin;
import static utils.Properties.getAdminPassword;
import static utils.Properties.getBaseAdminUrl;

/**
 * Created by olgaliutsko on 3/12/18.
 */
public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) throws InterruptedException {
        // TODO prepare driver object
        eventListener = new WebEventListener();
        EventFiringWebDriver driver = getConfiguredDriver();
        driver.register(eventListener);
        actions = new GeneralActions(driver);
        driver.get(getBaseAdminUrl());


        // login
        actions.login(getAdminLogin(), getAdminPassword());

        // create category
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String categoryName = "Test " + timestamp.getTime();
        actions.createCategory(categoryName);

        // check that new category appears in Categories table
        WebElement successMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
        assert (successMessage.isDisplayed());

        filterByCategoryName(driver, categoryName);

        WebElement categoryNameLabel = driver.findElement(By.xpath("//*[@id='table-category']//tr/td[3]"));
        assert (categoryNameLabel.getText().equals(categoryName));
        // finish script
        driver.quit();
    }

}
