package Tests;

import Pages.DialogContent;
import Pages.HeaderNavi;

import Utilities.BaseDriver;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @Test
    void LoginTest(){




    }





}