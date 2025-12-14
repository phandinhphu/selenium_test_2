package Cau2;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
	public void testSortProducts(Map<String, String> data) throws InterruptedException {
		Reporter.log("Step 1 : Điều hướng đến trang đăng nhập", true);
		loginPage.navigateToLoginPage();

		Reporter.log("Step 2 : Đăng nhập với tài khoản hợp lệ", true);
		loginPage.enterUsername(data.get("UserName"));
		loginPage.enterPassword(data.get("Password"));
		loginPage.clickLoginButton();

		Reporter.log("Step 3 : Chọn tùy chọn sắp xếp", true);
		inventoryPage.selectSortOption(data.get("SortOption"));

		Assert.assertEquals(inventoryPage.getSelectedSortOption(), data.get("SortOption").trim(),
				"Sort option bị thay đổi sau khi chọn");

		Thread.sleep(2000);
		Reporter.log("Step 4 : Xác minh sản phẩm được sắp xếp", true);
		int productCount = inventoryPage.getProductsCount();
		String firstProductName = inventoryPage.getProductNameByIndex(0);
		String lastProductName = inventoryPage.getProductNameByIndex(productCount - 1);

		if (data.get("SortOption").equals("Name (Z to A)")) {
			assert firstProductName.compareTo(lastProductName) > 0 : "Products are not sorted from Z to A";
		} else if (data.get("SortOption").equals("Name (A to Z)")) {
			assert firstProductName.compareTo(lastProductName) < 0 : "Products are not sorted from A to Z";
		}
	}

	@AfterMethod
	public void tearDown() {
		InitBrowser.quitDriver(driver);
	}
}
