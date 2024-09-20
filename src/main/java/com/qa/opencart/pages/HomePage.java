package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;

	// 1.String Locator - Object Repository

	private String search = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
	private String myAccount="a[title='My Account']";
	private String loginLink="a:text('Login')";
	// 2. Page constructor
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page action/methods
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title:" + title);
		return title;
	}
	/*
	 * Get Home Page URL
	 * */

	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page URL:" + url);
		return url;
	}

	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String header = page.textContent(searchPageHeader);
		System.out.println("Search text:" + header);
		return header;

	}
	public Loginpage navigateToLoginPage() {
		page.click(myAccount);
		page.click(loginLink);
		return new Loginpage(page); // return the login page object as TDD concept
	}
}
