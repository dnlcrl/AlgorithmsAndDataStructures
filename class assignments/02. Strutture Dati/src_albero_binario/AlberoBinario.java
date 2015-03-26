import java.util.List;
/**
 * Il tipo di dato Albero Binario, descritto dall'interfaccia <code>AlberoBinario</code>,
 * rappresenta alberi radicati in cui il grado di ogni nodo &egrave; al pi&ugrave; 2.
 * I figli di un albero binario hanno pertanto un ordine e sono distinti in figlio sinistro e figlio destro. 
 * In un albero binario, un nodo potrebbe avere un figlio sinistro, ma essere privo
 * del figlio destro, e viceversa.
 * 
 *  Le operazioni supportate permettono di recuperare
 * informazioni sull'albero (radice, numero di nodi, grado, padre, figlio sinistro e
 * figlio destro, e contenuto informativo di un nodo), di modificarne il contenuto 
 * informativo e la struttura (aggiungendo nodi oppure aggiungendo o rimuovendo
 * interi sottoalberi come figli sinistri o destri di nodi preesistenti),
 * o di visitarne i nodi in un ordine sistematico. 
 * 
 * <p>Per riferirci ai nodi di
 * un albero binario useremo il tipo <code>NodoBinario</code>. I riferimenti a nodi vengono generati
 * dalle operazioni che creano nodi (<code>aggiungiRadice</code>, <code>aggiungiFiglioSin</code>
 * e <code>aggiungiFiglioDes</code>) ed essi possono essere in seguito utilizzati per riferirsi ai nodi
 * dell'albero.
 * <br>Quando un sottoalbero viene staccato da un albero
 * mediante l'operazione <code>pota</code>, esso diventa una istanza di albero
 * a s&eacute; stante, ma i riferimenti ai nodi rimangono gli stessi. 
 * In modo simile, quando un albero viene innestato in un altro 
 * albero, smette di essere un'istanza di albero a s&eacute; stante,
 * ma conserva gli stessi riferimenti ai suoi nodi. 
 *
 */
public interface AlberoBinario {
		
	//METODI ACCESSORI PER RECUPERARE INFO SULL'ALBERO:
	/**
	 * Restituisce la radice dell'albero.
	 * 
	 * @return la radice dell'albero. <code>null</code>, se l'albero &egrave; vuoto
	 */
	public NodoBinario radice();
	
    /** 
     * Restituisce il numero di nodi presenti nell'albero.
	 * 
	 * @return il numero di nodi presenti nell'albero
	 */
	public int numNodi();
	
	/**
	 * Restituisce il numero di figli di un nodo.
	 * 
	 * @param v il nodo di cui si vuol conoscere il numero di figli
	 * @return il numero di figli di <code>v</code>
	 */
	public int grado(NodoBinario v);
	
	/**
	 * Restituisce il contenuto informativo di un nodo.
	 * 
	 * @param v il nodo di cui si vuol conoscere il contenuto informativo
	 * @return il contenuto informativo di <code>v</code>
	 */
	public Object info(NodoBinario v);
	
	/**
	 * Restituisce il padre di un nodo.
	 * 
	 * @param v il nodo di cui si vuole conoscere il padre
	 * @return il padre del nodo <code>v</code> nell'albero.
	 *          <code>null</code>, se <code>v</code> &egrave; la radice
	 */
	public NodoBinario padre(NodoBinario v);
	
	/**
	 * Restituisce il figlio sinistro di un nodo.
	 * 
	 * @param v il nodo di cui si vuol conoscere il figlio sinistro
	 * @return il figlio sinistro del nodo <code>v</code> nell'albero.
	 * <code>null</code>, se il figlio non &egrave; presente
	 */
	public NodoBinario sin(NodoBinario v);
	
	/**
	 * Restituisce il figlio destro di un NodoBinario.
	 * 
	 * @param v il NodoBinario di cui si vuol conoscere il figlio destro
	 * @return il figlio destro del NodoBinario <code>v</code> nell'albero.
	 * <code>null</code>, se il figlio non &egrave; presente
	 */	
	public NodoBinario des(NodoBinario v);
	
