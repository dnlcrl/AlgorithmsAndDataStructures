import java.util.LinkedList;
import java.util.List;


public class OrdinamentoLineare {
	
	
	
	
	/**
	 * Ordina l'array di input utilizzando l'algoritmo di integer sort (<font color=red>Tempo O(n+k)</font>).
	 * Assume che gli elementi dell'array abbiano tutti valore nell'intervallo
	 * [0, k - 1], dove k &egrave; indicato da input. 
	 *  
	 * @param A l'array da ordinare
	 * @param k il valore massimo assumibile dagli elementi di <code>A</code>
	 * @throws ArrayOutOfBoundsException se <code>A</code> contiene elementi il cui valore non &egrave; incluso nell'intervallo [0, k - 1]
	 * 
	 * 
	 */

	public static void integerSort(int A[], int k) {
		int[] Y = new int[k];
		for (int i = 0; i < k; i++) Y[i] = 0;//Fase 1: calcolo di Y
		for (int i = 0; i < A.length; i++) Y[A[i]]++;
		for (int i = 0, j = 0; i < k; i++) {//Fase 2: Ricostruzione di X
			while (Y[i] > 0) {
				A[j] = i;
				j++;
				Y[i]--;
			}
		}
	}
	

	public static void main(String argc[]){
		int[] A = {5,5,3,1,5,6,8,7}; //[0, k - 1] con k=9
		System.out.println("Array da ordinare con IntegerSort:");
		for (int i =0; i< A.length; i++)
			System.out.print(A[i]+" ");
		
		long tempo = System.nanoTime();
		integerSort(A,9);
		tempo = System.nanoTime()-tempo; //notate il diverso ruolo di "tempo"
		
		System.out.println("\nArray ordinato:");
		for (int i =0; i< A.length; i++)
			System.out.print(A[i]+" ");	
	    System.out.println("\nTempo IntegerSort in ms=" + (double)tempo/1000000);
	}
}
