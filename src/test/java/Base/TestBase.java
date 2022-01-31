package Base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;

    final public static String BASE_URL = "https://www.yemeksepeti.com/istanbul";

    public static void WaitaBit(){driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);}

    @BeforeMethod
    public void setUp(){

        WaitaBit();
        driver.get(BASE_URL);

    }

    @BeforeClass
    @Parameters("browser")
    public void openBrowser(String browser) throws Exception {

        if (browser.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
            driver = new ChromeDriver();

        }

        else {
            throw new Exception("Browser is not correct");

        }

        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDownTest(){

        WaitaBit();
        driver.manage().deleteAllCookies();
        driver.quit();

    }

    @AfterSuite
    public void openReport() throws IOException {

        File htmlfile = new File(System.getProperty("user.dir") +"/test-output/YS_Extent_Report.html");
        Desktop.getDesktop().browse(htmlfile.toURI());

    }

}