package com.evandrofranca.frameworkautomacao.utils;

public class XPath {

	public static String montar(String tipo, String atributo, String valor, int index) {
		String xpath = "";
		switch (atributo.toLowerCase()) {
		case "text":
			xpath = "//" + tipo + "[" + atributo + "()='" + valor + "']";
			break;
		case "textcontents":
			xpath = "//" + tipo + "[contains(text(), '" + valor + "')]";
			break;
		default:
			xpath = "//" + tipo + "[@" + atributo + "='" + valor + "']";
			break;
		}

		if (index == 0) {
			return xpath;
		} else {
			return "(" + xpath + ")[" + index + "]";
		}
	}

}
