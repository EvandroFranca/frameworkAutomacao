package com.evandrofranca.frameworkautomacao.utils;

public class XPath {
	
	public static String montar(String tipo, String atributo, String valor) {
		return "//"+tipo+"[@"+atributo+"='"+valor+"']";
	}
	
	public static String montar(String tipo, String atributo, String valor, int index) {
		return "(//"+tipo+"[@"+atributo+"='"+valor+"'])["+index+"]";
	}

}
