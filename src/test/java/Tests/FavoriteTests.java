package Tests;

import Base.TestBase;
import Pages.YSFavoritesPageObjects;
import Pages.YSLoggedInPageObjects;
import Pages.YSMainPageObjects;
import org.testng.annotations.Test;

public class FavoriteTests extends TestBase {

    @Test(testName = "Prepare for Add Favorite Actions", priority = 8)
    public static void PrepareForAddFavoriteActions() throws InterruptedException {

        YSMainPageObjects.TryLogin("ysotmsyn@gmail.com","123Ys456.");
        Thread.sleep(2000);
        YSLoggedInPageObjects.selectFoodOrderPlatform();
        YSFavoritesPageObjects.clearFavorites();

    }

    @Test(testName = "Add Restaurant to Favorites", priority = 9)
    public static void AddRestaurantToFavorites() throws Exception {

        YSLoggedInPageObjects.selectAddress();
        YSLoggedInPageObjects.closeJoker();
        Thread.sleep(2000);
        YSLoggedInPageObjects.searchFood("burger");
        String expectedAddedRestName = YSLoggedInPageObjects.selectFirstBurgerRestaurant();
        YSLoggedInPageObjects.clickFavAction("Ekle");
        YSFavoritesPageObjects.searchForAdd_SubRest(expectedAddedRestName, "Ekle");

    }

    @Test(testName = "Delete All Favorites if Exists", priority = 10)
    public static void DeleteAllFavoritesIfExists() throws InterruptedException {

        YSLoggedInPageObjects.openFavorites();
        YSFavoritesPageObjects.clearFavorites();
        driver.navigate().refresh();
        YSFavoritesPageObjects.confirmNoFavorites();

    }

    @Test(testName = "Prepare for Subtract Favorite Actions", priority = 11)
    public static void PrepareForSubtractFavoriteActions()
    {
        YSLoggedInPageObjects.favThreeRestaurants();
    }

    @Test(testName = "Subtract Restaurant from Favorites", priority = 12)
    public static void SubtractRestaurantFromFavorites() throws Exception {

        YSLoggedInPageObjects.goToHome();
        String expectedRestNameForSubtraction = YSLoggedInPageObjects.selectFirstHomeRestaurant();
        YSLoggedInPageObjects.clickFavAction("Çıkar");
        YSFavoritesPageObjects.searchForAdd_SubRest(expectedRestNameForSubtraction, "Çıkar");

    }

}