package org.SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //pageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement toasterdisappear;


    By productby = By.cssSelector(".mb-3");
    By addtocart = By.cssSelector(".card-body button:last-of-type");
    By toastermessage = By.id("toast-container");

    public List<WebElement> GetProductlist() {
        WaitforElementtoAppear(productby);
        return products;
    }

    public WebElement GetProductByName(String productname) {
        WebElement item = GetProductlist().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        return item;
    }

    public void AddProducttoCart(String productname) {
        WebElement item = GetProductByName(productname);
        item.findElement(addtocart).click();
        WaitforElementtoAppear(toastermessage);
        WaitforElementtoDisappear(toasterdisappear);
    }




}
