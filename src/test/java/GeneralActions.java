import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by olgaliutsko on 3/12/18.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     *
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        WebElement loginField = driver.findElement(By.id("email"));
        enterText(loginField, login);

        WebElement passwordField = driver.findElement(By.id("passwd"));
        enterText(passwordField, password);

        WebElement loginButton = driver.findElement(By.name("submitLogin"));
        loginButton.click();
    }

    /**
     * Adds new category in Admin Panel.
     *
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        WebElement quickLink = driver.findElement(By.id("quick_select"));
        quickLink.click();

        WebElement newCategory = driver.findElement(By.xpath("//ul[@id='header_quick']//li[2]"));
        newCategory.click();

        waitForContentLoad();

        WebElement nameInputField = driver.findElement(By.id("name_1"));
        nameInputField.sendKeys(categoryName);

        WebElement saveCategoryButton = driver.findElement(By.id("category_form_submit_btn"));
        saveCategoryButton.click();

    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("category_form")));
    }

    public static void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
