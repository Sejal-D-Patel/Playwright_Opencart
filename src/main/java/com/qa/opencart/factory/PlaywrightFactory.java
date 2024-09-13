package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Properties prop;
	
	public static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	public static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	public static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	public static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	
	public Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	public Browser getBrowser() {
		return tlBrowser.get();
	}
	public BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	public static Page getPage() {
		return tlPage.get();
	}
	

	public Page initBrowser(Properties prop) {
		
		String browserName=prop.getProperty("browser").trim();
		System.out.println("Browser name:" + browserName);

		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set( getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		default:
			System.out.println("Please pass the correct Browser name...!!");
			break;
		}
		/*browserContext = browser.newContext();
		Page page = browserContext.newPage();
		String URL=prop.getProperty("url").trim();
		page.navigate(URL);
		return page;*/
		
		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate( prop.getProperty("url").trim());
		return getPage();

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			try {
				prop.load(ip);
			} catch (FileNotFoundException e) { 
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String takeScreenShot() {
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path;
				
	}
}
