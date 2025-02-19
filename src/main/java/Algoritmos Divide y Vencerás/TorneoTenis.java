package org.eda2.practica01;

import java.util.ArrayList;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * /** La clase TorneoTenis se utiliza para simular la organización de un torneo
 * de tenis.Ofrece funcionalidades para generar los emparejamientos del torneo
 * basándose en diferentes configuraciones del número de jugadores. Implementa
 * tres casos específicos de organización dependiendo de si el número de
 * jugadores es una potencia de dos, es par pero no una potencia de dos, o es
 * impar.
 */

public class TorneoTenis {

	/**
	 * Imprime la tabla de emparejamientos para el caso donde el número de jugadores
	 * es potencia de dos.
	 *
	 * @param tabla La matriz que contiene los emparejamientos del torneo.
	 * @param n     El número de jugadores, que debe ser una potencia de dos.
	 */
	public static void imprimirTablaCaso1(int[][] tabla, int n) {
		int nLongitud = (n + " ").length();

		System.out.println("\nEl cuadro de cruces es:\n");
		for (int i = 0; i < nLongitud; i++) {
			System.out.printf(" ");
		}
		System.out.print("|");
		for (int dia = 1; dia < n; dia++) {
			System.out.printf("%" + nLongitud + "s ", "d" + dia);
		}
		System.out.println("");

		for (int dia = 0; dia < n; dia++) {
			for (int i = 0; i < nLongitud + 1; i++) {
				System.out.printf("-");
			}
		}
		System.out.println("");

		for (int i = 1; i < n + 1; i++) {
			System.out.printf("%-" + nLongitud + "s|", "j" + i);
			for (int j = 1; j < n; j++) {
				System.out.printf("%" + nLongitud + "d ", tabla[i][j]);

			}
			System.out.println("");
		}
	}

	/**
	 * Imprime la tabla de emparejamientos para el caso donde el número de jugadores
	 * es par pero no una potencia de dos.
	 *
	 * @param tabla La matriz que contiene los emparejamientos del torneo.
	 * @param n     El número de jugadores, que es par pero no una potencia de dos.
	 */
	public static void imprimirTablaCaso2(int[][] tabla, int n) {
		int nLongitud = (n + " ").length();

		System.out.println("\nEl cuadro de cruces es:\n");
		for (int i = 0; i < nLongitud; i++) {
			System.out.printf(" ");
		}
		System.out.print("|");
		for (int dia = 1; dia <= n; dia++) {
			System.out.printf("%-" + (nLongitud + 2) + "s", "d" + dia);
		}
		System.out.println();

		System.out.print("---");
		for (int dia = 1; dia <= n; dia++) {
			for (int i = 0; i < nLongitud + 2; i++) {
				System.out.print("-");
			}
		}
		System.out.println();

		for (int i = 1; i <= n; i++) {
			System.out.printf("%-" + nLongitud + "s|", "j" + i);
			for (int j = 1; j <= n; j++) {
				if (tabla[i][j] == 0) {
					System.out.printf("%-" + (nLongitud + 2) + "s", "0");
				} else {
					System.out.printf("%-" + (nLongitud + 2) + "d", tabla[i][j]);
				}
			}
			System.out.println();
		}
	}

	/**
	 * Imprime la tabla de emparejamientos para el caso donde el número de jugadores
	 * es impar.
	 *
	 * @param tabla La matriz que contiene los emparejamientos del torneo.
	 * @param n     El número de jugadores, que es impar.
	 */
	public static void imprimirTablaCaso3(int[][] tabla, int n) {
		int nLongitud = (n + " ").length();

		System.out.println("\nEl cuadro de cruces es:\n");

		for (int i = 0; i < nLongitud; i++) {
			System.out.print(" ");
		}
		System.out.print("|");
		for (int jug = 1; jug <= n; jug++) {
			System.out.printf("%-" + (nLongitud + 2) + "s", "j" + jug);
		}
		System.out.println();

		System.out.print("---");
		for (int dia = 1; dia <= n; dia++) {
			for (int i = 0; i < nLongitud + 2; i++) {
				System.out.print("-");
			}
		}
		System.out.println();

		for (int dia = 1; dia <= n - 1; dia++) {
			System.out.printf("%-" + (nLongitud) + "s|", "d" + dia);
			for (int jug = 1; jug <= n; jug++) {
				if (tabla[dia - 1][jug - 1] == 0) {
					System.out.printf("%-" + (nLongitud + 2) + "s", "-");
				} else {
					System.out.printf("%-" + (nLongitud + 2) + "d", tabla[dia - 1][jug - 1]);
				}
			}
			System.out.println();
		}
	}

