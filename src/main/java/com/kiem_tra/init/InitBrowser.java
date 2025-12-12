package com.kiem_tra.init;

import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitBrowser {
	public static WebDriver getDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static Robot getRobot() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return robot;
	}
	
	public static void quitDriver(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}
}
