import java.util.List;

/**
 * Interfaccia Algoritmo per la ricerca dei Duplicati in una List.
 * @author Enrico Bacis
 */
public interface DuplicateAlgorithm
{
	public <T> boolean verificaDup(List<T> collection);
}
