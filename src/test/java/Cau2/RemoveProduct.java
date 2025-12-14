package Cau2;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.kiem_tra.init.InitBrowser;
import com.kiem_tra.page.saucedemo.InventoryPage;
import com.kiem_tra.page.saucedemo.LoginPage;

import utils.FileDataProvider;

public class RemoveProduct {
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
	public void testRemoveProductFromCart(Map<String, String> data) throws InterruptedException {
		Reporter.log("Step 1 : Điều hướng đến trang đăng nhập", true);
		loginPage.navigateToLoginPage();

		Reporter.log("Step 2 : Đăng nhập với tài khoản hợp lệ", true);
		System.out.println("Username: " + data.get("UserName"));
		System.out.println("Password: " + data.get("Password"));
		loginPage.enterUsername(data.get("UserName"));
		loginPage.enterPassword(data.get("Password"));
		loginPage.clickLoginButton();
		Thread.sleep(2000);
		
		Reporter.log("Step 3: Kiểm tra có sản phẩm hiển thị trên trang Inventory", true);
		int productsCount = inventoryPage.getProductsCount();
		System.out.println("Products count: " + productsCount);
		Assert.assertTrue(productsCount > 0, "No products found on Inventory page");
		
		String cartItemCountInitial = inventoryPage.getCartItemCount();
		int intCartItemCountInitial = cartItemCountInitial.isEmpty() ? 0 : Integer.parseInt(cartItemCountInitial);

		int idxProduct = (int) Double.parseDouble(data.get("ProductIndex"));
		Reporter.log("Step 4 : Thêm một sản phẩm vào giỏ hàng", true);
		inventoryPage.addProductToCartByIndex(idxProduct);
		Thread.sleep(2000);
		
		Reporter.log("Step 5 : Xác minh sản phẩm đã được thêm vào giỏ hàng", true);
		String cartItemCountBefore = inventoryPage.getCartItemCount();
		Assert.assertEquals(cartItemCountBefore, String.valueOf(intCartItemCountInitial + 1), "Product was not added to cart");

		Reporter.log("Step 6 : Xóa sản phẩm khỏi giỏ hàng", true);
		inventoryPage.removeProductFromCartByIndex(0);
		Thread.sleep(2000);

		String cartItemCount = inventoryPage.getCartItemCount();
		int intCartItemCountAfter = cartItemCount.isEmpty() ? 0 : Integer.parseInt(cartItemCount);
		
		Reporter.log("Step 7 : Xác minh sản phẩm đã được xóa khỏi giỏ hàng", true);
		Assert.assertTrue(intCartItemCountAfter == intCartItemCountInitial, "Product was not removed from cart");
	}
	
	@AfterMethod
	public void tearDown() {
		InitBrowser.quitDriver(driver);
	}

}
