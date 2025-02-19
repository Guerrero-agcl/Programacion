package org.eda2.practica03;

import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Camion.
 */
public class Camion {

	/** The objetos. */
	private List<Objeto> objetos;

	/** The p. */
	private int P;

	/** The tabla. */
	private double[][] tabla;

	/** The array. */
	private double[] array;

	/**
	 * Instantiates a new camion.
	 */
	public Camion() {
		this.objetos = new LinkedList<>();
		this.P = 0;
	}

	/**
	 * Instantiates a new camion.
	 *
	 * @param P the p
	 */
	public Camion(int P) {
		this();
		this.P = P;
	}

	/**
	 * Instantiates a new camion.
	 *
	 * @param beneficios the beneficios
	 * @param pesos      the pesos
	 * @param P          the p
	 */
	public Camion(double[] beneficios, int[] pesos, int P) {
		this(P);
		for (int i = 0; i < beneficios.length; i++) {
			Objeto obj = new Objeto("Objeto " + i, pesos[i], beneficios[i]);
			objetos.add(obj);
		}
	}

	/**
	 * Gets the objetos.
	 *
	 * @return the objetos
	 */
	public List<Objeto> getObjetos() {
		return objetos;
	}

	/**
	 * Sets the objetos.
	 *
	 * @param objects the new objetos
	 */
	public void setObjetos(List<Objeto> objects) {
		this.objetos = objects;
	}

	/**
	 * Gets the p.
	 *
	 * @return the p
	 */
	public int getP() {
		return P;
	}

	/**
	 * Sets the p.
	 *
	 * @param p the new p
	 */
	public void setP(int p) {
		P = p;
	}

	/**
	 * Adds the.
	 *
	 * @param obj the obj
	 */
	public void add(Objeto obj) {
		this.objetos.add(obj);
	}

	/**
	 * Removes the.
	 *
	 * @param obj the obj
	 */
	public void remove(Objeto obj) {
		this.objetos.remove(obj);
	}

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public int[] getPesos() {
		int[] p = new int[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = (int) objetos.get(i).getPeso();
		}
		return p;
	}

