package Cau1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.kiem_tra.init.InitBrowser;
import com.kiem_tra.page.orange_hrm.LoginPage;
import com.kiem_tra.page.orange_hrm.PimPage;

public class SearchEmployee {
	private WebDriver driver;
	private LoginPage loginPage;
	private PimPage pimPage;
	
	@BeforeMethod
	public void setUp() {
		driver = InitBrowser.getDriver();
		loginPage = new LoginPage(driver);
		pimPage = new PimPage(driver);
	}
	
	@Test
	public void testSearchEmployee() throws InterruptedException {
		Reporter.log("Step 1: Điều hướng đến trang đăng nhập OrangeHRM", true);
		loginPage.navigateToLoginPage();
		Thread.sleep(2000);
		
		Reporter.log("Step 2: Nhập tên đăng nhập", true);
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginButton();
		Thread.sleep(3000);
		
		Reporter.log("Step 3: Điều hướng đến trang PIM", true);
		pimPage.navigateToPimPage();
		Thread.sleep(2000);

		Reporter.log("Step 4: Điều hướng đến trang Danh Sách Nhân Viên", true);
		pimPage.navigateToEmployeeListPage();
		Thread.sleep(2000);

		Reporter.log("Step 5: Tìm kiếm nhân viên theo tên 'John'", true);
		pimPage.enterSearchFirstName("John");
		pimPage.clickSearchButton();
		Thread.sleep(3000);

		Reporter.log("Step 6: Lấy và in ra số lượng bản ghi tìm thấy", true);
		String recordsText = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
		Reporter.log("Số lượng bản ghi tìm thấy: " + recordsText, true);
		Assert.assertTrue(recordsText.contains("Records Found"));
	}
	
	@AfterMethod
	public void tearDown() {
		InitBrowser.quitDriver(driver);
	}
}
