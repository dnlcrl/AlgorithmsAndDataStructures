/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 



import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;        

public class GUI2 {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	private String fontFamilysString;
	private int fontDimension;
	private Color fontColor;
	
	public GUI2(){
		fontFamilysString = null;
		fontDimension = 18;
		fontColor = Color.black;
		getFont();
		createAndShowGUI();
	}
	
	private int getNum(String s) {
    	try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
	
	private void getFont(){
		int result = JOptionPane.DEFAULT_OPTION;
		while (result != JOptionPane.OK_OPTION) {

			JPanel myPanel = new JPanel();

			String fonts[] = 
					GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			JComboBox fontBox = new JComboBox(fonts);
			JTextField fontDimensionField = new JTextField(3);
			JButton chooseButton = new JButton("Choose Color");
			chooseButton.addActionListener((ActionListener) new ButtonListener());


			myPanel.add(new JLabel("Font:"));
			myPanel.add(fontBox);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Dimension:"));
			myPanel.add(fontDimensionField);
			myPanel.add(chooseButton);


			result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				int n = getNum(fontDimensionField.getText());
				if (n != 0) fontDimension  = n;
				fontFamilysString =  fonts[fontBox.getSelectedIndex()];
			}	
		}
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Color c = JColorChooser.showDialog(null, "Choose a Color", fontColor);
			if (c != null)
				fontColor = c;
		}
	}
	
    private void createAndShowGUI() {
        final JFrame frame = new JFrame("ES2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("ES2");
        label.setFont(new Font(fontFamilysString, Font.PLAIN, fontDimension ));
        label.setForeground(fontColor);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
