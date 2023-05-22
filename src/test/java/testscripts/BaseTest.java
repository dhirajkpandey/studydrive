package testscripts;
import generic.Screenshot;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest extentTest;
    public static String TEST_URL = "https://www.studydrive.net/";

    @BeforeClass
    public void setUp()
    {
        // launch browser to perform tests
        System.setProperty("WebDriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

       /* System.setProperty("WebDriver.gecko.driver","src/test/resources/firefoxdriver_win32/geckodriver.exe" );

        driver= new FirefoxDriver();
        driver.manage().window().maximize();
       */
        driver.get(TEST_URL);

    }
    @AfterClass
    public void tearDown() throws InterruptedException
    {
        //driver.close();
    }
    @AfterMethod
    public void fnAfterMethodCondition(ITestResult t)
    {
        String testname = t.getMethod().getMethodName();
        try
        {
            if(t.isSuccess())
            {
                extentTest.log(Status.PASS, MarkupHelper.createLabel(testname+"Test Case PASS", ExtentColor.GREEN));

            }
            else
            {
                Screenshot s = new Screenshot();
                s.fnGetScreenshot(driver,testname);

                //extent report functionality when the test case fails
                extentTest.log(Status.FAIL, MarkupHelper.createLabel(testname+"Test Case FAILED due to following reason", ExtentColor.RED));
                extentTest.fail(t.getThrowable());   // it will print the stack trace due to which the test case failed
            }


        }
        catch(Exception e)
        {

        }
    }
    @BeforeSuite
    public void extentReportInvoke()
    {

        // directory where output is to be printed
        ExtentSparkReporter spark = new ExtentSparkReporter("report/report.html/");
        reports = new ExtentReports();
        reports.attachReporter(spark);

        reports.setSystemInfo("Host Name", "localhost");
        reports.setSystemInfo("Env", "Staging");
        reports.setSystemInfo("User", "Dhiraj Pandey");

    }

    @AfterSuite
    public void removeOldReport(){
        reports.flush();
    }



}
