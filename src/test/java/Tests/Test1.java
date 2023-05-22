package Tests;

import Pages.DialogContent;
import Pages.HeaderNavi;

import Utilities.BaseDriver;

import com.github.javafaker.Faker;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test1 extends BaseDriver {

    Faker faker=new Faker();

    public static String Email;
    public static String pass_;

    @Test(priority = 1)
    void Registrations(){

        driver.get("https://demo.nopcommerce.com/");

        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        hn.register.click();

        dc.genderfemale.click();

        dc.firtsName.sendKeys(faker.name().firstName());

        dc.lastName.sendKeys(faker.name().lastName());

        Select dayMenu=new Select(dc.selectDay);
        dayMenu.selectByIndex(1);

        Select monthMenu=new Select(dc.selectMonth);
        monthMenu.selectByIndex(1);

        Select yearMenu=new Select(dc.selectYear);
        yearMenu.selectByIndex(1);

        Email=faker.internet().emailAddress();
        System.out.println(Email);
        dc.email.sendKeys(Email);

        dc.company.sendKeys(faker.book().title());

        dc.newsletter.click();

        pass_=faker.internet().password();
        System.out.println(pass_);
        dc.password.sendKeys(pass_);

        dc.confirmPassword.sendKeys(pass_);

        dc.register2.click();

        Assert.assertTrue(dc.success.getText().contains("Your registration completed"));

    }

    @Test(dataProvider = "UserData",dependsOnMethods = "Registrations")
    void DataProviderLogin(String email_, String password){

        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        hn.logIn.click();

        dc.email.sendKeys(email_);

        dc.password.sendKeys(password);

        dc.logIn2.click();

        if(!email_.contains(Email)||!password.contains(pass_))
        {
            Assert.assertTrue(dc.errorMsg.getText().contains("Login was unsuccessful."));
        }
        else {
            Assert.assertTrue(hn.logOut.getText().contains("Log out"));
        }

    }
    @DataProvider()
    public Object[][] UserData()
    {
        Object[][] data={
                {"asaaa@gmail.com","1234567"},
                {Email,pass_}
        };
        return data;
    }

    @Test(dependsOnMethods = "DataProviderLogin")
    void TabMenuTest(){

        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        List<String> isimler=new ArrayList<>();
        isimler.add("Computers");
        isimler.add("Electronics");
        isimler.add("Apparel");
        isimler.add("Digital downloads");
        isimler.add("Books");
        isimler.add("Jewelry");
        isimler.add("Gift Cards");

        Boolean varMi=false;
        for (int i = 0; i < isimler.size(); i++)
            for (int j = 0; j <hn.tabMenu.size() ; j++) {
                if(isimler.get(i).contains(hn.tabMenu.get(j).getText()))
                {
                    varMi=true;
                    break;
                }
            }
        Assert.assertTrue(varMi,"true");

    }

    @Test(enabled = true,dependsOnMethods = "TabMenuTest",priority = 5)
    void OrderGiftsTest(){
        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        hn.giftCards.click();

     int a=(int)(Math.random()* dc.products.size());
        dc.products.get(a).click();

        if (a==0)
        {
            String email__=faker.internet().emailAddress();
            dc.recipientName.sendKeys(faker.name().firstName());
            dc.recipientEmail.sendKeys(email__);

            dc.yourName.sendKeys(faker.name().name());
            dc.yourEmail.clear();
            dc.yourEmail.sendKeys(email__);

            dc.message.sendKeys(faker.book().title());
            dc.addToCart.click();

            wait.until(ExpectedConditions.visibilityOf(dc.success2));
            Assert.assertTrue(dc.success2.getText().contains("product has been added"));
        }
        else{
            dc.recipientName.sendKeys(faker.name().firstName());
            dc.yourName.sendKeys(faker.name().name());
            dc.message.sendKeys(faker.book().title());
            dc.addToCart.click();

            wait.until(ExpectedConditions.visibilityOf(dc.success2));
            Assert.assertTrue(dc.success2.getText().contains("product has been added"));
        }
    }

    @Test(dependsOnMethods = "OrderGiftsTest",priority = 7)
    void OrderComputerTest()  {

        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        Actions actions=new Actions(driver);

        actions.moveToElement(hn.computer).build().perform();

        actions.moveToElement(hn.desktops).click().build().perform();

        String suctProduct="Build your own computer";

        for (WebElement baslik:dc.titleProducts)
            if(baslik.getText().contains(suctProduct)) {
                baslik.click(); break;
            }
        //Random bir RAM secimi Select Menu
        Select ramMenu=new Select(dc.ram);
        int ramSec=(int) (Math.random()*(ramMenu.getOptions().size()-1)+1);
        ramMenu.selectByIndex(ramSec);

        //Random bir HDD secimi    radio
        int hddSec=(int) (Math.random()*dc.hdd.size());
        dc.hdd.get(hddSec).click();

        dc.addToCart.click();

        wait.until(ExpectedConditions.visibilityOf(dc.success2));
        Assert.assertTrue(dc.success2.getText().contains("product has been added"));

    }

    @Test(dependsOnMethods = "OrderComputerTest",priority = 8)
    @Parameters("word")
    void SearchTest(String search_word)
    {
        HeaderNavi hn=new HeaderNavi();
        DialogContent dc=new DialogContent();

        hn.searchTerms.sendKeys(search_word);

        wait.until(ExpectedConditions.elementToBeClickable(hn.searchButton));
        hn.searchButton.click();

        Assert.assertTrue(dc.title.getText().contains(search_word));

    }

}