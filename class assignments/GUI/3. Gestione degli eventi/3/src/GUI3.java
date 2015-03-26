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


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;        

@SuppressWarnings("serial")
public class GUI3 extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private JFrame frame;
	private JLabel label;
	private ArrayList<Shape> shapes;
	private Shape tempShape;
	private Point tempPosition;
	private Point mousePosition;
	private Boolean gettingRay, exitedGettingRay;
	int tempRay;
	int shape;

	public GUI3(){
		super();
		shapes =  new ArrayList<Shape>();
		gettingRay = exitedGettingRay = false;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		frame = new JFrame("ES3");
		frame.addKeyListener(this);
		frame.addMouseMotionListener(this);
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel("<html>usa i tasti 'c' o 's' e clicca!</html>");
		frame.add(label, BorderLayout.NORTH);
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		frame.add(this, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
	}

	private void generatePositionAndRay(){
		tempPosition = new Point( randInt(10, this.getWidth()-10),randInt(10, this.getHeight()-10));
		if(mousePosition != null) tempRay = (int) tempPosition.distance(mousePosition);
		else tempRay = 10;
	}

	private void newSquare(){
		gettingRay = true;
		shape = 0;
		tempShape = new Rectangle2D.Double(tempPosition.x, tempPosition.y,
				tempRay,
				tempRay);
		repaint();
	}

	private void newCircle(){
		gettingRay = true;
		shape = 1;
		tempShape = new Ellipse2D.Double(tempPosition.x, tempPosition.y,
				tempRay,
				tempRay);
		repaint();
	}

	private void addTempShape(){
		shapes.add(tempShape);
		tempShape = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		drawShapes(g2);
	}


	public void drawShapes(Graphics2D g2){
		if (tempShape != null) g2.draw(tempShape);
		for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
			Shape s = (Shape) iterator.next();
			g2.draw(s);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 83:
			generatePositionAndRay();
			System.out.println("new square");
			newSquare();
			break;
		case 67:
			generatePositionAndRay();
			System.out.println("new circle");
			newCircle();
			break;
		default:break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (gettingRay){
			mousePosition = arg0.getPoint();
			tempRay = (int) tempPosition.distance(mousePosition);
			if (shape == 0 ) newSquare();
			else newCircle();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gettingRay){
			gettingRay = false;
			addTempShape();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if (exitedGettingRay){
			gettingRay = true;
			exitedGettingRay = false;
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (gettingRay){ 
			gettingRay = false;
			exitedGettingRay = true;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
