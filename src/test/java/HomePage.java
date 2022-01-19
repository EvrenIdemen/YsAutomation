import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends PageObject {

    @FindBy(id = "UserName")
    private WebElement UserName;

    public void Xdeneme(String warningMessageText) {

        WebElement warningTextElement = driver.
                findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[1]/div[1]/div[1]/div/small"));
        String warningText = warningTextElement.getText();
        Assert.assertEquals(warningText,warningMessageText);
           }

    public void evrenDeneme(){
        this.favoriteTab.isDisplayed();
    }

    @FindBy(id = "password")
    private WebElement Password;

    @FindBy(id = "ys-fastlogin-button")
    private WebElement submit_button;

    @FindBy(xpath = "//small[contains(text(),'Lütfen kullanıcı adınızı/e-postanızı giriniz.')]")
    private WebElement emptyUserName;

    @FindBy(xpath = "//small[contains(text(),'Lütfen şifrenizi giriniz.')]")
    private WebElement emptyPassword;

    @FindBy(xpath = "//button[contains(text(),'TAMAM')]")
    private WebElement popUpOkButtonElement;

    @FindBy(xpath = "//a[contains(text(),' Favorilerim')]")
    private WebElement favoriteTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName){
        this.UserName.clear();
        this.UserName.sendKeys(userName);
    }

    public void enterPassword(String password){
        this.Password.clear();
        this.Password.sendKeys(password);
    }

    public void clickLoginButton(){
        this.submit_button.click();
    }

    public void popUpOkButton(){
        this.popUpOkButtonElement.click();
    }

    public void checkFavoriteTabDisplayed(){
        this.favoriteTab.isDisplayed();
    }

    public void verifyNullUsernameMessage(){
        this.emptyUserName.isDisplayed();
    }

    public void verifyNullPasswordMessage(){
        this.emptyPassword.isDisplayed();
    }

    public void verifyInvalidCredentialsMessage(){
        this.popUpOkButtonElement.isDisplayed();
    }

}