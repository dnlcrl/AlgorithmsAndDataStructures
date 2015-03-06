
public class TemperatureModel {
	
	private double temperature;
	
	public TemperatureModel() {
		temperature = 18;
	}
	
	public void increase() {
		temperature++;
	}
	
	public void decrease() {
		temperature--;
	}
	
	public double getValue() {
		return temperature;
	}
}
