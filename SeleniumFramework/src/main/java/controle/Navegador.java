package controle;

public enum Navegador {
	
	CHROME("webdriver.chrome.driver", "C:\\\\Users\\\\evand\\\\OneDrive\\\\Documentos\\\\chromedriver.exe");
	
	String driver;
	String local;
	
	Navegador(String driverOpcao, String localOpcao) {
		driver = driverOpcao;
		local = localOpcao;
	}
	
	public String getDriver() {
		return driver;
	}
	
	public String getLocal() {
		return local;
	}

}
