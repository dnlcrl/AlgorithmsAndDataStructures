package alberoBinario;

/**
 * Co-Author: Enrico Bacis
 */

import java.util.List; //Per l'output delle visite
import java.util.LinkedList; //Per l'output delle visite
import java.util.ListIterator;

import coda.*; //Per la visita BFS (come esempio di struttura dati propria)

import java.util.Stack; //Per la visita DFS (come esempio di struttura dati predefinita di JFC)

public class AlberoBinarioImpl<D extends Comparable<? super D>> implements
		AlberoBinario<D> {
	protected NodoBinario<D> radice;

	// Metodo costruttore di default
	public AlberoBinarioImpl() {
		radice = null;
	}

	// Metodi costruttori: due varianti (uno con l'info ed uno direttamente con
	// il nodo)
	public AlberoBinarioImpl(D data) {
		radice = new NodoBinario<D>(data);
	}

	public AlberoBinarioImpl(NodoBinario<D> rad) {
		radice = rad;
	}

	// Metodi accessori:

	@Override
	public NodoBinario<D> radice() {
		return radice;
	}

	@Override
	public int numNodi() {
		return numNodi(radice);
	}

	/**
	 * Restituisce il numero di nodi dell'albero radicato in un nodo
	 * <code>r</code>. Tale informazione viene determinata conteggiando
	 * ricorsivamente il numero di nodi dei sottoalberi radicati nei figli di
	 * <code>r</code>.
	 * 
	 * @return il numero di nodi presenti nell'albero radicato in <code>r</code>
	 *         .
	 */
	private int numNodi(NodoBinario<D> r) {
		return r == null ? 0 : numNodi(r.sinistro) + numNodi(r.destro) + 1;
	}

	@Override
	public int numFoglie() {
		return numFoglie(radice);
	}

	private int numFoglie(NodoBinario<D> v) {
		if (v == null)
			return 0;
		if (isFoglia(v))
			return 1;
		return numFoglie(v.sinistro) + numFoglie(v.destro);
	}

	@Override
	public int numNodiInterni() {
		return numNodiInterni(radice);
	}

	private int numNodiInterni(NodoBinario<D> v) {
		if ((v == null) || (isFoglia(v)))
			return 0;
		return 1 + numNodiInterni(v.sinistro) + numNodiInterni(v.destro);
	}

	private boolean isFoglia(NodoBinario<D> v) {
		return ((v.sinistro == null) && (v.destro == null)) ? true : false;
	}

	@Override
	public int grado(NodoBinario<D> v) {
		int grado = 0;
		if (v == null)
			return -1;
		if (v.sinistro != null)
			grado++;
		if (v.destro != null)
			grado++;
		return grado;
	}

	@Override
	public int altezza() {
		return altezza(radice);
	}

	private int altezza(NodoBinario<D> v) {
		if (v == null)
			return -1;
		if ((v.destro == null) && (v.sinistro == null))
			return 0;
		return 1 + Math.max(altezza(v.destro), altezza(v.sinistro));
	}

	// La soluzione seguente e' la piu' banale ma e' anche poco performante,
	// gli ultimi nodi vengono presi in considerazione h volte dove
	// h e' l'altezza dell'albero.

	// public boolean isBalanced() {
	// return isBalanced(radice);
	// }
	//
	// private boolean isBalanced(NodoBinario v) {
	// if (v == null) return true;
	// if (altezza(v.sinistro) != altezza(v.destro)) return false;
	// return isBalanced(v.sinistro) && isBalanced(v.destro);
	// }

	@Override
	public boolean isBalanced() {
		return (isBalanced(radice) == -2) ? false : true;
	}

	private int isBalanced(NodoBinario<D> v) {
		if (v == null) return -1;

		int hs = isBalanced(v.sinistro);
		if (hs == -2) return -2;

		int hd = isBalanced(v.destro);
		if (hd == -2) return -2;

		if (Math.abs(hs - hd) <= 1)
			return 1 + Math.max(hs, hd);
		else
			return -2;
	}
	
	// Lo stesso problema esposto per il metodo isBalanced() si riscontra
	// anche nei metodi isPerfectlyBalanced e isCompleted.

	@Override
	public boolean isPerfectlyBalanced() {
		return (isPerfectlyBalanced(radice) == -1) ? false : true;
	}

	private int isPerfectlyBalanced(NodoBinario<D> v) {
		if (v == null) return 0;

		int ns = isPerfectlyBalanced(v.sinistro);
		if (ns == -1) return -1;

		int nd = isPerfectlyBalanced(v.destro);
		if (nd == -1) return -1;

		if (Math.abs(ns - nd) <= 1)
			return 1 + Math.max(ns, nd);
		else
			return -1;
	}

	@Override
	public boolean isCompleted() {
		return (isCompleted(radice) == -1) ? false : true;
	}
	
	private int isCompleted(NodoBinario<D> v) {
		if (v == null) return 0;
		
		int ns = isCompleted(v.sinistro);
		if (ns == -1) return -1;
		
		int nd = isCompleted(v.destro);
		if (nd == -1) return -1;
		
		if (ns == nd)
			return 1 + ns;
		else
			return -1;
	}
	
	// Per controllare se è BST si potrebbe semplicemente controllare per ogni nodo
	// se il massimo del sottoalbero del figlio sinistro è minore o uguale
	// al nodo corrente e il minimo del sottoalbero del figlio destro è maggiore
	// o uguale al nodo corrente. I questo caso si ha un BST.
	// Tuttavia in questo modo il tempo è pseudolineare.
	// si potrebbe allora chiamare la funzione inorder (tempo lineare) e poi
	// controllare che la lista sia in ordine.
	// Anche questa soluzione e' troppo costosa.
	// Partiamo da un intervallo non limitato di valori (-inf, +inf).
	// Per ogni nodo controlliamo che il nodo appartenga all'intervallo [min, max].
	// Se non lo e' si ha una violazione. Se e' nell'intervallo allora
	// si spezza l'intervallo in due sottointervalli [min, elem] e [elem, max] .
	// Quindi si controlla che il figlio sinistro (se esiste) appartenga
	// all'intervallo [min, elem] e il figlio destro (se esiste) all'intervallo
	// [elem, max] . Si procede poi iterativamente.
	
	@Override
	public boolean isBST() {
		// null rappresenta l'estremo non limitato
		return isBST(radice, null, null);
	}

	private boolean isBST(NodoBinario<D> v, D min, D max) {

		if (v == null)
			return true;

		if (min != null)
			if (v.elem.compareTo(min) < 0)
				return false;

		if (max != null)
			if (v.elem.compareTo(max) > 0)
				return false;

		return (isBST(v.sinistro, min, v.elem) && isBST(v.destro, v.elem, max));
	}

	@Override
	public boolean search(D elem) {
		return search(elem, radice);
	}

	private boolean search(D elem, NodoBinario<D> v) {
		if (v == null)
			return false;
		if (v.elem == elem)
			return true;
		return (search(elem, v.sinistro) || search(elem, v.destro));
	}

	@Override
	public D max() {
		return max(radice);
	}

	private D max(NodoBinario<D> v) {
		if (v == null)
			return null;
		if (isFoglia(v))
			return v.elem;

		D ms = max(v.sinistro);
		D md = max(v.destro);
		D max = null;

		if (ms == null)
			max = md;
		else if (md == null)
			max = ms;
		else
			max = (ms.compareTo(md) > 0) ? ms : md;

		return (max.compareTo(v.elem) > 0) ? max : v.elem;
	}

	@Override
	public D min() {
		return min(radice);
	}

	private D min(NodoBinario<D> v) {
		if (v == null)
			return null;
		if (isFoglia(v))
			return v.elem;

		D ms = min(v.sinistro);
		D md = min(v.destro);
		D min = null;

		if (ms == null)
			min = md;
		else if (md == null)
			min = ms;
		else
			min = (ms.compareTo(md) < 0) ? ms : md;

		return (min.compareTo(v.elem) < 0) ? min : v.elem;
	}

	@Override
	public List<D> minmax() {
		List<D> minmax = new LinkedList<D>();
		
		List<D> elements = new LinkedList<D>();
		inorder(radice, elements);

		if (!elements.isEmpty()) {
			D min = elements.get(0);
			D max = min;

			if (elements.size() > 1) {
				ListIterator<D> iter = elements.listIterator();
				D d1 = null;
				D d2 = null;
				D mi = null;
				D ma = null;

				while (iter.hasNext()) {
					d1 = iter.next();

					if (iter.hasNext()) {
						d2 = iter.next();

						if (d1.compareTo(d2) > 0) {
							ma = d1;
							mi = d2;
						} else {
							ma = d2;
							mi = d1;
						}

					} else {
						mi = d1;
						ma = d2;
					}

					if (mi.compareTo(min) < 0)
						min = mi;
					if (ma.compareTo(max) > 0)
						max = ma;
				}
			}

			minmax.add(min);
			minmax.add(max);
		}
		
		return minmax;
	}
	
	@Override
	public Object info(NodoBinario<D> v) {
		return v.elem;
	}

	// Restituisce true se v e' un figlio sinistro
	@Override
	public boolean figlioSinistro(NodoBinario<D> v) {
		if (v.equals(v.padre.sinistro))
			return true;
		else
			return false;
	}

	// Restituisce true se v e' un figlio sinistro
	@Override
	public boolean figlioDestro(NodoBinario<D> v) {
		if (v.equals(v.padre.destro))
			return true;
		else
			return false;
	}
	
	@Override
	public NodoBinario<D> padre(NodoBinario<D> v) {
		return v.padre;
	}
	
	@Override
	public NodoBinario<D> sin(NodoBinario<D> v) {
		return v.sinistro;
	}
	
	@Override
	public NodoBinario<D> des(NodoBinario<D> v) {
		return v.destro;
	}
	
	@Override
	public void cambiaInfo(NodoBinario<D> v, D info) {
		v.elem = info;
	}
	
	@Override
	public void scambiaInfo(NodoBinario<D> v1, NodoBinario<D> v2) {
		D temp = v1.elem;
		v1.elem = v2.elem;
		v2.elem = temp;
	}

	// aggiunge "albero" come sottoalbero sinistro di "padre"
	@Override
	public void innestaSin(NodoBinario<D> padre, AlberoBinario<D> albero) {
		NodoBinario<D> figlio = albero.radice();
		if (figlio != null)
			figlio.padre = padre;
		padre.sinistro = figlio;
	}

	// aggiunge "albero" come sottoalbero destro di "padre"
	@Override
	public void innestaDes(NodoBinario<D> padre, AlberoBinario<D> albero) {
		NodoBinario<D> figlio = albero.radice();
		if (figlio != null)
			figlio.padre = padre;
		padre.destro = figlio;
	}

	// elimina il sottoalbero sinistro di "padre" e lo restituisce
	@Override
	public AlberoBinario<D> potaSinistro(NodoBinario<D> padre) {
		NodoBinario<D> figlio = padre.sinistro;
		figlio.padre = null;
		AlberoBinario<D> newalbero = new AlberoBinarioImpl<D>(figlio);
		padre.sinistro = null;
		return newalbero;
	}

	// elimina il sottoalbero destro di "padre" e lo restituisce
	@Override
	public AlberoBinarioImpl<D> potaDestro(NodoBinario<D> padre) {
		NodoBinario<D> figlio = padre.destro;
		figlio.padre = null;
		AlberoBinarioImpl<D> newalbero = new AlberoBinarioImpl<D>(figlio);
		padre.destro = null;
		return newalbero;
	}

	// Stacca e restituisce il sottoalbero radicato in un certo 
	// NodoBinario "rimosso" dell'albero
	@Override
	public AlberoBinario<D> pota(NodoBinario<D> rimosso) {
		if (rimosso == null)
			return new AlberoBinarioImpl<D>(); // l'albero vuoto
		if (rimosso.padre == null) { // "rimosso" e' la radice dell'albero
			radice = null;
			return new AlberoBinarioImpl<D>(rimosso); // restituiamo l'intero albero
		}
		NodoBinario<D> pad = rimosso.padre;
		if (figlioSinistro(rimosso)) { // se "rimosso" e' figlio sinistro
			AlberoBinario<D> newalbero = potaSinistro(pad);
			return newalbero;
		} else { // "rimosso" e' figlio destro
			AlberoBinario<D> newalbero = potaDestro(pad);
			return newalbero;
		}
	}

	// Restituisce la lista degli elementi in una visita DFS (iterativa)
	@Override
	public List<D> visitaDFS() {
		List<D> output = new LinkedList<D>(); // lista di elementi in output 
		Stack<NodoBinario<D>> pila = new Stack<NodoBinario<D>>(); // Classe generica Stack<T>
		if (radice != null)
			pila.push(radice);
		while (!pila.isEmpty()) {
			NodoBinario<D> u = pila.pop(); // Richiederebbe il casting se usassimo la classe Stack non generica
			output.add(u.elem); // mettiamo in output corrente
			// inseriamo in pila il figlio destro e poi quello sinistro
			if (u.destro != null)
				pila.push(u.destro);
			if (u.sinistro != null)
				pila.push(u.sinistro);
		}
		
		return output;
	}

	// Restituisce la lista degli elementi in una visita BFS
	@Override
	public List<D> visitaBFS() {
		List<D> output = new LinkedList<D>();
		Coda<NodoBinario<D>> coda = new CodaCollegata<NodoBinario<D>>();
		if (radice != null)
			coda.enqueue(radice);
		while (!coda.isEmpty()) {
			NodoBinario<D> u = coda.dequeue();
			output.add(u.elem); // Il metodo add di LinkedList aggiunge il nuovo elemnto alla fine
			// Inseriamo in coda il figlio sinistro e poi quello destro
			if (u.sinistro != null)
				coda.enqueue(u.sinistro);
			if (u.destro != null)
				coda.enqueue(u.destro);
		}
		return output;
	}

	// Visita ricorsiva inorder
	@Override
	public List<D> inorder() {
		List<D> output = new LinkedList<D>();
		inorder(radice, output);
		return output;
	}

	private void inorder(NodoBinario<D> n, List<D> output) {
		if (n == null)
			return;

		if (n.sinistro != null)
			inorder(n.sinistro, output);

		output.add(n.elem);

		if (n.destro != null)
			inorder(n.destro, output);
	}
}
