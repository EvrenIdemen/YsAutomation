import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInPage extends PageObject {
    public LoggedInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@tabindex='1']")
    private WebElement searchFoodElement;

    public void typeFood(String food) {
        this.searchFoodElement.sendKeys(food);
    }


    @FindBy(xpath = "//span[@class='ys-icons ys-icons-search icon']")
    private WebElement clickSearchElement;

    public void clickSearchBtn() {
        this.clickSearchElement.click();
    }

    @FindBy(xpath = "/html/body/div[13]/div/div/div/div[2]/div/a[2]/div[3]/button/strong")
    private WebElement popUpOrderFood;

    public void clickPopupOrderFood() {this.popUpOrderFood.click();}

        @FindBy(xpath = "//span[@title='Üsküdar (Bahçelievler Mah.)']")
        private WebElement addressElement;
        public void clickAddress(){this.addressElement.click();}

    @FindBy(id="cboxClose")
    private WebElement closeJokerElement;
        public void clickCloseJoker(){this.closeJokerElement.click();}

    @FindBy(xpath = "/html/body/div[1]/div/span[2]")
        private WebElement restDetailPageRestTitle;
        public String getRestName(){
            return restDetailPageRestTitle.getText();
        }

    @FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/a")
    private WebElement listedFirstRestaurantElement;

        public void clickRestaurant(){
            String favRestName = listedFirstRestaurantElement.getText();
            this.listedFirstRestaurantElement.click();}

    @FindBy(id ="ysUserName")
            private WebElement profileMenuElement;
        public void clickProfileMenu(){this.profileMenuElement.click();}

    public void closeIfJokerDisplayed() throws InterruptedException {
        Thread.sleep(3000);

        if (driver.findElements(By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/div[1]/div/div[1]/i")).size() != 0){
            clickCloseJoker();
        }else {
            System.out.println("No Joker");
        }
    }

    @FindBy(xpath = "//*[@id=\"basket-container\"]/div[1]/div[2]/div[1]/ul/li[4]/a")
    private WebElement myFavoritesElement;
        public void clickMyFavorites(){this.myFavoritesElement.click();}

    @FindBy(xpath = "//*[@id=\"favorites\"]/form/div[1]/div/a/span/b")
    private WebElement favList1;
        public void checkAddedFav(){
            String testfav = driver.findElement(By.xpath("//*[@id=\"favorites\"]/form/div[1]/div/a/span/b")).getText();
        }

    public String getAddedFavRest(){
        String restText =  driver.findElement(
                By.xpath("//*[@id=\"favorites\"]/form/div[1]/div/a/span/b")).getText();
        return restText;
    }

    public String getSubtractedFavRest(){
        String restText =  driver.findElement(
                By.xpath("//*[@id=\"favorites\"]/form/div[1]/div/a/span/b")).getText();
        return restText;
    }

    @FindBy(xpath = "//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[2]/b")
    private WebElement addFavStar;
        public void clickAddFavStar() throws InterruptedException {
            String secondRest = "/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/a";
            String addFavText = "//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[1]/b";

            if (addFavStar.getText().contains("Çıkar")) {
                driver.navigate().back();
                String actualText2 = driver.findElement(By.xpath(secondRest)).getText();
                System.out.println("asd" + actualText2);
                driver.findElement(By.xpath(secondRest)).click();
                driver.findElement(By.xpath(addFavText)).click();
                Thread.sleep(3000);
                clickProfileMenu();
                clickMyFavorites();

                if (getAddedFavRest().equals(actualText2)){
                    System.out.println("ok");
                }else {
                    System.out.println("get" +getAddedFavRest());
                    System.out.println("nok" + getAddedFavRest() + " " + actualText2);
                }
            } else {

                String actualText = getRestName();
                driver.findElement(By.xpath(addFavText)).click();
                Thread.sleep(3000);
                clickProfileMenu();
                clickMyFavorites();

                if (getAddedFavRest().equals(actualText)){
                    System.out.println("ok : " + "getAdded : " + getAddedFavRest() + " " + "actual " + actualText);

                }else {
                    System.out.println("nok : " + "getAdded : " + getAddedFavRest() + " " + "actual " + actualText);
                }
            }
        }


    @FindBy(xpath = "//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[2]/b")
    private WebElement subFavStar;
    public void clickSubFavStar() throws InterruptedException {
        String secondRest = "/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/a";
        String subFavText = "//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[2]/b";

        if (subFavStar.getText().contains("Ekle")) {
            driver.navigate().back();
            String actualText2 = driver.findElement(By.xpath(secondRest)).getText();
            System.out.println("asd" + actualText2);
            driver.findElement(By.xpath(secondRest)).click();
            driver.findElement(By.xpath(subFavText)).click();

        } else {
            driver.findElement(By.xpath(subFavText)).click();

        }
    }

    }



