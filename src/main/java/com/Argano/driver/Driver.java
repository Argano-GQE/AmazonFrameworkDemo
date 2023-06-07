package com.Argano.driver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.Argano.constants.FrameworkConstants;
import com.Argano.enums.ConfigProperties;
import com.Argano.utlis.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class Driver {

	private Driver() {

	}

	public static void initDriver(String browser) {
		if (Objects.isNull(DriverManager.getDriver())) {
			//System.out.println(Objects.isNull(DriverManager.getDriver()));
			if (browser.equalsIgnoreCase("Chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				DriverManager.setDriver(new ChromeDriver(chromeOptions));
			} else if (browser.equalsIgnoreCase("Firefox")) {
				FirefoxOptions firefoxOptions = new FirefoxOptions(); 
				firefoxOptions.addPreference("dom.webnotifications.enabled",false)
				.addPreference("dom.webnotifications.enabled", false)
                .addPreference("geo.enabled", false)
                .addPreference("geo.provider.use_corelocation", false)
                .addPreference("geo.prompt.testing", false)
                .addPreference("geo.prompt.testing.allow", false);
				WebDriverManager.firefoxdriver().setup();
				DriverManager.setDriver(new FirefoxDriver(firefoxOptions));

			} else if (browser.equalsIgnoreCase("InternetExplorer")) {
				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
				ieOptions.addCommandSwitches("--disable-notifications");
				WebDriverManager.iedriver().setup();
				DriverManager.setDriver(new InternetExplorerDriver(ieOptions));
			}
			
				else if (browser.equalsIgnoreCase("Edge")) {
					//EdgeDriver edgedriver = new EdgeDriver();
					//ieOptions.addCommandSwitches("--disable-notifications");
					WebDriverManager.edgedriver().setup();
					DriverManager.setDriver(new EdgeDriver());
			} 
			
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().manage().timeouts().implicitlyWait(FrameworkConstants.getImplicitywaittime(),
					TimeUnit.SECONDS);
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
		}
	}
		

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
