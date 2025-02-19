package org.eda2.practica01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TorneoTenisTestCaso3 {
	
	@Test
	public void torneoCaso3n02() {
		int n = 2;
		int[][] tabla = new int[n - 1][n];
		int[][] expected = { { 2, 1 } };

		TorneoTenis.torneoRecursivoCaso3(tabla, 1, n);
		assertArrayEquals(expected, tabla);
	}
	
	@Test
	public void torneoCaso3n04() {
		int n = 4;
		int[][] tabla = new int[n - 1][n];
		int[][] expected = { 
				{ 2, 1, 4, 3 }, 
				{ 3, 4, 1, 2 }, 
				{ 4, 3, 2, 1 }, 
			};
		TorneoTenis.torneoRecursivoCaso3(tabla, 1, n);
		assertArrayEquals(expected, tabla);
	}

	@Test
	public void torneoCaso3n08() {
		int n = 8;
		int[][] tabla = new int[n - 1][n];
		int[][] expected = { 
			    { 2, 1, 4, 3, 6, 5, 8, 7 }, 
			    { 3, 4, 1, 2, 7, 8, 5, 6 }, 
			    { 4, 3, 2, 1, 8, 7, 6, 5 }, 
			    { 5, 6, 7, 8, 1, 2, 3, 4 },
			    { 6, 7, 8, 5, 4, 1, 2, 3 },
			    { 7, 8, 5, 6, 3, 4, 1, 2 },
			    { 8, 5, 6, 7, 2, 3, 4, 1 }
			};

		TorneoTenis.torneoRecursivoCaso3(tabla, 1, n);
		assertArrayEquals(expected, tabla);
	}

	@Test
	public void torneoCaso3n16() {
		int n = 16;
		int[][] tabla = new int[n - 1][n];
		int[][] expected = { 
			    { 2, 1, 4, 3, 6, 5, 8, 7, 10, 9, 12, 11, 14, 13, 16, 15 }, 
			    { 3, 4, 1, 2, 7, 8, 5, 6, 11, 12, 9, 10, 15, 16, 13, 14 }, 
			    { 4, 3, 2, 1, 8, 7, 6, 5, 12, 11, 10, 9, 16, 15, 14, 13 }, 
			    { 5, 6, 7, 8, 1, 2, 3, 4, 13, 14, 15, 16, 9, 10, 11, 12 },
			    { 6, 7, 8, 5, 4, 1, 2, 3, 14, 15, 16, 13, 12, 9, 10, 11 },
			    { 7, 8, 5, 6, 3, 4, 1, 2, 15, 16, 13, 14, 11, 12, 9, 10 },
			    { 8, 5, 6, 7, 2, 3, 4, 1, 16, 13, 14, 15, 10, 11, 12, 9 },
			    { 9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8 },
			    { 10, 11, 12, 13, 14, 15, 16, 9, 8, 1, 2, 3, 4, 5, 6, 7 },
			    { 11, 12, 13, 14, 15, 16, 9, 10, 7, 8, 1, 2, 3, 4, 5, 6 },
			    { 12, 13, 14, 15, 16, 9, 10, 11, 6, 7, 8, 1, 2, 3, 4, 5 },
			    { 13, 14, 15, 16, 9, 10, 11, 12, 5, 6, 7, 8, 1, 2, 3, 4 },
			    { 14, 15, 16, 9, 10, 11, 12, 13, 4, 5, 6, 7, 8, 1, 2, 3 },
			    { 15, 16, 9, 10, 11, 12, 13, 14, 3, 4, 5, 6, 7, 8, 1, 2 },
			    { 16, 9, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5, 6, 7, 8, 1 }
			};

		TorneoTenis.torneoRecursivoCaso3(tabla, 1, n);
		assertArrayEquals(expected, tabla);
	}

}
