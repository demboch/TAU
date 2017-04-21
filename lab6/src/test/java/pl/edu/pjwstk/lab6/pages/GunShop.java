package pl.edu.pjwstk.lab6.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FileUtils.copyFile;

public class GunShop extends WebDriverPage {

    public GunShop(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void openPage() {
        get("https://mvcprojektgunshop.azurewebsites.net/Account/Login");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        manage().window().maximize();

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("20.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String idEmail, String email) {
        WebElement element = findElement(By.id(idEmail));
        element.sendKeys(email);

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("21.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String idPassword, String password) {
        WebElement element = findElement(By.id(idPassword));
        element.sendKeys(password);

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("22.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void submitClick(String className) { //btn btn-default
        WebElement element = findElement(By.className(className));
        element.submit();
        manage().window().maximize();

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("23.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getFailed(String linkText) {
        WebElement element = findElement(By.linkText(linkText));

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("24.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return element.getText();
    }

    public String getLogout(String text) {
        manage().window().maximize();
        WebElement element = findElement(By.linkText(text));

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("25.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        if(element.isDisplayed()) return element.getText();
        else return "Failed";

    }

    public void backHomePage(String backToHomePage) {
        WebElement element = findElement(By.linkText(backToHomePage));
        element.click();

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("26.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getSuccess(String linkText) {
        WebElement element = findElement(By.linkText(linkText));

        try {
            File screenshot = ((TakesScreenshot) this.getDriverProvider().get()).getScreenshotAs(OutputType.FILE);
            copyFile(screenshot, new File("27.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return element.getText();
    }
}