	/**
	 * Gets the beneficios.
	 *
	 * @return the beneficios
	 */
	public double[] getBeneficios() {
		double[] p = new double[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = objetos.get(i).getBeneficio();
		}
		return p;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public int getN() {
		return this.objetos.size();
	}

	/**
	 * Gets the tabla.
	 *
	 * @return the tabla
	 */
	public double[][] getTabla() {
		return tabla;
	}

	/**
	 * Gets the array.
	 *
	 * @return the array
	 */
	public double[] getArray() {
		return array;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CAMION (").append(this.P).append(")\n");
		for (Objeto objeto : objetos) {
			sb.append(objeto.toString()).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Camion recursive.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 1.
	public Resultado camionRecursive() {
		int n = getN();
		int P = getP();
		int[] pesos = getPesos();
		double[] beneficios = getBeneficios();
		double maxBeneficio = camionRecursive(n, P, pesos, beneficios);
		Resultado resultado = new Resultado();
		resultado.setBeneficioTotal(maxBeneficio);
		return resultado;
	}

	/**
	 * Camion recursive.
	 *
	 * @param n the n
	 * @param P the p
	 * @param p the p
	 * @param b the b
	 * @return the double
	 */
	public double camionRecursive(int n, int P, int[] p, double[] b) {
		if (n == 0 || P == 0)
			return 0;
		if (p[n - 1] > P) {
			return camionRecursive(n - 1, P, p, b);
		}
		return Math.max(camionRecursive(n - 1, P - p[n - 1], p, b) + b[n - 1], camionRecursive(n - 1, P, p, b));
	}
	// FIN EJERCICIO 1.

	/**
	 * Camion table.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 2.
	public Resultado camionTable() {
		int n = getN();
		int P = getP();
		int[] p = getPesos();
		double[] b = getBeneficios();
		tabla = new double[n + 1][P + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= P; j++) {
				tabla[i][j] = -1;
			}
		}

		double maxBeneficio = camionRec(n, P, p, b);
		Resultado resultado = new Resultado();
		resultado.setBeneficioTotal(maxBeneficio);
		return resultado;
	}

	/**
	 * Camion rec.
	 *
	 * @param n the n
	 * @param P the p
	 * @param p the p
	 * @param b the b
	 * @return the double
	 */
	public double camionRec(int n, int P, int[] p, double[] b) {
		if (n == 0 || P == 0)
			return 0;
		if (tabla[n][P] != -1)
			return tabla[n][P];
		if (p[n - 1] > P) {
			tabla[n][P] = camionRec(n - 1, P, p, b);
		} else {
			double conObjeto = camionRec(n - 1, P - p[n - 1], p, b) + b[n - 1];
			double sinObjeto = camionRec(n - 1, P, p, b);
			tabla[n][P] = Math.max(conObjeto, sinObjeto);
		}
		return tabla[n][P];
	}
	// FIN EJERCICIO 2.

	/**
	 * Camion DP.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 3.
	public Resultado camionDP() {
		int P = getP();
		int[] p = getPesos();
		double[] b = getBeneficios();
		int n = b.length;
		tabla = new double[n + 1][P + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < p[i - 1]; j++) {
				tabla[i][j] = tabla[i - 1][j];
			}
			for (int j = p[i - 1]; j <= P; j++) {
				double b1 = tabla[i - 1][j - p[i - 1]] + b[i - 1];
				double b2 = tabla[i - 1][j];
				tabla[i][j] = Math.max(b1, b2);
			}
		}

		double maxBeneficio = tabla[n][P];
		Resultado resultado = new Resultado();
		resultado.setBeneficioTotal(maxBeneficio);
		resultado.setPesoTotal(calcularPesoTotal(getItemSolRecursiva(), p));
		return resultado;
	}

	/**
	 * Gets the item sol recursiva.
	 *
	 * @return the item sol recursiva
	 */
	public int[] getItemSolRecursiva() {
		int n = this.getN();
		int P = this.getP();
		int[] p = this.getPesos();
		double[] b = this.getBeneficios();
		int[] sol = new int[n];
		test(n, P, p, b, sol);
		return sol;
	}

	/**
	 * Test.
	 *
	 * @param j   the j
	 * @param c   the c
	 * @param p   the p
	 * @param b   the b
	 * @param sol the sol
	 */
	public void test(int j, int c, int[] p, double[] b, int[] sol) {
		if (j > 0) {
			if (c < p[j - 1]) {
				test(j - 1, c, p, b, sol);
			} else {
				if (tabla[j - 1][c - p[j - 1]] + b[j - 1] > tabla[j - 1][c]) {
					test(j - 1, c - p[j - 1], p, b, sol);
					sol[j - 1] = 1;
				} else {
					test(j - 1, c, p, b, sol);
				}
			}
		}
	}

	/**
	 * Camion DP 2.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 5.
	public Resultado camionDP2() {
		int P = getP();
		int[] p = getPesos();
		double[] b = getBeneficios();
		int n = b.length;
		tabla = new double[n + 1][P + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= P; j++) {
				tabla[i][j] = tabla[i - 1][j];
				if (p[i - 1] <= j) {
					double b1 = tabla[i - 1][j - p[i - 1]] + b[i - 1];
					double b2 = tabla[i][j];
					tabla[i][j] = Math.max(b1, b2);
				}
			}
		}

		double maxBeneficio = tabla[n][P];
		Resultado resultado = new Resultado();
		resultado.setBeneficioTotal(maxBeneficio);
		int[] sol = getItemSolIterativa();
		resultado.setPesoTotal(calcularPesoTotal(sol, p));
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] == 1) {
				resultado.agregarObjeto(new Objeto("Objeto " + (i + 1), p[i], b[i]));
			}
		}
		return resultado;
	}

	/**
	 * Gets the item sol iterativa.
	 *
	 * @return the item sol iterativa
	 */
	public int[] getItemSolIterativa() {
		int[] sol = new int[getN()];
		int j = tabla[0].length - 1;
		for (int i = tabla.length - 1; i > 0; i--) {
			if (tabla[i][j] != tabla[i - 1][j]) {
				sol[i - 1] = 1;
				j -= getPesos()[i - 1];
			}
		}
		return sol;
	}
	// FIN EJERCICIO 5.

	/**
	 * Camion DP 3.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 6.
	public Resultado camionDP3() {
		int P = getP();
		int[] p = getPesos();
		double[] b = getBeneficios();
		double maxBeneficio = camionDP3(P, p, b);
		Resultado resultado = new Resultado();
		resultado.setBeneficioTotal(maxBeneficio);
		return resultado;
	}

	/**
	 * Camion DP 3.
	 *
	 * @param P the p
	 * @param p the p
	 * @param b the b
	 * @return the double
	 */
	public double camionDP3(int P, int[] p, double[] b) {
		int n = b.length;
		array = new double[P + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = P; j >= p[i - 1]; j--) {
				array[j] = Math.max(array[j], array[j - p[i - 1]] + b[i - 1]);
			}
		}
		return array[P];
	}
	// FIN EJERCICIO 6.

	/**
	 * Camion greedy.
	 *
	 * @return the resultado
	 */
	// EJERCICIO 9.
	public Resultado camionGreedy() {
		int pesoFinal = 0;
		boolean[] used = new boolean[getN()];
		Resultado resultado = new Resultado();
		resultado.setPesoTotal(0);
		resultado.setBeneficioTotal(0);

		while (pesoFinal < P) {
			double maxBeneficioCoste = 0;
			int item = -1;
			for (int i = 0; i < objetos.size(); i++) {
				if (!used[i]) {
					double rel = objetos.get(i).getBeneficio() / objetos.get(i).getPeso();
					if (rel > maxBeneficioCoste) {
						maxBeneficioCoste = rel;
						item = i;
					}
				}
			}
			if (item == -1)
				break;

			used[item] = true;
			if (pesoFinal + objetos.get(item).getPeso() <= P) {
				pesoFinal += objetos.get(item).getPeso();
				resultado.getObjetos().add(new Objeto(objetos.get(item).getNombre(), objetos.get(item).getPeso(),
						objetos.get(item).getBeneficio()));
				resultado.setPesoTotal(resultado.getPesoTotal() + objetos.get(item).getPeso());
				resultado.setBeneficioTotal(resultado.getBeneficioTotal() + objetos.get(item).getBeneficio());
			} else {
				break;
			}
		}

		return resultado;
	}
	// FIN EJERCICIO 9.

	/**
	 * Gets the solucion array.
	 *
	 * @return the solucion array
	 */
	public int[] getSolucionArray() {
		int n = tabla.length - 1;
		int[] sol = new int[n];
		int auxP = P;
		for (int i = n; i >= 2; i--) {
			if (tabla[i - 1][auxP] == tabla[i][auxP]) {
				sol[i - 1] = 0;
			} else {
				sol[i - 1] = 1;
				auxP -= objetos.get(i - 1).getPeso();
			}
		}
		if (tabla[1][auxP] != -1) {
			sol[0] = 1;
		} else {
			sol[0] = 0;
		}
		return sol;
	}

	/**
	 * Prints the tabla.
	 */
	public void printTabla() {
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[0].length; j++) {
				if (tabla[i][j] == -1.0) {
					System.out.printf("%-5s", "X");
				} else {
					System.out.printf("%-5.1f", tabla[i][j]);
				}
			}
			System.out.println();
		}
	}

