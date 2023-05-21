package Pages;

import Utilities.BaseDriver;
import io.cucumber.java.en_lol.WEN;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.interfaces.RSAPublicKey;
import java.util.List;

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

    @FindBy(css = "[class='top-menu notmobile']>li")
    public List<WebElement> tabMenu;

    @FindBy(xpath = "(//ul[@class='top-menu notmobile']/*)[7]")
    public WebElement giftCards;

    @FindBy(xpath = "(//ul[@class='top-menu notmobile']/*)[1]")
    public WebElement computer;

    @FindBy(xpath = "(//a[text()='Desktops '])[1]")
    public WebElement desktops;

    @FindBy(id = "small-searchterms")
    public WebElement searchTerms;

    @FindBy(css = "[class='button-1 search-box-button']")
    public WebElement searchButton;


}
