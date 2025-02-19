package org.eda2.practica03;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Objeto.
 */
public class Objeto {

	/** The nombre. */
	private String nombre;

	/** The peso. */
	private double peso;

	/** The beneficio. */
	private double beneficio;

	/** The cantidad. */
	private double cantidad;

	/**
	 * Instantiates a new objeto.
	 *
	 * @param nombre    the nombre
	 * @param peso      the peso
	 * @param beneficio the beneficio
	 */
	public Objeto(String nombre, double peso, double beneficio) {
		this.nombre = nombre;
		this.peso = peso;
		this.beneficio = beneficio;
		this.cantidad = 1;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
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
	 * Gets the beneficio.
	 *
	 * @return the beneficio
	 */
	public double getBeneficio() {
		return beneficio;
	}

	/**
	 * Sets the beneficio.
	 *
	 * @param beneficio the new beneficio
	 */
	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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
		Objeto otro = (Objeto) obj;
		return Objects.equals(nombre, otro.nombre);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		if (cantidad == 1) {
			return String.format("%-15s p=%-10.1f b=%-10.1f", nombre, (peso * cantidad), (beneficio * cantidad));
		} else {
			return String.format("%-15s p=%-10.1f b=%-10.1f, q=$-10.2f", nombre, (peso * cantidad),
					(beneficio * cantidad), cantidad);
		}
	}
}