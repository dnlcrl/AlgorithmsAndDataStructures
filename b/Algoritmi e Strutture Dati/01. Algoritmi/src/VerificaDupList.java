import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Verifica Duplicati.
 * @author Enrico Bacis
 */
public class VerificaDupList implements DuplicateAlgorithm
{
	// Leggendo la documentazione JCF si nota che
	// per le List sarebbe meglio un accesso con in metodo get,
	// infatti questo e' fatto in tempo costante per Vector e
	// per ArrayList (non vale pero' per LinkedList)
	
	@Override
	public <T> boolean verificaDup(List<T> collection)
	{
		if (collection instanceof LinkedList)
		{
			// Se la lista e' una LinkedList quindi eseguiamo la
			// scansione attraverso gli Iterator, si noti che
			// in questo caso anche collection.listIterator(iter1.nextIndex())
			// quindi il tempo di esecuzione sara' O(N²), purtroppo gli iteratori
			// non sono copiabili (clonabili) a meno di re-implementazioni della
			// classe e quindi pur avendo gia' un Iterator all'elemento che ci
			// interessa non possiamo creare un secondo Iterator a partire da
			// questo.
			
			// Utilizziamo due Iterator per prendere a coppie i valori della
			// List (le coppie vengono confrontate una sola volta dato che nel
			// ciclo interno vengono iterati tutti i valori a partire dal valore
			// corrente del ciclo esterno in avanti.
			// Sarebbe come fare:
			//
			// for (int i = 0; i < n-1; i++)
			//     for(int j = i+1; j < n; j++)
			//
			// Questo sara' il vero e proprio codice che verra' usato poi per il
			// caso in cui la List non e' una instance di LinkedList.
			//
			// In questo caso il ciclo esterno arriva fino a n-1
			// compreso (dato che usando gli Iterator non possiamo fermarci al
			// penultimo), tuttavia nell'ultima iterazione del ciclo non si entra
			// nel ciclo interno non essendoci ulteriori valori da confrontare.
			
			// Usiamo ListIterator al posto di Iterator perche' abbiamo sviluppato
			// l'algoritmo solo per le liste, e quindi siamo sicuri che la collezione
			// sia una lista, questo tipo di iteratori ci permettono di creare un
			// iteratore che parte da un certo indice invece che dall'inizio della
			// lista.
			
			ListIterator<T> iter1 = collection.listIterator();
			ListIterator<T> iter2 = null;
			T t1 = null;

			while (iter1.hasNext())
			{
				t1 = iter1.next();
				iter2 = collection.listIterator(iter1.nextIndex());

				while (iter2.hasNext())
				{
					if (t1.equals(iter2.next()))
						return true;
				}
			}

			return false;
		}
		else
		{
			// Se la List non e' una LinkedList e' piu' conveniente l'operazione di
			// get dato che le classi ArrayList e Vector implementano l'interfaccia
			// RandomAccess.
			//
			// Ulteriori spiegazioni si possono trovare a:
			// http://download.oracle.com/javase/1.4.2/docs/api/java/util/RandomAccess.html
			//
			// L'algoritmo di ricerca usato e' spiegato precedentemente.
			
			int n = collection.size();
			T t1 = null;
			
			for (int i = 0; i < n-1; i++)
			{
				t1 = collection.get(i);
				for (int j = i+1; j < n; j++)
				{
					if (t1.equals(collection.get(j)))
						return true;
				}
			}
			
			return false;
		}
	}
}
