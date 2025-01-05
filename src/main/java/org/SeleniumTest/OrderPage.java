package org.SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = " //tr/td[2]")
    List<WebElement> productorder;

    public boolean VerifyOrderDisplay(String productname) {
        boolean match = productorder.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productname));
        return match;
    }

}
