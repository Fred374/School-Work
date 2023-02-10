import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LineDraw extends Frame{

	public static void main(String[] args) {new LineDraw();}
	//call the draw line method
	LineDraw() {
		super("LineDraw");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		setSize(600, 400);
		add("Center",new CvLineDraw());
		setVisible(true);
	
	}
}
class CvLineDraw extends Canvas{
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
		float xP, yP, xQ, yQ;
		xP = (float)scan.nextInt(); yP = (float)scan.nextInt();
		xQ = (float)scan.nextInt(); yQ = (float)scan.nextInt();
		float x = xP;
		float y = yP;
		float m = (float)(yQ - yP)/(float)(xQ -xP);
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
		g.drawLine(iX(xP),iY(yP),iX(xQ),iY(yQ));
	}
}