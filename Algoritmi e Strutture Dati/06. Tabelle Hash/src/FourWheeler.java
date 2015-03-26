package es2;

/**
 * @author Enrico Bacis
 */

public class FourWheeler {
	
	private String name;
	private int purchaseValue;
	private int noOfTyres;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(int purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public int getNoOfTyres() {
		return noOfTyres;
	}

	public void setNoOfTyres(int noOfTyres) {
		this.noOfTyres = noOfTyres;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FourWheeler)) return false;
		
		FourWheeler other = (FourWheeler)o;
		
		return (((name == null) ? other.getName() == null : name.equalsIgnoreCase(other.getName())) &&
				(this.purchaseValue == other.getPurchaseValue()) &&
				(this.noOfTyres == other.getNoOfTyres()));
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (name == null ? 0 : name.hashCode());
		hash = 31 * hash + purchaseValue;
		hash = 31 * hash + noOfTyres;
		return hash;
	}
}
