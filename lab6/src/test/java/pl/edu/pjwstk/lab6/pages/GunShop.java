package pl.edu.pjwstk.lab6.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class GunShop extends WebDriverPage {

    public GunShop(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void openPage() {
        get("https://mvcprojektgunshop.azurewebsites.net/Account/Login");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void login(String idEmail, String idPassword) {
        //findElement(By.linkText("Zaloguj")).click();
        WebElement element = findElement(By.id(idEmail));
        element.sendKeys("Marian@wp.pl");
        findElement(By.id(idPassword));
        element.sendKeys("P@ssw0rd");
        element.submit();
    }

    public String logout(String linkText) {
        WebElement element = findElement(By.linkText(linkText));
        return element.getText();
    }


//    public void open() {
//        get("http://szuflandia.pjwstk.edu.pl/helpdesk.html");
//        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
//    }
//
//    public void click(String linkText) {
//        WebElement e = findElement(By.partialLinkText(linkText));
//        e.click();
//    }
//
//    public String getClassesForLink(String linkText) {
//        WebElement e = findElement(By.partialLinkText(linkText));
//        return e.getAttribute("class");
//    }
}
