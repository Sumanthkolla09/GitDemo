package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports GetReportObject(){
        // ExtentReport & ExtentsparkReporter

        //Created a path and add the path to the extentsparkreporter
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Web Automation Reports");
        sparkReporter.config().setDocumentTitle("Test Results");

        // Now need to create a object for ExtentReport
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester", "Sumanth kolla");
        extentReports.createTest(path);
        return extentReports;
    }

}
