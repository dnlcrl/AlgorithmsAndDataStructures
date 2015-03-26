import java.util.List;

/**
 * Ricerca Binaria per Iterazione (in una List ordinata).
 * @author Enrico Bacis
 */
public class RicercaBinariaIter implements OrderedSearchAlgorithm
{
	// Anche se la richiesta dell'esercizio era di usare gli Iterator,
	// in questa classe non verranno usati per due motivi, il primo e'
	// quello spiegato approfonditamente nella classe VerificaDupList
	// per cui con una List, ilmodo piu' efficiente per accedere a degli
	// elementi e' il metodo get. Nel caso invece la List sia una instance
	// di LinkedList, comunque la non sequenzialita' intrinseca dell'accesso
	// ai valori utilizzato per la ricerca binaria nullifica l'efficienza
	// degli Iterator, dato che dovremmo sempre creare degli Iterator
	// a partire da un indice, e dato che nella LinkedList (unico caso
	// in cui useremmo effettivamente gli iteratori) questo metodo ha
	// un tempo lineare, allora preferiamo usare get (che in questo caso
	// ha anch'esso tempo lineare) per non differenziare l'algoritmo
	// inutilmente.
	
	// Per non appesantire la struttura la ricerca ammette che il
	// numero di elementi della lista sia una potenza intera di 2.
	
	@Override
	public <T extends Comparable<? super T>> boolean searchFor(List<T> collection, T element)
	{
		// L'implementazione sull'algoritmo sulle slide prevede che il primo
		// elemento della lista occupi la posizione 1, tuttavia in Java il
		// primo elemento di una List occupa la posizione 0, quindi a dovra' essere
		// inizializzato a 0, stesso discorso per il valore massimo.
		
		int a = 0;
		int b = collection.size() - 1;
		int m;
		
		while (!collection.get((a+b)/2).equals(element))
		{
			m = (a+b)/2;
			
			if (collection.get(m).compareTo(element) > 0)
				b = m-1;
			else
				a = m+1;
			
			if (a > b)
				return false;
		}
		
		return true;
	}

}
