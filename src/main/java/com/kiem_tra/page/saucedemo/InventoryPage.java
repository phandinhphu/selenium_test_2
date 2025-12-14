package com.kiem_tra.page.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	private WebDriver driver;
	private By sortDropdown = By.xpath("//select[@class='product_sort_container']");
	private By productsList = By.xpath("//div[@class='inventory_item']");
	private By addToCartButtons = By.xpath("//button[contains(@class,'btn_inventory')]");
	private By removeButtons = By.xpath("//button[contains(@class,'btn_inventory') and text()='Remove']");
	private By cartIcon = By.xpath("//span[@class='shopping_cart_badge']");

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Actions methods
	public void selectSortOption(String optionValue) {
	    Select select = new Select(driver.findElement(sortDropdown));
	    select.selectByVisibleText(optionValue.trim());
	}
	
	public String getSelectedSortOption() {
	    Select select = new Select(driver.findElement(sortDropdown));
	    return select.getFirstSelectedOption().getText();
	}
	
	public int getProductsCount() {
		return driver.findElements(productsList).size();
	}
	
	public String getProductNameByIndex(int index) {
		return driver.findElements(productsList).get(index).findElement(By.className("inventory_item_name")).getText();
	}
	
	public void addProductToCartByIndex(int index) {
		driver.findElements(addToCartButtons).get(index).click();
	}
	
	public void removeProductFromCartByIndex(int index) {
		driver.findElements(removeButtons).get(index).click();
	}
	
	public String getCartItemCount() {
		try {
			return driver.findElement(cartIcon).getText();
		} catch (Exception e) {
			return "";
		}
	}
}
