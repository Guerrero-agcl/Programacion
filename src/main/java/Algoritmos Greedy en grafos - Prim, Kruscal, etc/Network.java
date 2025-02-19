package org.eda2.practica02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Network.
 */
public class Network implements Iterable<String> {

	/** The dirigido. */
	private boolean dirigido;
	
	/** The grafo. */
	private HashMap<String, HashMap<String, Double>> grafo;

	/**
	 * Instantiates a new network.
	 */
	public Network() {
		this.dirigido = true;
		this.grafo = new HashMap<>();
	}

	/**
	 * Instantiates a new network.
	 *
	 * @param dirigido the dirigido
	 */
	public Network(boolean dirigido) {
		this.dirigido = dirigido;
		this.grafo = new HashMap<>();
	}

	/**
	 * Instantiates a new network.
	 *
	 * @param archivo the archivo
	 * @param origen the origen
	 */
	public Network(File archivo, String origen) {
		this(false);

		clear();
		try {
			Scanner sc = new Scanner(archivo);
			int nVertex = -1;
			int nEdges = -1;
			String[] tokens;
			if (sc.hasNext()) {
				setDirected(sc.nextLine().equals("1"));
			}
			if (sc.hasNext()) {
				nVertex = Integer.parseInt(sc.nextLine().trim());
			}
			for (int i = 0; i < nVertex; i++) {
				addVertice(sc.nextLine());
			}
			if (sc.hasNext()) {
				nEdges = Integer.parseInt(sc.nextLine().trim());
			}
			for (int i = 0; i < nEdges; i++) {
				tokens = sc.nextLine().trim().split("[ ]+");
				if (tokens.length != 3)
					continue;
				addArista(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the grafo.
	 *
	 * @return the grafo
	 */
	public HashMap<String, HashMap<String, Double>> getGrafo() {
		HashMap<String, HashMap<String, Double>> copia = new HashMap<>();
		for (String vertice : grafo.keySet()) {
			copia.put(vertice, new HashMap<>(grafo.get(vertice)));
		}
		return copia;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return this.grafo.isEmpty();
	}

	/**
	 * Clear.
	 */
	public void clear() {
		this.grafo.clear();
	}

	/**
	 * Numero vertices.
	 *
	 * @return the int
	 */
	public int numeroVertices() {
		return this.grafo.size();
	}

	/**
	 * Numero aristas.
	 *
	 * @return the int
	 */
	public int numeroAristas() {
		int count = 0;
		for (HashMap<String, Double> vecinos : this.grafo.values()) {
			count += vecinos.size();
		}
		return count;
	}

	/**
	 * Gets the directed.
	 *
	 * @return the directed
	 */
	public boolean getDirected() {
		return this.dirigido;
	}

	/**
	 * Sets the directed.
	 *
	 * @param dirigido the new directed
	 */
	public void setDirected(boolean dirigido) {
		this.dirigido = dirigido;
	}

	/**
	 * Contains vertice.
	 *
	 * @param v the v
	 * @return true, if successful
	 */
	public boolean containsVertice(String v) {
		return this.grafo.containsKey(v);
	}

	/**
	 * Contains arista.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @return true, if successful
	 */
	public boolean containsArista(String v1, String v2) {
		HashMap<String, Double> vecinos = this.grafo.get(v1);
		return vecinos != null && vecinos.containsKey(v2);
	}

	/**
	 * Gets the weight.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @return the weight
	 */
	public double getWeight(String v1, String v2) {
		HashMap<String, Double> vecinos = this.grafo.get(v1);
		return (vecinos != null) ? vecinos.getOrDefault(v2, -1.0) : -1.0;
	}

	/**
	 * Sets the weight.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @param w the w
	 */
	public void setWeight(String v1, String v2, double w) {
		if (containsVertice(v1)) {
			HashMap<String, Double> vecinos = this.grafo.get(v1);
			if (vecinos != null) {
				vecinos.put(v2, w);
			}
		}
	}

	/**
	 * Checks if is adjacent.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @return true, if is adjacent
	 */
	public boolean isAdjacent(String v1, String v2) {
		return containsArista(v1, v2);
	}

	/**
	 * Adds the vertice.
	 *
	 * @param v the v
	 * @return true, if successful
	 */
	public boolean addVertice(String v) {
		if (this.grafo.containsKey(v)) {
			return false;
		}
		this.grafo.put(v, new HashMap<>());
		return true;
	}

	/**
	 * Adds the arista.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @param w the w
	 * @return true, if successful
	 */
	public boolean addArista(String v1, String v2, double w) {
		if (!containsVertice(v1)) {
			addVertice(v1);
		}
		if (!containsVertice(v2)) {
			addVertice(v2);
		}
		this.grafo.get(v1).put(v2, w);
		if (!this.dirigido) {
			this.grafo.get(v2).put(v1, w);
		}
		return true;
	}

	/**
	 * Removes the vertice.
	 *
	 * @param v the v
	 * @return true, if successful
	 */
	public boolean removeVertice(String v) {
		if (!containsVertice(v)) {
			return false;
		}
		for (HashMap<String, Double> vecinos : this.grafo.values()) {
			vecinos.remove(v);
		}
		this.grafo.remove(v);
		return true;
	}

	/**
	 * Removes the arista.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @return true, if successful
	 */
	public boolean removeArista(String v1, String v2) {
		if (!containsArista(v1, v2)) {
			return false;
		}
		this.grafo.get(v1).remove(v2);
		if (!this.dirigido) {
			this.grafo.get(v2).remove(v1);
		}
		return true;
	}

	/**
	 * Gets the vertices.
	 *
	 * @return the vertices
	 */
	public Set<String> getVertices() {
		return this.grafo.keySet();
	}

	/**
	 * Gets the neighbors.
	 *
	 * @param v the v
	 * @return the neighbors
	 */
	public HashSet<String> getNeighbors(String v) {
		HashMap<String, Double> vecinos = this.grafo.get(v);
		if (vecinos == null) {
			return new HashSet<String>();
		}
		return new HashSet<String>(vecinos.keySet());
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.grafo.toString();
	}

	/**
	 * Iterator.
	 *
	 * @return the iterator
	 */
	@Override
	public Iterator<String> iterator() {
		return this.grafo.keySet().iterator();
	}

	/**
	 * Buscar raiz.
	 *
	 * @param verticesDesde the vertices desde
	 * @param v the v
	 * @return the string
	 */
	private String buscarRaiz(HashMap<String, String> verticesDesde, String v) {
		String raiz = verticesDesde.get(v);
		if (!raiz.equals(v)) {
			raiz = buscarRaiz(verticesDesde, raiz);
		}

		verticesDesde.put(v, raiz);
		return raiz;
	}

	/**
	 * Clave menor.
	 *
	 * @param claves the claves
	 * @param visitados the visitados
	 * @return the string
	 */
	private String claveMenor(HashMap<String, Double> claves, HashSet<String> visitados) {
		String mejorVecino = null;
		double min = Double.MAX_VALUE;
		for (Entry<String, Double> entry : claves.entrySet()) {
			if (!visitados.contains(entry.getKey()) && entry.getValue() < min) {
				min = entry.getValue();
				mejorVecino = entry.getKey();
			}
		}
		return mejorVecino;
	}

	/**
	 * Vertice mas cercano.
	 *
	 * @param visitados the visitados
	 * @param actual the actual
	 * @return the string
	 */
	private String verticeMasCercano(HashSet<String> visitados, String actual) {
		Double min = Double.MAX_VALUE;
		String mejorVecino = null;
		for (String vertice : getNeighbors(actual)) {
			if (!visitados.contains(vertice)) {
				Double peso = getWeight(actual, vertice);
				if (peso >= 0 && peso < min) { // Solo considerar pesos válidos
					min = peso;
					mejorVecino = vertice;
				}
			}
		}
		return mejorVecino;
	}

	/**
	 * Simple paths.
	 *
	 * @param vertices the vertices
	 * @param resultado the resultado
	 * @param current the current
	 * @param destino the destino
	 * @param source the source
	 */
	private void simplePaths(LinkedList<String> vertices, Resultado resultado, String current, String destino,
			String source) {
		vertices.add(current);
		if (current.equals(destino)) {
			if (vertices.size() == numeroVertices()) {
				Double peso = getWeight(vertices.getLast(), source);
				if (peso != null) {
					Resultado res = new Resultado(source);
					for (int i = 0; i < vertices.size() - 1; i++) {
						String v1 = vertices.get(i);
						String v2 = vertices.get(i + 1);
						res.addArista(v1, v2, getWeight(v1, v2));
					}
					String v1 = vertices.getLast();
					res.addArista(v1, source, getWeight(v1, source));
					if (resultado.getTotal() == 0 || res.getTotal() < resultado.getTotal()) {
						resultado.clear();
						resultado.copy(res);
					}
				}
			}
			vertices.remove(vertices.getLast());
			return;
		}
		for (String v1 : grafo.get(current).keySet()) {
			if (vertices.contains(v1))
				continue;
			simplePaths(vertices, resultado, v1, destino, source);
		}
		vertices.removeLast();
	}

	/**
	 * Menor clave PQ.
	 *
	 * @param claves the claves
	 * @param visitados the visitados
	 * @return the string
	 */
	public String menorClavePQ(TreeMap<Double, HashSet<String>> claves, HashSet<String> visitados) {

		do {
			Entry<Double, HashSet<String>> p = claves.firstEntry();
			for (String vertice : p.getValue()) {
				if (!visitados.contains(vertice)) {
					p.getValue().remove(vertice);
					return vertice;
				}
			}
			claves.remove(p.getKey());
		} while (!claves.isEmpty());

		return null;
	}

	public Resultado prim(String origen) {
	    Resultado solucion = new Resultado(origen);
	    LinkedList<Arista> activada = new LinkedList<>();
	    HashSet<String> verticesSol = new HashSet<>();
	    verticesSol.add(origen);

	    String actual = origen;
	    while (solucion.size() < numeroVertices() - 1) {
	        HashSet<String> vecinos = getNeighbors(actual);
	        for (String destino : vecinos) {
	            if (verticesSol.contains(destino))
	                continue;
	            Double peso = getWeight(actual, destino);
	            activada.add(new Arista(actual, destino, peso));
	        }
	        Arista aristaMenor = new Arista(null, null, Double.MAX_VALUE);
	        for (Arista a : activada) {
	            if (aristaMenor.getPeso() > a.getPeso()) {
	                aristaMenor = a;
	            }
	        }

	        solucion.addArista(aristaMenor);
	        activada.remove(aristaMenor);
	        verticesSol.add(aristaMenor.getDestino());
	        actual = aristaMenor.getDestino();
	        Iterator<Arista> it = activada.iterator();
	        while (it.hasNext()) {
	            Arista a = it.next();
	            if (verticesSol.contains(a.getOrigen()) && verticesSol.contains(a.getDestino())) {
	                it.remove();
	            }
	        }
	    }
	    return solucion;
	}

	/**
	 * Prim V 2.
	 *
	 * @param origen the origen
	 * @return the resultado
	 */
	public Resultado primV2(String origen) {
		Resultado solucion = new Resultado(origen);
		HashMap<String, Double> claves = new HashMap<>();
		HashMap<String, String> padres = new HashMap<>();
		HashSet<String> visitados = new HashSet<>();

		for (String vertice : getVertices()) {
			claves.put(vertice, Double.MAX_VALUE);
		}
		claves.put(origen, 0.0);

		for (int i = 0; i < numeroVertices(); i++) {
			String desde = claveMenor(claves, visitados);
			if (desde == null)
				break; // Añadido para manejar si no hay más vértices accesibles
			visitados.add(desde);

			for (String vecino : getNeighbors(desde)) {
				if (!visitados.contains(vecino)
						&& getWeight(desde, vecino) < claves.getOrDefault(vecino, Double.MAX_VALUE)) {
					claves.put(vecino, getWeight(desde, vecino));
					padres.put(vecino, desde);
				}
			}
		}

		for (Entry<String, String> entry : padres.entrySet()) {
			String destino = entry.getKey();
			String desde = entry.getValue();
			if (desde != null && destino != null) {
				solucion.addArista(new Arista(desde, destino, getWeight(desde, destino)));
			}
		}

		return solucion;
	}

	/*public Resultado primPQ(String origen) {
	    Resultado solucion = new Resultado(origen);
	    PriorityQueue<Arista> activada = new PriorityQueue<>();
	    HashSet<String> verticesSol = new HashSet<>();
	    verticesSol.add(origen);

	    String actual = origen;
	    while (solucion.size() < numeroVertices() - 1) {
	        HashSet<String> vecinos = getNeighbors(actual);
	        for (String destino : vecinos) {
	            if (verticesSol.contains(destino))
	                continue;
	            Double peso = getWeight(actual, destino);
	            activada.add(new Arista(actual, destino, peso));
	        }

	        Arista aristaMenor = activada.poll();
	        solucion.addArista(aristaMenor);
	        verticesSol.add(aristaMenor.getDestino());
	        actual = aristaMenor.getDestino();
	        Iterator<Arista> it = activada.iterator();
	        while (it.hasNext()) {
	            Arista a = it.next();
	            if (verticesSol.contains(a.getOrigen()) && verticesSol.contains(a.getDestino())) {
	                it.remove();
	            }
	        }
	    }
	    return solucion;
	}*/


	/**
	 * Prim PQV 2.
	 *
	 * @param origen the origen
	 * @return the resultado
	 */
	public Resultado primPQV2(String origen) {
		Resultado solucion = new Resultado(origen);
		TreeMap<Double, HashSet<String>> claves = new TreeMap<>();
		HashMap<String, String> padres = new HashMap<>();
		HashSet<String> visitados = new HashSet<>();
		claves.put(0.0, new HashSet<>());
		claves.get(0.0).add(origen);
		padres.put(origen, null);
		for (int i = 0; i < numeroVertices(); i++) {
			String desde = menorClavePQ(claves, visitados);
			if (desde == null) {
				break;
			}
			visitados.add(desde);
			for (String vecino : getNeighbors(desde)) {
				if (!visitados.contains(vecino)) {
					Double peso = getWeight(desde, vecino);
					String padre = padres.get(vecino);
					Double pesoOld = padre == null ? null : getWeight(padre, vecino);
					if (pesoOld == null || peso < pesoOld) {
						if (pesoOld != null && claves.containsKey(pesoOld)) {
							claves.get(pesoOld).remove(vecino);
							if (claves.get(pesoOld).isEmpty()) {
								claves.remove(pesoOld);
							}
						}
						HashSet<String> temp = claves.get(peso);
						if (temp == null) {
							claves.put(peso, temp = new HashSet<>());
						}
						temp.add(vecino);
						padres.put(vecino, desde);
					}
				}
			}
		}
		for (Entry<String, String> arista : padres.entrySet()) {
			if (arista.getValue() != null) {
				solucion.addArista(
						new Arista(arista.getValue(), arista.getKey(), getWeight(arista.getValue(), arista.getKey())));
			}
		}

		return solucion;
	}

	/**
	 * Kruskal.
	 *
	 * @param origen the origen
	 * @return the resultado
	 */
	public Resultado kruskal(String origen) {

		Resultado resultado = new Resultado(origen);
		PriorityQueue<Arista> pq = new PriorityQueue<>();
		HashMap<String, String> vertices = new HashMap<>();
		HashMap<String, Integer> grupos = new HashMap<>();

		for (Entry<String, HashMap<String, Double>> it : grafo.entrySet()) {
			String desde = it.getKey();
			vertices.put(desde, desde);
			grupos.put(desde, 1);
			for (Entry<String, Double> it2 : it.getValue().entrySet()) {
				String hasta = it2.getKey();
				Double w = it2.getValue();
				if (desde.compareTo(hasta) > 0)
					continue;
				pq.add(new Arista(desde, hasta, w));
			}
		}
		while (resultado.size() < grafo.size() - 1) {
			Arista arista = pq.poll();
			String raiz = buscarRaiz(vertices, arista.getOrigen());
			String raiz2 = buscarRaiz(vertices, arista.getDestino());
			if (!raiz.equals(raiz2)) {
				resultado.addArista(arista);
				int tam = grupos.get(raiz);
				int tam2 = grupos.get(raiz2);
				if (tam < tam2) {
					vertices.put(raiz, raiz2);
					grupos.put(raiz2, tam + tam2);
				} else {
					vertices.put(raiz2, raiz);
					grupos.put(raiz, tam + tam2);
				}
			}

		}
		return resultado;
	}

	/**
	 * Greedy TSP.
	 *
	 * @param origen the origen
	 * @return the resultado
	 */
	public Resultado greedyTSP(String origen) {
		Resultado solucion = new Resultado(origen);
		HashSet<String> visitados = new HashSet<>();
		String actual = origen;
		visitados.add(actual);

		while (visitados.size() < numeroVertices()) {
			String siguiente = verticeMasCercano(visitados, actual);
			if (siguiente == null)
				break;
			Double peso = getWeight(actual, siguiente);
			solucion.addArista(actual, siguiente, peso);
			visitados.add(siguiente);
			actual = siguiente;
		}

		Double peso = getWeight(actual, origen);
		if (peso == null || peso < 0)
			throw new RuntimeException("No es posible regresar al origen desde " + actual);
		solucion.addArista(actual, origen, peso);

		return solucion;
	}

	/**
	 * Simple paths.
	 *
	 * @param origen the origen
	 * @return the resultado
	 */
	public Resultado simplePaths(String origen) {
		if (origen == null || !grafo.containsKey(origen))
			return null;
		Resultado resultado = new Resultado(origen);
		for (String destino : grafo.get(origen).keySet()) {
			simplePaths(new LinkedList<String>(), resultado, origen, destino, origen);
		}
		return resultado;
	}
}
