package br.com.qintess.projetoEventosAPI.util;

import java.io.UnsupportedEncodingException;

public class ConversorImagem {

	public static String to64(byte[] imagem) throws UnsupportedEncodingException {
		return new String(imagem, "UTF-8");
	}
}