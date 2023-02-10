import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LineCut extends Frame{

	public static void main(String[] args) {new LineCut();}
	//call the Cut line method
	LineCut() {
		super("LineCut");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		setSize(600, 400);
		add("Center",new CvLineCut());
		setVisible(true);
	
	}
}
class CvLineCut extends Canvas{
	int maxX, maxY, minMaxXY, xCenter, yCenter;
	
	void initgr() {
      Dimension d = getSize();
      maxX = d.width - 1; maxY = d.height - 1;
      minMaxXY = Math.min(maxX, maxY);
      xCenter = maxX / 2; yCenter = maxY / 2;
   }
   
   int iX(float x) {return Math.round(x);}

   int iY(float y) {return maxY - Math.round(y);}
   
   void putPixel(Graphics g, int x, int y) {
	   g.drawLine(x,y,x,y);
   }
   
	public void paint (Graphics g) {
		initgr();
		Scanner scan=new Scanner(System.in);
		float xP, yP, xQ, yQ, xMin, xMax, yMin, yMax;
		xP = (float)scan.nextInt(); yP = (float)scan.nextInt();
		xQ = (float)scan.nextInt(); yQ = (float)scan.nextInt();
		float x = xP;
		float y = yP;
		float m = (float)(yQ - yP)/(float)(xQ -xP);
		if (m > 100) 
			m = 0;
		xMin = 100; yMin = 100;
		xMax = 200; yMax = 200;
		//draw the line
		if (m <= 1 && m >= -1) {
			for (x=xP; x<=xQ; x++) {
				putPixel(g, iX(x), iY(y));
				y = y+m;
			}
		}
		else {
			for (y = yP; y<=yQ; y++) {
				putPixel(g, iX(x), iY(y));
				x = x+m;
			}
		}
		g.clearRect(0, 0, 600, 400);
		g.drawLine(iX(xP),iY(yP),iX(xQ),iY(yQ));
		try { Thread.sleep(1000);}
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		System.out.println("cut line will be 10 pixels right of original");
		if (xP < xMin) {
			xP = xMin;
			yP = yP + m * (xMin - xP);
		}
		if (xP > xMax) {
			xP = xMax;
			yP = yP - m * (xMax - xP);
		}
		if (xQ < xMin) {
			xQ = xMin;
			yQ = yQ + m * (xMin - xQ);
		}
		if (xQ > xMax) {
			xQ = xMax;
			yQ = yQ - m * (xMax - xQ);
		}
		if (yP < yMin) {
			yP = yMin;
			xP = xP + m * (yMin - yP);
		}
		if (yP > yMax) {
			yP = yMax;
			xP = xP - m * (yMax - yP);
		}
		if (yQ < yMin) {
			yQ = xMin;
			xQ = xQ + m * (yMin - yQ);
		}
		if (yQ > yMax) {
			yQ = yMax;
			xQ = xQ - m * (yMax - yQ);
		}
		g.drawLine(iX(xP+10),iY(yP),iX(xQ+10),iY(yQ));
	}
}