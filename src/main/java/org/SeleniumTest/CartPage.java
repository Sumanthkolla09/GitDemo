package org.SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent{
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkout;

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> cartProduct;


    public boolean CheckCcartProductTitles(String productname){
        boolean match = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
        return match;

    }

    public Checkoutpage Checkout(){
        checkout.click();
        return new Checkoutpage(driver);

    }
}
