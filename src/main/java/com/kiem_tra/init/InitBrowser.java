package com.kiem_tra.init;

import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitBrowser {
	public static WebDriver getDriver() {
		ChromeOptions options = new ChromeOptions();
	    options.addArguments(
	        "--disable-notifications",
	        "--disable-infobars"
	    );

	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.password_manager_leak_detection", false);

	    options.setExperimentalOption("prefs", prefs);

	    WebDriver driver = new ChromeDriver(options);
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
