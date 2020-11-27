import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

/**
 * @author Filip Saint, Boris Michiels
 * */

public class ContactOverviewTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\boris\\Documents\\Programs\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.logIn("test", "t");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Contact_Boris_Michiels() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(contactsPage.containsUserWithName("Boris Michiels"));
        assertTrue(contactsPage.containsUserWithDate("12/11/2020"));
        assertTrue(contactsPage.containsUserWithTime("16:26"));
    }
}