import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TrianglesHW extends Frame {
   public static void main(String[] args) {new TrianglesHW();}

   TrianglesHW() {
      super("pointProjection");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(600, 400);
      add("Center", new CvTrianglesHW());
      setVisible(true);
   }
}

class CvTrianglesHW extends Canvas {
   int maxX, maxY, minMaxXY, xCenter, yCenter;

   void initgr() {
      Dimension d = getSize();
      maxX = d.width - 1; maxY = d.height - 1;
      minMaxXY = Math.min(maxX, maxY);
      xCenter = maxX / 2; yCenter = maxY / 2;
   }

   int iX(float x) {return Math.round(x);}

   int iY(float y) {return maxY - Math.round(y);}

   public void paint(Graphics g) {
      initgr();
	  Scanner scan=new Scanner(System.in);
      float xA, yA, xB, yB, xC, yC, xD, yD, a1, a2, a3, a4;
      xA = (float)scan.nextInt(); yA = (float)scan.nextInt();
      xB = (float)scan.nextInt(); yB = (float)scan.nextInt();
      xC = (float)scan.nextInt(); yC = (float)scan.nextInt();
	  xD = (float)scan.nextInt(); yD = (float)scan.nextInt();
	  
	  g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
	  g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
	  g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
	  
	  Point2D a = new Point2D(xA,yA);
	  Point2D b = new Point2D(xB,yB);
	  Point2D c = new Point2D(xC,yC);
	  Point2D d = new Point2D(xD,yD);
	  a1 = Tools2D.area2(a,b,d)*2;
	  a2 = Tools2D.area2(b,c,d)*2;
	  a3 = Tools2D.area2(c,d,a)*2;
	  a4 = Tools2D.area2(a,b,c)*2;
	  g.drawRect(iX(xD) - 2, iY(yD) - 2, 4, 4);
	  if (a1 == 0.05F || a2 == 0.05F || a3 == 0.05F) {
		  System.out.println("the fourth point is on the triangle");
	  } else if (a1+a2+a3 == a4) {
		System.out.println("the fourth is inside the triangle");
	  }
	else
		System.out.println("the fourth point is not inside the triangle");
	  
      }
   }
