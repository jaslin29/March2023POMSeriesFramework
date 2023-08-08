package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver; // class driver
	private ElementUtil eleUtil;
	
	//1. private By locators - page locators
	
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password"); 
	private By registerLink = By.linkText("Register");
	private By naveenLink = By.linkText("Naveen");
	
	//2. Page Constructors- public
	
	public LoginPage(WebDriver driver) { // this will be the local driver
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. Page actions/methods - these will be public so that I can call it from my testng class
	
	@Step("....getting login page title....")
	public String getLoginPageTitle() {
		//String title = driver.getTitle();
		
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page title is: " +title);
		return title;
	}
	
	@Step("....getting login page url....")
	public String getLoginPageURL() {
		//String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page url is: " + url);
		return url;
	}
	
	@Step("....is forgot pwd link exist or not....")
	public boolean isForgotPwdLinkExist() {
	//	return driver.findElement(forgotPwdLink).isDisplayed();// using the private by locators in public methods- encapsulation
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step("login to app with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App creds are: " + username + ":" + pwd);
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		return driver.getTitle();
		
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	//	return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page....")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.SHORT_TIME_OUT).click();
		return new RegisterPage(driver);
	}
	
	
	
	
	
}