	/**
	 * Restituisce true se v e' un figlio sinistro.
	 * 
	 * @param v il NodoBinario di cui si vuol conoscere se è figlio sinistro
	 * @return <code>true</code> se il nodo <code>v</code> e' figlio sinistro;
	 * <code>false</code>, altrimenti
	 */	
	public boolean figlioSinistro(NodoBinario v);

	/**
	 * Restituisce true se v e' un figlio destro.
	 * 
	 * @param v il NodoBinario di cui si vuol conoscere se è figlio destro
	 * @return <code>true</code> se il nodo <code>v</code> e' figlio destro;
	 * <code>false</code>, altrimenti
	 */	
	public boolean figlioDestro(NodoBinario v);
	
	//METODI ACCESSORI PER MODIFICARE IL CONTENUTO INFORMATIVO E LA STRUTTURA DELL'ALBERO:
	/**
	 * Cambia il contenuto informativo di un NodoBinario.
	 * 
	 * @param v il NodoBinario di cui si vuole cambiare il contenuto informativo
	 * @param info il nuovo contenuto informativo per il NodoBinario <code>v</code>
	 */
	public void cambiaInfo(NodoBinario v, Object info);

	/**
	 * Scambia il contenuto informativo di due nodi.
	 * 
	 * @param <code>v1</code> e <code>v2</code> i nodi di cui si vuole scambiare il contenuto informativo.
	 * 
	 */
	public void scambiaInfo(NodoBinario v1, NodoBinario v2);
	
	
	/** 
	 * Inserisce un sottoalbero nell'albero come figlio sinistro
	 * di un NodoBinario preesistente.
	 * 
	 * @param u il NodoBinario cui aggiungere il sottoalbero
	 * @param albero l'albero la cui radice diventer&agrave; figlio
	 * sinistro di <code>u</code>
	 */	
	public void innestaSin(NodoBinario u, AlberoBinario albero);

	/** 
	 * Inserisce un sottoalbero nell'albero come figlio destro
	 * di un NodoBinario preesistente.
	 * 
	 * @param u il NodoBinario cui aggiungere il sottoalbero
	 * @param albero l'albero la cui radice diventer&agrave; figlio
	 * destro di <code>u</code>
	 */	
	public void innestaDes(NodoBinario u, AlberoBinario albero);
	
	/**
	 * Stacca e restituisce il sottoalbero sinistro di un certo
	 * NodoBinario dell'albero.
	 * 
	 * @param padre nodo padre da cui staccare il sottoalbero sinistro
	 * @return il sottoalbero sinistro di <code>padre</code>
	 */
	public AlberoBinario potaSinistro(NodoBinario padre);

	/**
	 * Stacca e restituisce il sottoalbero destro di un certo
	 * NodoBinario dell'albero.
	 * 
	 * @param padre nodo padre da cui staccare il sottoalbero destro
	 * @return il sottoalbero sinistro di <code>padre</code>
	 */
	public AlberoBinario potaDestro(NodoBinario padre);
	
	/**
	 * Stacca e restituisce il sottoalbero radicato in un certo
	 * NodoBinario dell'albero.
	 * 
	 * @param v la radice del sottoalbero da staccare
	 * @return il sottoalbero radicato in <code>v</code>
	 */
	public AlberoBinario pota(NodoBinario v);
	
	//ALGORITMI DI VISITA:
	
	/**
	 * Restituisce la lista dei nodi dell'albero nell'ordine in cui vengono
	 * incontrati da una visita in profondit&agrave; (Depth First Search, DFS).
	 * 
	 * @return la lista dei nodi incontrati durante la visita DFS
	 */
	public List visitaDFS();
	
	/**
	 * Restituisce la lista dei nodi dell'albero nell'ordine in cui vengono
	 * incontrati da una visita in ampiezza (Breadth First Search, BFS).
	 * 
	 * @return la lista dei nodi incontrati durante la visita BFS
	 */
	public List visitaBFS();
}
