package org.eda2.practica02;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Arista.
 */
public class Arista implements Comparable<Arista> {

	/** The origen. */
	private String origen;

	/** The destino. */
	private String destino;

	/** The peso. */
	private double peso;

	/**
	 * Instantiates a new arista.
	 *
	 * @param origen  the origen
	 * @param destino the destino
	 * @param peso    the peso
	 */
	public Arista(String origen, String destino, double peso) {

		this.origen = origen;
		this.destino = destino;
		this.peso = peso;

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
	 * Gets the destino.
	 *
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(destino, origen, peso);
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
		Arista other = (Arista) obj;
		return Objects.equals(destino, other.destino) && Objects.equals(origen, other.origen)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "\nArista [Origen = " + origen + ", Destino = " + destino + ", Peso = " + peso + "]";
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(Arista o) {
		int pesoCompare = Double.compare(this.peso, o.peso);
		if (pesoCompare != 0) {
			return pesoCompare;
		}
		int origenCompare = this.origen.compareTo(o.origen);
		if (origenCompare != 0) {
			return origenCompare;
		}
		return this.destino.compareTo(o.destino);
	}

}
