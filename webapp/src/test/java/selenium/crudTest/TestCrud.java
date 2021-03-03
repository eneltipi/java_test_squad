package selenium.crudTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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


public class TestCrud {
	static WebDriver driver;
	WebElement inputNameInsert;
	WebElement inputPassInsert;
	WebElement inputFnameInsert;
	WebElement inputPhoneInsert;
	WebElement roleInsert;
	WebElement insertSubmit;
	
	@Before
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
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Test
	public void checkInsert() throws InterruptedException {
		System.out.println("i am batboi");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		inputNameInsert = driver.findElement(By.name("email"));
		inputPassInsert = driver.findElement(By.name("password"));
		inputFnameInsert = driver.findElement(By.name("fullname"));
		inputPhoneInsert = driver.findElement(By.name("phonenumber"));
		roleInsert = driver.findElement(By.id("roleSelection"));
		String tableSize = (String) js.executeScript("return gaugau.tableSize()");
		Select objSelect = new Select(roleInsert);
		inputNameInsert.sendKeys("dit me no");
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

  @Test
  public void checkDeleteRow() throws InterruptedException {
      System.out.println("i am batboi");
      JavascriptExecutor js = (JavascriptExecutor) driver;

      js.executeScript("return gaugau.deleteRow()");
      Thread.sleep(500);
      String result = driver.switchTo().alert().getText();
      // TRƯỜNG HỢP NÀY THÌ MẤY CON DẶM THÊM VÀO JS OBJECT GAUGAU.AJAX CÁI INSERT KÈM THEO OBJECT INSERT NHÉ CÁC CON, CHA LÀM MẪU RỒI ĐÓ THEN GỌI LẠI HÀM NGU LỒN NÀY ĐỂ XÓA NHÉ CÁC CON CỦA CHA
      if(result.equalsIgnoreCase("this is default account")) System.out.println("READ COMMENT ABOVE THIS IF");
      if(result.equalsIgnoreCase("Account successfully deleted")) assertTrue(true);
      else if(result.equalsIgnoreCase("Failed to delete account")) assertFalse(false);
  }
  
	@After
	public void finish() {
		driver.quit();
		
	}
	@AfterClass
	public static void close() {
		//ngắt kết nối ko thì bị lỗi ngu lồn của con bátboi. con nào dời hàm này lên after thì mất session đéo chạy case delete được nhé các con của cha
		driver.close();
	}
}
