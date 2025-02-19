package org.eda2.practica04;

// TODO: Auto-generated Javadoc
/**
 * The Class IterativeBTContLoading.
 */
public class IterativeBTContLoading {
	
	/** The n. */
	static int n;
	
	/** The p. */
	static int[] p;
	
	/** The p. */
	static int P;
	
	/** The pact. */
	static int pact;
	
	/** The voa. */
	static int voa;
	
	/** The s. */
	static int[] s;
	
	/** The soa. */
	static int[] soa;

	/**
	 * Carga maxima.
	 *
	 * @param pesos the pesos
	 * @param CMC the cmc
	 * @return the int
	 */
	public static int cargaMaxima(int[] pesos, int CMC) {
		n = pesos.length;
		p = pesos;
		P = CMC;
		pact = 0;
		voa = 0;
		s = new int[n];
		soa = new int[n];

		int pesoRestante = 0;
		for (int i = 0; i < n; i++) {
			pesoRestante += p[i];
			s[i] = -1;
		}

		iBacktracking(0, pesoRestante);

		return voa;
	}

	/**
	 * I backtracking.
	 *
	 * @param nivelActual the nivel actual
	 * @param pesoRestante the peso restante
	 */
	// Página 49 diapos clase.
	private static void iBacktracking(int nivelActual, int pesoRestante) {
		do {

			// GENERAR.
			s[nivelActual]++;
			if (s[nivelActual] == 1) {
				pact += p[nivelActual];
			} else {
				pesoRestante -= p[nivelActual];
			}

			// SOLUCIÓN.
			if (nivelActual == n - 1 && pact <= P && pact > voa) {
				voa = pact;
				System.arraycopy(s, 0, soa, 0, n);
			}

			// CIRTERIO.
			if (nivelActual < n - 1 && pact <= P && pact + pesoRestante > voa) {
				nivelActual++;
			} else {

				// NO MAS HERMANOS.
				while (nivelActual > -1 && s[nivelActual] == 1) {
					// RETROCEDER.
					pact -= p[nivelActual];
					s[nivelActual] = -1;
					pesoRestante += p[nivelActual];
					nivelActual--;
				}

			}

		} while (nivelActual > -1);
	}
}