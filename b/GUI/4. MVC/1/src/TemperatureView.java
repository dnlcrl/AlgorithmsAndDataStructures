import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;


public class TemperatureView {
	
	TemperatureModel model;
	TemperatureController controller;
	
	private JFrame frame;
	JButton buttonPlus;
	JButton buttonMinus;
	private JLabel lblTemperature;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemperatureView window = new TemperatureView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TemperatureView() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		buttonMinus = new JButton("-");
		panel.add(buttonMinus);
		
		lblTemperature = new JLabel();
		panel.add(lblTemperature);
		
		buttonPlus = new JButton("+");
		panel.add(buttonPlus);
		
		model = new TemperatureModel();
		controller = new TemperatureController(model, this);
		
		updateView();
	}
	
	public void updateView() {
		lblTemperature.setText(Double.toString(model.getValue()));
	}

}
