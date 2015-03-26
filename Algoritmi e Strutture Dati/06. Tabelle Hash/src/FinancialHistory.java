package es3;

/**
 * @author Enrico Bacis
 */

import java.util.Hashtable;

public class FinancialHistory {
	
	private int cashOnHand; // la cifra presente sul conto
	private Hashtable<String, Integer> incomes; // le entrate
	private Hashtable<String, Integer> expenditures; // le uscite
	
	@SuppressWarnings("serial")
	class InvalidAmountException extends Exception {
		
		public InvalidAmountException(String message) {
			super(message);
		}
	}
	
	@SuppressWarnings("serial")
	class NegCashException extends Exception {
		
		public NegCashException(String message) {
			super(message);
		}
	}
	
	public FinancialHistory(int amount) throws InvalidAmountException {
		if (amount <= 0)
			throw new InvalidAmountException("Initial Amount (" + amount + ") <= 0");
		
		incomes = new Hashtable<String, Integer>();
		expenditures = new Hashtable<String, Integer>();
		
		cashOnHand = amount;
		incomes.put("Open History", amount);
	}
	
	public int cashOnHand() {
		return cashOnHand;
	}
	
	// Per evitare Null Pointer Exception controllo a priori se l'elemento esiste.
	// Se non esiste ritorno 0.
	
	public int receivedFrom(String s) {
		Integer received = (Integer)incomes.get(s);
		return (received == null ? 0 : received);
	}
	
	public int spentFor(String s) {
		Integer spent = (Integer)expenditures.get(s);
		return (spent == null ? 0 : spent);
	}
	
	public void receiveFrom(int amount, String s) throws InvalidAmountException {
		if (amount <= 0)
			throw new InvalidAmountException("Amount (" + amount + ") <= 0");
		
		cashOnHand += amount;
		incomes.put(s, amount);
	}
	
	public void spendFor(int amount, String s) throws InvalidAmountException, NegCashException {
		if (amount <= 0)
			throw new InvalidAmountException("Amount (" + amount + ") <= 0");
		
		if (cashOnHand - amount < 0)
			throw new NegCashException("Not enough cash for: '" + s + "'. (Cash = " + cashOnHand + ", Amount = " + amount + ")");
		
		cashOnHand -= amount;
		expenditures.put(s, amount);
	}
	
	public String printIncomes() {
		return incomes.toString();
	}
	
	public String printExpenditures() {
		return expenditures.toString();
	}
}
