import java.util.Observable;

public class CalcModel extends Observable {
	
	private double value;
	
	public CalcModel() {
		value = 1;
	}
	
	public void clear() {
		value = 1;
		setChanged();
		notifyObservers(value);
	}
	
	public void multiply(double d) {
		value *= d;
		setChanged();
		notifyObservers(value);
	}
	
	public double getValue() {
		return value;
	}
}