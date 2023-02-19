package com.evandrofranca.frameworkautomacao.controle;

public enum Navegador {
	
	CHROME("chrome.exe", "chromedriver.exe"), 
	FIREFOX("firefox.exe", "geckodriver.exe"), 
	EDGE("edge.exe", "edgedriver.exe"), 
	IE("iexplore.exe", "IEDriverServer.exe");
	
	private String processoNavegador;
	private String processoDriver;
	
	private Navegador(String processoNavegador, String processoDriver) {
		this.processoNavegador = processoNavegador;
		this.processoDriver = processoDriver;
	}
	
	public String getProcessoNavegador() {
		return processoNavegador;
	}
	
	public String getProcessoDriver() {
		return processoDriver;
	}
	
}