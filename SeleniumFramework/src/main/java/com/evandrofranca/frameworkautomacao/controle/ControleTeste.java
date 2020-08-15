package com.evandrofranca.frameworkautomacao.controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.evandrofranca.frameworkautomacao.driver.DriverFactory;
import com.evandrofranca.frameworkautomacao.massa.LeitorMassaDadosJson;

public class ControleTeste {
	
	private static ControleTeste instancia;
	protected static String navegador;
	protected static String processoNavegador;
	protected static String processoDriver;
	private LeitorMassaDadosJson leitorMassaDados;
	
	public static ControleTeste recuperarInstancia() {
		if(instancia == null) {
			instancia = new ControleTeste();
		}
		return instancia;
	}
	
	public static void determinarNavegador(Navegador navegador) {
		switch (navegador) {
		case CHROME:
			ControleTeste.navegador = "chrome";
			ControleTeste.processoNavegador = "chrome.exe";
			ControleTeste.processoDriver = "chromedriver.exe";
			break;
			
		case FIREFOX:
			ControleTeste.navegador = "firefox";
			ControleTeste.processoNavegador = "firefox.exe";
			ControleTeste.processoDriver = "geckodriver.exe";
			break;
			
		case EDGE:
			ControleTeste.navegador = "edge";
			ControleTeste.processoNavegador = "edge.exe";
			ControleTeste.processoDriver = "edgedriver.exe";
			break;
			
		case IE:
			ControleTeste.navegador = "ie";
			ControleTeste.processoNavegador = "iexplore.exe";
			ControleTeste.processoDriver = "IEDriverServer.exe";
			break;
			
		default:
			System.err.println("Navegador inválido");
			break;
		}
	}
	
	public static void abrirBrowser(String url) {
		
		try {
			
			if(DriverFactory.getInstance().getDriver() == null) {
				encerrarProcesso(ControleTeste.processoNavegador);
				encerrarProcesso(ControleTeste.processoDriver);
			}
			
			switch (navegador) {
			case "chrome":
				DriverFactory.getInstance().setChromeDriver(url);
				break;
				
			case "firefox":
				DriverFactory.getInstance().setFirefoxDriver(url);
				break;
				
			case "edge":
				DriverFactory.getInstance().setEdgeDriver(url);
				break;
				
			case "ie":
				DriverFactory.getInstance().setInternetExplorerDriver(url);
				break;
				
			default:
				break;
			}
		} catch(IOException e) {
			
		} catch (NullPointerException e) {
			System.err.println("Não foi determinado um navegador.\nDetermine um utilizando o método determinarNavegador da classe ControleTeste");
			throw e;
		}
		
	}
	
	public static void fecharBrowser() {
		DriverFactory.getInstance().getDriver().quit();
	}
	
	private static void encerrarProcesso(String processo) throws IOException{
		 try {
	            String line;
	            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
	            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            while ((line = input.readLine()) != null) {
	                if (!line.trim().equals("")) {
	                    if (line.substring(1, line.indexOf("\"", 1)).equalsIgnoreCase(processo)) {
	                        Runtime.getRuntime().exec("taskkill /F /IM " + line.substring(1, line.indexOf("\"", 1)));
	                    }
	                }
	            }
	            input.close();
	        } catch (Exception err) {
	            err.printStackTrace();
	        }
	}
	
	public LeitorMassaDadosJson recuperarLeitorMassaDados() {
		if(leitorMassaDados == null) {
			leitorMassaDados = new LeitorMassaDadosJson();
		}
		
		return leitorMassaDados;
	}

}
