package Cau1;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.kiem_tra.init.InitBrowser;
import com.kiem_tra.page.orange_hrm.LoginPage;

import utils.FileDataProvider;

public class Bai1 {
	private WebDriver driver;
	private LoginPage loginPage;

	@BeforeTest
	void setUp() {
		driver = InitBrowser.getDriver();
		loginPage = new LoginPage(driver);
	}

	@Test(dataProvider = "excelData", dataProviderClass = FileDataProvider.class)
	public void testLoginOrangeHRM(Map<String, String> data) throws InterruptedException {
		Reporter.log("Step 1: Điều hướng đến trang đăng nhập OrangeHRM", true);
		loginPage.navigateToLoginPage();
		Thread.sleep(2000);

		Reporter.log("Step 2: Nhập tên đăng nhập", true);
		loginPage.enterUsername(data.get("Username"));

		Reporter.log("Step 3: Nhập mật khẩu", true);
		loginPage.enterPassword(data.get("Password"));

		Reporter.log("Step 4: Nhấn nút Đăng nhập", true);
		loginPage.clickLoginButton();

		Thread.sleep(3000);
		
		Reporter.log("Step 5: Kiểm tra đăng nhập thành công", true);
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertTrue(expectedUrl.contains("/web/index.php/dashboard"));
		
		Reporter.log("Step 6: Đăng xuất để chuẩn bị cho lần đăng nhập tiếp theo", true);
		loginPage.logout();
	}

	@AfterTest
	void tearDown() {
		InitBrowser.quitDriver(driver);
	}
}
