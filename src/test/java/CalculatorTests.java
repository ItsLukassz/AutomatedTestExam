import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTests {

    // Values that can be changed
    String Username = "Testas";
    String Password = "Testas";


    static ChromeDriver browser;

    @BeforeMethod(onlyForGroups = "DoesntNeedLogin")
    public void Start() {
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");
        browser = new ChromeDriver();
        browser.get("http://localhost:8080");
    }

    @BeforeMethod(onlyForGroups = "NeedLogin")
    public void StartWithLogin(){
        System.setProperty("webdriver.chrome.driver", "src/main/ChromeWebDriver/chromedriver102.exe");
        browser = new ChromeDriver();
        browser.get("http://localhost:8080");

        UserLogin.Login(Username, Password, browser);
    }

    @AfterMethod
    public void Stop(){

        browser.close();

    }

    @Test(groups = "DoesntNeedLogin")
    public void RegisterPositive(){

        Faker faker = new Faker();
        Assert.assertTrue(UserRegistration.Register(faker.name().username(), faker.internet().password(), browser));

    }

    @Test(groups = "DoesntNeedLogin")
    public void RegisterNegative(){

        Faker faker = new Faker();
        Assert.assertFalse(UserRegistration.Register(faker.name().username(), "", browser));

    }

    // Login
    @Test(groups = "DoesntNeedLogin")
    public void LoginPositive(){

        Assert.assertTrue(UserLogin.Login(Username, Password, browser));

    }
    @Test(groups = "DoesntNeedLogin")
    public void LoginNegative(){

        Assert.assertFalse(UserLogin.Login(Username, "", browser));

    }

    @Test(groups = "NeedLogin")
    public void CreateEntryPositive(){

        Assert.assertTrue(EntryCreation.CreateNewEntry("12", "22", browser));

    }

    @Test(groups = "NeedLogin")
    public void CreateEntryNegative(){

        Assert.assertFalse(EntryCreation.CreateNewEntry("-5", "22", browser));

    }

    @Test(groups = "NeedLogin")
    public void SearchResultPositive(){

        Assert.assertTrue(EntrySearch.SearchEntry(false,browser));

    }

    @Test(groups = "NeedLogin")
    public void SearchResultNegative(){

        Assert.assertFalse(EntrySearch.SearchEntry(true,browser));

    }

    @Test(groups = "NeedLogin")
    public void EditEntryPositive(){

        Assert.assertTrue(EntryEdit.EditEntry(false,"12",browser));

    }

    @Test(groups = "NeedLogin")
    public void EditEntryNegative(){

        Assert.assertFalse(EntryEdit.EditEntry(true,"",browser));

    }

    @Test(groups = "NeedLogin")
    public void DeleteEntryPositive(){

        Assert.assertTrue(EntryDeletion.DeleteEntry(false,browser));

    }

    @Test(groups = "NeedLogin")
    public void DeleteEntryNegative(){

        Assert.assertFalse(EntryDeletion.DeleteEntry(true,browser));

    }

}
