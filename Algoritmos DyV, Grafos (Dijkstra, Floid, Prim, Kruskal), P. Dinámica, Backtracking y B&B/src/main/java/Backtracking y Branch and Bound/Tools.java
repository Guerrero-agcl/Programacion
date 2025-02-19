package org.eda2.practica04;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

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
				int opcion = Integer.parseInt(CargaMaritimaMain.entrada.nextLine());
				if (opcion >= min && opcion <= max) {
					return opcion;
				} else {
					System.err.println("\nError: Introduzca una opción entre " + min + " y " + max + ".");
				}
			} catch (NumberFormatException e) {
				System.err.println("\nError: Introduzca un valor numérico entero.");
			}
		} while (true);
	}

	/**
	 * Test 01 cmc vpesos C.
	 */
	public static void test01cmcVpesosC() {
		System.out.println("Cargas y pesos constantes, CMC variable.");
		System.out.println("CMC\trBacktracking01\trBacktracking02\trBacktracking03\tiBacktracking\tGreedy");

		// Definir los pesos una vez
		int[] pesos = { 200, 300, 150, 250, 100 };

		// Realizar las pruebas para diferentes valores de CMC
		for (int CMC = 1000; CMC <= 1000000; CMC += 1000) {
			long sumaTiemposRB01 = 0;
			long sumaTiemposRB02 = 0;
			long sumaTiemposRB03 = 0;
			long sumaTiemposIB = 0;
			long sumaTiemposGreedy = 0;

			// Realizar cada algoritmo 10 veces para obtener un promedio
			for (int i = 0; i < 10; i++) {
				long start, end;

				// rBacktracking01
				start = System.nanoTime();
				RecursiveBTContLoading1.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB01 += (end - start);

				// rBacktracking02
				start = System.nanoTime();
				RecursiveBTContLoading2.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB02 += (end - start);

				// rBacktracking03
				start = System.nanoTime();
				RecursiveBTContLoading3.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB03 += (end - start);

				// iBacktracking
				start = System.nanoTime();
				IterativeBTContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposIB += (end - start);

				// Greedy
				start = System.nanoTime();
				GreedyContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposGreedy += (end - start);
			}

			// Calcular el tiempo promedio para cada algoritmo
			long mediaRB01 = sumaTiemposRB01 / 10;
			long mediaRB02 = sumaTiemposRB02 / 10;
			long mediaRB03 = sumaTiemposRB03 / 10;
			long mediaIB = sumaTiemposIB / 10;
			long mediaGreedy = sumaTiemposGreedy / 10;

			// Imprimir los resultados para este valor de CMC
			System.out.println(CMC + "\t" + mediaRB01 + "\t\t" + mediaRB02 + "\t\t" + mediaRB03 + "\t\t" + mediaIB
					+ "\t\t" + mediaGreedy);
		}
	}

	/**
	 * Test 02 cmc ccarga V.
	 */
	// CMC y pesos fijos, carga variable
	public static void test02cmcCcargaV() {
		int CMC = 100;

		System.out.println("El CMC es: " + CMC + " y los pesos son: 1");
		System.out.println("NºCargas\trBacktracking01\trBacktracking02\trBacktracking03\tiBacktracking\tGreedy");

		// Realizar las pruebas para diferentes cantidades de pesos
		for (int carga = 1; carga <= 30; carga++) {
			// Todos los elementos de valor 1.
			int[] pesos = new int[carga];
			for (int i = 0; i < carga; i++) {
				pesos[i] = 1;
			}

			long sumaTiemposRB01 = 0;
			long sumaTiemposRB02 = 0;
			long sumaTiemposRB03 = 0;
			long sumaTiemposIB = 0;
			long sumaTiemposGreedy = 0;

			// Realizar cada algoritmo 10 veces para obtener un promedio
			for (int i = 0; i < 10; i++) {
				long start, end;

				// rBacktracking01
				start = System.nanoTime();
				RecursiveBTContLoading1.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB01 += (end - start);

				// rBacktracking02
				start = System.nanoTime();
				RecursiveBTContLoading2.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB02 += (end - start);

				// rBacktracking03
				start = System.nanoTime();
				RecursiveBTContLoading3.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB03 += (end - start);

				// iBacktracking
				start = System.nanoTime();
				IterativeBTContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposIB += (end - start);

				// Greedy
				start = System.nanoTime();
				GreedyContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposGreedy += (end - start);
			}

			// Calcular el tiempo promedio para cada algoritmo
			long mediaRB01 = sumaTiemposRB01 / 10;
			long mediaRB02 = sumaTiemposRB02 / 10;
			long mediaRB03 = sumaTiemposRB03 / 10;
			long mediaIB = sumaTiemposIB / 10;
			long mediaGreedy = sumaTiemposGreedy / 10;

			// Imprimir los resultados para esta cantidad de pesos
			System.out.println(carga + "\t\t" + mediaRB01 + "\t\t" + mediaRB02 + "\t\t" + mediaRB03 + "\t\t" + mediaIB
					+ "\t\t" + mediaGreedy);
		}
	}

	/**
	 * Test 03 cmc cpesos vcreciente.
	 */
	// CMC es fijo, la carga tambien y varia lo que pesa cada elemento de la carga.
	public static void test03cmcCpesosVcreciente() {
		int CMC = 1000;
		int carga = 10;

		System.out.println("El CMC es: " + CMC + " y la carga: " + carga);
		System.out.println("Peso\trBacktracking01\trBacktracking02\trBacktracking03\tiBacktracking\tGreedy");

		// Iterar sobre cada valor de peso desde 1 hasta 100
		for (int peso = 1; peso <= 1000; peso++) {
			int[] pesos = new int[carga];
			// Inicializar todos los pesos a 'peso'
			for (int j = 0; j < carga; j++) {
				pesos[j] = peso;
			}

			long sumaTiemposRB01 = 0;
			long sumaTiemposRB02 = 0;
			long sumaTiemposRB03 = 0;
			long sumaTiemposIB = 0;
			long sumaTiemposGreedy = 0;

			// Realizar cada algoritmo 10 veces para obtener un promedio
			for (int i = 0; i < 10; i++) {
				long start, end;

				// rBacktracking01
				start = System.nanoTime();
				RecursiveBTContLoading1.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB01 += (end - start);

				// rBacktracking02
				start = System.nanoTime();
				RecursiveBTContLoading2.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB02 += (end - start);

				// rBacktracking03
				start = System.nanoTime();
				RecursiveBTContLoading3.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB03 += (end - start);

				// iBacktracking
				start = System.nanoTime();
				IterativeBTContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposIB += (end - start);

				// Greedy
				start = System.nanoTime();
				GreedyContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposGreedy += (end - start);
			}

			// Calcular el tiempo promedio para cada algoritmo
			long mediaRB01 = sumaTiemposRB01 / 10;
			long mediaRB02 = sumaTiemposRB02 / 10;
			long mediaRB03 = sumaTiemposRB03 / 10;
			long mediaIB = sumaTiemposIB / 10;
			long mediaGreedy = sumaTiemposGreedy / 10;

			// Imprimir los resultados para este valor de peso
			System.out.println(peso + "\t" + mediaRB01 + "\t\t" + mediaRB02 + "\t\t" + mediaRB03 + "\t\t" + mediaIB
					+ "\t\t" + mediaGreedy);
		}
	}

	/**
	 * Test 04 cmc cpesos vdecreciente.
	 */
	public static void test04cmcCpesosVdecreciente() {
		int CMC = 1000; // Capacidad máxima de carga fija
		int carga = 10; // Cantidad fija de elementos

		System.out.println("El CMC es: " + CMC + " y la carga: " + carga);
		System.out.println("Peso\trBacktracking01\trBacktracking02\trBacktracking03\tiBacktracking\tGreedy");

		// Iterar sobre cada valor de peso desde 1000 hasta 1
		for (int peso = 1000; peso >= 1; peso--) {
			int[] pesos = new int[carga];
			// Inicializar todos los pesos a 'peso'
			for (int j = 0; j < carga; j++) {
				pesos[j] = peso;
			}

			long sumaTiemposRB01 = 0;
			long sumaTiemposRB02 = 0;
			long sumaTiemposRB03 = 0;
			long sumaTiemposIB = 0;
			long sumaTiemposGreedy = 0;

			// Realizar cada algoritmo 10 veces para obtener un promedio
			for (int i = 0; i < 10; i++) {
				long start, end;

				// rBacktracking01
				start = System.nanoTime();
				RecursiveBTContLoading1.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB01 += (end - start);

				// rBacktracking02
				start = System.nanoTime();
				RecursiveBTContLoading2.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB02 += (end - start);

				// rBacktracking03
				start = System.nanoTime();
				RecursiveBTContLoading3.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB03 += (end - start);

				// iBacktracking
				start = System.nanoTime();
				IterativeBTContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposIB += (end - start);

				// Greedy
				start = System.nanoTime();
				GreedyContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposGreedy += (end - start);
			}

			// Calcular el tiempo promedio para cada algoritmo
			long mediaRB01 = sumaTiemposRB01 / 10;
			long mediaRB02 = sumaTiemposRB02 / 10;
			long mediaRB03 = sumaTiemposRB03 / 10;
			long mediaIB = sumaTiemposIB / 10;
			long mediaGreedy = sumaTiemposGreedy / 10;

			// Imprimir los resultados para este valor de peso
			System.out.println(peso + "\t" + mediaRB01 + "\t\t" + mediaRB02 + "\t\t" + mediaRB03 + "\t\t" + mediaIB
					+ "\t\t" + mediaGreedy);
		}
	}

	/**
	 * Test 05 potencias.
	 */
	public static void test05potencias() {
		// Inicializar valores iniciales
		int CMC = 1;
		int carga = 1;
		int maxInt = 16384;

		System.out.println("Valores incrementales en potencias de 2.");
		System.out.println("CMC\tCarga\tpeso\trBacktracking01\trBacktracking02\trBacktracking03\tiBacktracking\tGreedy");

		for (int peso = 1; peso <= maxInt; peso *= 2) {
			int[] pesos = new int[carga];
			// Inicializar todos los pesos a 'peso'
			for (int j = 0; j < carga; j++) {
				pesos[j] = peso;

			}

			long sumaTiemposRB01 = 0;
			long sumaTiemposRB02 = 0;
			long sumaTiemposRB03 = 0;
			long sumaTiemposIB = 0;
			long sumaTiemposGreedy = 0;

			// Realizar cada algoritmo 10 veces para obtener un promedio
			for (int i = 0; i < 10; i++) {
				long start, end;

				// rBacktracking01
				start = System.nanoTime();
				RecursiveBTContLoading1.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB01 += (end - start);

				// rBacktracking02
				start = System.nanoTime();
				RecursiveBTContLoading2.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB02 += (end - start);

				// rBacktracking03
				start = System.nanoTime();
				RecursiveBTContLoading3.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposRB03 += (end - start);

				// iBacktracking
				start = System.nanoTime();
				IterativeBTContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposIB += (end - start);

				// Greedy
				start = System.nanoTime();
				GreedyContLoading.cargaMaxima(pesos, CMC);
				end = System.nanoTime();
				sumaTiemposGreedy += (end - start);
			}

			// Calcular el tiempo promedio para cada algoritmo
			long mediaRB01 = sumaTiemposRB01 / 10;
			long mediaRB02 = sumaTiemposRB02 / 10;
			long mediaRB03 = sumaTiemposRB03 / 10;
			long mediaIB = sumaTiemposIB / 10;
			long mediaGreedy = sumaTiemposGreedy / 10;

			// Imprimir los resultados para este valor de peso
			System.out.println(CMC + "\t" + carga + "\t" + peso + "\t" + mediaRB01 + "\t\t" + mediaRB02 + "\t\t"
					+ mediaRB03 + "\t\t" + mediaIB + "\t\t" + mediaGreedy);
			
			// Aumentar CMC y carga en potencias de 2
			if (CMC < maxInt / 2)
				CMC *= 2;
			if (carga < maxInt / 2)
				carga *= 2;
		}
	}

}
