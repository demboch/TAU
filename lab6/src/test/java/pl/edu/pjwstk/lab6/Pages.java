package pl.edu.pjwstk.lab6;

import org.jbehave.web.selenium.WebDriverProvider;
import pl.edu.pjwstk.lab6.pages.GunShop;

public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private GunShop gunShop;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public GunShop gunshop() {
        if (gunShop == null) {
            gunShop = new GunShop(driverProvider);
        }
        return gunShop;
    }
}
