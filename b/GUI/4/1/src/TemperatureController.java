import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TemperatureController {
	
	private TemperatureModel model;
	private TemperatureView view;
	
	public TemperatureController(TemperatureModel m, TemperatureView v) {
		model = m;
		view = v;
		
		registerListeners();
	}
	
	private void registerListeners() {
		view.buttonMinus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.decrease();
				view.updateView();
			}
		});
		
		view.buttonPlus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.increase();
				view.updateView();
			}
		});
	}

}
