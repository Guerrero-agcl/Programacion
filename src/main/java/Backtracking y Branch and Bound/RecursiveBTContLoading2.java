package org.eda2.practica04;

// TODO: Auto-generated Javadoc
/**
 * The Class RecursiveBTContLoading2.
 */
public class RecursiveBTContLoading2 {

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

	/**
	 * Carga maxima.
	 *
	 * @param pesos the pesos
	 * @param CMC   the cmc
	 * @return the int
	 */
	public static int cargaMaxima(int[] pesos, int CMC) {
		n = pesos.length;
		p = pesos;
		P = CMC;
		pact = 0;
		voa = 0;

		int pesoRestante = 0;
		for (int i = 0; i < n; i++) {
			pesoRestante += p[i];
		}

		rBacktracking02(0, pesoRestante);

		return voa;
	}

	/**
	 * R backtracking 02.
	 *
	 * @param nivel  the nivel actual
	 * @param pesoRestante the peso restante
	 */
	private static void rBacktracking02(int nivel, int pesoRestante) {
		if (pact + pesoRestante <= voa)
			return;
		if (nivel > n - 1) {
			if (pact > voa)
				voa = pact;
			return;
		}
		if (pact + p[nivel] <= P) {
			pact += p[nivel];
			rBacktracking02(nivel + 1, pesoRestante - p[nivel]);
			pact -= p[nivel];
		}
		rBacktracking02(nivel + 1, pesoRestante - p[nivel]);
	}
}
