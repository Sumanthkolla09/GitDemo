package StepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.SeleniumTest.CartPage;
import org.SeleniumTest.Checkoutpage;
import org.SeleniumTest.Landingpage;
import org.SeleniumTest.ProductCatalog;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {

    public Landingpage landingpage;
    public ProductCatalog productCatalog;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingpage = LaunchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        landingpage = new Landingpage(driver);
        landingpage.loginnApplication(username, password);
    }

    @When("^I add the product (.+) to Cart$")
    public void  I_add_the_product_to_Cart(String productname){
        List<WebElement> products = productCatalog.GetProductlist();
        productCatalog.AddProducttoCart(productname);
    }
    @When("^And Checkout (.+) and submit the order&")
    public void And_Checkout_and_submit_the_order(String productname) throws InterruptedException {
        Thread.sleep(1000);
        CartPage cartPage = productCatalog.ClickOncart();
        Boolean match = cartPage.CheckCcartProductTitles(productname);
        Assert.assertTrue(match);
        Checkoutpage checkoutpage = cartPage.Checkout();
        checkoutpage.ClickOnCountry("India", "India");
    }

    @Then("{string} country should be selected")
    public void Country_india_should_be_selected(String string){
        Assert.assertTrue("India".equalsIgnoreCase(string));
        driver.close();
    }




}
