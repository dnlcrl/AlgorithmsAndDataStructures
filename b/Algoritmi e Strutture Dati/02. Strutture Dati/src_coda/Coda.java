

/* ============================================================================
 *  $RCSfile: Coda.java,v $
 * ============================================================================
 * Copyright (C) 2007 Camil Demetrescu, Umberto Ferraro Petrillo,
 *                    Irene Finocchi, Giuseppe F. Italiano
 *  License:          See the end of this file for license information
 *  Created:          
 *  Last changed:   $Date: 2007/03/22 17:06:14 $  
 *  Changed by:     $Author: umbfer $
 *  Revision:       $Revision: 1.10 $
 */


/**
 * Una coda &egrave; una struttura dati lineare nella quale 
 * gli accessi avvengono secondo uno schema di tipo FIFO (first-in, first-out).
 * Questo implica che gli inserimenti (<code>enqueue</code>) aggiungono elementi 
 * alla fine della sequenza, mentre le cancellazioni (<code>dequeue</code>) 
 * ne rimuovono sempre il primo elemento. In una coda, gli accessi
 * avvengono quindi ad entrambe le estremit&agrave; della sequenza
 * di elementi, e nessun elemento interno pu&ograve; essere estratto
 * prima che tutti quelli che lo precedono siano stati estratti.
 *
 */

public interface Coda {
	/**
	 * Verifica se la coda &egrave; vuota.
	 * 
	 * @return <code>true</code>, se la coda &egrave; vuota. <code>false</code>, altrimenti
	 */
	public boolean isEmpty();
	
	/**
	 * Aggiunge l'elemento <code>e</code> al termine della sequenza di elementi
	 * presenti nella coda.
	 * 
	 * @param e l'elemento da mantenere nella coda
	 */
	public void enqueue(Object e);
	
	/**
	 * Restituisce il primo elemento della sequenza di elementi
	 * presenti nella coda.
	 * 
	 * @return il primo elemento della coda
	 */
	public Object first();
	
	/** 
	 * Cancella il primo elemento della sequenza di elementi presenti nella coda.
	 * 
	 * @return l'elemento rimosso
	 */
	public Object dequeue();
}

/* Copyright (C) 2007 Camil Demetrescu, Umberto Ferraro Petrillo,
 *                    Irene Finocchi, Giuseppe F. Italiano

 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
