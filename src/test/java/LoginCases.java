import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.*;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class LoginCases {
    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
    Date date = new Date();
    String date1= dateFormat.format(date);

    static ExtentReports extent;
    ExtentSparkReporter sparkReporter;


    public static WebDriver driver = null;


    @BeforeClass
    @Parameters("browser")
    public <ExtentHtmlReporter> void setup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            throw new Exception("Browser is not correct");
        }

        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("LoginCasesExtentReport"+date1+".html");
        extent.attachReporter(sparkReporter);

        driver.manage().window().maximize();

    }

    @Test(testName = "Login with Empty User Name",priority=1)
    public void LoginWithEmptyUserName(){
        ExtentTest test = extent.createTest("Login with Empty User Name");
        try {
            driver.get(Utils.BASE_URL);
            HomePage homePage = new HomePage(driver);
            homePage.enterUserName("");
            homePage.enterPassword("123Ys456.");
            homePage.clickLoginButton();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            homePage.Xdeneme("Lütfen kullanıcı adınızı/e-postanızı giriniz.");
            homePage.verifyNullUsernameMessage();
            test.pass("Test Case Passed");
        }
        catch (Exception e){test.fail("Test Case Failed");}
    }

    @Test(testName = "Login with Empty Password",priority=2)
    public static void LoginWithEmptyPassword(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("ysotmsyn@gmail.com");
        homePage.enterPassword("");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.verifyNullPasswordMessage();
    }

    @Test(testName = "Login with Empty User Name and Password",priority=3)
    public static void LoginWithEmptyUserNameAndPassword(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("");
        homePage.enterPassword("");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //düzelmesi lazım
        homePage.Xdeneme("Lütfen kullanıcı adınızı/e-postanızı giriniz.");
        homePage.verifyNullPasswordMessage();
    }

    @Test(testName = "Login with Invalid User Name",priority=4)
    public static void LoginWithInvalidUserName(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("username");
        homePage.enterPassword("123Ys456.");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.verifyInvalidCredentialsMessage();
        homePage.popUpOkButton();
    }

    @Test(testName = "Login with Invalid Password",priority=5)
    public static void LoginWithInvalidPassword(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("ysotmsyn@gmail.com");
        homePage.enterPassword("password");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.verifyInvalidCredentialsMessage();
        homePage.popUpOkButton();
    }

    @Test(testName = "Login with Invalid User Name and Password",priority=6)
    public static void LoginWithInvalidUserNameAndPassword(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("username");
        homePage.enterPassword("password");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.verifyInvalidCredentialsMessage();
        homePage.popUpOkButton();
    }

    @Test(testName = "Login to YemekSepeti",priority=7)
    public static void Login(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("ysotmsyn@gmail.com");
        homePage.enterPassword("123Ys456.");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.checkFavoriteTabDisplayed();


    }

    @Test(testName = "Fail Login Case for ScreenShot", priority = 0)
    public static void LoginForFail(){
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterUserName("InvalidUserName");
        homePage.enterPassword("InvalidPassword");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("FakeObject")).isDisplayed();
    }

    @AfterMethod
    public static void cleanUp(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus())
        {
            Utils.captureScreenShot(driver, result.getName());
        }
        extent.flush();
    }

    @AfterSuite
    public static void TearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
            }

}