	/**
	 * Organiza los emparejamientos del torneo para el caso donde el número de
	 * jugadores es par pero no una potencia de dos.
	 *
	 * @param tabla La matriz donde se almacenarán los emparejamientos del torneo.
	 * @param n     El número de jugadores, que es par pero no una potencia de dos.
	 */
	public static void torneoRecursivoCaso1(int[][] tabla, int n) {
		if (n == 2) {
			tabla[1][1] = 2;
			tabla[2][1] = 1;
		} else {
			// Rellenar cuadrante superior izquierdo
			torneoRecursivoCaso1(tabla, n / 2);

			// Rellenar cuadrante inferior izquierdo
			for (int jug = (n / 2) + 1; jug <= n; jug++) {
				for (int dia = 1; dia <= (n / 2) - 1; dia++) {
					tabla[jug][dia] = tabla[jug - (n / 2)][dia] + (n / 2);
				}
			}
			// Rellenar cuadrante superior derecho
			for (int jug = 1; jug <= (n / 2); jug++) {
				for (int dia = (n / 2); dia <= n - 1; dia++) {
					if (jug + dia <= n) {
						tabla[jug][dia] = jug + dia;
					} else {
						tabla[jug][dia] = jug + dia - (n / 2);
					}
				}
			}
			// Rellenar cuadrante inferior derecho
			for (int jug = (n / 2) + 1; jug <= n; jug++) {
				for (int dia = (n / 2); dia <= n - 1; dia++) {
					if (jug > dia) {
						tabla[jug][dia] = jug - dia;
					} else {
						tabla[jug][dia] = (jug + (n / 2)) - dia;
					}

				}
			}
		}
	}

	/**
	 * Organiza los emparejamientos del torneo para el caso donde el número de
	 * jugadores es par o impar pero no una potencia de dos.
	 *
	 * @param tabla La matriz donde se almacenarán los emparejamientos del torneo.
	 * @param n     El número de jugadores, que es par pero no una potencia de dos.
	 */
	public static void torneoRecursivoCaso2(int[][] tabla, int n) {
		// Caso base.
		if (n == 2) {
			tabla[1][1] = 2;
			tabla[2][1] = 1;

		}
		// Caso n es impar.
		else if (n % 2 != 0) {
			torneoRecursivoCaso2(tabla, n + 1);

			// Eliminar el jugador n + 1
			for (int jug = 1; jug <= n; jug++) {
				for (int dia = 1; dia <= n; dia++) {
					if (tabla[jug][dia] == n + 1)
						tabla[jug][dia] = 0;
				}
			}
		}
		// Caso n es par.
		else {
			int m = n / 2;

			// Cuadrante superior izquierdo m impar.
			torneoRecursivoCaso2(tabla, m);

			// m es par
			if (m % 2 == 0) {
				// Cuadrante inferior izquierdo.
				for (int jug = m + 1; jug <= n; jug++) {
					for (int dia = 1; dia <= m - 1; dia++) {
						tabla[jug][dia] = tabla[jug - m][dia] + m;
					}
				}

				// Cuadrante superior derecho.
				for (int jug = 1; jug <= m; jug++) {
					for (int dia = m; dia <= n - 1; dia++) {
						if ((jug + dia) <= n) {
							tabla[jug][dia] = jug + dia;
						} else
							tabla[jug][dia] = jug + dia - m;
					}
				}

				// Cuadrante inferior derecho.
				for (int jug = m + 1; jug <= n; jug++) {
					for (int dia = m; dia <= n - 1; dia++) {
						if (jug > dia) {
							tabla[jug][dia] = jug - dia;
						} else {
							tabla[jug][dia] = (jug + m) - dia;
						}
					}
				}
			}
			// m es impar.
			else {
				// Cuadrante inferior izquierdo.
				for (int jug = m + 1; jug <= n; jug++) {
					for (int dia = 1; dia <= m; dia++) {
						if (tabla[jug - m][dia] == 0) {
							tabla[jug][dia] = 0;
						} else {
							tabla[jug][dia] = tabla[jug - m][dia] + m;
						}
					}
				}
				// Tratamiento de los ceros los cuadrantes izq.
				for (int jug = 1; jug <= m; jug++) {
					for (int dia = 1; dia <= m; dia++) {
						if (tabla[jug][dia] == 0) {
							tabla[jug][dia] = jug + m;
							tabla[jug + m][dia] = jug;

						}
					}
				}
				// Cuadrante superior derecho.
				for (int jug = 1; jug <= m; jug++) {
					for (int dia = m + 1; dia <= n - 1; dia++) {
						if ((jug + dia) <= n) {
							tabla[jug][dia] = jug + dia;
						} else {
							tabla[jug][dia] = jug + dia - m;
						}
					}
				}
				// Cuadrante inferior izquierdo.
				for (int jug = m + 1; jug <= n; jug++) {
					for (int dia = m + 1; dia <= n - 1; dia++) {
						if (jug > dia) {
							tabla[jug][dia] = jug - dia;
						} else {
							tabla[jug][dia] = (jug + m) - dia;
						}
					}
				}

			}
		}
	}

