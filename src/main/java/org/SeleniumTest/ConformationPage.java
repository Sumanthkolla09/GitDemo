package org.SeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConformationPage extends  AbstractComponent {
    WebDriver driver;

    public ConformationPage(WebDriver driver){
        super(driver);
        //Initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement conformMessage;

    public String GetConfimationMessage(){
        return conformMessage.getText();
    }

}
