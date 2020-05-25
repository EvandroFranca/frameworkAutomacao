package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
