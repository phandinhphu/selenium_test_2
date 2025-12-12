package com.kiem_tra.page.orange_hrm;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kiem_tra.utils.Helper;

public class PimPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private By pimPage = By.xpath("//span[normalize-space()='PIM']");
	private By addEmployeeButton = By.xpath("//button[normalize-space()='Add']");
	private By firstNameInput = By.xpath("//input[@placeholder='First Name']");
	private By middleNameInput = By.xpath("//input[@placeholder='Middle Name']");
	private By lastNameInput = By.xpath("//input[@placeholder='Last Name']");
	private By saveButton = By.xpath("//button[@type='submit']");
	private By employeeListPage = By.xpath("//a[normalize-space()='Employee List']");
	private By searchFirstNameInput = By.xpath("//input[@placeholder='Type for hints...']");
	private By searchButton = By.xpath("//button[normalize-space()='Search']");
	private By userNameHeader = By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']");
	
	public PimPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// Actions methods
	public void navigateToPimPage() {
		Helper.safeClick(driver, driver.findElement(pimPage));
	}
	
	public void clickAddEmployeeButton() {
		Helper.safeClick(driver, driver.findElement(addEmployeeButton));
	}
	
	public void enterInformation(String firstName, String middleName, String lastName) {
		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(middleNameInput).sendKeys(middleName);
		driver.findElement(lastNameInput).sendKeys(lastName);
	}
	
	public void clickSaveButton() {
		Helper.safeClick(driver, driver.findElement(saveButton));
	}
	
	public void navigateToEmployeeListPage() {
		Helper.safeClick(driver, driver.findElement(employeeListPage));
	}
	
	public void enterSearchFirstName(String firstName) {
		driver.findElement(searchFirstNameInput).sendKeys(firstName);
	}
	
	public void clickSearchButton() {
		Helper.safeClick(driver, driver.findElement(searchButton));
	}
	
	public String getUserNameHeader() throws InterruptedException {
		WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameHeader));
		Thread.sleep(2000);
		return headerElement.getText();
	}
}
