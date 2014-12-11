import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalcController {
	
	private CalcModel model;
	private CalcView view;
	
	public CalcController(CalcModel m, CalcView v) {
		model = m;
		view = v;
		
		registerListener();
	}

	private void registerListener() {
		// eleganza zero con la classe anonima.
		view.btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
				view.textField.setText("");
			}
		});
		
		view.btnMultiply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double d = Double.parseDouble(view.textField.getText());
					model.multiply(d);
				} catch (Exception E) {	}								
			}
		});
	}
}
