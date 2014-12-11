import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class CalcView implements Observer {

	private CalcModel model;
	private CalcController controller;
	
	private JFrame frame;
	JButton btnReset;
	JButton btnMultiply;
	JTextField textField;
	private JLabel lblValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcView window = new CalcView();
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
	public CalcView() {
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
		
		btnReset = new JButton("Reset");
		panel.add(btnReset);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		btnMultiply = new JButton("multiply");
		panel.add(btnMultiply);
		
		lblValue = new JLabel();
		panel.add(lblValue);
		
		model = new CalcModel();
		setController(new CalcController(model, this));
		model.addObserver(this);
		
		forceUpdateView();
	}
	
	public void forceUpdateView() {
		lblValue.setText(Double.toString(model.getValue()));
	}

	public CalcController getController() {
		return controller;
	}

	public void setController(CalcController controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			lblValue.setText(Double.toString((Double) arg1));
		} catch (Exception E) {	}
	}

}
