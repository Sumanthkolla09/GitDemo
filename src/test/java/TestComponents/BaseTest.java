package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.SeleniumTest.Landingpage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Landingpage landingpage;

    public WebDriver InitializeBrower() throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//Resources//GlobalData.properties");
        properties.load(file);
        String browsername = System.getProperty("browser")!=null ?  System.getProperty("browser") : properties.getProperty("browser");
        //String browsername = properties.getProperty("browser");

        if (browsername.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            WebDriverManager.chromedriver().setup();
            if(browsername.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browsername.equalsIgnoreCase("firefox")) {
            //Firefox
        } else if (browsername.equalsIgnoreCase("edge")) {
            //Firefox
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> GetJsonDataToHasMap(String filepath) throws IOException {

        //Reading the Json to String
        String jsonContent = FileUtils.readFileToString(new File(filepath));

        //Convert String to hashMap - jackson databind used to converter
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
        //Data will store the List of data in Hashmap {map, map}
    }


    public String GetScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public Landingpage LaunchApplication() throws IOException {
        driver = InitializeBrower();
        landingpage = new Landingpage(driver);
        landingpage.GotoUrl();
        return landingpage;
    }

    @AfterMethod(alwaysRun = true)
    public void Closebrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
