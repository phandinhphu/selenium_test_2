package com.kiem_tra.page.orange_hrm;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kiem_tra.utils.Helper;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private String url = "https://opensource-demo.orangehrmlive.com/";
	private By usernameInput = By.xpath("//input[@placeholder='Username']");
	private By passwordInput = By.xpath("//input[@placeholder='Password']");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By avatarIcon = By.xpath("//p[@class='oxd-userdropdown-name']");
	private By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// Actions methods
	public void navigateToLoginPage() {
		driver.get(url);
	}
	
	public void enterUsername(String username) {
		WebElement usernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
		usernameElem.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
		passwordElem.sendKeys(password);
	}
	
	public void clickLoginButton() {
		Helper.safeClick(driver, driver.findElement(loginButton));
	}
	
	public void logout() {
		Helper.safeClick(driver, driver.findElement(avatarIcon));
		Helper.safeClick(driver, driver.findElement(logoutButton));
	}
}
