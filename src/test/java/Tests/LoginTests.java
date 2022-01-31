package Tests;

import Base.TestBase;
import Pages.YSLoggedInPageObjects;
import Pages.YSMainPageObjects;
import org.testng.annotations.*;

public class LoginTests extends TestBase {

    @Test(testName = "Login with Empty Username")
    public void LoginWithEmptyUserName(){

        YSMainPageObjects.TryLogin("","password");
        YSMainPageObjects.verifyNullInputMssg("Lütfen kullanıcı adınızı/e-postanızı giriniz.", null);

    }

    @Test(testName = "Login with Empty Password", priority=1)
    public void LoginWithEmptyPassword(){


        YSMainPageObjects.TryLogin("username","");
        YSMainPageObjects.verifyNullInputMssg(null, "Lütfen şifrenizi giriniz.");

    }

    @Test(testName = "Login with Empty Username and Password", priority=2)
    public void LoginWithEmptyUserNameAndPassword(){


        YSMainPageObjects.TryLogin("","");
        YSMainPageObjects.verifyNullInputMssg("Lütfen kullanıcı adınızı/e-postanızı giriniz.", "Lütfen şifrenizi giriniz.");

    }

    @Test(testName = "Login with Invalid Username", priority = 3)
    public void LoginWithInvalidUserName(){


        YSMainPageObjects.TryLogin("invalidusername","123Ys456.");
        YSMainPageObjects.verifyInvalidInputMssg("Hatalı giriş. Lütfen kullanıcı adı ve şifrenizi kontrol edip tekrar deneyiniz.");
        YSMainPageObjects.ClickInvalidCredentialsPopupButton();

    }

    @Test(testName = "Login with Invalid Password", priority = 4)
    public void LoginWithInvalidPassword(){


        YSMainPageObjects.TryLogin("ysotmsyn@gmail.com","invalidpassword");
        YSMainPageObjects.verifyInvalidInputMssg("Hatalı giriş. Lütfen kullanıcı adı ve şifrenizi kontrol edip tekrar deneyiniz.");
        YSMainPageObjects.ClickInvalidCredentialsPopupButton();

    }

    @Test(testName = "Login with Invalid Username and Password", priority = 5)
    public void LoginWithInvalidUsernameAndPassword(){


        YSMainPageObjects.TryLogin("invalidusername","invalidpassword");
        YSMainPageObjects.verifyInvalidInputMssg("Hatalı giriş. Lütfen kullanıcı adı ve şifrenizi kontrol edip tekrar deneyiniz.");
        YSMainPageObjects.ClickInvalidCredentialsPopupButton();

    }

    @Test(testName = "Fail Login Case for ScreenShot", priority = 6)
    public static void LoginForFail() throws InterruptedException {

        YSMainPageObjects.TryLogin("InvalidUserName","InvalidPassword");
        YSLoggedInPageObjects.checkSuccessfulLogin();

    }

    @Test(testName = "Login to YemekSepeti", priority=7)
    public static void SuccessfulLogin() throws InterruptedException {

        YSMainPageObjects.TryLogin("ysotmsyn@gmail.com","123Ys456.");
        YSLoggedInPageObjects.checkSuccessfulLogin();

    }

}