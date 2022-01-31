package Pages;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class YSFavoritesPageObjects extends TestBase {

    static By favCheckbox = By.xpath("//input[@type='checkbox']");
    static By favDeleteSubmit = By.xpath("//*[@id=\"favorites\"]/form/div[2]/button");
    static By checkFavorites = By.xpath("//*[@id=\"favorites\"]/div/p");
    static By noFavRestIcon = By.xpath("//*[@id=\"favorites\"]/div/div/i");
    static By favListElements = By.xpath("//div[@class='favorite-item']");

public static void clearFavorites() throws InterruptedException {

    YSLoggedInPageObjects.openFavorites();

    if(driver.findElements(checkFavorites).size() != 0){
        System.out.println("No Favorites to Delete");}
    else {
        List<WebElement> elements = driver.findElements(favCheckbox);
        for (WebElement el:elements)
        {
            el.click();
        }
        driver.findElement(favDeleteSubmit).click();
    }

    }

   public static void searchForAdd_SubRest(String expectedRestName, String action) throws Exception{

    YSLoggedInPageObjects.openFavorites();
    driver.navigate().refresh();

    List<WebElement> elements = driver.findElements(favListElements);

    if (driver.findElements(checkFavorites).size() != 0) {
        System.out.println("No Favorite to Match");}
    else {

        ArrayList<String> arrlist = new ArrayList<>(elements.size());
        for (int i=1;i<=elements.size();i++)
        {
            arrlist.add(driver.findElement(By.xpath("//*[@id=\"favorites\"]/form/div[1]/div["+i+"]/a/span/b")).getText());
        }

        if(action.equals("Ekle")){
        boolean retval = arrlist.contains(expectedRestName);

        if(!retval){throw new Exception("Favorilere eklenen restaurant favoilerde yok! Eklenen restaurant: "+expectedRestName);}

        }

        else if(action.equals("Çıkar")){
            boolean retval = arrlist.contains(expectedRestName);

            if(retval){throw new Exception("Favoriler'den çıkartılan restaurant favoilerde mevcut! Çıkartılan restaurant: "+expectedRestName);}
        }

        }

}

    public static void confirmNoFavorites() {

    driver.findElement(noFavRestIcon).isDisplayed();

    }

}
