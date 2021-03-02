package selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutoTestLogin {

	public WebDriver driver;
	public UIMap uimap;
	public UIMap datafile;
	public String workingDir;
	
	//Declare An Excel Work Book
	HSSFWorkbook workbook;
	//Declare An Excel work sheet
	HSSFSheet sheet;
	//Declare A Map Object To Hold TestNG Results
	Map<String, Object[]> TestNGResults;
	
	public static String driverPath = "C:\\Users\\iOllio\\Desktop\\java_test_squad";
	
	@Test(description="Open the TestNG Demo Website for Login Test", priority=1)
	public void LaunchWebsite() throws Exception {
		try {
			driver.get("http://localhost:8080/webapp/");
			driver.manage().window().maximize();
			
//			headless
//			Chrome + firefox
		} catch (Exception e) {
			TestNGResults.put("2", new Object[] {1d, "Navigate to demo website","Site gets opened","Fail"});
			Assert.assertTrue(false);
		}
	}
	
	@Test(description = "Fill the Login Details", priority=2)
	public void FillLoginDetails() throws Exception {
		try {
			//Get the username element
			WebElement username = driver.findElement(uimap.getLocator("Username_field"));
			username.sendKeys(datafile.getData("username"));
			
			//Get the password element
			WebElement password = driver.findElement(uimap.getLocator("Password_filed"));
			password.sendKeys(datafile.getData("password"));
			
			Thread.sleep(1000);
			
			TestNGResults.put("3", new Object[] {2d, "Fill Login form data (Username/Password",
									"Login details gets filled","Pass"});
		} catch (Exception e) {
			TestNGResults.put("3",
					new Object[] {2d, "Fill Login form data (Username/Password)","Login form gets filled"});
			Assert.assertTrue(false);
		}
	}
	
	@Test(description="Perform Login", priority=3)
	public void DoLogin() throws Exception {
		try {
			//Click on the login button
			WebElement login = driver.findElement(uimap.getLocator("Login_button"));
			login.click();
			
			Thread.sleep(1000);
			
			//Assert the user login by checking the Online user
			//WebElement onlineuser = driver.findElement(uimap.getLocator("online_user"));
			//AssertJUnit.assertEquals("Hi, John Smith",onlineuser_getText());
			TestNGResults.put("4", new Object[] {3d, "Click Login and verify welcome message","Login success","Pass"});
		}catch (Exception e) {
			TestNGResults.put("4", new Object[] {3d, "Click Login and verify welcome message", "Login success","Fail"});
			Assert.assertTrue(false);
		}
	}
	
	
	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() {
		//create a new work book
		workbook = new HSSFWorkbook();
		//create a new work sheet
		sheet = workbook.createSheet("TestNG Result Summary");
		TestNGResults = new LinkedHashMap<String,Object[]>();
		//add test result excel file column header
		//write the header in the first row
		TestNGResults.put("1", new Object[] {"Test Step No.","Action","Expected Output", "Actual Output"});
		try {
			//Get current working directory and load the data file
			workingDir = System.getProperty("user.dir");
			datafile = new UIMap(workingDir + "\\Resources\\datafile.properties");
			//Get the object map file
			uimap = new UIMap(workingDir + "\\Resources\\locator.properties");
			// Setting up Chrome driver path
			System.setProperty("webdriver.chrome.driver","C:\\Users\\iOllio\\Desktop\\java_test_squad\\webapp\\Resources\\chromedriver.exe");
			//Launching Chrome browser.
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch(Exception e) {
			throw new IllegalStateException("Can't start the Firefox web Driver", e);
		}
	}
	
	@AfterClass
	public void suiteTearDown() throws FileNotFoundException {
		//write excel file an file name is SaveTestNGResultToExcel.xls
		Set<String> keyset = TestNGResults.keySet();
		int rownum = 0;
		for (String key : keyset) {
			HSSFRow row = sheet.createRow(rownum++);
			Object[] objArr = TestNGResults.get(key);
			int celnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(celnum++);
				if(obj instanceof Date) {
					cell.setCellValue((Date) obj);
				}else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean)obj);
				}else if(obj instanceof String) {
					cell.setCellValue((String) obj);
				}else if(obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("SaveTestNGResultToExcel.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Successfully saved Selenium WebDriver TestNG result to Excel File!");
		}catch (IOException e) {
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
	}
}
