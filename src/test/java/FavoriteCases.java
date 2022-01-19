import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.ITestContext;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FavoriteCases {

    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
    Date date = new Date();
    String date1= dateFormat.format(date);

    static ExtentReports extent;
    ExtentSparkReporter sparkReporter;

    public static WebDriver driver = null;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ".\\Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ".\\Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            throw new Exception("Browser is not correct");
        }

        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("LoginCasesExtentReport"+date1+".html");
        extent.attachReporter(sparkReporter);


        driver.manage().window().maximize();

    }

    @Test(testName = "Prepare for Favorite", priority=0)
    public static void Login() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        HomePage homePage = new HomePage(driver);
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        homePage.enterUserName("ysotmsyn@gmail.com");
        homePage.enterPassword("123Ys456.");
        homePage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.checkFavoriteTabDisplayed();
        loggedInPage.clickPopupOrderFood();
        loggedInPage.closeIfJokerDisplayed();

    }

    @Test(testName = "Add Favorite", priority = 1)
    public static void AddFavorite() throws InterruptedException {
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        Thread.sleep(3000);
        loggedInPage.clickAddress();
        loggedInPage.typeFood("burger");
        loggedInPage.clickSearchBtn();
        loggedInPage.closeIfJokerDisplayed();
        loggedInPage.clickRestaurant();
        loggedInPage.clickAddFavStar();
    }

    @Test(testName = "Sub Favorite", priority = 2)
    public static void SubFavorite() throws InterruptedException{
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        loggedInPage.clickAddress();
        loggedInPage.typeFood("burger");
        loggedInPage.clickSearchBtn();
        Thread.sleep(3000);
        loggedInPage.clickRestaurant();
        loggedInPage.clickSubFavStar();}


    @Test(testName = "Delete All if Favorites Exist", priority = 3)
            public static void DeleteFavorites() throws InterruptedException {
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            loggedInPage.clickProfileMenu();
            loggedInPage.clickMyFavorites();
        if(driver.findElements(By.xpath("//*[@id=\"favorites\"]/div/p")).size() != 0){
            System.out.println("No Favorites to Delete");}
        else {
            List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
            for (WebElement el:elements)
            {
                el.click();
            }
            driver.findElement(By.xpath("//*[@id=\"favorites\"]/form/div[2]/button")).click();
            loggedInPage.clickProfileMenu();
            loggedInPage.clickMyFavorites();
            driver.findElement(By.xpath("//*[@id=\"favorites\"]/div/div/i")).isDisplayed();
            }
    }

    @AfterSuite
    public static void cleanUp(){
        extent.flush();
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
