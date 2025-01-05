package org.SeleniumTest;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EntireE2E extends BaseTest {
   String productname = "IPHONE 13 PRO";
    @Test(dataProvider = "GetDataProvider", groups = {"PurchaseOrder"})
    public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
        ProductCatalog productCatalog = landingpage.loginnApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalog.GetProductlist();
        productCatalog.AddProducttoCart(input.get("product"));
        Thread.sleep(1000);
        CartPage cartPage = productCatalog.ClickOncart();
        Boolean match = cartPage.CheckCcartProductTitles(input.get("product"));
        Assert.assertTrue(match);
        Checkoutpage checkoutpage = cartPage.Checkout();
        checkoutpage.ClickOnCountry("India", "India");

           /* ConformationPage conformationPage = checkoutpage.SubmtitOrder();
            String conformmessage = conformationPage.GetConfimationMessage();
            Assert.assertTrue(conformmessage.equalsIgnoreCase("Thankyou for the order."));
            */
    }

    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistory(){
        String email = "sumanth@testing.com";
        String pwd = "Demo@123";
        ProductCatalog productCatalog = landingpage.loginnApplication(email, pwd);
        OrderPage orderPage = productCatalog.GoToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productname));
    }

    @DataProvider
    public Object[][] GetDataProvider() throws IOException {

        List<HashMap<String,String>> data = GetJsonDataToHasMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};

         /*HashMap<String,String> map = new HashMap<String,String>();
        map.put("email","sumanth@testing.com");
        map.put("password","Demo@123");
        map.put("product","IPHONE 13 PRO");

        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email","test1@testing.com");
        map1.put("password","Demo@123");
        map1.put("product","ADIDAS ORIGINAL");*/

    }
}



/*
Why Frameworks? Its advantages
•	Create maven structure framework will all necessary Automation denpendencies
•	Select sample Ecommerce application to Automate the End-to-End flow
•	Implement Page Object modal mechanism to drive the locators for there respective classes
•	Drive object creation in Page object classes encapsulating it from tests
•	Create Base Test which sets browser configurations details and Global properties
•	Decide the Test strategy how tests should be clubbed & distributed with appropriate annotations
•	Create TestNG runner file to trigger the test with one single point of execution control
•	Introduce Grouping in TestNG.xml to categories tests with different tags of executions
•	Implement Data driven testing & Parameterization using TestNG data Provider Hashmaps & Json file readers – using Jackson dependency
•	Implement TestNG Listeners to capture screenshot on automatic test failure & logging
•	Create Extent Report Wrapper to generate excellent HTML reports for the applications
•	Make framework necessary changes to support parallel execution with thread safe mechanism – using this it will get the method thread id and store it
•	Implement TestNG Retry mechanism to rerun the failed flaky tests in the application –using Retry Analyzer
•	Run the Framework tests with maven commands with TestNG Maven Integrated plugins
•	Implement Maven run times variables to replaces Global parameters od test data at run time
•	Integrate the Framework with Jenkins with Parameterized build Pipeline Jobs & Schedule the Jobs on specific time frames
*/

