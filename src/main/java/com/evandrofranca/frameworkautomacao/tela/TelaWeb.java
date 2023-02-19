package com.evandrofranca.frameworkautomacao.tela;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.evandrofranca.frameworkautomacao.driver.DriverFactory;
import com.evandrofranca.frameworkautomacao.utils.XPath;

public class TelaWeb {

	protected WebDriver driver;
	protected WebDriverWait driverWait;

	public TelaWeb() {
		this.driver = DriverFactory.getInstance().getDriver();
		this.driverWait = new WebDriverWait(this.driver, 30);
	}

	public WebElement recuperarElemento(String tipo, String atributo, String valor) throws Exception {
		return recuperarElemento(By.xpath(XPath.montar(tipo, atributo, valor, 0)));
	}

	public WebElement recuperarElemento(String tipo, String atributo, String valor, int index) throws Exception {
		return recuperarElemento(By.xpath(XPath.montar(tipo, atributo, valor, (index + 1))));
	}

	public WebElement recuperarElemento(String xpath) throws Exception {
		return recuperarElemento(By.xpath(xpath));
	}

	private WebElement recuperarElemento(By by) throws Exception {
		try {
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement we = driver.findElement(by);
			highlight(we, 1);
			return we;
		} catch (Exception e) {
			System.out.println("Erro ao recuperar Elemento\n" + e.getMessage());
			throw e;
		}
	}

	private void highlight(WebElement element, int segundos_duracao) throws InterruptedException {
		String originalStyle = element.getAttribute("style");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', ' border: 2px solid red;');", element);

		try {
			Thread.sleep(segundos_duracao * 500);
		}catch (InterruptedException e) {}

		js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

}
