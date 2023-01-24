import java.awt.*;
import java.awt.event.*;

public class TriangleHex extends Frame {
   public static void main(String[] args) {new TriangleHex();}

   TriangleHex() {
      super("Triangles: 50 triangles inside each other");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(600, 400);
      add("Center", new CvTriangleHex());
      setVisible(true);
   }
}

class CvTriangleHex extends Canvas {
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
      float side = 0.45F * minMaxXY, sideHalf = 0.5F * side, 
            h = sideHalf * (float) Math.sqrt(3), 
            xA, yA, xB, yB, xC, yC, xA1, yA1, xB1, yB1, xC1, yC1, p, q;
      q = 0.1F; p = 1 - q;
      xA = xCenter - sideHalf-0.5F*h; yA = yCenter;
      xB = xCenter - sideHalf; yB = yA-h;
      xC = xCenter; yC = yCenter;
      for (int i = 0; i < 25; i++) {
		  g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
		  g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
		  g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
		  xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
		  xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
		  xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
		  xA = xA1; xB = xB1; xC = xC1;
		  yA = yA1; yB = yB1; yC = yC1;
	  }
	 
	 xA = xCenter - sideHalf; yA = yCenter - h;
	 xB = xCenter + sideHalf; yB = yA;
	 xC = xCenter; yC = yCenter;
	 for (int i = 0; i < 25; i++) {
         g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
         g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
         g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
         xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
         xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
         xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
         xA = xA1; xB = xB1; xC = xC1;
         yA = yA1; yB = yB1; yC = yC1;
	 }
	 
	 xA = xCenter + sideHalf; yA = yCenter - h;
	 xB = xCenter + sideHalf+0.5F*h; yB = yA + h;
	 xC = xCenter; yC = yCenter;
	 for (int i = 0; i < 25; i++) {
         g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
         g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
         g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
         xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
         xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
         xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
         xA = xA1; xB = xB1; xC = xC1;
         yA = yA1; yB = yB1; yC = yC1;
	 }
	 
	       xA = xCenter - sideHalf-0.5F*h; yA = yCenter;
      xB = xCenter - sideHalf; yB = yA+h;
      xC = xCenter; yC = yCenter;
      for (int i = 0; i < 25; i++) {
		  g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
		  g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
		  g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
		  xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
		  xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
		  xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
		  xA = xA1; xB = xB1; xC = xC1;
		  yA = yA1; yB = yB1; yC = yC1;
	  }
	 
	 xA = xCenter - sideHalf; yA = yCenter + h;
	 xB = xCenter + sideHalf; yB = yA;
	 xC = xCenter; yC = yCenter;
	 for (int i = 0; i < 25; i++) {
         g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
         g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
         g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
         xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
         xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
         xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
         xA = xA1; xB = xB1; xC = xC1;
         yA = yA1; yB = yB1; yC = yC1;
	 }
	 
	 xA = xCenter + sideHalf; yA = yCenter + h;
	 xB = xCenter + sideHalf+0.5F*h; yB = yA - h;
	 xC = xCenter; yC = yCenter;
	 for (int i = 0; i < 25; i++) {
         g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
         g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
         g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
         xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
         xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
         xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
         xA = xA1; xB = xB1; xC = xC1;
         yA = yA1; yB = yB1; yC = yC1;
		 System.out.println(Tools2D.area2(new Point2D(xC,yC),new Point2D(xB,yB),new Point2D(xA,yA)));
		 System.out.println((Tools2D.circumcenter(new Point2D(xC,yC),new Point2D(xB,yB),new Point2D(xA,yA))).x +
		 ", " + (Tools2D.circumcenter(new Point2D(xC,yC),new Point2D(xB,yB),new Point2D(xA,yA))).y);
	 }
   }
}