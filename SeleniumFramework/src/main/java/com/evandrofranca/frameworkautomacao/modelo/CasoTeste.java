package com.evandrofranca.frameworkautomacao.modelo;

import static org.junit.Assert.assertFalse;

import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.base.Strings;


public abstract class CasoTeste implements ICasoTeste{
	
	private static String caminhoCompletoClasseTeste = "";
	private static String nomeClasseTeste = "";
	
	public static String recuperarCaminhoClasseTeste() throws Exception {
		if(Strings.isNullOrEmpty(caminhoCompletoClasseTeste)) {
			recuperarCaminhoClasseTeste(Thread.currentThread().getStackTrace());
		}
		
		return caminhoCompletoClasseTeste;
	}
	
	public static String recuperarNomeClasseTeste() throws Exception {
		if(Strings.isNullOrEmpty(nomeClasseTeste)) {
			recuperarCaminhoClasseTeste(Thread.currentThread().getStackTrace());
		}
		
		return nomeClasseTeste;
	}

	private static void recuperarCaminhoClasseTeste(StackTraceElement[] pilha) throws Exception {
		StackTraceElement item;
		String nomeCaminhoClasseTeste = null;
		String strNomeClasseTeste = null;
		Pattern pattern = Pattern.compile("CT\\D*\\d+.JAVA");
		
		for(int i = 1; i < pilha.length; i++) {
			item = pilha[i];
			
			if (pattern.matcher(item.getFileName().toUpperCase()).matches()) {
				nomeCaminhoClasseTeste = item.getClassName();
				strNomeClasseTeste = item.getFileName();
				break;
			}
		}
		
		if (nomeCaminhoClasseTeste == null) {
			System.out.println("Erro ao localizar classe do caso de teste. Verifique se o nome está dentro do padrão CT + NUMEROS");
			throw new Exception();
		}
		
		nomeClasseTeste = strNomeClasseTeste;
		caminhoCompletoClasseTeste = nomeCaminhoClasseTeste;
	}
	
	@Override
	public abstract void executarCasoTeste() throws Exception;
	
	@Override
	@Test
	public void iniciarCasoTeste() {
		try {
			System.out.println("Iniciando execução do caso de teste");
			this.executarCasoTeste();
			System.out.println("Caso de teste finalizado com sucesso");
		}catch(Exception e) {
			System.err.println("Falha na execução do caso de teste");
			assertFalse(true);
		}
	}
}
