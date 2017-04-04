package pl.edu.pjwstk.lab5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class MyLocalSiteTest {

	private static WebDriver driver;
	WebElement element;
	int i = 30;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
		System.setProperty("webdriver.chrome.driver", "/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.manage().window().maximize();
		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");

		element = driver.findElement(By.linkText("GunShop"));
		assertNotNull(element);
	}

	@Test
	public void loginLogoutScreenshots(){
		driver.manage().window().maximize();
		driver.get("http://localhost/ProjektTIN/index.php");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/1przedLogowaniem.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		driver.findElement(By.linkText("Zaloguj")).click();
		element = driver.findElement(By.name("password"));
		element.sendKeys("haslo");
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/2wTrakcieLogowania.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		element.submit();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/3zalogowanie.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		driver.findElement(By.linkText("Wyloguj")).click();
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/4wylogowanie.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void addNewPostPage() throws InterruptedException, AWTException {
		driver.manage().window().maximize();
		driver.get("http://localhost/ProjektTIN/index.php");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		driver.findElement(By.linkText("Zaloguj")).click();
		element = driver.findElement(By.name("password"));
		element.sendKeys("haslo");
		element.submit();

		driver.get("http://localhost/ProjektTIN/index.php?akcja=dodajpostform");
		element = driver.findElement(By.name("tresc"));
		element.sendKeys("bekonowa amunicja");
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/5trescPost.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		driver.findElement(By.className("upload")).click();
		//element = driver.findElement(By.name("fileToUpload"));
		Thread.sleep(2000);
		StringSelection ss = new StringSelection("C:\\Users\\dembo\\Desktop\\PJWSTK\\SEMESTR6\\TAU\\boczek.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.name("submit")).click();

		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/6dodanyPost.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void editNewPostPage() throws InterruptedException, AWTException {
		driver.manage().window().maximize();
		driver.get("http://localhost/ProjektTIN/index.php");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		driver.findElement(By.linkText("Zaloguj")).click();
		element = driver.findElement(By.name("password"));
		element.sendKeys("haslo");
		element.submit();

		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/7przedEdycja.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		driver.get("http://localhost/ProjektTIN/index.php?akcja=potwierdzenieedycji&przedmiotakcji=" + i);
		element = driver.findElement(By.name("tresc"));
		element.sendKeys("jednak beconBomb					");
		driver.findElement(By.className("uploadedytuj")).click();
		Thread.sleep(2000);
		StringSelection ss = new StringSelection("C:\\Users\\dembo\\Desktop\\PJWSTK\\SEMESTR6\\TAU\\bacon.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.name("submit")).click();

		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/8poEdycji.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void deleteNewPostPage() throws InterruptedException, AWTException{
		driver.manage().window().maximize();
		driver.get("http://localhost/ProjektTIN/index.php");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		driver.findElement(By.linkText("Zaloguj")).click();
		element = driver.findElement(By.name("password"));
		element.sendKeys("haslo");
		element.submit();

		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/9jestPost.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		driver.get("http://localhost/ProjektTIN/index.php?akcja=potwierdzenieusuniecia&przedmiotakcji=" + i);
		driver.get("http://localhost/ProjektTIN/index.php?akcja=usunpost&przedmiotakcji=" + i);
		driver.get("http://localhost/ProjektTIN/index.php?akcja=brak");
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/10usunietyPost.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void projektPHPtest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12902/ProjektPHP/");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(2000);

		//driver.findElement(By.className("line czas")).click();
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12902/ProjektPHP/czas.php");
		Thread.sleep(1000);
		element = driver.findElement(By.name("czas"));
		element.sendKeys("3600");
		Thread.sleep(1000);
		Select dropdown = new Select(driver.findElement(By.name("wyborczasu")));
		dropdown.selectByValue("min");
		Thread.sleep(1000);
		driver.findElement(By.name("submitczas")).click();

		Thread.sleep(2000);
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		try {
			FileUtils.copyFile(screenshot, new File("C:/Users/dembo/Desktop/PJWSTK/SEMESTR6/TAU/Lab5Screenshot/phpWynik.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
