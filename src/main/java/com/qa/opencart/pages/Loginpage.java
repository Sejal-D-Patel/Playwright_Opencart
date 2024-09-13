package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class Loginpage {
	private Page page;

	// 1.String Locator - Object Repository

	private String txt_email = "input#input-email";
	private String txt_password = "input#input-password";
	private String loginbtn="//input[@value='Login']";
	private String fogotPwdLink="//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLink="//a[@class='list-group-item'][normalize-space()='Logout']";
	
	// 2. Page constructor
	public Loginpage(Page page) {
		this.page = page;
	}

	// 3. page action/methods
	public String getLoginPageTitle() {
		return page.title();
	}

	public boolean isForgotLinkExist() {
		return page.isVisible(fogotPwdLink);
	}
	
	public boolean doLogin(String username, String password) {
		//System.out.println("Username:"+username+"   "+"Password:"+password);
		page.fill(txt_email, username);
		page.fill(txt_password, password);
		page.click(loginbtn);
		if(page.isVisible(logoutLink)) {
			System.out.println("User sucessfully logged IN..!!");
			return true;
		}else {
			return false;
		}
		
	}
}
