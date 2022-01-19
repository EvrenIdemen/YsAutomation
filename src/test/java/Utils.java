import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;


public class Utils {
    final static String BASE_URL = "https://www.yemeksepeti.com/istanbul";
    final static String CHROME_DRIVER_LOCATION = "chromedriver";
    final static String FIREFOX_DRIVER_LOCATION = "geckodriver";


    public static void captureScreenShot(WebDriver driver, String screenshotName) throws IOException
    {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
            Date date = new Date();
            String date1= dateFormat.format(date);
            FileUtils.copyFile(source, new File("./ScreenShots/"+screenshotName+date1+".png"));
            Reporter.log("<br><img src='"+screenshotName+date1+"' height='400' width='400'/><br>");
        }
        catch (Exception e)
        {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }

    }

}