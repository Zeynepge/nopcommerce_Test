package Pages;

import Utilities.BaseDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DialogContent {

    public DialogContent()
    {
        PageFactory.initElements(BaseDriver.driver,this);
    }

    @FindBy(id="gender-female")
    public WebElement genderfemale;

    @FindBy(id="FirstName")
    public WebElement firtsName;

    @FindBy(id="LastName")
    public WebElement lastName;

    @FindBy(css="[name='DateOfBirthDay']")
    public WebElement selectDay;

    @FindBy(css="[name='DateOfBirthMonth']")
    public WebElement selectMonth;

    @FindBy(css="[name='DateOfBirthYear']")
    public WebElement selectYear;

    @FindBy(id="Email")
    public WebElement email;

    @FindBy(id="Company")
    public WebElement company;

    @FindBy(css="[id='Newsletter']")
    public WebElement newsletter;

    @FindBy(id="Password")
    public WebElement password;

    @FindBy(id="ConfirmPassword")
    public WebElement confirmPassword;

    @FindBy(id="register-button")
    public WebElement register2;

    @FindBy(css="[class='result']")
    public WebElement success;

    @FindBy(css = "[class='button-1 login-button']")
    public WebElement logIn2;

    @FindBy(css = "[class='message-error validation-summary-errors']")
    public WebElement errorMsg;

    @FindBy(xpath = "//div[@class='item-grid']/*")
    public List<WebElement> products;

    @FindBy(css = "[class='recipient-name']")
    public WebElement recipientName;

    @FindBy(css = "[class='recipient-email']")
    public WebElement recipientEmail;

    @FindBy(css="[class='sender-name']")
    public WebElement yourName;

    @FindBy(css="[class='sender-email']")
    public WebElement yourEmail;

    @FindBy(css="[class='message']")
    public WebElement message;

    @FindBy(css="[class='button-1 add-to-cart-button']")
    public WebElement addToCart;

    @FindBy(css = "[class='content']")
    public WebElement success2;

    @FindBy(xpath = "//h2[@class='product-title']//a")
    public List<WebElement> titleProducts;

    @FindBy(id = "product_attribute_2")
    public WebElement ram;

    @FindBy(xpath = "//input[@name='product_attribute_3']")
    public List<WebElement> hdd;

    @FindBy(xpath = "//h2[@class='product-title']")
    public WebElement title;



}
