import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Proyecto3 {
	/**
	 * Stack with the order in which a character is removed.
	 */
	private static Stack<Character> pila;

	/**
	 * "pila" reversed
	 */
	private static Queue<Character> cola;

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
		String currentLine = br.readLine();

		// Por cada caso.
		while (0 < casosInteger) {

			pila = new Stack<Character>();
			repeticiones = new HashMap<>();
			repeticionesOrginal = new HashMap<>();
			cola = new LinkedList<>();

			calcularRepeticionesYOrden(currentLine);

			repeticionesCadenaOriginal(cola);

			String original = cadenaOriginal(repeticionesOrginal, currentLine);
			if (noExiste(original, currentLine, pila.peek()))
				System.out.println("NO EXISTE");
			else {
				System.out.print(original);
				System.out.print(" ");

				for (int i = pila.size(); i > 0; i--) {
					System.out.print(pila.pop());
				}
				System.out.println("");
			}
			currentLine = br.readLine();
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
	private static String cadenaOriginal(HashMap<Character, Integer> h, String ingreso) {
		String originalString = "";
		HashMap<Character, Integer> hashPorLlenar = new HashMap<>();
		int indice = 0;

		/**
		 * Mientras no se tenga la misma cantidad de repeticiones por char
		 */
		while (!hashPorLlenar.equals(h) && indice < ingreso.length()) {
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
	private static boolean noExiste(String original, String encriptada, char letra) {
		String sinLetra = original + original.replaceAll(Character.toString(letra), "");

		return !encriptada.regionMatches(0, sinLetra, 0, sinLetra.length());
	}

	/**
	 * Finds how many times all unique chars appear in the original string
	 * 
	 * @param q
	 */
	private static void repeticionesCadenaOriginal(Queue<Character> q) {
		int sizeCola = q.size();

		while (sizeCola > 0) {
			char caracter = q.remove();

			repeticionesOrginal.put(caracter, repeticiones.get(caracter) / sizeCola);

			sizeCola--;
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
				pila.add(currentChar);
				cola.add(currentChar);
				repeticiones.put(currentChar, 1);
			} else {
				int numRepeticionesCaracter = repeticiones.get(currentChar);
				repeticiones.replace(currentChar, numRepeticionesCaracter + 1);
			}
		}
	}
}
