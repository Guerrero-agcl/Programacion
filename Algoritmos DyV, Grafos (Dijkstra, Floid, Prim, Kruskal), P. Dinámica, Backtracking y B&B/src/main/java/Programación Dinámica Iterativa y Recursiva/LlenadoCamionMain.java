package org.eda2.practica03;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class LlenadoCamionMain.
 */
public class LlenadoCamionMain {

	/** The camion. */
	private static Camion camion;

	/** The sc. */
	static Scanner sc;

	/**
	 * Opcion generador.
	 */
	private static void opcionGenerador() {
		int opcionCarga = Tools.leerOpcionInt(
				"1. Generador de carga aleatorio.\n2. Generador de carga manual.\nSeleccione una opción: ", 1, 2);
		switch (opcionCarga) {
		case 1:
			System.out.println("Generando carga aleatoria...");
			camion = Tools.cargaAleatoria();
			System.out.println(camion);
			System.out.println("Carga generada correctamente");
			break;
		case 2:
			System.out.println("Generando carga manual...");
			camion = Tools.cargaManual();
			System.out.println(camion);
			System.out.println("Carga generada correctamente");
			break;
		}
	}

	/**
	 * Opcion camion recursive.
	 */
	private static void opcionCamionRecursive() {
		if (!comprobarCamion())
			return;
		System.out.println("Beneficio final: " + camion.camionRecursive());
	}

	/**
	 * Opcion camion table.
	 */
	private static void opcionCamionTable() {
		if (!comprobarCamion())
			return;
		camion.camionTable();
		camion.printTabla();
		int[] solucion = camion.getSolucionArray();
		camion.printObjSolArray(solucion);
	}

	/**
	 * Opcion camion DP.
	 */
	private static void opcionCamionDP() {
		if (!comprobarCamion())
			return;
		camion.camionDP();
		camion.printTabla();
		int[] solucion = camion.getItemSolRecursiva();
		camion.printObjSolArray(solucion);
	}

	/**
	 * Opcion camion DP 2.
	 */
	private static void opcionCamionDP2() {
		if (!comprobarCamion())
			return;
		camion.camionDP2();
		camion.printTabla();
		int[] solucion = camion.getItemSolIterativa();
		camion.printObjSolArray(solucion);
	}

	/**
	 * Opcion camion DP 3.
	 */
	private static void opcionCamionDP3() {
		if (!comprobarCamion())
			return;
		System.out.println(camion.camionDP3());
		camion.printArray();
		System.out.println("Beneficio final: " + camion.getArray()[camion.getArray().length - 1]);
	}

	/**
	 * Opcion camion greedy.
	 */
	private static void opcionCamionGreedy() {
		if (!comprobarCamion())
			return;
		Resultado solucion = camion.camionGreedy();
		camion.printObjSolArray(solucion);
	}

	/**
	 * Test rendimiento.
	 */
	private static void testRendimiento() {
		int opcion = Tools
				.leerOpcionInt("1. N constante P variable.\n2. N variable P constante.\nSeleccione una opción: ", 1, 2);
		switch (opcion) {
		case 1:
			Tools.test01NConstantePVariable();
			break;
		case 2:
			Tools.test02NVariablePConstante();
			break;
		}
	}

	/**
	 * Menu.
	 */
	private static void menu() {
		System.out.println("\nLLENAR EL CAMIÓN CON LOS PRODUCTOS MÁS BENEFICIOSOS ");
		System.out.println("-----------------------------------------------------");
		System.out.println("1. Generador de contenido.");
		System.out.println("2. Ejercicio 1 camionRecursive.");
		System.out.println("3. Ejercicio 2 camionTable.");
		System.out.println("4. Ejercicio 3 camionDP.");
		System.out.println("5. Ejercicio 5 camionDP2.");
		System.out.println("6. Ejercicio 6 camionDP3.");
		System.out.println("7. Ejercicio 9 camionGreedy.");
		System.out.println("8. Test de rendimineto.");
		System.out.println("9. Finalizar programa.");
		System.out.println("-----------------------------------------------------\n");

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
			opcionGenerador();
			break;
		case 2:
			opcionCamionRecursive();
			break;
		case 3:
			opcionCamionTable();
			break;
		case 4:
			opcionCamionDP();
			break;
		case 5:
			opcionCamionDP2();
			break;
		case 6:
			opcionCamionDP3();
			break;
		case 7:
			opcionCamionGreedy();
			break;
		case 8:
			testRendimiento();
			break;
		case 9:
			System.out.println("Fin del programa");
			return false;
		default:
			System.out.println("Opción no válida");
		}
		return true;
	}

	/**
	 * Comprobar camion.
	 *
	 * @return true, if successful
	 */
	private static boolean comprobarCamion() {
		if (camion == null || camion.getObjetos().size() == 0) {
			System.err.println("\nError: Genera primero una carga.");
			return false;
		}
		return true;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int opcion = 0;
		do {
			menu();
			opcion = Tools.leerOpcionInt("Seleccione una opción: ", 1, 9);
		} while (ejecutar(opcion));
		sc.close();
	}

}