import java.util.List;

// Dobbiamo creare una nuova interfaccia che useremo per le ricerche in
// liste ordinate. Potremo infatti sfruttare le proprieta' di ordinamento
// per migliorare le prestazioni. Tuttavia dato che dovremo usare il
// metodo compareTo dobbiamo richiedere che il tipo generico della List
// implementi Comparable. La dicitura <? super T> serve ad indicare che
// il tipo generico implementi Comparable per un suo supertipo (utile
// per le gerarchie).

/**
 * Interfaccia Algoritmo per la ricerca di un elemento in una List ordinata.
 * @author Enrico Bacis
 */
public interface OrderedSearchAlgorithm
{
	public <T extends Comparable<? super T>> boolean searchFor(List<T> collection, T element);
}
