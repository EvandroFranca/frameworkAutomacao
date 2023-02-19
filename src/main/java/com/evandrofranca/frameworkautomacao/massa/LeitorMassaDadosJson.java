package com.evandrofranca.frameworkautomacao.massa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.evandrofranca.frameworkautomacao.modelo.CasoTeste;
import com.google.common.base.Strings;

public class LeitorMassaDadosJson {
	
	private JSONObject arquivoLido;
	private String nomeCasoTeste = "";
	private String caminhoCasoTeste = "";
	private boolean arquivoCarregado = false;
	
	/**
	 * Construtor Padrão da classe
	 */
	public LeitorMassaDadosJson() {
	}
	
	/**
	 * Construtor que recebe o nome do caso de teste
	 * @param nomeCasoTeste
	 */
	public LeitorMassaDadosJson(String nomeCasoTeste) {
		this.nomeCasoTeste = nomeCasoTeste;
	}
	
	/**
	 * Efetua o carregamento do arquivo json (referente ao caso de teste) em memória
	 * @throws Exception
	 */
	public void carregarArquivo() throws Exception {
		if(arquivoCarregado) {
			return;
		}
		
		if(Strings.isNullOrEmpty(nomeCasoTeste)) {
			nomeCasoTeste = CasoTeste.recuperarNomeClasseTeste();
		}
		
		caminhoCasoTeste = CasoTeste.recuperarCaminhoClasseTeste();
		
		try {
			BufferedReader arquivoJsonMemoria = new BufferedReader(new InputStreamReader(buscarArquivoMassa(caminhoCasoTeste, nomeCasoTeste.replace(".java", "")), "UTF-8"));
			
			String linhaArquivo = "";
			StringBuilder sb = new StringBuilder();
			
			while((linhaArquivo = arquivoJsonMemoria.readLine()) != null) {
				sb.append(linhaArquivo);
			}
			
			arquivoLido =  new JSONObject(sb.toString().trim());
			
			arquivoJsonMemoria.close();
			
			this.arquivoCarregado = true;
			
		}catch(Exception e) {
			System.err.println("Erro ao carregar arquivo JSON\n" + e.getMessage());
		}
		
	}
	
	public <T> T lerCampo(Class<T> tipo, String nomeCampo) throws Exception {
		return this.lerCampo(tipo, nomeCampo, true);
	}

	public <T> T lerCampo(Class<T> tipo, String nomeCampo, boolean lancarException) throws Exception {
		try {
			return tipo.cast(arquivoLido.get(nomeCampo));
			//return (T) arquivoLido.get(nomeCampo);
		}catch (Exception e) {
			if(lancarException) {
				System.err.println("Erro ao dar localizar chave no JSON ou ao tentar dar cast para o tipo informado");
				throw e;
			}
		}
		return null;
	}

	private InputStream buscarArquivoMassa(String nomeCompletoCasoTeste, String nomeArquivo) throws Exception {
		try {
			String path;
			ClassLoader classLoader = this.getClass().getClassLoader();
			path = obterCaminhoPacote(nomeCompletoCasoTeste, "massadados");
			InputStream file = classLoader.loadClass(nomeCompletoCasoTeste)
					.getResourceAsStream("/" + path + nomeArquivo + ".json");
			return file;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String obterCaminhoPacote(String nomeCompletoCasoTeste, String pacoteMassaDeDados) {
		try {
			String path = nomeCompletoCasoTeste.replaceAll("\\.", "/").replaceAll("/ct/",
					"/" + pacoteMassaDeDados + "/");
			int index = path.lastIndexOf("/");
			if (index != -1) {
				path = path.substring(0, index + 1);
			}
			return path;
		} catch (Exception e) {
			System.err.println("Erro ao retornar caminho da massa de dados ...");
			System.err.println("[Verifique se o CT está na mesma estrutura da massa]");
			return null;
		}

	}
}
