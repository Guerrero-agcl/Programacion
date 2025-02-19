package org.eda2.practica04;

import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class GreedyContLoading.
 */
public class GreedyContLoading {
	
	/** The n. */
	static int n;
	
	/** The p. */
	static int[] p;
	
	/** The p. */
	static int P;
	
	/** The peso actual. */
	static int pact;
	
	/** valor óptimo actual. */
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

		Greedy();

		return voa;
	}

	/**
	 * Greedy.
	 *
	 * @return the int
	 */
	private static int Greedy() {
		Arrays.sort(p);
		for (int i = 0; i < n; i++) {
			if (pact + p[i] <= P) {
				pact += p[i];
				continue;
			}
			break;

		}
		voa = pact;
		return voa;
	}
}
