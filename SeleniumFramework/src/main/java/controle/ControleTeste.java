package controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import driver.DriverFactory;
import massa.LeitorMassaDadosJson;

public class ControleTeste {
	
	private static ControleTeste instancia;
	protected String navegador;
	private LeitorMassaDadosJson leitorMassaDados;
	
	public static ControleTeste recuperarInstancia() {
		if(instancia == null) {
			instancia = new ControleTeste();
		}
		return instancia;
	}
	
	public void determinarNavegador(Navegador navegador) {
		switch (navegador) {
		case CHROME:
			System.setProperty(navegador.getDriver(), navegador.getLocal());
			this.navegador = "chrome";
			break;

		default:
			break;
		}
	}
	
	public void abrirBrowser(String url) {
		if(navegador.equals("chrome")) {
			try {
				encerrarProcesso("chrome.exe");
				encerrarProcesso("chromedriver.exe");
			} catch (IOException e) {}
			DriverFactory.getInstance().setChromeDriver(url);
		}
	}
	
	public void fecharBrowser() {
		DriverFactory.getInstance().getDriver().quit();
	}
	
	private void encerrarProcesso(String processo) throws IOException{
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
