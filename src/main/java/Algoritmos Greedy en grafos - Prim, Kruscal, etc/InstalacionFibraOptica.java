package org.eda2.practica02;

import java.io.File;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class InstalacionFibraOptica.
 */
public class InstalacionFibraOptica {

	/** The sc. */
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Leer opcion.
	 *
	 * @param mensaje the mensaje
	 * @param min     the min
	 * @param max     the max
	 * @return the int
	 */
	private static int leerOpcion(String mensaje, int min, int max) {
		do {
			System.out.print(mensaje);
			try {
				int opcion = Integer.parseInt(sc.nextLine());
				if (opcion >= min && opcion <= max) {
					return opcion;
				}
			} catch (NumberFormatException e) {
				System.out.println("ERROR Introduzca un valor numerico");
			}
			System.out.println("ERROR Introduzca una opcion entre " + min + " y " + max);
		} while (true);
	}

	/**
	 * Ejecutar.
	 *
	 * @param opcion the opcion
	 * @return true, if successful
	 */
	private static boolean ejecutar(int opcion) {

		String archivo = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "org" + File.separator + "eda2" + File.separator
				+ "practica02" + File.separator;

		switch (opcion) {
		case 1:
			System.out.println("Opción seleccionada: 1. Prim sin cola de prioridad.");
			opcion = leerOpcion(
					"\nRedes disponibles:\n1. graphEDAland.txt.\n2. graphEDAlandLarge.txt.\n\nSeleccione una red: ", 1,
					2);
			if (opcion == 1) {
				File f = new File(archivo + "graphEDAland.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res1 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res1 = net.primV2("Almeria");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res1);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else {
				File f = new File(archivo + "graphEDAlandLarge.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res1 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res1 = net.primV2("1");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res1);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			}
			break;
		case 2:
			System.out.println("Opción seleccionada: 2. Prim con cola de prioridad.");
			opcion = leerOpcion(
					"\nRedes disponibles:\n1. graphEDAland.txt.\n2. graphEDAlandLarge.txt.\n3. grafoSintetico.txt.\n\nSeleccione una red: ",
					1, 3);
			if (opcion == 1) {
				File f = new File(archivo + "graphEDAland.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res2 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res2 = net.primPQV2("Almeria");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res2);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else if (opcion == 2) {
				File f = new File(archivo + "graphEDAlandLarge.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res2 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res2 = net.primPQV2("1");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res2);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else {
				File f = new File(archivo + "grafoSintetico.txt");
				Network net = new Network(f, "Sintetico");

				long totalTime = 0;
				Resultado res2 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res2 = net.primPQV2("A");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res2);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");
			}
			break;
		case 3:
			System.out.println("Opción seleccionada: 3. Kruskal.");
			opcion = leerOpcion(
					"\nRedes disponibles:\n1. graphEDAland.txt.\n2. graphEDAlandLarge.txt.\n3. grafoSintetico.txt.\n\nSeleccione una red: ",
					1, 3);
			if (opcion == 1) {
				File f = new File(archivo + "graphEDAland.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res3 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res3 = net.kruskal("Almeria");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res3);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else if (opcion == 2) {
				File f = new File(archivo + "graphEDAlandLarge.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res3 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res3 = net.kruskal("1");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res3);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else {
				File f = new File(archivo + "grafoSintetico.txt");
				Network net = new Network(f, "Sintetico");

				long totalTime = 0;
				Resultado res3 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res3 = net.kruskal("A");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res3);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");
			}
			break;

		case 4:
			System.out.println("Opción seleccionada: 4. Viajante de comercio con Caminos simples.");

			opcion = leerOpcion(
					"\nRedes disponibles:\n1. graphEDAlandNewroads.txt.\n2. grafoSintetico.txt.\nSeleccione una red: ",
					1, 2);

			if (opcion == 1) {
				File f = new File(archivo + "graphEDAlandNewroads.txt");
				Network net = new Network(f, "Almeria");

				long totalTime = 0;
				Resultado res4 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res4 = net.simplePaths("Almeria");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res4);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");

			} else {
				File f = new File(archivo + "grafoSintetico.txt");
				Network net = new Network(f, "Sintetico");

				long totalTime = 0;
				Resultado res4 = null;
				for (int i = 0; i < 10; i++) {
					long startTime = System.nanoTime();
					res4 = net.simplePaths("A");
					long endTime = System.nanoTime();
					long duration = endTime - startTime;
					totalTime += duration;
				}
				long averageTime = totalTime / 10;
				System.out.println(res4);
				System.out.println("Tiempo promedio de ejecución: " + averageTime + " nanosegundos\n");
			}

			break;
		case 5:
			System.out.println("Opción seleccionada: 5. Generador de redes sintéticas.\n");
			int numNodos;
			do {
				numNodos = leerOpcion("Ingrese el número de nodos para la red sintética (mínimo 2): ", 2,
						Integer.MAX_VALUE);
				if (numNodos < 2) {
					System.out.println("ERROR: El número mínimo de nodos es 2.");
				}
			} while (numNodos < 2);
			int numAristas;
			do {
				numAristas = leerOpcion("Ingrese el número de aristas para la red sintética: ", numNodos - 1,
						numNodos * (numNodos - 1) / 2);
				if (numAristas < numNodos - 1) {
					System.out.println(
							"ERROR: El número mínimo de aristas para hacer el grafo conexo es " + (numNodos - 1));
				} else if (numAristas > numNodos * (numNodos - 1) / 2) {
					System.out.println("ERROR: El número máximo de aristas para " + numNodos + " nodos es "
							+ (numNodos * (numNodos - 1) / 2));
				}
			} while (numAristas < numNodos - 1 || numAristas > numNodos * (numNodos - 1) / 2);
			List<String> redSintetica = Generador.generarRedSintetica(numNodos, numAristas, 100);
			System.out.println(redSintetica + "\n");
			break;
		case 6:
			System.out.println("Opción seleccionada: 6. Viajante de comercio con Greedy TSP.");

			File f2 = new File(archivo + "grafoSintetico.txt");
			Network net2 = new Network(f2, "Sintetico");

			long totalTime2 = 0;
			Resultado res5 = null;
			for (int i = 0; i < 10; i++) {
				long startTime = System.nanoTime();
				res5 = net2.greedyTSP("A");
				long endTime = System.nanoTime();
				long duration = endTime - startTime;
				totalTime2 += duration;
			}
			long averageTime2 = totalTime2 / 10;
			System.out.println(res5);
			System.out.println("Tiempo promedio de ejecución: " + averageTime2 + " nanosegundos\n");
			break;
		default:
			System.out.println("Fin de la ejecución.");
			return false;
		}
		return true;
	}

	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("   INSTALACIÓN DE FIBRA OPTICA   ");
		System.out.println("---------------------------------");
		System.out.println("1. Prim sin cola de prioridad.");
		System.out.println("2. Prim con cola de prioridad.");
		System.out.println("3. Kruskal.");
		System.out.println("4. Viajante de comercio con Caminos simples.");
		System.out.println("5. Generador de redes sinteticas.");
		System.out.println("6. Viajante de comercio con TSP.");
		System.out.println("7. Finalizar programa");
		System.out.println("---------------------------------\n");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		int opcion;
		do {
			menu();
			opcion = leerOpcion("Seleccione una opcion: ", 1, 7);
		} while (ejecutar(opcion));

		sc.close();

	}
}