package Pages;

import Base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;

public class YSMainPageObjects extends TestBase {

    static By username = By.id("UserName");
    static By password = By.id("password");
    static By submit_button = By.id("ys-fastlogin-button");
    static By emptyusernamemssg = By.xpath("//*[@id=\"login-form\"]/div[1]/div/small");
    static By emptypasswordmssg = By.xpath("//*[@id=\"login-form\"]/div[2]/div/small");
    static By invalidcredentialmssg = By.xpath("/html/body/div[9]/div[2]/div[1]/span/strong");
    static By invalidcredentialPopupOkButton = By.xpath("/html/body/div[9]/div[2]/div[2]/button");

    public static void TryLogin(String textUsername, String textPassword){

        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(textUsername);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(textPassword);
        driver.findElement(submit_button).click();

    }

    public static void verifyNullInputMssg(String expectedNullUserMssgText, String expectedNullPassMssgText){

        if (expectedNullUserMssgText != null && expectedNullPassMssgText == null){
            String nullUserMssgDisplayedText = driver.findElement(emptyusernamemssg).getText();
            Assert.assertEquals(nullUserMssgDisplayedText, expectedNullUserMssgText);
        }
        else if (expectedNullPassMssgText != null && expectedNullUserMssgText == null){
            String nullPassMssgDisplayedText = driver.findElement(emptypasswordmssg).getText();
            Assert.assertEquals(nullPassMssgDisplayedText, expectedNullPassMssgText);
        }
        else {
            String nullUserMssgDisplayedText = driver.findElement(emptyusernamemssg).getText();
            String nullPassMssgDisplayedText = driver.findElement(emptypasswordmssg).getText();
            Assert.assertEquals(nullUserMssgDisplayedText, expectedNullUserMssgText);
            Assert.assertEquals(nullPassMssgDisplayedText, expectedNullPassMssgText);

        }

    }

    public static void verifyInvalidInputMssg(String expectedInvalidInputMssgText){

        String invalidCredentialMssgDisplayedText = driver.findElement(invalidcredentialmssg).getText();
        Assert.assertEquals(invalidCredentialMssgDisplayedText, expectedInvalidInputMssgText);

    }

    public static void ClickInvalidCredentialsPopupButton(){

        driver.findElement(invalidcredentialPopupOkButton).click();

    }

}