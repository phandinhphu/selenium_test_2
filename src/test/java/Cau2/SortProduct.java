package Cau2;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.kiem_tra.init.InitBrowser;
import com.kiem_tra.page.saucedemo.*;

import utils.FileDataProvider;

public class SortProduct {
	private WebDriver driver;
	private LoginPage loginPage;
	private InventoryPage inventoryPage;
	
	@BeforeMethod
	public void setUp() {
		driver = InitBrowser.getDriver();
		loginPage = new LoginPage(driver);
		inventoryPage = new InventoryPage(driver);
	}
	
	@Test(dataProvider = "excelData", dataProviderClass = FileDataProvider.class)
	public void testSortProductsByNameZToA(Map<String, String> data) throws InterruptedException {
		Reporter.log("Step 1 : Điều hướng đến trang đăng nhập", true);
		loginPage.navigateToLoginPage();
		
		Reporter.log("Step 2 : Đăng nhập với tài khoản hợp lệ", true);
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLoginButton();

		Reporter.log("Step 3 : Chọn tùy chọn sắp xếp 'Name (Z to A)'", true);
		inventoryPage.selectSortOption("Name (Z to A)");
		Thread.sleep(2000);

		Reporter.log("Step 4 : Xác minh sản phẩm được sắp xếp theo tên từ Z đến A", true);
		int productCount = inventoryPage.getProductsCount();
		String firstProductName = inventoryPage.getProductNameByIndex(0);
		String lastProductName = inventoryPage.getProductNameByIndex(productCount - 1);
		
		assert firstProductName.compareTo(lastProductName) > 0 : "Products are not sorted from Z to A";
	}

	@AfterMethod
	public void tearDown() {
		InitBrowser.quitDriver(driver);
	}
}
