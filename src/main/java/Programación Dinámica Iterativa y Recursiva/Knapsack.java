package org.eda2.practica03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Knapsack. Representa el problema de la mochila
 */
public class Knapsack {
	
	/** Lista de objetos que se quieren valorar. */
	private List<Objeto> objetos;
	
	/** La capacidad de la mochila. */
	private int P;
	
	/** Tabla auxiliar para desarrollar la memorización de resultados. */
	private double[][] tabla;
	
	/** Array para mochila unidimensional. */
	private double[] array;
	
	/**
	 * Instancia una nueva mochila vacia y sin capacidad.
	 */
	public Knapsack() {
		this.objetos = new ArrayList<>();
		this.P = 0;
	}
	
	/**
	 * Instancia una nueva mochila con una capacidad concreta.
	 *
	 * @param P la capacidad máxima de la mochila
	 */
	public Knapsack(int P) {
		this();
		this.P = P;
	}
	
	/**
	 * Instancia una nueva mochila con los beneficios y pesos de los
	 * objetos así como la capacidad máxima de la misma.
	 *
	 * @param beneficios el array de beneficios de cada objeto
	 * @param pesos el array de pesos de cada objeto
	 * @param P la capacidad de la mochila
	 */
	public Knapsack(double[] beneficios, int[] pesos, int P) {
		this(P);
		for (int i = 0; i < beneficios.length; i++) {
			Objeto obj = new Objeto("Objeto "+i, pesos[i], beneficios[i]);
			objetos.add(obj);
		}
	}
	