	/**
	 * Organiza los emparejamientos del torneo para el caso donde el número de
	 * jugadores es potencia de 2.
	 *
	 * @param tabla La matriz donde se almacenarán los emparejamientos del torneo.
	 * @param i     Índice inicial (comienza en 1).
	 * @param j     Índice final (igual al número de jugadores).
	 */
	public static void torneoRecursivoCaso3(int[][] tabla, int i, int j) {
		// Caso base.
		if (j == i + 1) {
			tabla[0][i - 1] = j;
			tabla[0][j - 1] = i;
		}
		// Caso recursivo.
		else {
			int k = i + ((j - i + 1) / 2);
			torneoRecursivoCaso3(tabla, i, k - 1);
			torneoRecursivoCaso3(tabla, k, j);

			int n = j - i + 1;
			for (int m = 0; m < n / 2; m++) {
				int dia = (n / 2) + m;
				tabla[dia - 1][i - 1] = k + m;
				tabla[dia - 1][k + m - 1] = i;
				for (int l = 1; l < n / 2; l++) {
					int oponente = k + ((l + m) % (n / 2));
					tabla[dia - 1][i + l - 1] = oponente;
					tabla[dia - 1][oponente - 1] = i + l;
				}
			}

		}
	}

	/**
	 * Determina si un número dado es primo.
	 *
	 * @param num El número a verificar.
	 * @return true si el número es primo; false en caso contrario.
	 */
	public static boolean esPrimo(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Genera una lista de números primos hasta un límite dado.
	 *
	 * @param limite El valor máximo para generar números primos.
	 * @return Una lista de números primos hasta el límite especificado.
	 */
	public static ArrayList<Integer> generarPrimos(int limite) {
		ArrayList<Integer> primos = new ArrayList<>();
		for (int i = 2; i <= limite; i *= 2) {
			i++; // Incrementa para tomar el siguiente número después de multiplicar por 2
			while (!esPrimo(i)) {
				i++; // Continúa incrementando hasta encontrar el siguiente número primo
			}
			primos.add(i);
		}
		return primos;
	}

	/**
	 * Uso memoria.
	 *
	 * @param mem the mem
	 */
	public static void usoMemoria(int[][] mem, int n) {

		// complejidad espacial.
		// int 32 bits = 4 bytes
		// 2^14 * 2^14 * 2^2 = 2^30 bytes = 1GB
		// 2^15*2^15*2^2 = 4GB
		double t = mem.length * mem[0].length * (double) Integer.SIZE / 8.0;

		System.out.println(n + "\t" + t / Math.pow(2, 20) + " MB");
	}

	/**
	 * Método principal que ejecuta el programa. Gestiona la entrada del usuario
	 * para seleccionar el modo de operación del torneo y el número de jugadores.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		int funcion, n, caso;

		// Menú de selección.
		try {
			System.out.println("TORNEO DE TENIS\n");
			System.out.print(
					"SELECCIONE UNA OPCIÓN:\nOPCIÓN 1: Cálculo tiempo de respuesta.\nOPCIÓN 2: Estudio memoria principal.\nOPCIÓN 3: Imprimir cuadro de cruces.\n\nOPCIÓN SELECCIONADA: ");
			funcion = entrada.nextInt();
			if (funcion < 1 || funcion > 3) {
				System.out.println("Opción no válida. Por favor, ingrese 1, 2 o 3.");
				entrada.close();
				return;
			}

			System.out.print("Introduzca el número de jugadores: ");
			n = entrada.nextInt();

		} catch (java.util.InputMismatchException e) {
			System.out.println("Error en la entrada. Por favor, ingrese un número válido.");
			entrada.close();
			return;
		}

		// N es insuficiente.
		if (n <= 1) {
			System.out.println("\nSe requieren más jugadores para realizar los cruces.");
		}

		// N es potencia de 2.
		else if ((n & (n - 1)) == 0) {
			try {
				System.out.println("\nEl número de jugadores es potencia de 2.");

				System.out
						.print("SELECCIONE UNA OPCIÓN:\nOPCIÓN 1: Caso 1.\nOPCIÓN 2: Caso 3.\n\nOPCIÓN SELECCIONADA: ");
				caso = entrada.nextInt();
				if (caso < 1 || caso > 2) {
					System.out.println("Opción no válida. Por favor, ingrese 1 o 2.");
					entrada.close();
				}

				// Caso 1.
				if (caso == 1) {
					if (funcion == 1) {
						System.out.println("N\tTiempo(ns)");
						for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
							int[][] tablaTest = new int[nEntrada + 1][nEntrada];
							long inicio = System.nanoTime();

							for (int i = 0; i <= 10; i++) {
								torneoRecursivoCaso1(tablaTest, nEntrada);
							}
							long fin = System.nanoTime();
							System.out.println(nEntrada + "\t" + ((fin - inicio) / 10));
						}

					} else if (funcion == 2) {
						System.out.println("N\tEspacio(MB)");
						for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
							int[][] tablaTest = new int[nEntrada + 1][nEntrada];
							usoMemoria(tablaTest, nEntrada);
						}
					}

					else {
						int[][] tabla = new int[n + 1][n];
						torneoRecursivoCaso1(tabla, n);
						imprimirTablaCaso1(tabla, n);
					}

				}

				// Caso 3.
				else if (caso == 2) {

					if (funcion == 1) {
						System.out.println("N\tTiempo(ns)");
						for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
							int[][] tablaTest = new int[nEntrada - 1][nEntrada];
							long inicio = System.nanoTime();

							for (int i = 0; i <= 10; i++) {
								torneoRecursivoCaso3(tablaTest, 1, nEntrada);
							}
							long fin = System.nanoTime();
							System.out.println(nEntrada + "\t" + ((fin - inicio) / 10));
						}

					} else if (funcion == 2) {
						for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
							int[][] tablaTest = new int[nEntrada - 1][nEntrada];
							usoMemoria(tablaTest, nEntrada);
						}
					} else {
						int[][] tabla = new int[n - 1][n];
						torneoRecursivoCaso3(tabla, 1, n);
						imprimirTablaCaso3(tabla, n);
					}
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Error en la entrada. Por favor, ingrese un número válido.");
				entrada.close();
				return;
			}

		}

		// N es par.
		else if (n % 2 == 0) {

			System.out.println("\nEl número de jugadores es par.");

			if (funcion == 1) {
				System.out.println("Potencias de 2.");
				System.out.println("N\tTiempo(ns)");
				for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
					int[][] tablaTest = new int[nEntrada + 1][nEntrada];
					long inicio = System.nanoTime();

					for (int i = 0; i <= 10; i++) {
						torneoRecursivoCaso2(tablaTest, nEntrada);
					}
					long fin = System.nanoTime();
					System.out.println(nEntrada + "\t" + ((fin - inicio) / 10));
				}

			} else if (funcion == 2) {
				for (int nEntrada = 2; nEntrada <= n; nEntrada *= 2) {
					int[][] tablaTest = new int[nEntrada + 1][nEntrada];
					usoMemoria(tablaTest, nEntrada);
				}
			} else {
				int[][] tabla = new int[n + 1][n];
				torneoRecursivoCaso2(tabla, n);
				imprimirTablaCaso1(tabla, n);
			}
		}

		// N es impar.
		else {
			System.out.println("\nEl número de jugadores es impar.");

			if (funcion == 1) {

				System.out.println("\nNúmeros primos.");
				System.out.println("N\tTiempo(ns)");
				ArrayList<Integer> listaPrimos = generarPrimos(n);

				for (int primo : listaPrimos) {
					int[][] tablaTest = new int[primo + 2][primo + 1];
					long inicio = System.nanoTime();

					for (int i = 0; i <= 10; i++) {
						torneoRecursivoCaso2(tablaTest, primo);
					}

					long fin = System.nanoTime();
					System.out.println(primo + "\t" + ((fin - inicio) / 10));

					primo *= 2;
				}

			} else if (funcion == 2) {
				ArrayList<Integer> listaPrimos = generarPrimos(n);
				for (int primo : listaPrimos) {
					int[][] tablaTest = new int[primo + 2][primo + 1];
					usoMemoria(tablaTest, primo);
				}
				
			} else {
				int[][] tabla = new int[n + 2][n + 1];
				torneoRecursivoCaso2(tabla, n);
				imprimirTablaCaso2(tabla, n);
			}

		}
	}
}