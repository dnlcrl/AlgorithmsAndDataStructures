
public class OrdinamentoDemo {
	public static void main(String argc[]){
		Integer[] A = {1,2,3,4,5,6,7};
		System.out.println("Array da ordinare con MergeSort:");
		for (int i =0; i< A.length; i++)
			System.out.print(A[i]+" ");
		
		long tempo = System.nanoTime();
		Ordinamento o = new OrdinamentoMergeSort();
		o.sort(A);
		tempo = System.nanoTime()-tempo; //notate il diverso ruolo di "tempo"
		
		System.out.println("\nArray ordinato:");
		for (int i =0; i< A.length; i++)
			System.out.print(A[i]+" ");	
	    System.out.println("\nTempo MergeSort in ms=" + (double)tempo/1000000);
  
	    Integer[] B = {1,2,3,4,5,6,7};
	    System.out.println("Array da ordinare con QuickSort:");
		for (int i =0; i< B.length; i++)
			System.out.print(B[i]+" ");
		
		tempo = System.nanoTime();
		Ordinamento o2 = new OrdinamentoQuickSort();
		o2.sort(B);
		tempo = System.nanoTime()-tempo; //notate il diverso ruolo di "tempo"
		
		System.out.println("\nArray ordinato:");
		for (int i =0; i< B.length; i++)
			System.out.print(B[i]+" ");	
	    System.out.println("\nTempo QuickSort in ms=" + (double)tempo/1000000);
	
	    Integer[] C = {3,5,1,6,2,7,4};
	    System.out.println("Array da ordinare con QuickSort:");
		for (int i =0; i< C.length; i++)
			System.out.print(C[i]+" ");
		
		tempo = System.nanoTime();
		o2.sort(C);
		tempo = System.nanoTime()-tempo; //notate il diverso ruolo di "tempo"
		
		System.out.println("\nArray ordinato:");
		for (int i =0; i< C.length; i++)
			System.out.print(C[i]+" ");	
	    System.out.println("\nTempo QuickSort in ms=" + (double)tempo/1000000);
	    
	    Integer[] D = {3,5,1,6,2,7,4};
	    System.out.println("Array da ordinare con MergeSort:");
		for (int i =0; i< D.length; i++)
			System.out.print(D[i]+" ");
		
		tempo = System.nanoTime();
		o.sort(D);
		tempo = System.nanoTime()-tempo; //notate il diverso ruolo di "tempo"
		
		System.out.println("\nArray ordinato:");
		for (int i =0; i< D.length; i++)
			System.out.print(D[i]+" ");	
	    System.out.println("\nTempo MergeSort in ms=" + (double)tempo/1000000);

	}
}
