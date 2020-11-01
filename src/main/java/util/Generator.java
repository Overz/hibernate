package util;

import java.util.Random;

/**
 * @author Christian Lima
 * <p>
 * Classe Útil para auxiliar na geração de valores.
 * https://www.devmedia.com.br/introducao-a-seguranca-da-informacao-em-java/28247
 */
public class Generator {
	public static final int PHONE = 1;
	public static final int CPF = 2;
	public static final int RG = 3;
	public static final int CNPJ = 4;
	public static final int INSCRICAO = 5;
	public static final int CARTAO = 6;
	public static final int EMAIL = 7;
	public static final int NOME = 8;
	public static final int ANO = 9;
	private static final int MINIMUM = 15;
	private static final int MEDIUM = 30;
	private static final int MAXIMUM = 55;
	private static final String REGEX = "\\D";

	/**
	 * Método Util para gerar characteres aleatórios.
	 * Obs: Método gera automaticamente valores abaixo de 10 caso o parametro Int não seja informado.
	 *
	 * @param value String - Valor a ser substituido pelo autogerado.
	 * @param i     Integer - Length a ser substituido pelo padrão atual(10).
	 * @return String com caracteres aleatórios.
	 */
	private static String generateChars(String value, Integer i) {
		if (value == null || value.isEmpty()) {
			value = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		}

		if (i == null || i <= 0) {
			i = MINIMUM;
		}

		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		while (builder.length() < i) { // length of the random string.
			int j = (int) (random.nextFloat() * value.length());
			builder.append(value.charAt(j));
		}
		return builder.toString().toUpperCase();
	}

	/**
	 * Gera nome FAKE, simulando espaços entre os caracteres.
	 *
	 * @param i Integer - Length a ser substituido pelo padrão atual(10).
	 * @return String com caracteres aleatórios.
	 */
	private static String generateName(Integer i) {
		StringBuilder res = new StringBuilder();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if (i == null || i <= 10) {
			i = MEDIUM;
		}
		for (int j = 0; j < 2; j++) {
			res.append(generateChars(chars, i / 3));
			res.append(' ');
		}
		return res.toString().toUpperCase();
	}

	/**
	 * Gera um FAKE email padrão @email.com
	 *
	 * @param i Integer - Length a ser substituido pelo padrão atual(10).
	 * @return String com padrão AlgumaCoisa@email.com
	 */
	private static String generateEmail(Integer i) {
		return generateChars(null, i).concat("@email.com").toUpperCase();
	}

	/**
	 * Gera um FAKE Cpf, de 11 posições.
	 *
	 * @return String CPF sem formatação
	 */
	private static String generateNumber(int lenght) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 11; i++) {
			Integer n = (int) (Math.random() * lenght);
			builder.append(n);
		}
		return builder.toString().toUpperCase();
	}

	public static String generate(int type, Integer... length) {
		switch (type) {
			case PHONE:
				return "(48)".concat(generateNumber(9));
			case CPF:
				return generateNumber(11);
			case RG:
				return generateNumber(8);
			case CNPJ:
				return generateNumber(14);
			case INSCRICAO:
				return generateNumber(9);
			case CARTAO:
				return generateNumber(16);
			case EMAIL:
				int e = length.length > 0 && length[0] != null ? length[0] : 5;
				return generateEmail(e);
			case NOME:
				int n = length.length > 0 && length[0] != null ? length[0] : 10;
				return generateName(n);
			case ANO:
				return generateNumber(4);
			default:
				throw new Error("invalid lenght number!");
		}
	}
}
