package es1;

/**
 * @author Enrico Bacis
 */

public class CoordinatePair {
	
	private int x, y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CoordinatePair)) return false;
		
		CoordinatePair other = (CoordinatePair)o;
		return ((this.x == other.getX()) && (this.y == other.getY()));
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + getX();
		hash = 31 * hash + getY();
		return hash;
	}
}
