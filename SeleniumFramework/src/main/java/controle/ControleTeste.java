package controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.base.CaseFormat;

import driver.DriverFactory;
import massa.LeitorMassaDadosJson;

public class ControleTeste {
	
	private static ControleTeste instancia;
	protected static String navegador;
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
			break;
			
		case FIREFOX:
			ControleTeste.navegador = "firefox";
			break;
			
		case EDGE:
			ControleTeste.navegador = "edge";
			break;
			
		case IE:
			ControleTeste.navegador = "ie";
			break;
			
		default:
			System.err.println("Navegador inválido");
			break;
		}
	}
	
	public static void abrirBrowser(String url) {
		
		try {
			switch (navegador) {
			case "chrome":
				encerrarProcesso("chrome.exe");
				encerrarProcesso("chromedriver.exe");
				
				DriverFactory.getInstance().setChromeDriver(url);
				break;
				
			case "firefox":
				encerrarProcesso("firefox.exe");
				encerrarProcesso("geckodriver.exe");
				
				DriverFactory.getInstance().setFirefoxDriver(url);
				break;
				
			case "edge":
				//TODO adicionar encerramento do processos do BROWSER e driver
				
				DriverFactory.getInstance().setEdgeDriver(url);
				break;
				
			case "ie":
				encerrarProcesso("iexplore.exe");
				encerrarProcesso("IEDriverServer.exe");
				
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
		
		
		/*
		 * if(navegador.equals("chrome")) { try { encerrarProcesso("chrome.exe");
		 * encerrarProcesso("chromedriver.exe"); } catch (IOException e) {}
		 * DriverFactory.getInstance().setChromeDriver(url); }
		 */
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
