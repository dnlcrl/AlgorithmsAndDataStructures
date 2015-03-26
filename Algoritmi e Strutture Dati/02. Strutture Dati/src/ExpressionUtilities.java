import java.util.Stack;
import java.util.EmptyStackException;

/**
 * Classe di utilita' sulle Espressioni
 * @author Enrico Bacis, Roberto Santini
 */
public class ExpressionUtilities
{
	public static int evalPostfix(String[] expr) throws IllegalArgumentException
	{
		// Creiamo uno stack in cui metteremo i numeri.
		
		Stack<Integer> stack = new Stack<Integer>();
		int number;
		
		for (String x : expr)
		{
			try
			{
				// Se la stringa e' un numero lo inseriamo nello stack.
				// Se non e' un numero verra' lanciata l'eccezione NumberFormatException
				// catturata sotto.
				
				number = Integer.parseInt(x);
				stack.push(number);
			}
			catch (NumberFormatException e)
			{
				// Se entriamo in questo blocco significa che la stringa non e' un numero.
				
				// In questo caso ammettiamo (per poter scrivere il codice in una
				// maniera piu' leggibile) di avere solo operatori binari.
				// Se avessimo anche operatori unari (come il fattoriale ad esempio)
				// dovremmo differenziare per ogni operazione il numero di pop da
				// effettuare.
				
				// Potremmo seguire due strade differenti, la prima e' quella di usare
				// il metodo size() sullo stack per verificare il numero degli elementi,
				// oppure mettere i metodi pop() in un try-catch.
				
				int op1, op2;
				
				try
				{
					// Inseriamo i pop sullo stack in un costrutto try-catch per
					// evitare di verificare la dimensione corrente dello stack.
					// Se cercheremo di fare pop su uno stack vuoto verra' generata
					// l'eccezione EmptyStackException catturata dallo stack sottostante.
					
					// Si noti che il primo operando estratto dallo stack viene chiamato
					// op2, infatti se pensiamo a come e' creato lo stack, se scriviamo
					// 3 7 +, lo stack conterra'
					//
					// 3
					// 7
					//
					// e il primo numero estratto sara' il 7, che e' il secondo operando.
					// Con questa accortezza possiamo scrivere anche in maniera semplice
					// le operazioni per gli operatori non commutativi (come -, /, %)
					
					op2 = stack.pop();
					op1 = stack.pop();
				}
				catch (EmptyStackException s)
				{
					throw new IllegalArgumentException("Operatore " + x + " su uno stack con meno di 2 elementi.");
				}
				
				// Avremmo potuto utilizzare un costrutto switch ma abbiamo preferito
				// lasciare aperto il codice per l'implementazione di operatori che
				// possono essere scritti con piu' caratteri.
				// Verifichiamo quindi con il metodo equals delle stringhe l'operatore
				// corrente e eseguiamolo. Se non viene trovato un operatore viene lanciata
				// l'eccezione IllegalArgumentException.
				
				if (x.equals("+"))
					stack.push(op1 + op2);
				else if (x.equals("*"))
					stack.push(op1 * op2);
				else if (x.equals("-"))
					stack.push(op1 - op2);
				else if (x.equals("/"))
					stack.push(op1 / op2);
				else if (x.equals("%"))
					stack.push(op1 % op2);
				else
					throw new IllegalArgumentException("Simbolo '" + x + "' non riconosciuto.");
			}
		}
		
		// Se alla fine dell'elaborazione lo stack contiene esattamente un elemento, quello
		// e' il risultato, altrimenti la valutazione segnala un errore nell'espressione.
		
		if (stack.size() == 1)
			return stack.pop();
		else
			throw new IllegalArgumentException("");
	}
	
	// Aggiungiamo un metodo che faccia lo split sullo spazio, in modo da poter testare
	// piu' agevolmente la valutazione delle espressioni in notazione postfissa.
	
	public static int evalPostfix(String expr)
	{
		return evalPostfix(expr.split(" "));
	}
	
	// Questo e' il metodo per controllare le parentesi di un'espressione.
	// Abbiamo dovuto sopprimere i warning "unchecked" perche' nella versione 6 di Java
	// l'utilizzo di varargs (in questo caso si veda successivamente il metodo
	// printErrors che viene richiamato da questa funzione) con tipi non generici
	// e' scoraggiato per un qualche motivo.
	// Ad ogni modo in casi come questo e' necessario che il tipo non sia generico
	// (dato che poi useremo gli Integer come indice di una stringa). Questo problema
	// e' stato evidenziato anche da Oracle che infatti ha permesso questo utilizzo
	// nella versione 7 di Java.
	
