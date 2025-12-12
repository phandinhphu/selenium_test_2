package Cau1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.kiem_tra.init.InitBrowser;
import com.kiem_tra.page.orange_hrm.LoginPage;
import com.kiem_tra.page.orange_hrm.PimPage;

public class AddEmployee {
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
	public void testAddEmployee() throws InterruptedException {
		Reporter.log("Step 1: Đieu hướng đến trang đăng nhập OrangeHRM", true);
		loginPage.navigateToLoginPage();
		Thread.sleep(5000);
		Reporter.log("Step 2: Nhập tên đăng nhập", true);
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginButton();
		Thread.sleep(3000);

		Reporter.log("Step 3: Điều hướng đến trang PIM", true);
		pimPage.navigateToPimPage();
		Thread.sleep(2000);

		Reporter.log("Step 4: Nhấn nút Thêm Nhân Viên", true);
		pimPage.clickAddEmployeeButton();
		Thread.sleep(2000);

		Reporter.log("Step 5: Nhập thông tin nhân viên và lưu", true);
		pimPage.enterInformation("John", "A.", "Doe");
		pimPage.clickSaveButton();

		Reporter.log("Step 6: Điều hướng đến trang Danh Sách Nhân Viên và tìm kiếm nhân viên vừa thêm", true);
		String expectedName = "John" + " " + "Doe";
		String result = pimPage.getUserNameHeader();
		Reporter.log("Expected Name: " + expectedName, true);
		Reporter.log("Actual Name: " + result, true);
		Assert.assertEquals(result, expectedName, "Employee name does not match!");

	}

	@AfterMethod
	public void tearDown() {
		InitBrowser.quitDriver(driver);
	}
}
