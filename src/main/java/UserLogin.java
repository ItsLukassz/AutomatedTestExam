import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserLogin {


    static String Link = "http://localhost:8080";

    static ChromeDriver browser;

    public static void main(String[] args) {

        // using chrome 102 web driver
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");


        OpenChrome();
        Login("Testas", "Testas", browser);
        CloseChrome();

    }

    public static boolean Login(String Username, String Password, WebDriver browser){

        // Finding the form fields
        WebElement UsernameField = browser.findElement(By.xpath("//input[@placeholder='Prisijungimo vardas']"));
        WebElement PasswordField = browser.findElement(By.xpath("//input[@placeholder='Slaptažodis']"));

        // Filling out the fields
        UsernameField.sendKeys(Username);
        PasswordField.sendKeys(Password);

        // Submiting the form
        PasswordField.submit();

        // Confirming that logging in was a success

        boolean success;
        try {
            // Using "Skaiciuoti" button to check if Login was successful
            browser.findElement(By.xpath("//input[@value='skaičiuoti']"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        System.out.println("Was the login successful: " + success);
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
