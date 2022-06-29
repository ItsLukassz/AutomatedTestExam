import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

public class EntryDeletion {

    static String Link = "http://localhost:8080";

    static ChromeDriver browser;

    public static void main(String[] args) {

        // using chrome 102 web driver
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");

        OpenChrome();
        UserLogin.Login("Testas", "Testas", browser);
        DeleteEntry(false, browser);
        CloseChrome();

    }

    public static boolean DeleteEntry(boolean SupposedToFail, WebDriver browser) {


        // Finding "Atliktos operacijos button and clicking it
        browser.findElement(By.xpath("//a[normalize-space()='Atliktos operacijos']")).click();

        List<WebElement> NumberOfEntriesBefore = browser.findElements(By.xpath("/html/body/div/table/tbody/tr"));
        System.out.println("Num rows: " + NumberOfEntriesBefore.size());

        // Deleting the first entry
        browser.findElement(By.xpath("/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/a[2]")).click();

        // Checking if the test is supposed to fail
        if (SupposedToFail) {
            browser.switchTo().alert().dismiss();
            System.out.println("Dismissing the alert");
        } else {
            // Accepting the alert
            browser.switchTo().alert().accept();
            System.out.println("Accepting the alert");
        }

        // Checking how many rows again after deleting an entry
        List<WebElement> NumberOfEntriesAfter = browser.findElements(By.xpath("/html/body/div/table/tbody/tr"));
        System.out.println("Num rows: " + NumberOfEntriesAfter.size());


        if (NumberOfEntriesBefore.size() > NumberOfEntriesAfter.size()) {

            return true;

        } else {

            return false;
        }
    }

    // Opening Chrome
    public static void OpenChrome() {

        browser = new ChromeDriver();
        browser.get(Link);

    }

    // Closing Chrome
    public static void CloseChrome() {
        browser.close();
    }


}
