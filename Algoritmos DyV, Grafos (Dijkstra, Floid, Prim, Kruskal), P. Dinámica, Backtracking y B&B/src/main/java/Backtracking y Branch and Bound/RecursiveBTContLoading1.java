package org.eda2.practica04;

// TODO: Auto-generated Javadoc
/**
 * The Class RecursiveBTContLoading1.
 */
public class RecursiveBTContLoading1 {
	
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
	 * @param CMC the cmc
	 * @return the int
	 */
	public static int cargaMaxima(int[] pesos, int CMC) {
		n = pesos.length;
		p = pesos;
		P = CMC;
		pact = 0;
		voa = 0;

		rBacktracking01(0);

		return voa;
	}

	/**
	 * R backtracking 01.
	 *
	 * @param nivel the nivel actual
	 */
	private static void rBacktracking01(int nivel) {
		if (nivel > n - 1) {
			if (pact > voa)
				voa = pact;
			return;
		}
		if (pact + p[nivel] <= P) {
			pact += p[nivel];
			rBacktracking01(nivel + 1);
			pact -= p[nivel];
		}
		rBacktracking01(nivel + 1);
	}
}