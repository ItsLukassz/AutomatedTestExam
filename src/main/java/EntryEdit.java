import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class EntryEdit {

    static String Link = "http://localhost:8080";

    static ChromeDriver browser;

    public static void main(String[] args) {

        // using chrome 102 web driver
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");

        OpenChrome();
        UserLogin.Login("Testas", "Testas" ,browser);
        EditEntry(false, "567", browser);
        CloseChrome();

    }

    public static boolean EditEntry(boolean SupossedToFail, String NumberChange, WebDriver browser){

        // Finding "Atliktos operacijos button and clicking it
        browser.findElement(By.xpath("//a[normalize-space()='Atliktos operacijos']")).click();

        // clicking "Keisti" on the first entry of the table
        browser.findElement(By.xpath("/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/a[1]")).click();

        // Checking if the test is supposed to be negative
        // If SupossedToFail = true we fail the test
        if(SupossedToFail){
            // Finding the first number and clearing the input field before submitting
            WebElement FirstNumberField = browser.findElement(By.xpath("//input[@name='sk1']"));
            FirstNumberField.clear();
            FirstNumberField.submit();


        } else{
            // Finding the first number and changing it
            WebElement FirstNumberField = browser.findElement(By.xpath("//input[@name='sk1']"));
            FirstNumberField.clear();
            FirstNumberField.sendKeys(NumberChange);
            FirstNumberField.submit();
        }

        // Checking if the entry edit worked

        boolean success;
        try {
            // Using "Atgal" button to check if creation was successful

            browser.findElement(By.xpath("//a[@type='button']"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        System.out.println("Was the entry edit successful: " + success);
        return success;


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
