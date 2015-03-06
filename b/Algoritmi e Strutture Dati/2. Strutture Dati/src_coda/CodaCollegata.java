



/* ============================================================================
 *  $RCSfile: CodaCollegata.java,v $
 * ============================================================================
 * Copyright (C) 2007 Camil Demetrescu, Umberto Ferraro Petrillo,
 *                    Irene Finocchi, Giuseppe F. Italiano
 *  License:          See the end of this file for license information
 *  Created:          
 *  Last changed:   $Date: 2007/03/22 17:06:14 $  
 *  Changed by:     $Author: umbfer $
 *  Revision:       $Revision: 1.6 $
 */


/**
 * La classe <code>CodaCollegata</code> implementa il tipo di dato
 * Coda mediante una collezione di n record contenenti
 * ciascuno una coppia (elem, next), dove elem &egrave;
 * un elemento della coda e prev &egrave; il puntatore
 * al successivo record della coda. La classe mantiene
 * inoltre un puntatore (inizio) al primo record della
 * coda e un puntatore (fine) all'ultimo record della coda.
 *
 */
public class CodaCollegata implements Coda {

	/**
	 * Puntatore al primo record della coda
	 */
	private Record inizio = null;
	
	/** 
	 * Puntatore all'ultimo record della coda
	 */
	private Record fine = null;
	
	/** 
	 * Classe definita localmente al tipo CodaCollegata
	 * per il mantenimento dei record contenenti
	 * le coppie (elem, next)
	 */
	private class Record {
	   	/**
    	 * Elemento da conservare nel record
    	 */
 		public Object elem;

        /**
         * Puntatore al prossimo record nella coda
         */
 		public Record next;
 		/**
 		 * Costruttore per l'allocazione di un nuovo record
 		 * 
 		 * @param e l'elemento da conservare nel record
 		 */
		public Record(Object e) {
			this.elem = e; this.next = null;
		}
	}
	
	/**
	 * Verifica se la coda &egrave; vuota (<font color=red>Tempo O(1)</font>).
	 * 
	 * @return <code>true</code>, se la coda &egrave; vuota. <code>false</code>, altrimenti
	 */
	public boolean isEmpty() {
		return this.inizio == null;
	}

	/**
	 * Aggiunge l'elemento <code>e</code> al termine della sequenza di elementi
	 * presenti nella coda (<font color=red>Tempo O(1)</font>).
	 * 
	 * @param e l'elemento da mantenere nella coda
	 */
	public void enqueue(Object e) {
		if (this.isEmpty())
			this.inizio = this.fine = new Record(e);
		else {
			this.fine.next = new Record(e);
			this.fine = this.fine.next;
		}
	}

	/**
	 * Restituisce il primo elemento della sequenza di elementi
	 * presenti nella coda (<font color=red>Tempo O(1)</font>).
	 * 
	 * @return il primo elemento della coda
	 * @throws EccezioneStrutturaVuota se la coda &egrave; vuota
	 */
	public Object first() throws EccezioneStrutturaVuota{
		if (this.isEmpty())
			throw new EccezioneStrutturaVuota("Coda vuota");
		return this.inizio.elem;
	}

	/** 
	 * Cancella il primo elemento della sequenza di elementi presenti nella coda (<font color=red>Tempo O(1)</font>).
	 * 
	 * @return l'elemento rimosso
	 * @throws EccezioneStrutturaVuota se la coda &egrave; vuota
	 */
	public Object dequeue(){
		if (this.isEmpty())
			throw new EccezioneStrutturaVuota("Coda vuota");
		Object e = this.inizio.elem;
		this.inizio = this.inizio.next;
		return e;
	}

}
/*
 * Copyright (C) 2007 Camil Demetrescu, Umberto Ferraro Petrillo, Irene
 * Finocchi, Giuseppe F. Italiano
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
