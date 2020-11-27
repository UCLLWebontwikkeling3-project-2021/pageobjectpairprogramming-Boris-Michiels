import org.openqa.selenium.*;

import java.util.ArrayList;

/**
 * @author Filip Saint, Boris Michiels
 * */

public class ContactsPage extends Page {
    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=ContactsPage");
    }

    public boolean containsUserWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithDate(String date) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(date)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithTime(String time) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(time)) {
                found=true;
            }
        }
        return found;
    }
}