	/**
	 * Instancia una nueva mochila a partir de los archivos de prueba
	 * disponibles en la web.
	 *
	 * @param basefilename el nombre base del archivo
	 */
	public Knapsack(String basefilename) {
		this.objetos = new ArrayList<>();
		Scanner sc;
		try {
			File f = new File(basefilename+"_c.txt");
			sc = new Scanner(f);
			this.P = Integer.parseInt(sc.nextLine().trim());
			sc.close();
			f = new File(basefilename+"_w.txt");
			sc = new Scanner(f);
			int i = 1;
			while(sc.hasNextLine()) {
				this.objetos.add(new Objeto("Objeto "+i, Double.parseDouble(sc.nextLine().trim()), 0));
				i++;
			}
			sc.close();
			f = new File(basefilename+"_p.txt");
			sc = new Scanner(f);
			i = 0;
			while (sc.hasNextLine()) {
				this.objetos.get(i).setProfit(Double.parseDouble(sc.nextLine().trim()));
				i++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Obtiene la lista de objetos.
	 *
	 * @return los objetos
	 */
	public List<Objeto> getObjetos() {
		return objetos;
	}

	/**
	 * Establece los objetos.
	 *
	 * @param objects la nueva lista de objetos
	 */
	public void setObjetos(List<Objeto> objects) {
		this.objetos = objects;
	}

	/**
	 * Obtiene la capacidad de la mochila.
	 *
	 * @return la capacidad de la mochila
	 */
	public int getP() {
		return P;
	}

	/**
	 * Establece el peso de la mochila.
	 *
	 * @param p la nueva capacidad de la mochila
	 */
	public void setP(int p) {
		P = p;
	}
	
	public void add(Objeto obj) {
		this.objetos.add(obj);
	}
	
	public void remove(Objeto obj) {
		this.objetos.remove(obj);
	}
	
	/**
	 * Obtiene un array con los pesos de los objetos de la lista.
	 *
	 * @return el array de pesos de los objetos
	 */
	public int[] getWeigths() {
		int[] p = new int[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = (int) objetos.get(i).getWeight();
		}
		return p;
	}
	
	/**
	 * Obtiene un array con los beneficios de los objetos de la lista.
	 *
	 * @return el array de beneficios de los objetos
	 */
	public double[] getProfits() {
		double[] p = new double[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = objetos.get(i).getProfit();
		}
		return p;
	}
	
	/**
	 * Obtiene el numero de objetos de la mochila.
	 *
	 * @return el numero de objetos de la mochila
	 */
	public int getN() {
		return this.objetos.size();
	}
	
	/**
	 * Obtiene la tabla de resultados.
	 *
	 * @return la tabla de resultados
	 */
	public double[][] getTabla() {
		return tabla;
	}

	/**
	 * Obtiene el array de resultados unidimensional.
	 *
	 * @return el array de resultados unidimensional
	 */
	public double[] getArray() {
		return array;
	}

	/**
	 * Devuelve una representación de la mochila junto con
	 * los objetos que están en ella contenida.
	 *
	 * @return la representación de la mochila
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MOCHILA (").append(this.P).append(")\n");
		for (Objeto objeto : objetos) {
			sb.append(objeto.toString()).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque recursivo.
	 *
	 * Este método implementa un algoritmo de programación dinámica recursiva para 
	 * resolver el problema de la mochila, también conocido como "Knapsack problem". 
	 * El problema consiste en determinar la máxima cantidad de beneficio que se 
	 * puede obtener al llenar una mochila con capacidad P con elementos de pesos p 
	 * y beneficios b.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	/* EJERCICIO 1*/
	public double camionRecursive() {
		return camionRecursive(getN(), getP(), getWeigths(), getProfits());
	}
	
	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque recursivo.
	 *
	 * Este método implementa un algoritmo de programación dinámica recursiva para 
	 * resolver el problema de la mochila, también conocido como "Knapsack problem". 
	 * El problema consiste en determinar la máxima cantidad de beneficio que se 
	 * puede obtener al llenar una mochila con capacidad P con elementos de pesos p 
	 * y beneficios b.
	 *
	 * @param n La cantidad de elementos disponibles para elegir.
	 * @param P La capacidad total de la mochila.
	 * @param p Un array de enteros que representa los pesos de los elementos.
	 * @param b Un array de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	public double camionRecursive(int n, int P, int[] p, double[] b) {
		if(n == 0 || P == 0) return 0;
		if(p[n-1] > P) {
			return camionRecursive(n-1, P, p, b);
		}
		return Math.max(camionRecursive(n-1, P - p[n-1], p, b) + b[n-1]
				, camionRecursive(n-1, P, p, b));
	}
	/* FIN EJERCICIO 1*/
	
	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica con una 
	 * tabla de memorización.
	 *
	 * Este método inicializa una tabla de memorización y luego llama al método 
	 * auxiliar `camionRec` para calcular el máximo beneficio posible.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con 
	 * los elementos disponibles.
	 */
	/* EJERCICIO 2 */
	public double camionTable() {
		return camionTable(getN(), getP(), getWeigths(), getProfits());
	}
	
	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica con una 
	 * tabla de memorización.
	 *
	 * Este método inicializa una tabla de memorización y luego llama al método 
	 * auxiliar `camionRec` para calcular el máximo beneficio posible.
	 *
	 * @param n La cantidad de elementos disponibles para elegir.
	 * @param P La capacidad total de la mochila.
	 * @param p Un arreglo de enteros que representa los pesos de los elementos.
	 * @param b Un arreglo de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con 
	 * los elementos disponibles.
	 */
	public double camionTable(int n, int P, int p[], double b[]) {
		tabla = new double[n+1][P+1];
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[0].length; j++) {
				tabla[i][j] = -1;
			}
		}
		return camionRec(n, P, p, b);
	}
	
	/**
	 * Calcula de manera recursiva el máximo beneficio posible que se puede obtener 
	 * al llenar una mochila con capacidad limitada, utilizando un enfoque de 
	 * programación dinámica con una tabla de memorización.
	 *
	 * Este método resuelve el problema de la mochila recursivamente, almacenando 
	 * los resultados de subproblemas en una tabla de memorización para evitar el 
	 * recálculo de soluciones ya conocidas.
	 *
	 * @param n La cantidad de elementos disponibles para elegir.
	 * @param P La capacidad total de la mochila.
	 * @param p Un array de enteros que representa los pesos de los elementos.
	 * @param b Un array de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con 
	 * los elementos disponibles.
	 */
	public double camionRec(int n, int P, int p[], double b[]) {
		if(n == 0 || P == 0) return 0;
		if(tabla[n][P] != -1) {
			return tabla[n][P];
		}
		if(p[n-1] > P) {
			return tabla[n][P] = camionRec(n-1, P, p, b);
		}
		double b1 = camionRec(n-1, P - p[n-1],  p, b) + b[n-1];
		double b2 = camionRec(n-1, P, p, b);
		return tabla[n][P] = Math.max(b1, b2);
	}
	/* FIN EJERCICIO 2*/
	
	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación dinámica, 
	 * donde se construye una tabla de manera iterativa para almacenar y actualizar los 
	 * resultados de subproblemas.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con 
	 * los elementos disponibles.
	 */
	/* EJERCICIO 3*/
	public double camionDP() {
		return camionDP(getP(), getWeigths(), getProfits());
	}

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación dinámica, 
	 * donde se construye una tabla de manera iterativa para almacenar y actualizar los 
	 * resultados de subproblemas.
	 *
	 * @param P La capacidad total de la mochila.
	 * @param p Un array de enteros que representa los pesos de los elementos.
	 * @param b Un array de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con 
	 * los elementos disponibles.
	 */
	public double camionDP(int P, int[] p, double[] b) {
		int i;
		int j;
		int n = b.length;
		tabla = new double[n+1][P+1];
		
		for (i = 1; i <= n; i++) {
			for (j = 1; j < p[i-1]; j++) {
				tabla[i][j] = tabla[i-1][j];
			}
			for (j = p[i-1]; j <= P; j++) {
				double b1 = tabla[i-1][j-p[i-1]] + b[i-1];
				double b2 = tabla[i-1][j];
				tabla[i][j] = Math.max(b1, b2);
			}
		}
		
		return tabla[n][P];
	}

	/**
	 * Devuelve una solución de los elementos que se incluyen en la mochila 
	 * para maximizar el beneficio, utilizando un enfoque recursivo.
	 *
	 * Este método utiliza un algoritmo recursivo para determinar qué elementos 
	 * se incluyen en la mochila para maximizar el beneficio, basándose en la 
	 * capacidad de la mochila, los pesos y los beneficios de los elementos.
	 *
	 * @return Un array de enteros que representa los índices de los elementos 
	 * incluidos en la solución óptima.
	 */
	public int[] getItemsSolutionRecursive() {
		int n = this.getN();
		int P = this.getP();
		int[] p = this.getWeigths();
		double[] b = this.getProfits();
		int[] sol = new int[n];
		test(n, P, p, b, sol);
//		printArraySolution(sol);
		return sol;
	}

	/**
	 * Prueba los elementos para determinar qué elementos se incluyen en la mochila 
	 * para maximizar el beneficio, utilizando un enfoque recursivo.
	 *
	 * Este método prueba los elementos disponibles para determinar cuáles se 
	 * incluyen en la mochila para maximizar el beneficio, considerando la capacidad 
	 * de la mochila, los pesos y los beneficios de los elementos.
	 *
	 * @param j La cantidad de elementos disponibles para considerar.
	 * @param c La capacidad restante de la mochila.
	 * @param p Un array de enteros que representa los pesos de los elementos.
	 * @param b Un array de punto flotante que representa los beneficios de los elementos.
	 * @param sol Un array de enteros que se utiliza para almacenar la solución.
	 */
	public void test(int j, int c, int[] p, double[] b, int[] sol) {
		if(j > 0) {
			if(c < p[j-1]) {
				test(j-1, c, p, b, sol);
			}else {
				if(tabla[j-1][c-p[j-1]] + b[j-1] > tabla[j-1][c]) {
					test(j-1, c-p[j-1], p, b, sol);
					sol[j-1] = 1;
				}else {
					test(j-1, c, p, b, sol);
				}
			}
		}
		
	}
	/* FIN EJERCICIO 3*/
	
	/* EJERCICIO 4 */
	/*  Los valores en la última columna de la tabla bidimensional del caso 3 deben coincidir con los valores de la última columna
	 *  de la matriz bidimensional del caso 2. Esto se debe a que en ambos casos la capacidad de la mochila es cero, por lo que 
	 *  no se puede llevar ningún elemento.
	 *  Los valores en las otras celdas de la tabla bidimensional del caso 3 son el resultado de tomar el máximo entre dos valores:
	 *  El valor obtenido en la celda de arriba (representando la misma capacidad de mochila) en la misma columna (correspondiente a 
	 *  no incluir el elemento actual).
	 *  El valor obtenido al restar el peso del elemento actual de la capacidad de la mochila y obtener el beneficio acumulado 
	 *  hasta ese punto (correspondiente a incluir el elemento actual).
	 *  Este comportamiento es análogo al algoritmo con memorización en el caso 2, donde tomamos el máximo entre el valor obtenido 
	 *  sin incluir el elemento actual y el valor obtenido al incluir el elemento actual.
	 *  Dado que ambos enfoques deben proporcionar la misma solución óptima para el problema de la mochila, podemos decir que hay 
	 *  una clara relación entre los valores obtenidos en la tabla bidimensional del caso 3 y los valores de la matriz bidimensional 
	 *  del caso 2. La única diferencia radica en cómo se organizan y calculan los valores en cada estructura de datos, pero ambos 
	 *  métodos convergen hacia la misma solución. 
	 */
	/* FIN EJERCICIO 4 */

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una 
	 * mochila con capacidad limitada, utilizando un enfoque de programación 
	 * dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación 
	 * dinámica, donde se construye una tabla de manera iterativa para almacenar 
	 * y actualizar los resultados de subproblemas.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	/* EJERCICIO 5*/
	public double camionDP2() {
		return camionDP2(getP(), getWeigths(), getProfits());
	}

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una 
	 * mochila con capacidad limitada, utilizando un enfoque de programación 
	 * dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación 
	 * dinámica, donde se construye una tabla de manera iterativa para almacenar 
	 * y actualizar los resultados de subproblemas.
	 *
	 * @param P La capacidad total de la mochila.
	 * @param p Un array de enteros que representa los pesos de los elementos.
	 * @param b Un array de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	public double camionDP2(int P, int[] p, double[] b) {
		int n = b.length;
		tabla = new double[n+1][P+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= P; j++) {
				tabla[i][j] = tabla[i-1][j];
				if(p[i-1] <= j) {
					double b1 = tabla[i-1][j-p[i-1]] + b[i-1];
					double b2 = tabla[i][j];
					tabla[i][j] = Math.max(b1, b2);
				}
			}
		}
		
		return tabla[n][P];
	}
	
