package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;

public class LoginTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actualTitle = loginPage.getLoginPageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, AppConstant.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.isForgotLinkExist());
	}

	@Test(priority = 3)
	public void loginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));

	}
}
