import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DefSquare extends Frame {
   public static void main(String[] args) {new DefSquare();}

   DefSquare() {
      super("Define Square vertices by clicking");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(500, 300);
      add("Center", new CvDefSquare());
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      setVisible(true);
   }
}





class CvDefSquare extends Canvas {
   Vector<Point2D> v = new Vector<Point2D>();
   float x0, y0, rWidth = 10.0F, rHeight = 7.5F, pixelSize;
   boolean ready = true;
   int centerX, centerY;

   CvDefSquare() {
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
            
            
            
            repaint();
         }
      });
   }

   void initgr() {
      Dimension d = getSize();
      int maxX = d.width - 1, maxY = d.height - 1;
      pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
      centerX = maxX / 2; centerY = maxY / 2;
   }

   int iX(float x) {return Math.round(centerX + x / pixelSize);}
   int iY(float y) {return Math.round(centerY - y / pixelSize);}
   float fx(int x) {return (x - centerX) * pixelSize;}
   float fy(int y) {return (centerY - y) * pixelSize;}

   public void paint(Graphics g) {
      initgr();
      int left = iX(-rWidth / 2), right = iX(rWidth / 2), 
          bottom = iY(-rHeight / 2), top = iY(rHeight / 2);
      g.drawRect(left, top, right - left, bottom - top);
      int n = v.size();
      if (n == 0)
         return;
      Point2D a = (Point2D) (v.elementAt(0));
      // Show tiny rectangle around first vertex:
	  Point2D b = (Point2D) (v.elementAt(1));
      g.drawRect(iX(a.x) - 2, iY(a.y) - 2, 4, 4);
      for (int i = 1; i <= 1; i++) {
         if (i == 2 && !ready)
            break;
         
         g.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y));
         g.drawRect(iX(b.x) - 2, iY(b.y) - 2, 4, 4); // Tiny rectangle; added
         g.drawString(""+(i%n), iX(b.x), iY(b.y));// to test.......
      }
	  float length = (float)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y))-0.5F;
	  if ((a.x-b.x) > 0) {
			length = -length;
	  }
	  g.drawLine(iX(b.x),iY(b.y),iX(b.x+(a.y-b.y)),iY(b.y+length));
	  g.drawLine(iX(b.x+(a.y-b.y)),iY(b.y+length),iX(a.x+(a.y-b.y)),iY(a.y+length));
	  g.drawLine(iX(a.x+(a.y-b.y)),iY(a.y+length),iX(a.x),iY(a.y));
   }
}
