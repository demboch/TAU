package pl.edu.pjwstk.lab6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GunShopSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				//PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/opt/tp/phantomjs-2.1.1-linux-x86_64/bin/phantomjs"); // LINUX
				//PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/PhantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe"); // WINDOWS
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/tmp/phantomjs"); // LINUX (/linux/phantomjs) lub dla Puźniakowskiego /tmp/phantomjs
		driver = new PhantomJSDriver(caps);
	}

	@Test
	public void checkCorrectEmailAndPassword()throws IOException {
		driver.manage().window().maximize();
		driver.get("https://mvcprojektgunshop.azurewebsites.net/Account/Login");

		element = driver.findElement(By.id("Email"));
		element.sendKeys("Marian@wp.pl");
		assertEquals("Marian@wp.pl", element.getAttribute("value")); // sprawdza czy value=Marian@wp.pl
		element = driver.findElement(By.id("Password"));
		element.sendKeys("P@ssw0rd");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("1 - LoginPage.png"));
		element.submit();

		WebElement exists = driver.findElement(By.className("jumbotron")); // sprawdza czy klasa istnieje
		assertNotNull(exists);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("2 - HomePage.png"));

		driver.findElement(By.linkText("Lista Broni")).click();
		String html = driver.getPageSource();
		assertTrue(html.contains("Amerykański karabin szturmowy")); // sprawdza czy istnieje taki tekst na stronie
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("3 - ListOfWeapons.png"));

		driver.findElement(By.xpath("//a[@href='/Bron/Details/2']")).click();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("4 - Details.png"));

		driver.findElement(By.linkText("Informacje")).click();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("5 - Information.png"));

//		driver.findElement(By.linkText("Kontakt")).click();
//		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		assertNotNull(screenshot);
//		FileUtils.copyFile(screenshot, new File("10 - Contact.png"));

		driver.findElement(By.linkText("Witaj, Marian@wp.pl!")).click();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("6 - Manage.png"));

		driver.findElement(By.linkText("Wyloguj")).click();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("7 - Logout.png"));
	}

	@Test
	public void checkIncorrectEmailAndPassword()throws IOException {
		driver.manage().window().maximize();
		driver.get("https://mvcprojektgunshop.azurewebsites.net/Account/Login");

		element = driver.findElement(By.id("Email"));
		element.sendKeys("Marian@wp"); // zły LOGIN
		element = driver.findElement(By.id("Password"));
		element.sendKeys("ZŁE_HASŁO"); // złe HASŁO
		element.submit();
		String html = driver.getPageSource();
		assertTrue(html.contains("Please Enter a Valid Email.")); // !html... to test nie przechodzi
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("8 - IncorrectEmail.png"));

		element = driver.findElement(By.id("Email"));
		element.sendKeys(".pl");
		element.submit();

		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("9 - RequiredPassword.png"));

		WebElement exists = driver.findElement(By.id("Password")); // sprawdza czy id=Password istnieje
		assertNotNull(exists);

		element = driver.findElement(By.id("Password"));
		element.sendKeys("ZŁE_HASŁO");
		element.submit();

		html = driver.getPageSource();
		assertTrue(html.contains("The Hasło field is required.")); // !html... to test nie przechodzi
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("10 - ProofIncorrectPassword.png"));
	}

	@Test
	public void showLoginPage()throws IOException {
		driver.manage().window().maximize();
		driver.get("https://mvcprojektgunshop.azurewebsites.net/Account/Login");

		element = driver.findElement(By.id("Email"));
		element.sendKeys("Marian@wp.pl");
		assertEquals("Marian@wp.pl", element.getAttribute("value")); // sprawdza czy value=Marian@wp.pl
		element = driver.findElement(By.id("Password"));
		element.sendKeys("P@ssw0rd");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("11 - LoginPageAgain.png"));
		element.submit();

		WebElement exists = driver.findElement(By.className("jumbotron")); // sprawdza czy klasa istnieje
		assertNotNull(exists);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		FileUtils.copyFile(screenshot, new File("12 - HomePageAgain.png"));
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}


	//	@Test
//	public void clickAndSelectTab() throws IOException {
//		driver.get("http://szuflandia.pjwstk.edu.pl/helpdesk.html");
//
//		element = driver.findElement(By.partialLinkText("Tags"));
//		assertTrue(!element.getAttribute("class").contains("tabSelected"));
//		File screenshot =
//				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(screenshot, new File("bss.1.png"));
//		element.click();
//		assertTrue(element.getAttribute("class").contains("tabSelected"));
//		screenshot =
//				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(screenshot, new File("bss.2.png"));
//		assertNotNull(element);
//	}


}
