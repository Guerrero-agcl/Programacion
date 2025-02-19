package org.eda2.practica02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Generador.
 */
public class Generador {

	/**
	 * Generar red sintetica.
	 *
	 * @param numNodos the num nodos
	 * @param numAristas the num aristas
	 * @param densidad the densidad
	 * @return the list
	 */
	public static List<String> generarRedSintetica(int numNodos, int numAristas, double densidad) {
		List<String> vertices = new ArrayList<>();
		for (int i = 0; i < numNodos; i++) {
			vertices.add(Character.toString((char) ('A' + i)));
		}

		String[] nombresVertices = vertices.toArray(new String[0]);

		List<String> todos = new LinkedList<>();
		for (int i = 0; i < vertices.size() - 1; i++) {
			for (int j = i + 1; j < vertices.size(); j++) {
				todos.add(vertices.get(i) + "-" + vertices.get(j));
			}
		}

		List<String> u = new LinkedList<>();
		List<String> resultado = new LinkedList<>();
		Collections.shuffle(vertices);
		u.add(vertices.remove(0));
		while (!vertices.isEmpty()) {
			String from = vertices.remove(0);
			String to = u.get((int) (Math.random() * u.size()));
			resultado.add(from + " " + to + " " + Math.round((Math.random() * 100 + 1)));
			if (from.compareTo(to) < 0) {
				todos.remove(from + "-" + to);
			} else {
				todos.remove(to + "-" + from);
			}
			u.add(from);
		}

		int minAristas = numNodos - 1;
		while (resultado.size() < minAristas && resultado.size() < numAristas) {
			String x = todos.remove((int) (Math.random() * todos.size()));
			String[] tokens = x.split("-");
			resultado.add(tokens[0] + " " + tokens[1] + " " + Math.round((Math.random() * 100 + 1)));
		}

		int nAristasAAgregar = numAristas - resultado.size();
		for (int i = 0; i < nAristasAAgregar && !todos.isEmpty(); i++) {
			String x = todos.remove(0);
			String[] tokens = x.split("-");
			resultado.add(tokens[0] + " " + tokens[1] + " " + Math.round((Math.random() * 100 + 1)));
		}

		String rutaArchivo = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "org" + File.separator + "eda2" + File.separator
				+ "practica02" + File.separator + "grafoSintetico.txt";

		try {
			FileWriter writer = new FileWriter(rutaArchivo);
			writer.write("0\n");
			writer.write(numNodos + "\n");
			for (int i = 0; i < nombresVertices.length; i++) {
				writer.write(nombresVertices[i]);
				if (i != nombresVertices.length - 1) {
					writer.write("\n");
				}
			}
			writer.write("\n");
			writer.write(numAristas + "\n");
			for (String edge : resultado) {
				writer.write(edge + "\n");
			}
			writer.close();
			System.out.println("\nGrafo generado y guardado en " + rutaArchivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultado;
	}
}