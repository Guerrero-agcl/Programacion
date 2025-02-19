package org.eda2.practica03;

import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Resultado.
 */
public class Resultado {

	/** The beneficio total. */
	private double beneficioTotal;

	/** The peso total. */
	private double pesoTotal;

	/** The objetos. */
	private LinkedList<Objeto> objetos;

	/**
	 * Instantiates a new resultado.
	 */
	public Resultado() {
		this.objetos = new LinkedList<>();
		this.beneficioTotal = 0;
		this.pesoTotal = 0;
	}

	/**
	 * Agregar objeto.
	 *
	 * @param objeto the objeto
	 */
	public void agregarObjeto(Objeto objeto) {
		objetos.addLast(objeto);
		beneficioTotal += objeto.getBeneficio();
		pesoTotal += objeto.getPeso();
	}

	/**
	 * Gets the beneficio total.
	 *
	 * @return the beneficio total
	 */
	public double getBeneficioTotal() {
		return beneficioTotal;
	}

	/**
	 * Gets the peso total.
	 *
	 * @return the peso total
	 */
	public double getPesoTotal() {
		return pesoTotal;
	}

	/**
	 * Mostrar resultado.
	 */
	public void mostrarResultado() {
		System.out.println("Resultado del camión:");
		System.out.println("Beneficio Total: " + beneficioTotal);
		System.out.println("Peso Total: " + pesoTotal);
		System.out.println("Objetos Seleccionados:");
		for (Objeto objeto : objetos) {
			System.out.println(objeto);
		}
	}

	/**
	 * Sets the beneficio total.
	 *
	 * @param beneficioTotal the new beneficio total
	 */
	public void setBeneficioTotal(double beneficioTotal) {
		this.beneficioTotal = beneficioTotal;
	}

	/**
	 * Sets the peso total.
	 *
	 * @param pesoTotal the new peso total
	 */
	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	/**
	 * Prints the objects.
	 */
	public void printObjects() {
		System.out.println("CAMIÓN DE CAPACIDAD (" + this.pesoTotal + ")");
		for (Objeto obj : this.objetos) {
			System.out.println(obj);
		}
		System.out.println("BENEFICIO TOTAL: " + this.beneficioTotal);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Resultado [");
		sb.append("beneficioTotal=").append(beneficioTotal);
		if (pesoTotal != 0.0) {
			sb.append(", pesoTotal=").append(pesoTotal);
		}
		if (objetos != null && !objetos.isEmpty()) {
			sb.append(", objetos=").append(objetos);
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Gets the objetos.
	 *
	 * @return the objetos
	 */
	public LinkedList<Objeto> getObjetos() {
		return objetos;
	}

	/**
	 * Sets the objetos.
	 *
	 * @param objetos the new objetos
	 */
	public void setObjetos(List<Objeto> objetos) {
		this.objetos = new LinkedList<>(objetos);
	}

}