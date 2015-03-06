import java.util.List;

/**
 * Ricerca Sequenziale.
 * @author Enrico Bacis
 */
public class RicercaSequenziale implements SearchAlgorithm
{
	@Override
	public <T> boolean searchFor(List<T> collection, T element)
	{
		for (T x : collection)
		{
			if (x.equals(element))
				return true;
		}
		
		return false;
	}

}
