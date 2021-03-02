package selenium.crudTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCrud {
	WebDriver driver;
	WebElement inputNameInsert;
	WebElement inputPassInsert;
	WebElement inputFnameInsert;
	WebElement inputPhoneInsert;
	WebElement roleInsert;
	WebElement insertSubmit;

	@Before
	public void starting() {
		try {
			System.setProperty("webdriver.chrome.driver",
					"D:\\test_project\\webapp\\src\\test\\resource\\chromedriver.exe");
			this.driver = new ChromeDriver();
			this.driver.get("http://localhost:8080/webapp/");
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
	public void checkLoginFail() throws InterruptedException {
		System.out.println("i am batboi");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		inputNameInsert = driver.findElement(By.name("email"));
		inputPassInsert = driver.findElement(By.name("password"));
		inputFnameInsert = driver.findElement(By.name("fullname"));
		inputPhoneInsert = driver.findElement(By.name("phonenumber"));
		roleInsert = driver.findElement(By.id("roleSelection"));
		String tableSize = (String) js.executeScript("return tableSize()");
		Select objSelect = new Select(roleInsert);
		inputNameInsert.sendKeys("dit me no");
		inputPassInsert.sendKeys("aaaa");
		inputFnameInsert.sendKeys("aaaa");
		inputPhoneInsert.sendKeys("aaaa");
		objSelect.selectByVisibleText("Admin");

		insertSubmit = driver.findElement(By.name("insertSubmit"));

		insertSubmit.click();
		Thread.sleep(2000);

		String newTableSize = (String) js.executeScript("return tableSize()");

		if (Integer.valueOf(tableSize) == (Integer.valueOf(newTableSize) + 1)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	@After
	public void finish() {
		this.driver.quit();
	}
}
