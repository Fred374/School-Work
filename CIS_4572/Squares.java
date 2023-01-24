import java.awt.*;
import java.awt.event.*;

public class Squares extends Frame {
   public static void main(String[] args) {new Squares();}

   Squares() {
      super("Squares: 50 Squares inside each other");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(600, 400);
      add("Center", new CvSquares());
      setVisible(true);
   }
}

class CvSquares extends Canvas {
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
	  for (int i=0; i<8; i++) {
		  for (int j=0; j<8; j++) {
			  xA = 0+i*side; yA = 0+j*side;
			  xB = side+i*side; yB = 0+j*side;
			  xC = side+i*side; yC = side+j*side;
			  xD = 0+i*side; yD = side+j*side;
			  for (int k = 0; k < 10; k++) {
				  g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
				  g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
				  g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
				  g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
				  xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
				  xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
				  xC1 = p * xC + q * xD; yC1 = p * yC + q * yD;
				  xD1 = p * xD + q * xA; yD1 = p * yD + q * yA;
				  xA = xA1; xB = xB1; xC = xC1; xD = xD1;
				  yA = yA1; yB = yB1; yC = yC1; yD = yD1;
				  }
			}
		}
	}
}