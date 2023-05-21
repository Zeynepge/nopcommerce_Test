package Pages;

import Utilities.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderNavi {

    public HeaderNavi(){
        PageFactory.initElements(BaseDriver.driver,this);
    }

    @FindBy(css = "[class='ico-register']")
    public WebElement register;

    @FindBy(linkText = "Log in")
    public WebElement logIn;

    @FindBy(xpath = "//*[text()='Log out']")
    public WebElement logOut;


}
