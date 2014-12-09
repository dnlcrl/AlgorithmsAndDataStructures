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

import java.awt.Dimension;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.*;        

public class Main4 {
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	JFrame[] frames;
	private void createAndShowGUI() {
		//Create and set up the window.
		String whatTheUserEntered = null;
		int width = 0;
		int heigth = 0;
		while (whatTheUserEntered == null || width == 0) {
			whatTheUserEntered = JOptionPane.showInputDialog("inserire larghezza");
			width = getNum(whatTheUserEntered);
		}
		whatTheUserEntered = null;
		while (whatTheUserEntered == null || heigth == 0) {
			whatTheUserEntered = JOptionPane.showInputDialog("inserire altezza");
			heigth = getNum(whatTheUserEntered);
		}
		JFrame frame = new JFrame("ES4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width,heigth));
		//Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel(width + "x" + heigth);
		frame.getContentPane().add(label);
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private int getNum(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}

	public static void main(String[] args) {
		Main4 m = new Main4();

		m.createAndShowGUI();
	}
}