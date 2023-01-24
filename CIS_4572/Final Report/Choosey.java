// DefPoly.java: Drawing a polygon.
// Uses: CvDefPoly (discussed below).

// Copied from Section 1.5 of
//    Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 2nd Edition,
//       Chichester: John Wiley.

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Choosey extends Frame {
   //public static void main(String[] args) {new Choose();}
	public static void main(String[] args) {new Choosey();}
   Choosey() {
      super("Choose column by clicking");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(800, 600);
      add("Center", new CvChoosey());
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      setVisible(true);
   }
}

class CvChoosey extends Canvas {
	int maxX, maxY, minMaxXY, xCenter, yCenter;
	Vector<Point2D> v = new Vector<Point2D>();
	float x0, y0, rWidth = 10.0F, rHeight = 7.5F, pixelSize;
	boolean ready = true;
	static Point2D a;
	
	CvChoosey() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				float xA = fx(evt.getX()), yA = fy(evt.getY());
				if (ready) {
					v.removeAllElements();
					x0 = xA; y0 = yA;
					ready = false;
				}
				float dx = xA - x0, dy = yA - y0;
				if (v.size() > 0 && 
				dx * dx + dy * dy < 20 * pixelSize * pixelSize)
				// Previously 4 instead of 20 .........................
				ready = true;
				else
					v.addElement(new Point2D(xA, yA));
				
				
				// Added December 2016:
				if(evt.getModifiers()==InputEvent.BUTTON3_MASK) {
					ready = true;
				}
				
				System.out.println(a.x);
				repaint();
			}
		});
	}
	
	void initgr() {
		Dimension d = getSize();
		maxX = d.width - 1; maxY = d.height - 1;
		minMaxXY = Math.min(maxX, maxY);
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		xCenter = maxX / 2; yCenter = maxY / 2;
	}
	
	int iX(float x) {return Math.round(x);}
	
	int iY(float y) {return maxY - Math.round(y);}

	float fx(int x) {return (x - xCenter) * pixelSize;}

	float fy(int y) {return (yCenter - y) * pixelSize;}

	public void paint(Graphics g) {
		initgr();
		
		int left = iX(-rWidth / 2), right = iX(rWidth / 2), 
		bottom = iY(-rHeight / 2), top = iY(rHeight / 2);
		g.drawRect(left, top, right - left, bottom - top);
		int n = v.size();
		if (n == 0)
			return;
		for (int m = n-1; m < n; m++) {
		a = (Point2D) (v.elementAt(m));
		// Show tiny rectangle around first vertex:
		g.drawRect(iX(a.x) - 2, iY(a.y) - 2, 4, 4);
		}
	  
	  
		float side = 0.1F * minMaxXY, sideHalf = 0.5F * side, 
		h = sideHalf * (float) Math.sqrt(4),
		xA, yA, xB, yB, xC, yC, xD, yD,
		xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1,
		xA2, yA2, xB2, yB2, xC2, yC2, xD2, yD2, p, q;
		q = 0.2F; p = 1 - q;
		xA = 0; yA = 0;
		xB = side; yB = yA;
		xC = side; yC = side;
		xD = 0; yD = yC;
		xA2 = xA; xB2 = xB; xC2 = xC; xD2 = xD;
		yA2 = yA; yB2 = yB; yC2 = yC; yD2 = yD;
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				xA = 0+i*side; yA = 0+j*side;
				xB = side+i*side; yB = 0+j*side;
				xC = side+i*side; yC = side+j*side;
				xD = 0+i*side; yD = side+j*side;
				g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
				g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
				g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
				g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
			}
		}
	}
}