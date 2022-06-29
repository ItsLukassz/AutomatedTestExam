import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

public class EntrySearch {

    static String Link = "http://localhost:8080";

    static ChromeDriver browser;

    public static void main(String[] args) {

        // using chrome 102 web driver
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");
        OpenChrome();
        UserLogin.Login("Testas", "Testas", browser);
        SearchEntry(true, browser);
        CloseChrome();

    }

    public static boolean SearchEntry(boolean SupossedtoFail, WebDriver browser) {

        // Creating at least 1 entry so the test doesn't fail when its not supossed to
        EntryCreation.CreateNewEntry("899", "898", browser);

        // Finding "Atliktos operacijos button and clicking it
        browser.findElement(By.xpath("//a[normalize-space()='Atliktos operacijos']")).click();

        List<WebElement> Results;

        if (SupossedtoFail) {
            // Searching how many entries exist with the given number
            Results = browser.findElements(By.xpath("//td[contains(.,'IMGOINGTOFAIL')]"));

        } else {
            // Searching how many entries exist with the given number
            Results = browser.findElements(By.xpath("//td[contains(.,'1797')]"));
        }

        if (Results.size() > 0) {
            System.out.println("Found: " + Results.size());
            return true;
        } else {

            System.out.println("Found: " + Results.size());
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
