import java.util.ArrayList;
import java.util.List;

/**
 * Classe main per gli esercizi 1,2,3 del Laboratorio 1 di Info III B
 * @author Enrico Bacis
 */
public class Prova
{
	public static void main(String[] args)
	{
		// Esercizio 1
		VerificaDupList checker = new VerificaDupList();
		List<Studente> students = new ArrayList<Studente>();
		
		// I nomi sono stati generati a partire da nomi reali con il generatore di nomi:
		//                 "The Very British Name Generator"
		//         http://rumandmonkey.com/widgets/toys/namegen/10/
		// Questo e' stato fatto senza nessuna motivazione, era bello cosi'.
		
		students.add(new Studente(1, "Quentin", "Cavendish"));
		students.add(new Studente(2, "Rupert",  "Salisbury"));
		students.add(new Studente(3, "Nigel",   "Wilson"));
		System.out.println("1) Duplicates found: " + checker.verificaDup(students));
		
		// Si iscrive un nuovo studente omonimo a quello con la matricola 2
		// Non e' comunque una duplicita'.
		students.add(new Studente(4, "Rupert",  "Salisbury"));
		System.out.println("2) Duplicates found: " + checker.verificaDup(students));
		
		// Si iscrive un nuovo studente ma gli viene assegnata una matricola
		// gia' nella lista, tuttavia lo studente potrebbe essere di un'altra
		// universita', non viene quindi segnalata la duplicita'.
		students.add(new Studente(2, "Terrance", "Pelham"));
		System.out.println("3) Duplicates found: " + checker.verificaDup(students));
		
		// Viene ora salvato nella lista uno studente che ha uguale matricola,
		// nome e cognome di un altro. E' un doppio, ci aspettiamo che ora
		// vengano rilevati dei doppi.
		students.add(new Studente(1, "Quentin",  "Cavendish"));
		System.out.println("4) Duplicates found: " + checker.verificaDup(students));
		
		
		// Esercizio 2 e 3
		System.out.println("");
		
		// Svuotiamo la lista e facciamola ordinata. (ci serve per la
		// ricerca binaria).
		// Per velocita' creiamo gli studenti solo con la matricola.
		// L'implementazione permette di avere una lista che non sia
		// necessariamente composta da una potenza di due di elementi.
		// Creiamo una lista ordinata di 6 elementi.
		
		students.clear();
		students.add(new Studente(1));
		students.add(new Studente(2));
		students.add(new Studente(3));
		students.add(new Studente(4));
		students.add(new Studente(5));
		students.add(new Studente(6));
		
		RicercaSequenziale sequentialSearcher = new RicercaSequenziale();
		RicercaBinariaIter binaryIterSearcher = new RicercaBinariaIter();
		RicercaBinariaRic  binaryRecSearcher  = new RicercaBinariaRic();
		
		// Cerco uno studente presente nella lista.
		Studente s = new Studente(1);
		System.out.println("1) Sequential Search.       Found: " + sequentialSearcher.searchFor(students, s));
		System.out.println("1) Binary Iterator Search.  Found: " + binaryIterSearcher.searchFor(students, s));
		System.out.println("1) Binary Recursive Search. Found: " + binaryRecSearcher.searchFor(students, s));
		System.out.println("");
		
		// Cerco ora uno studente che non c'e' nella lista.
		s = new Studente(7);
		System.out.println("2) Sequential Search.       Found: " + sequentialSearcher.searchFor(students, s));
		System.out.println("2) Binary Iterator Search.  Found: " + binaryIterSearcher.searchFor(students, s));
		System.out.println("2) Binary Recursive Search. Found: " + binaryRecSearcher.searchFor(students, s));
	}
}
