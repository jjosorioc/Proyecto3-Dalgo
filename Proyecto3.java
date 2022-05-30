import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Proyecto3 {

	private static LinkedList<Character> listaEncadenada;

	/**
	 * Repetitions per unique char in the encrypted string
	 */
	private static HashMap<Character, Integer> repeticiones;

	/**
	 * Repetitions per unique char in the original string
	 */
	private static HashMap<Character, Integer> repeticionesOrginal;

	public static void main(String[] args) throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);

		int casosInteger = Integer.parseInt(br.readLine());

		// Por cada caso.
		while (0 < casosInteger) {
			String currentLine = br.readLine();
			repeticiones = new HashMap<>();
			repeticionesOrginal = new HashMap<>();
			listaEncadenada = new LinkedList<>();

			calcularRepeticionesYOrden(currentLine);

			repeticionesCadenaOriginal();
			String original = cadenaOriginal(currentLine);
			if (!existe(original, currentLine, listaEncadenada.peekLast()))
				System.out.println("NO EXISTE");
			else {
				System.out.print(original);
				System.out.print(" ");

				for (int i = listaEncadenada.size(); i > 0; i--) {
					System.out.print(listaEncadenada.removeLast());
				}
				System.out.println("");
			}
			casosInteger--;

		}

	}

	/**
	 * Finds the original string
	 * 
	 * @param h       Reptitions per Character in the original string
	 * @param ingreso First removed char
	 * @return
	 */
	private static String cadenaOriginal(String ingreso) {
		String originalString = "";
		HashMap<Character, Integer> hashPorLlenar = new HashMap<>();
		int indice = 0;

		/**
		 * Mientras no se tenga la misma cantidad de repeticiones por char
		 */
		while (!hashPorLlenar.equals(repeticionesOrginal) && indice < ingreso.length()) {
			char currentChar = ingreso.charAt(indice);
			if (!hashPorLlenar.containsKey(currentChar)) {
				hashPorLlenar.put(currentChar, 1);
			} else {
				int numRepeticiones = hashPorLlenar.get(currentChar);

				hashPorLlenar.replace(currentChar, numRepeticiones + 1);
			}

			originalString = originalString + currentChar;

			indice++;
		}

		return originalString;
	}

	/**
	 * Checks if the original string doesn't exist.
	 * 
	 * @param original
	 * @param encriptada
	 * @param letra
	 * @return
	 */
	private static boolean existe(String original, String encriptada, char letra) {
		/**
		 * sinLetra is the encrypted string the string based on the order in which its
		 * characters where removed
		 */
		StringBuilder sinLetra = new StringBuilder(encriptada.length());

		sinLetra.append(original);

		for (int i = listaEncadenada.size() - 1; i >= 0; i--) {

			original = original.replaceAll(Character.toString(listaEncadenada.get(i)), "");
			sinLetra.append(original);
		}
		return encriptada.equals(sinLetra.toString());
	}

	/**
	 * Finds how many times all unique chars appear in the original string
	 * 
	 * @param q
	 */
	private static void repeticionesCadenaOriginal() {
		int indice = 0;

		while (listaEncadenada.size() > indice) {
			char caracter = listaEncadenada.get(indice);

			repeticionesOrginal.put(caracter, repeticiones.get(caracter) / (listaEncadenada.size() - indice));

			indice++;
		}
	}

	/**
	 * Finds the amount of times all unique chars repeat in the encrypted string
	 * 
	 * @param cadena
	 */
	private static void calcularRepeticionesYOrden(String cadena) {
		for (int i = cadena.length() - 1; i >= 0; i--) {
			char currentChar = cadena.charAt(i);
			if (!repeticiones.containsKey(currentChar)) {

				listaEncadenada.add(currentChar);
				repeticiones.put(currentChar, 1);
			} else {
				int numRepeticionesCaracter = repeticiones.get(currentChar);
				repeticiones.replace(currentChar, numRepeticionesCaracter + 1);
			}
		}
	}

}
