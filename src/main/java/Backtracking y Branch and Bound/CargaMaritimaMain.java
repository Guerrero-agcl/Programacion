package org.eda2.practica04;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FilenameFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class CargaMaritimaMain.
 */
public class CargaMaritimaMain {

	/** The carpeta. */
	private static String carpeta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
			+ File.separator + "java" + File.separator + "org" + File.separator + "eda2" + File.separator + "practica04"
			+ File.separator;

	/** The entrada. */
	static Scanner entrada;

	/** The cmc. */
	private static int CMC;

	/** The carga. */
	private static int carga;

	/** The pesos. */
	private static int[] pesos;

	/**
	 * Opcion greedy cont loading.
	 */
	private static void opcionGreedyContLoading() {
		if (!comprobarBuque())
			return;
		System.out.println("El valor máximo obtenido es " + GreedyContLoading.cargaMaxima(pesos, CMC) + ".\n");
	}

	/**
	 * Opcion recursive BT cont loading 1.
	 */
	private static void opcionRecursiveBTContLoading1() {
		if (!comprobarBuque())
			return;
		System.out.println("El valor máximo obtenido es " + RecursiveBTContLoading1.cargaMaxima(pesos, CMC) + ".\n");
	}

	/**
	 * Opcion recursive BT cont loading 2.
	 */
	private static void opcionRecursiveBTContLoading2() {
		if (!comprobarBuque())
			return;
		System.out.println("El valor máximo obtenido es " + RecursiveBTContLoading2.cargaMaxima(pesos, CMC) + ".\n");
	}

	/**
	 * Opcion recursive BT cont loading 3.
	 */
	private static void opcionRecursiveBTContLoading3() {
		if (!comprobarBuque())
			return;
		System.out.println("El valor máximo obtenido es " + RecursiveBTContLoading3.cargaMaxima(pesos, CMC) + ".\n");
	}

	/**
	 * Opcion iterative BT cont loading.
	 */
	private static void opcionIterativeBTContLoading() {
		if (!comprobarBuque())
			return;
		System.out.println("El valor máximo obtenido es " + IterativeBTContLoading.cargaMaxima(pesos, CMC) + ".\n");
	}

	/**
	 * Comprobar buque.
	 *
	 * @return true, if successful
	 */
	private static boolean comprobarBuque() {
		if (CMC <= 0 || pesos == null || pesos.length == 0) {
			System.err.println("\nCarga un conjunto de datos o genera unos con la OPCIÓN 1.\n");
			return false;
		}
		return true;
	}

