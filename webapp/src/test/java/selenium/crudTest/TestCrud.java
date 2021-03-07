package selenium.crudTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestCrud {
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
            System.setProperty("webdriver.chrome.driver","src\\test\\resource\\chromedriver.exe");
            driver = new ChromeDriver();
			driver.get("http://localhost:8080/webapp/");
			WebElement inputName = driver.findElement(By.name("email"));
			WebElement inputPass = driver.findElement(By.name("password"));
			WebElement btnLogIn = driver.findElement(By.className("loginbtn"));
			inputName.sendKeys("anh@gmail.com");
			inputPass.sendKeys("123");
			btnLogIn.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Test(priority=1)
	public void checkInsert() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		inputNameInsert = driver.findElement(By.name("email"));
		inputPassInsert = driver.findElement(By.name("password"));
		inputFnameInsert = driver.findElement(By.name("fullname"));
		inputPhoneInsert = driver.findElement(By.name("phonenumber"));
		roleInsert = driver.findElement(By.id("roleSelection"));
		String tableSize = (String) js.executeScript("return gaugau.tableSize()");
		Select objSelect = new Select(roleInsert);
		inputNameInsert.sendKeys("khioadmin@gmail.com");
		inputPassInsert.sendKeys("aaaa");
		inputFnameInsert.sendKeys("aaaa");
		inputPhoneInsert.sendKeys("aaaa");
		objSelect.selectByVisibleText("Admin");

		insertSubmit = driver.findElement(By.name("insertSubmit"));
		insertSubmit.click();
		Thread.sleep(2000);
		String newTableSize = (String) js.executeScript("return gaugau.tableSize()");
		if (Integer.valueOf(newTableSize) == (Integer.valueOf(tableSize) + 1)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

  @Test(priority=2)
  public void checkDeleteRow() throws InterruptedException {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("return gaugau.deleteRow()");
      
      Thread.sleep(1000);
      String result = driver.switchTo().alert().getText();
      if(result.equalsIgnoreCase("Account successfully deleted")) assertTrue(true);
      else if(result.equalsIgnoreCase("Failed to delete account")) assertFalse(false);
  }
  
	@AfterTest
	public void finish() {
		driver.quit();
	}
	
	@AfterClass
	public static void close() {
		driver.close();
	}
}