	/**
	 * Devuelve una solución de los elementos que se incluyen en la mochila para 
	 * maximizar el beneficio, utilizando un enfoque iterativo.
	 *
	 * Este método utiliza un enfoque iterativo para determinar qué elementos 
	 * se incluyen en la mochila para maximizar el beneficio, basándose en la 
	 * tabla de programación dinámica generada previamente.
	 *
	 * @return Un array de enteros que representa los índices de los elementos 
	 * incluidos en la solución óptima.
	 */
	public int[] getItemsSolutionIterative() {
		int[] sol = new int[getN()];
		int j = tabla[0].length - 1;
		String s = "";
		for (int i = tabla.length - 1; i > 0; i--) {
			if (tabla[i][j] != tabla[i - 1][j]) {
				sol[i-1] = 1;
				s = "1, "+s;
				j -= objetos.get(i-1).getWeight();
			}else {
				sol[i-1] = 0;
				s = "0, "+s;
			}
		}
		return sol;
	}

	/* FIN EJERCICIO 5*/

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación dinámica 
	 * y un array unidimensional, donde se almacena el máximo beneficio alcanzado hasta 
	 * el momento para cada capacidad de la mochila.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	/* EJERCICIO 6*/
	public double camionDP3() {
		return camionDP3(getP(), getWeigths(), getProfits());
	}

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada, utilizando un enfoque de programación dinámica (Dynamic Programming).
	 *
	 * Este método resuelve el problema de la mochila utilizando programación dinámica 
	 * y un array unidimensional, donde se almacena el máximo beneficio alcanzado hasta 
	 * el momento para cada capacidad de la mochila.
	 *
	 * @param P La capacidad total de la mochila.
	 * @param p Un arreglo de enteros que representa los pesos de los elementos.
	 * @param b Un arreglo de punto flotante que representa los beneficios de los elementos.
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila 
	 * con los elementos disponibles.
	 */
	public double camionDP3(int P, int[] p, double[] b) {
		int n = b.length;
		array = new double[P+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = P; j >= 0; j--) {
				if(p[i-1] <= j) {
					array[j] = Math.max(array[j], array[j - p[i-1]]+b[i-1]);
				}
			}
		}
		return array[P];
	}
	/*
	 * En el caso de este algoritmo de programación dinámica con un solo array unidimensional, encontrar el array Sol (donde Sol[i] 
	 * indica si el elemento i se incluye o no en el camión) no es tan sencillo como en el caso de la programación dinámica tradicional 
	 * con una tabla bidimensional.
	 * La razón principal es que este enfoque unidimensional no almacena información sobre qué elementos se incluyen o no en el camión 
	 * en cada etapa del proceso de decisión. En cambio, solo calcula el valor máximo de beneficio que se puede obtener con los elementos 
	 * disponibles.
	 * Dado que solo mantenemos un array unidimensional para calcular los valores óptimos, no tenemos una forma directa de reconstruir el 
	 * conjunto de elementos que llevamos en el camión.
	 * En la programación dinámica tradicional con una tabla bidimensional, podemos rastrear los movimientos que nos llevaron a un cierto 
	 * estado, lo que nos permite reconstruir el conjunto de elementos que llevamos. Pero en este caso unidimensional, esa información 
	 * no está explícitamente disponible.
	 * Para encontrar el conjunto de elementos incluidos en el camión, podríamos modificar el algoritmo para que, además de calcular el 
	 * valor máximo, también mantenga información sobre qué elementos se incluyen en cada etapa del proceso. Sin embargo, esto complicaría 
	 * significativamente el algoritmo y requeriría más espacio de almacenamiento.
	 */
	/* FIN EJERCICIO 6*/

	/**
	 * Calcula el máximo beneficio posible que se puede obtener al llenar una mochila 
	 * con capacidad limitada pero numero de elementos ilimitados, utilizando un enfoque 
	 * de programación dinámica (Dynamic Programming).
	 * 
	 * Este método resuelve el problema de la mochila utilizando programación dinámica y 
	 * un array unidimensional, donde se almacena el máximo beneficio alcanzado hasta el 
	 * momento para cada capacidad de la mochila.
	 *
	 * @return El máximo beneficio posible que se puede obtener al llenar la mochila con
	 * los elementos disponibles.
	 */
	/* EJERCICIO 7*/
	public double camionDPMP(){
		array = new double[P + 1];
		
		int n = this.objetos.size();
		for (int i = 0; i <= P; i++) {
			for (int j = 0; j < n; j++) {
				if(this.objetos.get(j).getWeight()  <= i) {
					array[i] = Math.max(array[i], array[i - (int)(this.objetos.get(j).getWeight()) ]+this.objetos.get(j).getProfit());
				}
			}
		}
		return array[array.length-1];
	}

	/**
	 * Recupera los elementos incluidos en la solución óptima de la mochila 
	 * basada en el máximo beneficio, utilizando un enfoque de recuperación de elementos.
	 *
	 * Este método recupera los elementos incluidos en la solución óptima de la mochila, 
	 * basándose en el máximo beneficio alcanzado y los pesos de los elementos.
	 *
	 * @return Un array de enteros que representa la cantidad de cada elemento 
	 * incluido en la solución óptima.
	 */
	public int[] retrieveItems() {
		double minWeight = 0;
		int c = P;
		int n = this.objetos.size();
		int[] sol = new int[n];
		while(c >= minWeight) {
			double maxValue = 0;
			int item = -1;
			for (int i = n-1; i >= 0; i--) {
				if(c - this.objetos.get(i).getWeight()  >= 0) {
					double newValue = array[c - (int)this.objetos.get(i).getWeight() ] + this.objetos.get(i).getProfit();
					if(newValue > maxValue) {
						maxValue = newValue;
						item = i;
					}
				}
			}
			if(item == -1) break;
			sol[item]++;
			c -= this.objetos.get(item).getWeight() ;
		}
		return sol;
	}
	/* FIN EJERCICIO 7*/
	
	/* EJERCICIO 8*/
	/* En el caso de que los pesos no sea número enteros y que se traten de valores decimales, entonces podríamos pensar a priori
	 * que el algoritmo de programación dinámica no está capacitado para resolver este problema, debido a que se considera que cada
	 * columna representa un valor exacto de peso para el que se está evaluando la mochila.
	 * Sin embargo, existen situaciones en las que se puede considerar que si el peso no es exacto, pero se conoce el número máximo
	 * de decimales con los que estarán representados, por ejemplo, dos decimales, entonces la solución podría venir dada por considerar
	 * una mochila de peso P * 10^2 y donde los pesos de los objetos también pueden considerarse multiplicados por ese valor 10^2.
	 * Por tanto, estaríamos hablando de considerar tanto el peso de la mochila como los pesos de cada objeto como un múltiplo entero de
	 * 10^m, siendo m el número de decimales que se van a considerar.
	 * Sin embargo, está técnica supone, en primer lugar considerar multiplicar todos los pesos de todos los objetos y después considerar un
	 * tamaño de tabla con P * 10^m columnas, lo cual supone un aumento considerable del espacio de memoria necesario para la tabla auxiliar.
	 * Una técnica para reducir este tamaño de la tabla es obtener el MCD de todos los pesos (incluido el peso de la mochila) para reducir
	 * el tamaño de esta tabla y permitir un mejor procesado sin tanto consumo de memoria. Pero la posibilidad de encontrar un MCD común para
	 * todos los objetos puede ser tan baja (y practicamente nula cuando se tratan de muchos objetos) que en ocasiones no suele ser rentable
	 * efectuarlo.
	 * En el caso en el que el número de decimales sea muy elevado, se pueden truncar o redondear los valores estos pesos a un numero menor de
	 * decimales, pero en esta ocasión el resultado de procesar el algoritmo no sería optimo y estariamos considerando un algoritmo de aproximación.
	 */
	/* FIN EJERCICIO 8*/
	
	/**
	 * Resuelve el problema de la mochila utilizando un enfoque codicioso (greedy),
	 * maximizando el beneficio por unidad de peso.
	 *
	 * Este método utiliza un enfoque codicioso para determinar qué elementos se 
	 * incluyen en la mochila con el objetivo de maximizar el beneficio por unidad de peso. 
	 * Devuelve una solución que indica la fracción de cada elemento que se incluye en la 
	 * mochila para alcanzar el máximo beneficio posible.
	 *
	 * @return Un array de punto flotante que representa la fracción de cada elemento 
	 * incluido en la solución óptima.
	 */
	/* EJERCICIO 9*/
	public double[] greedyKnapsack(){
		int finalWeight = 0;
		boolean[] used = new boolean[getN()];
		double[] sol = new double[getN()];
		for (int k = 0; k < objetos.size(); k++) {
			double maxProfitCost = 0;
			int item = -1;
			for (int i = 0; i < objetos.size(); i++) {
				if(used[i]) continue;
				double rel = objetos.get(i).getProfit()/objetos.get(i).getWeight();
				if(rel > maxProfitCost) {
					maxProfitCost = rel;
					item = i;
				}
			}
			used[item] = true;
			if(finalWeight + objetos.get(item).getWeight() <= P) {
				sol[item] = 1;
				finalWeight += objetos.get(item).getWeight();
			}else {
				sol[item] = (P-finalWeight)/objetos.get(item).getWeight();
				finalWeight += sol[item] * objetos.get(item).getWeight();
				break;
			}
			
		}
		return sol;
	}
	/* FIN EJERCICIO 9*/

	/**
	 * Obtiene la solución del problema de la mochila como un array de enteros.
	 *
	 * Este método recupera la solución del problema de la mochila representada en la tabla 
	 * de programación dinámica y la devuelve como un arreglo de enteros, donde cada elemento 
	 * indica si el objeto correspondiente se incluye (valor 1) o no se incluye (valor 0) en 
	 * la solución óptima.
	 *
	 * @return Un array de enteros que representa la solución del problema de la mochila.
	 */
	public int[] getSolucionAsArray() {
		int n = tabla.length-1;
		int[] sol = new int[n];
		int auxP = P;
		for (int i = n; i >= 2; i--) {
			if(tabla[i-1][auxP] == tabla[i][auxP]) {
				sol[i-1] = 0;
			}else {
				sol[i-1] = 1;
				auxP -= objetos.get(i-1).getWeight();
			}
		}
		if(tabla[1][auxP] != -1) {
			sol[0] = 1;
		}else {
			sol[0] = 0;
		}
		return sol;
	}

	/**
	 * Imprime la tabla de programación dinámica del problema de la mochila.
	 *
	 * Este método imprime la tabla de programación dinámica utilizada para resolver 
	 * el problema de la mochila. Los valores de la tabla representan los resultados 
	 * de los subproblemas, donde un valor de -1.0 indica que el subproblema no ha sido 
	 * resuelto aún.
	 */
	public void printTabla() {
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[0].length; j++) {
				if(tabla[i][j] == -1.0) {
					System.out.printf("%-5s", "X");
				}else {
					System.out.printf("%-5.1f", tabla[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Imprime los elementos de un array en una sola línea.
	 *
	 * Este método imprime los elementos de un arreglo en una sola línea, 
	 * con un formato de 1 decimal.
	 */
	public void printArray() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if(i < array.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Imprime una solución representada como un array de enteros.
	 *
	 * Este método imprime una solución representada como un array de enteros en una línea,
	 * mostrando cada elemento del array separado por comas.
	 *
	 * @param sol Un array de enteros que representa la solución.
	 */
	public void printArraySolution(int[] sol) {
		System.out.println("Solucion: ");
		for (int i = 0; i < sol.length; i++) {
			System.out.print(sol[i]);
			if(i < sol.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Imprime una solución representada como un array de decimales.
	 *
	 * Este método imprime una solución representada como un array de decimales en una línea,
	 * mostrando cada elemento del array separado por comas.
	 *
	 * @param sol Un array de decimales que representa la solución.
	 */
	public void printArraySolution(double[] sol) {
		System.out.println("Solucion: ");
		for (int i = 0; i < sol.length; i++) {
			System.out.print(sol[i]);
			if(i < sol.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Imprime los objetos que forman parte de la solución
	 *
	 * @param sol el array de solución
	 */
	public void printObjectsFromArraySolution(int[] sol) {
		System.out.println("MOCHILA DE CAPACIDAD ("+P+")");
		double profit = 0;
		int weight = 0;
		for (int i = 0; i < sol.length; i++) {
			if(sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q="+sol[i] : ""));
				profit += objetos.get(i).getProfit() * sol[i];
				weight += objetos.get(i).getWeight() * sol[i];
			}
		}
		System.out.println("BENEFICIO TOTAL: "+profit);
		System.out.println("PESO TOTAL: "+weight);
	}
	
	/**
	 * Imprime los objetos que forman parte de la solución
	 *
	 * @param sol el array de solución
	 */
	public void printObjectsFromArraySolution(double[] sol) {
		System.out.println("MOCHILA DE CAPACIDAD ("+P+")");
		double profit = 0;
		double weight = 0;
		for (int i = 0; i < sol.length; i++) {
			if(sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q="+sol[i] : ""));
				profit += objetos.get(i).getProfit() * sol[i];
				weight += objetos.get(i).getWeight() * sol[i];
			}
		}
		System.out.println("BENEFICIO TOTAL: "+profit);
		System.out.println("PESO TOTAL: "+weight);
	}

}