	/**
	 * Prints the array.
	 */
	public void printArray() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * Prints the array solucion.
	 *
	 * @param sol the sol
	 */
	public void printArraySolucion(int[] sol) {
		System.out.println("Solucion: ");
		for (int i = 0; i < sol.length; i++) {
			System.out.print(sol[i]);
			if (i < sol.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * Prints the array solucion.
	 *
	 * @param sol the sol
	 */
	public void printArraySolucion(double[] sol) {
		System.out.println("Solucion: ");
		for (int i = 0; i < sol.length; i++) {
			System.out.print(sol[i]);
			if (i < sol.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * Prints the obj sol array.
	 *
	 * @param sol the sol
	 */
	public void printObjSolArray(int[] sol) {
		System.out.println("Capacidad del camion = " + P + "");
		double profit = 0;
		int weight = 0;
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q=" + sol[i] : ""));
				profit += objetos.get(i).getBeneficio() * sol[i];
				weight += objetos.get(i).getPeso() * sol[i];
			}
		}
		System.out.println("Beneficio Total = " + profit);
		System.out.println("Peso Total =  " + weight);
	}

	/**
	 * Prints the obj sol array.
	 *
	 * @param sol the sol
	 */
	public void printObjSolArray(double[] sol) {
		System.out.println("Capacidad de la mochila = " + P + "");
		double profit = 0;
		double weight = 0;
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q=" + sol[i] : ""));
				profit += objetos.get(i).getBeneficio() * sol[i];
				weight += objetos.get(i).getPeso() * sol[i];
			}
		}
		System.out.println("Beneficio Total = " + profit);
		System.out.println("Peso Total =  " + weight);
	}

	/**
	 * Prints the obj sol array.
	 *
	 * @param resultado the resultado
	 */
	public void printObjSolArray(Resultado resultado) {
		System.out.println("Capacidad del camion = " + P);
		double totalBeneficio = 0;
		double totalPeso = 0;

		for (Objeto obj : resultado.getObjetos()) {
			double beneficioParcial = obj.getBeneficio() * obj.getCantidad();
			double pesoParcial = obj.getPeso() * obj.getCantidad();
			totalBeneficio += beneficioParcial;
			totalPeso += pesoParcial;

			System.out.print(obj.getNombre() + " p=" + obj.getPeso());
			if (obj.getCantidad() != 1) {
				System.out.print(", q=" + obj.getCantidad());
			}
			System.out.println(" b=" + obj.getBeneficio());
		}

		System.out.println("Beneficio Total = " + totalBeneficio);
		System.out.println("Peso Total = " + totalPeso);
	}

	/**
	 * Calcular peso total.
	 *
	 * @param sol   the sol
	 * @param pesos the pesos
	 * @return the double
	 */
	private double calcularPesoTotal(int[] sol, int[] pesos) {
		double pesoTotal = 0;
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] == 1) {
				pesoTotal += pesos[i];
			}
		}
		return pesoTotal;
	}

}