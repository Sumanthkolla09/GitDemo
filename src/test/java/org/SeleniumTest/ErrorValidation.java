package org.SeleniumTest;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {
    @Test(groups = {"ErrorHanding"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {
        String email = "sumanth@test.com";
        String pwd = "Demo@123";
        landingpage.loginnApplication(email,pwd);
        Assert.assertEquals("Incorrect email  password.", landingpage.GetErrorMessage());


    }

    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {
        String email = "sumanth@testing.com";
        String pwd = "Demo@123";
        String productname = "IPHONE 13 PRO";
        ProductCatalog productCatalog = landingpage.loginnApplication(email,pwd);
        List<WebElement> products = productCatalog.GetProductlist();
        productCatalog.AddProducttoCart(productname);
        Thread.sleep(1000);
        CartPage cartPage = productCatalog.ClickOncart();
        Boolean match = cartPage.CheckCcartProductTitles("IPHONE 133 PRO");
        Assert.assertFalse(match);


    }
}
