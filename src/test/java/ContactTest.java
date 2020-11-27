import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContactTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    private final DateTimeFormatter setDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter setTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH'h'");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\boris\\Documents\\Programs\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path + "?command=Overview");
        ArrayList<WebElement> users = (ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        if (!elementContainsText(users, "aze.rty@gmail.com")) {
            createUser("testAdmin", "aze", "rty", "aze.rty@gmail.com", "t");
        }
        logIn("testAdmin", "t");
        driver.get(path + "?command=Contacts");
    }

    @After
    public void clean() {
        logOut();
        driver.quit();
    }

    @Test
    public void test_AllFieldsCorrect_ContactAdded() {
        addTestContact("test", "testing", "0479666666", "test.testing@tester.com");

        String title = driver.getTitle();
        assertEquals(title, "Contacts");

        List<WebElement> tr = driver.findElements(By.cssSelector("table tr"));
        assertTrue(elementContainsText(tr, "test"));
    }

    private void addTestContact(String firstName, String lastName, String phoneNumber, String email) {
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("phoneNumber", phoneNumber);
        fillOutField("email", email);

        String date = LocalDate.now().format(dateFormatter);
        fillOutTimeField("date", date);

        String time = LocalTime.now().plusHours(2).format(setTimeFormatter);
        System.out.println(time);
        fillOutField("time", time);

        WebElement button = driver.findElement(By.id("add"));
        button.click();
    }

    private void createUser(String userid, String firstName, String lastName, String email, String password) {
        driver.get(path + "?command=Register");

        fillOutField("userid", userid);
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("signUp"));
        button.click();
    }

    private void logIn(String userid, String password) {
        driver.get(path + "?command=Profile");

        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("logIn"));
        button.click();
    }

    private void logOut() {
        driver.get(path + "?command=LogOut");
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void fillOutTimeField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
        field.sendKeys("\n");
    }

    private boolean elementContainsText (List<WebElement> elements, String text) {
        int tests = 0;
        for (WebElement e : elements) {
            if (e.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }
}