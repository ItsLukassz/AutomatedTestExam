import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserRegistration {


    static String Link = "http://localhost:8080";

    static ChromeDriver browser;

    public static void main(String[] args) {

        // using chrome 102 web driver
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");
    }

    public static Boolean Register(String Username, String Password, WebDriver browser){


        // Going to register page
        WebElement RegisterButton = browser.findElement(By.xpath("//a[contains(text(),'Sukurti naują paskyrą')]"));
        RegisterButton.click();

        //Finding all the fields

        WebElement UsernameField = browser.findElement(By.xpath("//input[@id='username']"));
        WebElement PasswordField = browser.findElement(By.xpath("//input[@id='password']"));
        WebElement ConfirmPasswordfield = browser.findElement(By.xpath("//input[@id='passwordConfirm']"));

        // Filling out the fields
        UsernameField.sendKeys(Username);
        PasswordField.sendKeys(Password);
        ConfirmPasswordfield.sendKeys(Password);

        // Submitting the form
        ConfirmPasswordfield.submit();


        // Confirming that the registration was a success

        boolean success;
        try {
            // Using "Skaiciuoti" button to check if registration was successful
            browser.findElement(By.xpath("//input[@value='skaičiuoti']"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        System.out.println("Was the registration successful: " + success);
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
