package com.kiem_tra.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {
	public static void safeClick(WebDriver driver, WebElement element) {
	    try {
	        element.click();
	    } catch (Exception e) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	    }
	}
	
	public static void takeScreenshot(WebDriver driver, String fileName) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("screenshots/" + fileName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
