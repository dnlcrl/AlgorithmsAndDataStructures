
public class OrdinamentoMergeSort implements Ordinamento {
	
	
	/**
	 * Ordina l'array di input utilizzando l'algoritmo di merge sort.
	 * L'ordinamento avviene richiamando internamente il metodo ricorsivo <code>mergeSort</code>.
	 * 
	 * @param A l'array da ordinare
	 */
	public void sort(Comparable A[]) {
		mergeSort(A, 0, A.length - 1);
	}

	/**
	 * L'ordinamento avviene richiamando ricorsivamente lo stesso metodo
	 * <code>mergeSort</code>, per l'ordinamento di sottosequenze di <code>A</code>, ed il metodo
	 * <code>merge</code>, per la fusione di sottosequenze ordinate.
	 * 
	 * @param A l'array da ordinare
	 * @param p l'indice dell'estremo inferiore della sequenza da ordinare
	 * @param r l'indice dell'estremo superiore della sequenza da ordinare
	 * 
	 */
	private void mergeSort(Comparable A[], int p, int r) {
	 if (p < r){ 
 	    int q = (p + r) / 2;
	    mergeSort(A, p, q);
		mergeSort(A, q + 1, r);
		merge(A, p, q, r);
	   }
	}
	
	/**
	 * Fonde due sottosequenze di elementi consecutivi appartenenti
	 * ad uno stesso array di input in un'unica sequenza ordinata.
	 * La sequenza prodotta dalla fusione viene collocata nelle
	 * posizioni originariamente occupate dalle due sottosequenze.
	 * Assume che gli elementi delle due sottosequenze siano
	 * ordinati
	 * 
	 * @param A l'array contenente le sottosequenze da ordinare
	 * @param i1 l'indice dell'estremo inferiore della prima sottosequenza
	 * @param f1 l'indice dell'estremo superiore della prima sottosequenza
	 * @param f2 l'indice dell'estremo superiore della seconda sottosequenza
	 */
	private void merge(Comparable A[], int i1, int f1, int f2) {
		Comparable[] X = new Comparable[f2 - i1 + 1]; //Array X di appoggio
		int i = 0, //indice di scansione di X
		    k = i1, //indice di scansione della prima sottosequenza A[i1..f1]
		    i2 = f1 + 1; //indice di scansione per la seconda sottosequenza A[f1+1..f2]
	    while (i1 <= f1 && i2 <= f2) {
			if (A[i1].compareTo(A[i2]) < 0)
				X[i++] = A[i1++];
				 
			else X[i++] = A[i2++];
		}
		//Copia degli elementi rimanenti
	    if (i1 <= f1) //La sottosequenza in cui rimangono elementi è A[i1..f1]
			for (int j = i1; j <= f1; j++, i++) X[i] = A[j];
		else //La sottosequenza in cui rimangono elementi potrebbe essere A[f1+1..f2] 
			for (int j = i2; j <= f2; j++, i++) X[i] = A[j];
		
		//Copia finale di X in A
		for (int t = 0; k <= f2; k++, t++) 
			A[k] = X[t]; 
	}
}
