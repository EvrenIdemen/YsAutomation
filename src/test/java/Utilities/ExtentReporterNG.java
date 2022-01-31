package Utilities;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReporterNG {

    static ExtentReports extent;

    public static ExtentReports extentReportGenerator() {

        extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/YS_Extent_Report.html", true);
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
        return extent;

    }

}