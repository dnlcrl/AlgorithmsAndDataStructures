package es3;

/**
 * @author Enrico Bacis
 */

import es3.FinancialHistory;
import es3.FinancialHistory.InvalidAmountException;
import es3.FinancialHistory.NegCashException;

public class FinancialHistoryDemo {
	
	public static void main(String[] args) {
		
		try {
			
			FinancialHistory history;
			
			// history = new FinancialHistory(-10);
			System.out.println("new FinancialHistory(100)");
			history = new FinancialHistory(100);
			
			System.out.println("\nhistory.receiveFrom(20, \"Enrico\")");
			history.receiveFrom(20, "Enrico");
			
			System.out.println("\nhistory.spendFor(80, \"Viaggio\")");
			history.spendFor(80, "Viaggio");
			
			System.out.println("\nhistory.printIncomes()");
			System.out.println(history.printIncomes());
			
			System.out.println("\nhistory.printExpenditures()");
			System.out.println(history.printExpenditures());
			
			System.out.println("\nhistory.receivedFrom(\"Enrico\")");
			System.out.println(history.receivedFrom("Enrico"));
			
			System.out.println("\nhistory.receivedFrom(\"Not Existed\")");
			System.out.println(history.receivedFrom("Not Existed"));
			
			System.out.println("\nhistory.spentFor(\"Viaggio\")");
			System.out.println(history.spentFor("Viaggio"));
			
			System.out.println("\nhistory.spentFor(\"Not Existed\")");
			System.out.println(history.spentFor("Not Existed"));
			
			System.out.println("\nhistory.cashOnHand()");
			System.out.println(history.cashOnHand());
			
			System.out.println("\nhistory.spendFor(80, \"Altro Viaggio\")");
			history.spendFor(80, "Altro Viaggio");
			
		} catch (InvalidAmountException e) {
			System.out.println("Negative Amount Exception: " + e.getMessage());
		}
		catch (NegCashException e) {
			System.out.println("Negative Cash Exception: " + e.getMessage());
		}
		
	}

}
