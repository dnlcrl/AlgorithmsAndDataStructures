
public class OrdinamentoQuickSort implements Ordinamento{
	
	
	/**
	 * Ordina <em>in loco </em> l'array di input utilizzando l'algoritmo di quick sort (<font color=red>Tempo O(n&sup2;), tempo atteso O(n&middot;log(n)</font>).
	 * L'ordinamento avviene richiamando internamente il metodo ricorsivo <code>quickSortRec</code>.
	 * 
	 * @param A l'array da ordinare
	 */	
	public void sort(Comparable A[]) {
		quickSort(A, 0, A.length - 1);
	}

	/**
	 * Ordina <em>in loco </em> una porzione dell'array di input
	 * utilizzando l'algoritmo di quick sort.
	 * L'ordinamento avviene invocando il metodo <code>partition</code> 
	 * per dividere la sequenza da ordinare in due sottosequenze
	 * nelle quali tutti gli elementi della prima sottosequenza sono pi&ugrave;
	 * piccoli di tutti gli elementi della seconda sottosequenza. 
	 * Le due sottosequenze sono a loro volta ordinate mediate una chiamata ricorsiva
	 * dello stesso metodo <code>quickSortRec</code>
	 * 
	 * @param A l'array da ordinare
	 * @param i l'indice dell'estremo inferiore della sequenza da ordinare
	 * @param f l'indice dell'estremo superiore della sequenza da ordinare
	 * 
	 */
	private void quickSort(Comparable[] A, int i, int f) {
		if (i >= f) return;
		int m = partition(A, i, f);
		quickSort(A, i, m - 1);
		quickSort(A, m+1, f);
	}
	
	/**
	 * Partiziona <em>in loco</em> gli elementi di una sequenza di input in due sottosequenze
	 * nelle quali tutti gli elementi della prima sottosequenza sono pi&ugrave; piccoli di
	 * tutti gli elementi della seconda sottosequenza. L'elemento perno viene scelto
	 * come elemento casuale all'interno della sequenza da ordinare.
	 * 
	 * @param A l'array contenente la sottosequenza da ordinare
	 * @param i l'indice dell'estremo inferiore della sottosequenza da ordinare
	 * @param f l'indice dell'estremo superiore della sottosequenza da ordinare
	 * @return la posizione dell'elemento perno nella sequenza partizionata
	 */
	private static int partition(Comparable A[], int i, int f) {
		int inf = i, 
		    sup = f + 1, 
		    pos = i + (int) Math.floor((f-i+1) * Math.random()); //indice random tra 0 e f-i+1
		Comparable temp, x = A[pos];
		A[pos] = A[i];
		A[i] = x;
		while (true) {
			do {inf++;}
			while (inf <= f && A[inf].compareTo(x) <= 0);
			do {sup--;}
			while (A[sup].compareTo(x) > 0);
			if (inf < sup) {
				temp = A[inf];
				A[inf] = A[sup];
				A[sup] = temp;
			} else
				break;
		}
		temp = A[i];
		A[i] = A[sup];
		A[sup] = temp;
		return sup;
	}
}