	/**
	 * Load file.
	 */
	private static void loadFile() {
		File dir = new File(carpeta);
		if (!dir.exists()) {
			System.out.println("El directorio " + carpeta + " no existe.");
			return;
		}

		File[] cargaFiles = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith("_c.txt");
			}
		});

		if (cargaFiles == null || cargaFiles.length == 0) {
			System.out.println("No se encontraron archivos de carga (_c.txt).");
			return;
		}

		System.out.println("Archivos de carga disponibles:");
		for (int i = 0; i < cargaFiles.length; i++) {
			System.out.println((i + 1) + ". " + cargaFiles[i].getName());
		}
		int cargaFileIndex = Tools.leerOpcionInt("Seleccione el conjuto de datos: ", 1, cargaFiles.length);
		File cargaFile = cargaFiles[cargaFileIndex - 1];

		String cargaFileName = cargaFile.getName();
		String pesosFileName = cargaFileName.replace("_c.txt", "_w.txt");
		File pesosFile = new File(dir, pesosFileName);

		if (!pesosFile.exists()) {
			System.out.println("El archivo de pesos correspondiente (" + pesosFileName + ") no existe.");
			return;
		}

		System.out.println("Cargando archivos \"" + cargaFile.getName() + "\" y \"" + pesosFile.getName() + "\"...");

		try (Scanner cargaScanner = new Scanner(cargaFile); Scanner pesosScanner = new Scanner(pesosFile)) {
			if (cargaScanner.hasNextInt()) {
				CMC = cargaScanner.nextInt();
			} else {
				System.out.println("Error: El archivo de carga no contiene un entero válido.");
				return;
			}

			List<Integer> pesosList = new ArrayList<>();
			while (pesosScanner.hasNextInt()) {
				pesosList.add(pesosScanner.nextInt());
			}
			carga = pesosList.size();
			pesos = pesosList.stream().mapToInt(i -> i).toArray();

			System.out.println("Archivo cargado correctamente.\n");
		} catch (Exception e) {
			System.out.println("Error al leer los archivos: " + e.getMessage());
		}
	}

	/**
	 * Generar carga aleatoria.
	 */
	private static void generarCargaAleatoria() {
		System.out.println("\nGenerando carga aleatoria...");

		CMC = Tools.leerOpcionInt("\nSeleccione una capacidad máxima de carga: ", 1, Integer.MAX_VALUE);

		carga = Tools.leerOpcionInt("\nSeleccione el número de cargas: ", 1, Integer.MAX_VALUE);

		pesos = new int[carga];

		Random random = new Random();
		for (int i = 0; i < carga; i++) {
			pesos[i] = random.nextInt(carga) + 1;
			System.out.printf("Carga %d de peso %d\n", i + 1, pesos[i]);
		}

		System.out.println("Carga generada correctamente.\n");
	}

	/**
	 * Generar carga manual.
	 */
	private static void generarCargaManual() {
		System.out.println("\nGenerando carga manual...");

		CMC = Tools.leerOpcionInt("\nSeleccione una capacidad máxima de carga (ej 12): ", 1, Integer.MAX_VALUE);

		carga = Tools.leerOpcionInt("\nSeleccione el número de cargas (ej 5 { 0, 8, 6, 2, 3 }): ", 1,
				Integer.MAX_VALUE);

		pesos = new int[carga];

		for (int i = 0; i < pesos.length; i++) {
			pesos[i] = Tools.leerOpcionInt("Introduzca la carga " + (i + 1) + ": ", 0, Integer.MAX_VALUE);
		}

		System.out.println("Carga generada correctamente.\n");
	}

	/**
	 * Generador.
	 */
	private static void generador() {
		int opcionCarga = Tools.leerOpcionInt(
				"\n1. Cargra archivos.\n2. Generador de carga aleatorio.\n3. Generador de carga manual.\n\nSeleccione una opción: ",
				1, 3);
		switch (opcionCarga) {
		case 1:
			loadFile();
			break;
		case 2:
			generarCargaAleatoria();
			break;
		case 3:
			generarCargaManual();
			break;
		default:
			System.out.println("Opción no válida.");
		}
	}

	/**
	 * Tests rendimiento.
	 */
	private static void testsRendimiento() {
		int opcionTest = Tools.leerOpcionInt(
				"\n1. test01cmcVpesosC." + "\n2. test02cmcCpesosV." + "\n3. test03cmcCpesosVcreciente."
						+ "\n4. test04cmcCpesosVdecreciente." + "\n5. test05potencias." + "\n\nSeleccione una opción: ",
				1, 5);
		switch (opcionTest) {
		case 1:
			Tools.test01cmcVpesosC();
			break;
		case 2:
			Tools.test02cmcCcargaV();
			break;
		case 3:
			Tools.test03cmcCpesosVcreciente();
			break;
		case 4:
			Tools.test04cmcCpesosVdecreciente();
			break;
		case 5:
			Tools.test05potencias();
			break;
		default:
			System.out.println("Opción no válida.");
		}
	}

	/**
	 * Ejecutar.
	 *
	 * @param opcion the opcion
	 * @return true, if successful
	 */
	private static boolean ejecutar(int opcion) {
		switch (opcion) {
		case 1:
			generador();
			break;
		case 2:
			opcionGreedyContLoading();
			break;
		case 3:
			opcionRecursiveBTContLoading1();
			break;
		case 4:
			opcionRecursiveBTContLoading2();
			break;
		case 5:
			opcionRecursiveBTContLoading3();
			break;
		case 6:
			opcionIterativeBTContLoading();
			break;
		case 7:
			testsRendimiento();
			break;
		case 8:
			System.out.println("Fin del programa.");
			return false;
		default:
			System.out.println("Opción no válida.");
		}
		return true;
	}

	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("LLENADO DE LOS BUQUES PORTACONTENEDORES");
		System.out.println("---------------------------------------");
		System.out.println("1. Selección de contenido.");
		System.out.println("2. Ejercicio 1 GreedyContLoading.");
		System.out.println("3. Ejercicio 2 RecursiveBTContLoading1.");
		System.out.println("4. Ejercicio 3 RecursiveBTContLoading2.");
		System.out.println("5. Ejercicio 4 RecursiveBTContLoading3.");
		System.out.println("6. Ejercicio 5 IterativeBTContLoading. ");
		System.out.println("7. Test de rendimineto.");
		System.out.println("8. Finalizar programa.");
		System.out.println("---------------------------------------");

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		entrada = new Scanner(System.in);
		int opcion = 0;

		do {
			menu();
			opcion = Tools.leerOpcionInt("Seleccione una opción: ", 1, 8);
		} while (ejecutar(opcion));
		entrada.close();
	}

}