	@SuppressWarnings("unchecked")
	public static boolean checkBrackets(String expr)
	{
		// Creiamo tre stack in cui inseriremo gli indici dell'apertura delle
		// parentesi rispettivamente tonde, quadre e graffe.
		
		Stack<Integer> round = new Stack<Integer>();
		Stack<Integer> square = new Stack<Integer>();
		Stack<Integer> curly = new Stack<Integer>();
		
		// Iniziamo a descrivere gli errori possibili per i diversi casi e numeriamoli
		// per poi segnalare quale parte del codice effettua il controllo.
		// Abbiamo individuato 8 tipi di errore, riteniamo che non si possano creare
		// errori formali nelle parentesi delle espressioni con parentesi tonde, quadre
		// e graffe oltre a questi.
		
		// Parentesi Tonde (Round Brackets):
		// - Si puo' sempre aprire una parentesi tonda.
		// - Si puo' chiudere una parentesi tonda solo se c'e' ancora almeno una parentesi
		//   tonda aperta. (1)
		
		// Parentesi Quadre (Square Brackets):
		// - Si puo' aprire una parentesi quadra solo se non ci sono parentesi tonde aperte. (2)
		// - Si puo' chiudere una parentesi quadra solo se c'e' ancora almeno una parentesi
		//   quadra aperta (3) e tutte le parentesi tonde sono state chiuse (4).
		
		// Parentesi Graffe (Curly Brackets):
		// - Si puo' aprire una parentesi graffa solo se non ci sono parentesi tonde o quadre aperte. (5)
		// - Si puo' chiudere una parentesi graffa solo se c'e' ancora almeno una parentesi
		//   graffa aperta (6) e tutte le parentesi quadre e tonde sono state chiuse (7).
		
		// Fine della stringa:
		// - Una espressione e' valida solo se alla fine della stringa non rimangono parentesi tonde,
		//   quadre o graffe aperte. (8)
		
		
		// Ora possiamo scorrere la lista alla ricerca di questi errori.
		// Oltre a verificare gli errori descritti in precedenza effettueremo le seguenti operazioni:
		// - Ogni volta che troveremo una parentesi aperta facciamo il push del suo indice (inteso come indice
		//   della stringa) nello Stack corrispondente.
		// - Ogni volta che troveremo una parentesi chiusa faremo un pop sullo Stack corrispondente.
		
		for (int index = 0; index < expr.length(); index++)
		{
			switch(expr.charAt(index))
			{
			case '(':
				round.push(index);
				break;
				
			case ')':
				if (round.size() < 1)
				{
					// Errore (1)
					printErrors(expr, "Trovata ) senza (", index);
					return false;
				}
				
				round.pop();
				break;
				
			case '[':
				if (!round.isEmpty())
				{
					// Errore (2)
					printErrors(expr, "Trovata [ con parentesi tonde aperte", round);
					return false;
				}
				
				square.push(index);
				break;
				
			case ']':
				if (square.isEmpty())
				{
					// Errore (3)
					printErrors(expr, "Trovata ] senza [", index);
					return false;
				}
				
				if (!round.isEmpty())
				{
					// Errore (4)
					printErrors(expr, "Parentesi interne rimaste aperte prima di ]", round);
					return false;
				}
				
				square.pop();
				break;
				
			case '{':
				if (!round.isEmpty() || !square.isEmpty())
				{
					// Errore (5)
					printErrors(expr, "Trovata { con parentesi tonde o quadre aperte", round, square);
					return false;
				}
				
				curly.push(index);
				break;
				
			case '}':
				if (curly.isEmpty())
				{
					// Errore (6)
					printErrors(expr, "Trovata } senza {", index);
					return false;
				}
				
				if (!round.isEmpty() || !square.isEmpty())
				{
					// Errore (7)
					printErrors(expr, "Parentesi interne rimaste aperte prima di }", round, square);
					return false;
				}
				
				curly.pop();
				break;
			}
		}
		
		if (round.isEmpty() && square.isEmpty() && curly.isEmpty())
			return true;
		else
		{
			// Errore (8)
			printErrors(expr, "Parentesi rimaste aperte", round, square, curly);
			return false;
		}
	}
	
	// Abbiamo creato un metodo per stampare gli errori nelle espressioni, bisogna inserire
	// l'espressione, l'errore e poi abbiamo creato un varargs di stacks, quindi possiamo poi
	// passare al metodo quanti Stack<Integer> vogliamo, ognuno degli elementi Integer di
	// ognuno degli stacks verra' considerato come l'indice di un errore (relativo alla stringa
	// expr) e quindi verra' posto un apice sotto a questo per indicarlo.
	
	private static void printErrors(String expr, String error, Stack<Integer> ... stacks)
	{
		String label = "Expression: ";
		
		// Usiamo System.err per segnalare l'errore (verra' stampato in rosso)
		
		System.err.println(" ");
		System.err.println(error);
		System.err.println(label + expr);
		
		// Usiamo StringBuilder per creare la stringa di apici. In questo modo possiamo evitare
		// l'uso di contains negli Stacks e quindi ridurre il tempo di esecuzione del codice.
		// Ogni elemento di ogni stack verra' analizzato una singola volta. A differenza di
		// quanto accadrebbe con il metodo contains.
		// La StringBuilder ci permette di creare una stringa intesa piu' come un array di
		// caratteri, la inizializziamo con tutti spazi, successivamente con il metodo
		// setCharAt potremo porre un apice in una determinata posizione della stringa.
		
		StringBuilder apices = new StringBuilder(label.length() + expr.length());
		
		for (int i = 0; i < apices.capacity(); i++)
			apices.append(" ");
		
		// Usiamo un doppio ciclo for each per ciclare ogni Integer (che identificato l'indice)
		// di goni stack.
		
		for (Stack<Integer> stack : stacks)
			for (Integer index : stack)
				apices.setCharAt(index + label.length(), '^');
					
		System.err.println(apices);
	}
	
	// Questo altro metodo di utilita' ci permette di stampare gli errori nelle espressioni
	// quando l'indice dell'errore e' uno solo. La funzione richiamera' quella con varargs.
	// Anche qui abbiamo doovuto sopprimere il warning "unchecked" come gia' spiegato
	// nel metodo checkBrackets.
	
	@SuppressWarnings("unchecked")
	private static void printErrors(String expr, String error, int index)
	{
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(index);
		printErrors(expr, error, stack);
	}
}
