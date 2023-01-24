import java.awt.*;
import java.awt.event.*;
import java.util.*;

// For some reason that I can't figure out you need to input the points twice
// you input the window and viewport boundaries then the point. Then do it again.
// Both times it actually draws all points but for some reason it erases it after the first time.

public class ViewTransform extends Frame {
	public static void main(String[] args) {new ViewTransform();}

	ViewTransform() {
		super("Viewport Transformation");
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		setSize(500, 300);
		add("Center", new CvViewTransform());
		setVisible(true);
	}
}

class CvViewTransform extends Canvas {
	int centerX, centerY, currentX, currentY, maxX, maxY;
	float pixelSize, rWidth = 100.0F, rHeight = 100.0F;

	void initgr() {
		Dimension d = getSize();
		maxX = d.width - 1;
		maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2; centerY = maxY / 2;
	}

	int iX(float x) {return Math.round(centerX + x / pixelSize);}
	int iY(float y) {return Math.round(centerY - y / pixelSize);}

	void moveTo(float x, float y) {currentX = iX(x); currentY = iY(y);}

	void lineTo(Graphics g, float x, float y) {
		int x1 = iX(x), y1 = iY(y);
		g.drawLine(currentX, currentY, x1, y1);
		currentX = x1; currentY = y1;
	}

	void drawArrow(Graphics g, int x, int y) {
		moveTo(x, y);
		lineTo(g, x+1, y);
		lineTo(g, x+1, y+1);
		lineTo(g, x, y+1);
		lineTo(g, x, y);
	}

	public void paint(Graphics g) {
		initgr();
		System.out.println("Please Input Window Boundaries(xmin xmax ymin ymax): ");
		Scanner scan = new Scanner(System.in);
		int xwmin = scan.nextInt();
		int xwmax = scan.nextInt();
		int ywmin = (scan.nextInt());
		int ywmax = (scan.nextInt());
		moveTo(xwmin,ywmin);
		lineTo(g,xwmax,ywmin);
		lineTo(g,xwmax,ywmax);
		lineTo(g,xwmin,ywmax);
		lineTo(g,xwmin,ywmin);
		System.out.println("Please Input Viewport Boundaries(xmin xmax ymin ymax): ");
		int xvmin = scan.nextInt();
		int xvmax = scan.nextInt();
		int yvmin = (scan.nextInt());
		int yvmax = (scan.nextInt());
		moveTo(xvmin,yvmin);
		lineTo(g,xvmax,yvmin);
		lineTo(g,xvmax,yvmax);
		lineTo(g,xvmin,yvmax);
		lineTo(g,xvmin,yvmin);
		System.out.println("Please Input Point to be transformed (x y within window boundaries): ");
		int xw = scan.nextInt();
		int yw = scan.nextInt();
		drawArrow(g, xw, yw);
		int scaleFactorX = (xvmax - xvmin)/(xwmax - xwmin);
		int scaleFactorY = (yvmax - yvmin)/(ywmax - ywmin);
		int xv = (xw * scaleFactorX) + xvmin - xwmin;
		int yv = (yw * scaleFactorY) + yvmin - ywmin;
		System.out.println("xv = " + xv " yv = " + yv);
		drawArrow(g, xv, yv);
   }
}
