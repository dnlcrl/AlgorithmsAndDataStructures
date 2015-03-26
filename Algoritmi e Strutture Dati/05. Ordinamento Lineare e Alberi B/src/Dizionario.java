/**
 * Un dizionario &egrave; una collezione di elementi a cui sono associate chiavi.
 * Ad esempio, un elenco telefonico potrebbe essere gestito mediante un
 * dizionario che ha come elementi i numeri telefonici e come chiavi
 * i nominativi degli abbonati. Operazioni tipiche su un dizionario
 * sono l'inserimento (<code>insert</code>) di un elemento e di una chiave ad
 * esso associata, la cancellazione (<code>delete</code>) di un elemento data
 * una chiave, e la ricerca (<code>search</code>) dell'elemento associato
 * a una data chiave. L'interfaccia presentata supporta le tre operazioni
 * <code>insert</code>, <code>search</code> e <code>delete</code>
 * assumendo che gli elementi da conservare nel dizionario siano oggetti generici di tipo
 * <code>Object</code> e che le chiavi usate per indicizzarle siano oggetti di tipo <code>Comparable</code>.
 */

public interface Dizionario<D, K extends Comparable<? super K>> {
	/**
	 * Aggiunge al dizionario la coppia <code>(data, key)</code>
	 * 
	 * @param data elemento da mantenere nel dizionario
	 * @param key chiave associata all'elemento
	 */
	public void insert(D data, K key);	
	
	/**
	 * Rimuove dal dizionario l'elemento con chiave <code>key</code>
	 * 
	 * @param key chiave dell'elemento da cancellare
	 */
	public void delete(K key);
	
	/**
	 * Restituisce l'elemento <code>e</code> con chiave <code>k</code>.
	 * In caso di duplicati, l'elemento restituito
	 * &egrave; scelto arbitrariamente tra quelli con chiave <code>k</code>.
	 * 
	 * @param k chiave dell'elemento da ricercare
	 * @return elemento di chiave k, null se assente
	 */	
	public D search(K k);
}
