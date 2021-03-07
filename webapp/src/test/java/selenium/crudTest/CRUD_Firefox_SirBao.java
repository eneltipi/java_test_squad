package selenium.crudTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CRUD_Firefox_SirBao {
	static WebDriver driver;
	WebElement inputNameInsert;
	WebElement inputPassInsert;
	WebElement inputFnameInsert;
	WebElement inputPhoneInsert;
	WebElement roleInsert;
	WebElement insertSubmit;

	String user = "adsaasf@gmail.co";
	String pass = "fffffff";

	@BeforeClass
	public void starting() {
		try {
			System.setProperty("webdriver.gecko.driver", "src\\test\\resource\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get("http://localhost:8080/webapp/");
			WebElement inputName = driver.findElement(By.name("email"));
			WebElement inputPass = driver.findElement(By.name("password"));
			WebElement btnLogIn = driver.findElement(By.className("loginbtn"));
			inputName.sendKeys("anh@gmail.com");
			inputPass.sendKeys("123");
			btnLogIn.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Test
	public void checkDeleteRow() throws InterruptedException {
		try {
			Robot robot = new Robot();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			ArrayList<Double> randomCellCordinate = (ArrayList<Double>) js.executeScript("return gaugau.getRandomCellCordinate()");
			int x = (int)  Math.round(randomCellCordinate.get(1)+200);
			int y = (int)  Math.round(randomCellCordinate.get(0)+200);
			robot.mouseMove(x, y);
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			Thread.sleep(500);
			robot.mouseMove(x+20, y+20);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(500);
			Alert alert = driver.switchTo().alert();
			String alertContent = alert.getText();
			Thread.sleep(1000);
			alert.dismiss();
			if(alertContent.equalsIgnoreCase("Account successfully deleted")) {
				assertTrue(true);
			}
			else {
				assertFalse(false);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}      
	}
	
	@Test
	public void checkInsert() throws InterruptedException {
		try {
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			inputNameInsert = driver.findElement(By.name("email"));
			inputPassInsert = driver.findElement(By.name("password"));
			inputFnameInsert = driver.findElement(By.name("fullname"));
			inputPhoneInsert = driver.findElement(By.name("phonenumber"));
			roleInsert = driver.findElement(By.id("roleSelection"));
			String tableSize = (String) js.executeScript("return gaugau.tableSize()");
			Select objSelect = new Select(roleInsert);
			inputNameInsert.sendKeys("anh@gmail.com");
			inputPassInsert.sendKeys("123");
			inputFnameInsert.sendKeys("54545");
			inputPhoneInsert.sendKeys("5656575");
			objSelect.selectByVisibleText("Admin");
			Thread.sleep(500);
			insertSubmit = driver.findElement(By.name("insertSubmit"));
			insertSubmit.click();
			Thread.sleep(2000);
			String newTableSize = (String) js.executeScript("return gaugau.tableSize()");
			if (Integer.valueOf(newTableSize) == (Integer.valueOf(tableSize) + 1)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	@AfterClass
	public static void close() {
		driver.close();
	}
}
