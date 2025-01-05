package org.SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landingpage extends AbstractComponent {

    WebDriver driver;

    public Landingpage(WebDriver driver) {
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //pageFactory
    @FindBy(id = "userEmail")
    WebElement useremail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errormessage;


    public ProductCatalog loginnApplication(String Email, String Pwd) {
        useremail.sendKeys(Email);
        password.sendKeys(Pwd);
        submit.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public void GotoUrl() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String GetErrorMessage() {
        WaitforElementtoAppear(errormessage);
        return errormessage.getText();
    }

}
