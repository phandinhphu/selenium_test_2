package com.kiem_tra.page.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kiem_tra.utils.Helper;

public class LoginPage {
	private WebDriver driver;
	private String url = "https://www.saucedemo.com/";
	private By usernameInput = By.xpath("//input[@id='user-name']");
	private By passwordInput = By.xpath("//input[@id='password']");
	private By loginButton = By.xpath("//input[@id='login-button']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Actions methods
	public void navigateToLoginPage() {
		driver.get(url);
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernameInput).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
	}
	
	public void clickLoginButton() {
		Helper.safeClick(driver, driver.findElement(loginButton));
	}
}
