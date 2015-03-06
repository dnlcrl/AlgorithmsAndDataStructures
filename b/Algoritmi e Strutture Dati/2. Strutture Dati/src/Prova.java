
/**
 * Classe di prova
 * @author Enrico Bacis, Roberto Santini
 */
public class Prova
{
	public static void main(String[] args)
	{
		System.out.println("Laboratorio 2: Stack");
		System.out.println("");
		
		System.out.println("Valutazione Espressioni in Notazione Postfissa.");
		
		// Se si chiama il programma passando degli argomenti dalla linea di comando
		// si usano questi per evalutare l'espressione postfissa passata.
		
		if (args.length != 0)
		{
			int result = ExpressionUtilities.evalPostfix(args);
			System.out.println("Il risultato in args e' " + result);
			System.out.println("");
		}
		
		// Per comodita' le nostre prove le faremo utilizzando il metodo evalPostfix
		// che prende una stringa e poi la spezza ad ogni carattere spazio incontrato.
		// Stampiamo poi il risultato.
		
		String expr = "11 2 * 4 - 10 % 1 3 + /";
		int result = ExpressionUtilities.evalPostfix(expr);
		System.out.println(expr);
		System.out.println("Il risultato e' " + result);
		System.out.println("");
		
		// Inversione di stringhe con metodo ricorsivo e non.
		
		System.out.println("Inversione di Stringhe");
		System.out.println("");
		
		String inv = "Metodo Non Ricorsivo";
		System.out.println(inv + " -> " + StringUtilities.inverti(inv));
		inv = "Metodo Ricorsivo";
		System.out.println(inv + " -> " + StringUtilities.invertiRic(inv));
		System.out.println("");
		
		System.out.println("Controllo Parentesi");
		
		// Inseriamo alcuni esempi di espressioni su cui viene eseguito
		// un controllo formale per le parentesi. Se il controllo va
		// a buon fine non viene stampato nulla, altrimenti viene stampata
		// la motivazione dell'errore e il punto dell'errore all'interno
		// dell'espressione.
		// Crediamo che non sia possibile generare degli errori formali che
		// non vengano rilevati da questo controllo. Gli esempi sarebbero troppi
		// da fare, qui ne sono riportati alcuni, lasciamo alla vostra fantasia
		// cercare di generare un errore corretto sintatticamente ma sbagliato
		// formalmente che pero' non viene rilevato.
		// Gli errori si riferiscono alla documentazione scritta nella classe
		// ExpressionUtilities.
		
		expr = "2*7";
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "(1+3)";
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "((2*16)+1)*(44+(17+9))";
		ExpressionUtilities.checkBrackets(expr);
		
		expr = ")"; // Errore (1)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "(44+38"; // Errore (8)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "(55+(12*11)"; // Errore (8)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "((()"; // Errore (8)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "(45+[6*2]*6)"; // Errore (2)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "(45+2]*3"; // Errore (3)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "[45+(2*3]"; // Errore (4)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "[45+(2*3)*{6/2}]"; // Errore (5)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "[45+(2*3}]"; // Errore (6)
		ExpressionUtilities.checkBrackets(expr);
		
		expr = "{[45+(6-2)*(2*3}"; // Errore (7)
		ExpressionUtilities.checkBrackets(expr);
	}
}
