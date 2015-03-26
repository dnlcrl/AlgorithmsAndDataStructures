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
import java.awt.Polygon;
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
public class GUI4 extends JPanel implements MouseMotionListener, MouseListener{
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private JFrame frame;
	private JLabel label;
	private ArrayList<Point[]> segmentes;
	private Point[] tempSegment;
	private Point tempStartPosition;
	private Point tempEndPosition;
	private Polygon polygon;
	private Boolean started;
	int tempRay;
	int shape;

	public GUI4(){
		super();
		tempSegment = new Point[2];
		segmentes =  new ArrayList<Point[]>();
		started = false;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		frame = new JFrame("ES3");
		frame.addMouseMotionListener(this);
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel("<html>traccia un poligono con il mouse</html>");
		frame.add(label, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		frame.add(this, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
	}


	private void addTempSegment(){
		segmentes.add(tempSegment.clone());
		tempSegment[0] = tempSegment[1] = null;
		tempStartPosition = tempEndPosition;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		drawShapes(g2);
	}


	public void drawShapes(Graphics2D g2){
		if (polygon != null) {
			g2.drawPolygon(polygon);
			g2.setColor( Color.lightGray);
			g2.fillPolygon(polygon);
		}
		else{


			if (started) g2.drawLine(tempSegment[0].x, tempSegment[0].y, tempSegment[1].x,  tempSegment[1].y );
			for (Iterator<Point[]> iterator = segmentes.iterator(); iterator.hasNext();) {
				Point[] s = (Point[]) iterator.next();
				g2.drawLine(s[0].x, s[0].y, s[1].x,  s[1].y );
			}
		}
	}

	private void start(Point p){
		tempStartPosition = p;
		started = true;
	}


	private void closeShape(){
		started = false;
		polygon = new Polygon();
		for (Iterator<Point[]> iterator = segmentes.iterator(); iterator.hasNext();) {
			Point[] s = (Point[]) iterator.next();
			polygon.addPoint(s[0].x, s[0].y );
		}
		repaint();
	}

	private void newSegment(){
		tempSegment[0] = tempStartPosition;
		tempSegment[1] = tempEndPosition;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			e.consume();
			closeShape();
		}
		else{
			if (!started) {
				start(e.getPoint());
			}
			else{
				//gettingEndPoint = false;
				addTempSegment();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//		if (exitedGettingPoint){
		//			gettingPoint = true;
		//			exitedGettingPoint = false;
		//		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//		if (gettingPoint){ 
		//			gettingPoint = false;
		//			exitedGettingPoint = true;
		//		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (started) {
			tempEndPosition = arg0.getPoint();
			newSegment();
		}
	}

}
