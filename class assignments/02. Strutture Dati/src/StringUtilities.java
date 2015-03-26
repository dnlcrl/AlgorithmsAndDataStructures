import java.util.Stack;

/**
 * Classe di utilita' sulle Stringhe
 * @author Enrico Bacis, Roberto Santini
 */
public class StringUtilities
{
	// Metodo statico per invertire una stringa.
	// Si sfrutta la struttura LIFO degli stack, infatti,
	// se inseriamo in ordine tutti i caratteri componenti una stringa
	// in uno stack, quando andremo a toglierli, il primo tolto sara'
	// l'ultimo inserito (paradigma LIFO - Last In First Out).
	// In questo modo sfruttando il += delle stringhe (concatenazione)
	// otteniamo la stringa invertita.
	
	public static String inverti(String s)
	{
		// Creiamo lo stack di supporto per invertire la stringa.
		
		Stack<Character> stringStack = new Stack<Character>();
		
		// Facciamo il push dei caratteri della stringa di partenza nello stack.
		
		for (int i = 0; i < s.length(); i++)
		{
			stringStack.push(s.charAt(i));
		}
		
		// Concatenando ora i vari pop (append) otterremo la lista invertita.
		// Si noti che se avessimo avuto una struttura FIFO come una coda, il metodo
		// avrebbe comunque funzionato con un piccolo accorgimento, durante la
		// ricostruzione della stringa concatenata avremmo dovuto fare il prepend
		// del carattere estratto dalla coda
		// (invertedString = stringQueue.dequeue() + invertedString;)
		
		String invertedString = new String();		
		while (!stringStack.isEmpty())
		{
			invertedString += stringStack.pop();
		}
		
		return invertedString;
	}
	
	// Questo metodo effettua l'inversione di una stringa in maniera ricorsiva.
	// Usiamo lo stesso trucco visto nel metodo precedente ma utilizzando lo
	// stack implicito di sistema.
	// Ogni volta che il metodo viene chiamato si controlla se la lunghezza è
	// minore di 2, in quel caso si ritorna la stringa.
	// Se la lunghezza è almeno due si chiama invertiRic sulla sottostringa che
	// si ottiene togliendo dalla stringa corrente il primo carattere. Al risultato
	// della chiamata ricorsiva si appendera' poi il primo carattere della stringa.
	// Se ci si pensa, il funzionamento e' identico al metodo precedente ma senza
	// una struttura ausilaria di supporto (si usa lo stack del sistema).
	
	public static String invertiRic(String s)
	{
		if (s.length() <= 1) return s;
		return invertiRic(s.substring(1)) + s.charAt(0);
	}
}
