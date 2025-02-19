package org.eda2.practica04;

// TODO: Auto-generated Javadoc
/**
 * The Class RecursiveBTContLoading3.
 */
public class RecursiveBTContLoading3 {

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
	 * @param CMC   the cmc
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
		}

		rBacktracking03(0, pesoRestante);

		return voa;
	}

	/**
	 * R backtracking 03.
	 *
	 * @param nivel  the nivel actual
	 * @param pesoRestante the peso restante
	 */
	private static void rBacktracking03(int nivel, int pesoRestante) {
		if (pact + pesoRestante <= voa)
			return;
		if (nivel > n - 1) {
			if (pact > voa)
				voa = pact;
			for (int i = 0; i < n; i++) {
				soa[i] = s[i];
			}
			return;
		}
		if (pact + p[nivel] <= P) {
			pact += p[nivel];
			s[nivel] = 1;
			rBacktracking03(nivel + 1, pesoRestante - p[nivel]);
			pact -= p[nivel];
			s[nivel] = 0;
		}
		rBacktracking03(nivel + 1, pesoRestante - p[nivel]);
	}
}
