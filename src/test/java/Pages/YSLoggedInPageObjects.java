package Pages;

import Base.TestBase;
import org.openqa.selenium.By;

public class YSLoggedInPageObjects extends TestBase {

    static By foodorder = By.xpath("/html/body/div[13]/div/div/div/div[2]/div/a[2]/div[3]/button");
    static By searchbar = By.xpath("/html/body/header/div/div/div/div[3]/input");
    static By searchbarbutton = By.xpath("/html/body/header/div/div/div/div[4]/button/span");
    static By profileMenu = By.id("ysUserName");
    static By myFavorites = By.xpath("//*[@id=\"basket-container\"]/div[1]/div[2]/div[1]/ul/li[4]/a");
    static By jokerPopup = By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/div[1]/div/div[1]/i");
    static By closeJokerElement = By.id("cboxClose");
    static By firstRestaurantName = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/a");
    static By addFavButton = By.xpath("//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[1]/b");
    static By subFavButton = By.xpath("//*[@id=\"restaurantDetail\"]/div[2]/div[1]/div/div/div[2]/b");
    static By addressElement = By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[1]/div/div[2]/ul/li/div/a/span[2]");
    static By yemeksepetiLogo = By.xpath("/html/body/header/div/div/div/div[1]/a");
    static By firstHomeRestName = By.xpath("/html/body/div[2]/div/div[2]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/a/span");

    public static void checkSuccessfulLogin() throws InterruptedException {

        Thread.sleep(2000);
        driver.findElement(foodorder).isDisplayed();

    }

    public static void selectFoodOrderPlatform(){

        WaitaBit();
        driver.findElement(foodorder).click();

    }

    public static void searchFood(String foodname){

        driver.findElement(searchbar).sendKeys(foodname);
        driver.findElement(searchbarbutton).click();

    }

    public static void openFavorites() throws InterruptedException {

        Thread.sleep(2000);
        driver.findElement(profileMenu).click();
        driver.findElement(myFavorites).click();

    }

    public static void closeJoker() throws InterruptedException {

        Thread.sleep(3000);
        if (driver.findElements(jokerPopup).size() != 0){
            driver.findElement(closeJokerElement).click();
        }else {
            System.out.println("No Joker");
        }
    }

    public static String selectFirstBurgerRestaurant() {

        String addedRestName = driver.findElement(firstRestaurantName).getText();
        driver.findElement(firstRestaurantName).click();
        return addedRestName;

    }

    public static String selectFirstHomeRestaurant() {

        String selectedFirstHomeRestName = driver.findElement(firstHomeRestName).getText();
        driver.findElement(firstHomeRestName).click();
        return selectedFirstHomeRestName;

    }

    public static void clickFavAction(String action) {

        if(action.equals("Ekle"))
        driver.findElement(addFavButton).click();
        else
            driver.findElement(subFavButton).click();

    }

    public static void selectAddress() {

        driver.findElement(addressElement).click();

    }

    public static void goToHome() {

        driver.findElement(yemeksepetiLogo).click();
        driver.findElement(addressElement).click();

    }

    public static void favThreeRestaurants() {

        goToHome();

        for(int i = 1; i<4; i++)
        {
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[4]/div[2]/div["+i+"]/div[2]/div[1]/div[1]/a/span")).click();
            clickFavAction("Ekle");
            driver.navigate().back();

        }

    }

}