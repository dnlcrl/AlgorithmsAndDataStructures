import java.util.List;

/**
 * Interfaccia Algoritmo per la ricerca di un elemento in una List.
 * @author Enrico Bacis
 */
public interface SearchAlgorithm
{
	public <T> boolean searchFor(List<T> collection, T element);
}
