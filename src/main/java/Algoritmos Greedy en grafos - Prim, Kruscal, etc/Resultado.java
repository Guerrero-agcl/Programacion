package org.eda2.practica02;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Resultado.
 */
public class Resultado {

	/** The origen. */
	private String origen;

	/** The set. */
	private HashSet<Arista> set;

	/** The total. */
	private double total;

	/**
	 * Instantiates a new resultado.
	 *
	 * @param origen the origen
	 */
	public Resultado(String origen) {
		this.origen = origen;
		this.set = new HashSet<>();
		this.total = 0.0;
	}

	/**
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Gets the aristas.
	 *
	 * @return the aristas
	 */
	public Set<Arista> getAristas() {
		return set;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Clear.
	 */
	public void clear() {
		set.clear();
		total = 0.0;
	}

	/**
	 * Adds the arista.
	 *
	 * @param a the a
	 */
	public void addArista(Arista a) {
		set.add(a);
		total += a.getPeso();
	}

	/**
	 * Adds the arista.
	 *
	 * @param origen  the origen
	 * @param destino the destino
	 * @param peso    the peso
	 */
	public void addArista(String origen, String destino, double peso) {
		Arista nuevaArista = new Arista(origen, destino, peso);
		if (set.add(nuevaArista)) {
			total += peso;
		}
	}

	/**
	 * Contains arista.
	 *
	 * @param origen  the origen
	 * @param destino the destino
	 * @return true, if successful
	 */
	public boolean containsArista(String origen, String destino) {
		for (Arista a : this.set) {
			if (a.getOrigen().equals(origen) && a.getDestino().equals(destino)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return this.set.size();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "\nResultado:\nOrigen = " + origen + "\nSet = " + set + "\nPeso Total = " + total + "\n";
	}

	/**
	 * Iterator.
	 *
	 * @return the iterator
	 */
	public Iterator<Arista> iterator() {
		return set.iterator();
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(origen, set, total);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resultado other = (Resultado) obj;
		return Objects.equals(origen, other.origen) && Objects.equals(set, other.set)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}

	/**
	 * Copy.
	 *
	 * @param resultado the resultado
	 */
	public void copy(Resultado resultado) {
		this.origen = resultado.origen;
		this.set = new HashSet<>(resultado.set);
		this.total = resultado.total;
	}
}
