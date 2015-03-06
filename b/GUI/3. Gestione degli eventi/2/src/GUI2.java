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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;        

public class GUI2 implements KeyListener{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	private JFrame frame;
	private JLabel label;
	private int fontSize;
	private int fontR; 
	private int fontG; 
	private int fontB; 
	
	public GUI2(){
		fontSize = 14;
		fontR = 0;
		fontG = 0;
		fontB = 0;
		createAndShowGUI();
	}
	
    private void createAndShowGUI() {
        frame = new JFrame("ES1");
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("<html>usa i tasti su e giù!<br>o r(e), g(f), b(v)!</html>", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, fontSize));
        frame.add(label);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
    
    private void enhanceRedColor(){
    	if (fontR < 255) fontR ++;
    	reloadColor();
    }
    private void enhanceGreenColor(){
    	if (fontG < 255) fontG ++;
    	reloadColor();
    }
    private void enhanceBlueColor(){
    	if (fontB < 255) fontB ++;
    	reloadColor();
    }
    private void reduceRedColor(){
    	if (fontR > 0) fontR --;
    	reloadColor();
    }
    private void reduceGreenColor(){
    	if (fontG > 0) fontG --;
    	reloadColor();
    }
    private void reduceBlueColor(){
    	if (fontB > 0) fontB --;
    	reloadColor();
    }
    private void reloadColor(){
		System.out.println("red " + fontR + "green " + fontG + "blue " + fontB);
    	label.setForeground(new Color(fontR, fontG, fontB));
    }
    
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()) {
		case 38:
			label.setFont(new Font("Serif", Font.PLAIN, fontSize++));
            System.out.println("size " +fontSize);
			break;
		case 40:
			label.setFont(new Font("Serif", Font.PLAIN, fontSize--));
            System.out.println("size " + fontSize);
			break;
		case 82:
			for (int i = 0; i < 10; i++) enhanceRedColor();
			break;
		case 71:
			for (int i = 0; i < 10; i++) enhanceGreenColor();
			break;
		case 66:
			for (int i = 0; i < 10; i++) enhanceBlueColor();
			break;
		case 69:
			for (int i = 0; i < 10; i++) reduceRedColor();
			break;
		case 70:
			for (int i = 0; i < 10; i++) reduceGreenColor();
			break;
		case 86:
			for (int i = 0; i < 10; i++) reduceBlueColor();
			break;
		default:
			System.out.println("keyPressed " + e.getKeyCode());
			break;
    	}
            
    }

    public void keyReleased(KeyEvent e) {
    	System.out.println("keyReleased");
    }
    public void keyTyped(KeyEvent e) {
    }
}
