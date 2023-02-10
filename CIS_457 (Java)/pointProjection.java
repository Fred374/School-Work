import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class pointProjection extends Frame {
   public static void main(String[] args) {new pointProjection();}

   pointProjection() {
      super("pointProjection");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(600, 400);
      add("Center", new CvPointProjection());
      setVisible(true);
   }
}

class CvPointProjection extends Canvas {
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
      float xA, yA, xB, yB, xC, yC;
      xA = (float)scan.nextInt(); yA = (float)scan.nextInt();
      xB = (float)scan.nextInt(); yB = (float)scan.nextInt();
      xC = (float)scan.nextInt(); yC = (float)scan.nextInt();
	  
	  g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
	  
	  g.drawRect(iX(xC) - 2, iY(yC) - 2, 4, 4);
	  float theta = ((xC*xA)+(yC*yA))*((xB*xA)+(yB*yA));
	  float theta2 = ((xB*xA)+(yB*yA))*((xB*xA)+(yB*yA));
	  System.out.println(theta);
	  System.out.println(theta2);
	  if (0<theta2 && theta2<theta)
		System.out.println("the third point is not projected on the line");
	else
		System.out.println("the third point is projected on the line");
	  
      }
   }
