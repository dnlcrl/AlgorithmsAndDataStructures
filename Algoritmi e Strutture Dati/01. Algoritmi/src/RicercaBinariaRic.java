import java.util.List;

/**
 * Ricerca Binaria Ricorsiva (in una List ordinata).
 * @author Enrico Bacis
 */
public class RicercaBinariaRic implements OrderedSearchAlgorithm
{
	// Anche in questo caso si utilizzera' la funzione get per quanto spiegato
	// abbondantemente nella classe RicercaBinariaIter.
	
	@Override
	public <T extends Comparable<? super T>> boolean searchFor(List<T> collection, T element)
	{
		if (collection.size() == 0)
			return false;
		
		// Anche in questo caso dobbiamo decrementare la n dato che le posizioni delle
		// List arrivano a n-1.
		
		int n = collection.size() - 1;
		
		int i = n/2;
		T obj = collection.get(i);
		
		// Si noti che questo metodo e' completamente inefficiente, infatti
		// ogni volta deve copiare tutti gli elementi della sottolista in
		// una nuova lista, e questo tempo e' lineare, potremmo correggere
		// questa disfunzione cambiando la segnatura del metodo e passandogli
		// sempre l'intera lista (di cui quindi non verrebbe fatta la copia)
		// e gli estremi tra cui verificare.
		// Non e' stato fatto per non stravolgere la funzione di partenza.
		//
		// Notiamo che gli intervalli sono diversi rispetto a quelli riportati
		// sulle slides, infatti la funzione subList(fromIndex, toIndex) crea
		// una sottolista partenda da fromIndex incluso e arrivando a toIndex
		// escluso. Quindi l'estremo superiore e' stato incrementato di una unita'.
		
		if (obj.equals(element))
			return true;
		else if (obj.compareTo(element) > 0)
			return searchFor(collection.subList(0, i), element);
		else
			return searchFor(collection.subList(i+1, n+1), element);
	}
}
