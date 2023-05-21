package Tests;

import Pages.DialogContent;

import Pages.HeaderNavi;
import Utilities.BaseDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseDriver {

    Faker faker=new Faker();

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

        dc.email.sendKeys(faker.internet().emailAddress());

        dc.company.sendKeys(faker.book().title());

        dc.newsletter.click();

        String pass_=faker.internet().password();
        dc.password.sendKeys(pass_);

        dc.confirmPassword.sendKeys(pass_);

        dc.register2.click();

        Assert.assertTrue(dc.success.getText().contains("Your registration completed"));

    }





}