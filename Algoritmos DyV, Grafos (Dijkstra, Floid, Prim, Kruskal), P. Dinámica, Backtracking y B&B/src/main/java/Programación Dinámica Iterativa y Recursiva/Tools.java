package org.eda2.practica03;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

	/**
	 * Carga aleatoria.
	 *
	 * @return the camion
	 */
	public static Camion cargaAleatoria() {
		int n = Tools.leerOpcionInt("Introduzca el número de objetos: ", 1, Integer.MAX_VALUE);
		int P = Tools.leerOpcionInt("Introduzca el peso maximo autorizado del camion: ", 0, Integer.MAX_VALUE);
		Camion ks = new Camion(P);
		for (int i = 0; i < n; i++) {
			Objeto obj = new Objeto("Objeto " + (i + 1), (int) (Math.random() * P), (int) (Math.random() * 100));
			ks.add(obj);
		}
		return ks;
	}

	/**
	 * Carga manual.
	 *
	 * @return the camion
	 */
	public static Camion cargaManual() {
		int n = leerOpcionInt("Introduzca el número de objetos: ", 1, Integer.MAX_VALUE);
		int P = leerOpcionInt("Introduzca el peso maximo autorizado del camion: ", 0, Integer.MAX_VALUE);
		Camion camion = new Camion(P);
		for (int i = 0; i < n; i++) {
			String nombre = leerOpcionString("Introduzca el nombre del objeto " + (i + 1) + ": ");
			double peso = leerOpcionInt("Introduzca el peso entero del objeto " + (i + 1) + ": ", 0, Integer.MAX_VALUE);
			double beneficio = leerOpcionDouble("Introduzca el beneficio del objeto " + (i + 1) + ": ", 0,
					Double.MAX_VALUE);
			camion.add(new Objeto(nombre, peso, beneficio));
		}
		return camion;
	}

	/**
	 * Leer opcion int.
	 *
	 * @param mensaje the mensaje
	 * @param min     the min
	 * @param max     the max
	 * @return the int
	 */
	public static int leerOpcionInt(String mensaje, int min, int max) {
		do {
			System.out.print(mensaje);
			try {
				int opcion = Integer.parseInt(LlenadoCamionMain.sc.nextLine());
				if (opcion >= min && opcion <= max) {
					return opcion;
				} else {
					System.err.println("\nError: Debe introducir una opcion entre " + min + " y " + max);
				}
			} catch (NumberFormatException e) {
				System.err.println("\nError: Debe introducir valor numerico");
			}
		} while (true);
	}

	/**
	 * Leer opcion double.
	 *
	 * @param mensaje the mensaje
	 * @param min     the min
	 * @param max     the max
	 * @return the double
	 */
	public static double leerOpcionDouble(String mensaje, double min, double max) {
		do {
			System.out.print(mensaje);
			try {
				double opcion = Double.parseDouble(LlenadoCamionMain.sc.nextLine());
				if (opcion >= min && opcion <= max) {
					return opcion;
				} else {
					System.err.println("\nError: Debe introducir una opcion entre " + min + " y " + max);
				}
			} catch (NumberFormatException e) {
				System.err.println("\nError: Debe introducir valor numerico");
			}
		} while (true);
	}

	/**
	 * Leer opcion string.
	 *
	 * @param mensaje the mensaje
	 * @return the string
	 */
	public static String leerOpcionString(String mensaje) {
		do {
			System.out.print(mensaje);
			String opcion = LlenadoCamionMain.sc.nextLine();
			if (!opcion.trim().isEmpty()) {
				return opcion.trim();
			} else {
				System.err.println("\nError: Debe introducir un texto válido");
			}
		} while (true);
	}

	/**
	 * Test 01 N constante P variable.
	 */
	public static void test01NConstantePVariable() {
		long inicio = 0;
		long fin = 0;
		Camion test;
		int n = 10;
		System.out.println("Rendimiento para camion de " + n + " objetos");
		System.out.println("P\tcRec\tcTable\tDP\tDP2\tDP3\tGreedy");
		for (int P = 1; P < 1000; P++) {
			test = new Camion(P);
			for (int i = 0; i < n; i++) {
				Objeto obj = new Objeto("Objeto " + (i + 1), (int) (Math.random() * P), (int) (Math.random() * 100));
				test.add(obj);
			}
			System.out.print(P + "\t");
			inicio = System.nanoTime();
			test.camionRecursive();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionTable();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP2();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP3();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionGreedy();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\n");

		}

	}

	/**
	 * Test 02 N variable P constante.
	 */
	public static void test02NVariablePConstante() {
		long inicio = 0;
		long fin = 0;
		Camion test;
		int P = 10;
		System.out.println("Rendimiento para camion de capacidad " + P);
		System.out.println("N\tcRec\tcTable\tDP\tDP2\tDP3\tGreedy");
		for (int n = 1; n < 10; n++) {
			test = new Camion(P);
			for (int i = 0; i < n; i++) {
				Objeto obj = new Objeto("Objeto " + (i + 1), (int) (Math.random() * P), (int) (Math.random() * 100));
				test.add(obj);
			}
			System.out.print(n + "\t");
			inicio = System.nanoTime();
			test.camionRecursive();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionTable();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP2();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionDP3();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\t");

			inicio = System.nanoTime();
			test.camionGreedy();
			fin = System.nanoTime();
			System.out.print((fin - inicio) + "\n");

		}

	}

}