import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/**
 * @author Filip Saint, Boris Michiels
 * */

public class ProfilePage extends Page {
    @FindBy(id = "useridLogin")
    private WebElement useridField;

    @FindBy(id = "passwordLogIn")
    private WebElement passwordField;

    @FindBy(id = "logIn")
    private WebElement logInButton;

    public ProfilePage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=ProfilePage");
    }

    public void logIn(String userName, String password) {
        useridField.sendKeys(userName);
        passwordField.sendKeys(password);
        logInButton.click();
    }
}