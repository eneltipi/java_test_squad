package selenium.crudTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class TestUpdate {
	static WebDriver driver;
	WebElement inputNameInsert;
	WebElement inputPassInsert;
	WebElement inputFnameInsert;
	WebElement inputPhoneInsert;
	WebElement roleInsert;
	WebElement insertSubmit;
	String chromeDriverPath = "src\\test\\resource\\chromedriver.exe";
	
	@Before
	public void starting() {
		try {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
			driver.get("http://localhost:8080/webapp/");
			WebElement inputName = driver.findElement(By.name("email"));
			WebElement inputPass = driver.findElement(By.name("password"));
			WebElement btnLogIn = driver.findElement(By.className("loginbtn"));
			inputName.sendKeys("anh@gmail.com");
			inputPass.sendKeys("123");
			btnLogIn.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
  
  @Test
  public void checkUpdateRow() throws InterruptedException, AWTException {
	Robot robot = new Robot();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement cell = (WebElement) js.executeScript("return gaugau.getRandomCell()");
	cell.click();
	cell.click();
	Thread.sleep(500);
	WebElement input = cell.findElement(By.name("currentInput"));
  	String newValue = "new Value";
  	input.sendKeys(newValue);
	Thread.sleep(500);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	assertTrue(cell.getText().equalsIgnoreCase(newValue));
  }
  
	@After
	public void finish() {
		driver.quit();
		
	}
	
	@AfterClass
	public static void close() {
//		driver.close();
	}
}
