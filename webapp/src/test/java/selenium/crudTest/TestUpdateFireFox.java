package selenium.crudTest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUpdateFireFox {
	static WebDriver driver;
	WebElement inputNameInsert;
	WebElement inputPassInsert;
	WebElement inputFnameInsert;
	WebElement inputPhoneInsert;
	WebElement roleInsert;
	WebElement insertSubmit;
	
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
  public void checkUpdateRow() throws InterruptedException, AWTException {
	  try {
		Robot robot = new Robot();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement cell = (WebElement) js.executeScript("return gaugau.getRandomCell()");
		cell.click();
		cell.click();
		Thread.sleep(300);
		WebElement input = cell.findElement(By.name("currentInput"));
		Thread.sleep(300);
		String newValue = "new Value";
		input.sendKeys(newValue);
		Thread.sleep(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		if(cell.getText().equalsIgnoreCase(newValue))
		assertTrue(true);
		else 
		assertFalse(true);
	  } catch (Exception e) {
			System.out.println(e.toString());
	  }
  }
  
//	@AfterTest
//	public void finish() {
//		driver.quit();
//		
//	}
//	
//	@AfterClass
//	public static void close() {
//		driver.close();
//	}
}
