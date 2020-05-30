package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private WebDriver driver;
	private static DriverFactory instancia;
	
	public DriverFactory() {
		instancia = this;
	}
	
	public static DriverFactory getInstance() {
		if(instancia == null) {
			instancia = new DriverFactory();
		}
		return instancia;
	}
	
	public void setChromeDriver(String url) {
		if(driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to(url);
		}
	}
	
	public void setFirefoxDriver(String url) {
		if(driver == null) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.navigate().to(url);
		}
	}
	
	public void setInternetExplorerDriver(String url) {
		if(driver == null) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.get(url);
			driver.manage().window().maximize();
		}
	}
	
	public void setEdgeDriver(String url) {
		if(driver == null) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.navigate().to(url);
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
