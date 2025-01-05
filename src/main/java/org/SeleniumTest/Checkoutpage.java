package org.SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkoutpage extends AbstractComponent {
    WebDriver driver;

    public Checkoutpage(WebDriver driver){
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(css = "button[class*='ta-item']")
    List<WebElement> selectedcountry;

    @FindBy(css = ".action__submit")
    WebElement placeorder;

    public void ClickOnCountry(String clickoncountry, String seledtcountry){
        // driver.findElement(By.xpath("//input[@placeholder='Select Country']")).
        country.sendKeys(clickoncountry);
        selectedcountry.stream().filter(s->s.getText().equals(clickoncountry)).forEach(s->s.click());
    }

    public ConformationPage SubmtitOrder(){
        placeorder.click();
        return new ConformationPage(driver);
    }


}
