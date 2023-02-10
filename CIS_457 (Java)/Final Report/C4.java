import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class C4 extends Choose{
	public static void main(String[] args) {
		new Choose();
		int[][] t= {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{1,2,3,4,5,6,7}};
		Scanner scan=new Scanner(System.in);
		printT(t);
		int b=1;
		int a;
		int c;
		boolean win=false;
		while (win==false) {
			a = CvChoose.Column();
			while (!(a>0&&a<8)) {
				System.out.println("please try again");
				a = CvChoose.Column();
			}
			c = addT(t,a,b);
			if (c == 0) {
				printT(t);
				win = wint(t,b);
				b=-b;
			}
		}
	}

	
	public static void printT(int[][] t) {
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public static int addT(int[][] t,int a,int b) {
		int c=6;
		int d=1;
		a--;
		do {
			if (c != -1 && t[c][a]==0) {
				t[c][a]=b;
				d=0;
			} 
			else if (c == -1) {
				System.out.println("please try again");
				d = -1;
			}
			else
				c--;
		} while (d>0);
		return d;
	}
	
	public static boolean wint(int[][] t, int b) {
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				if (j<4&&t[i][j]==b&&(t[i][j]==t[i][j+1]&&t[i][j]==t[i][j+2]&&t[i][j]==t[i][j+3])) {
					System.out.println("Player " + b + " wins");
					return true;
				}
				else if (i<4&&t[i][j]==b&&(t[i][j]==t[i+1][j]&&t[i][j]==t[i+2][j]&&t[i][j]==t[i+3][j])) {
					System.out.println("Player " + b + " wins");
					return true;
				}
				else if (i<4&&j<4&&t[i][j]==b&&(t[i][j]==t[i+1][j+1]&&t[i][j]==t[i+2][j+2]&&t[i][j]==t[i+3][j+3])) {
					System.out.println("Player " + b + " wins");
					return true;
				}
				else if (i>3&&j<4&&t[i][j]==b&&(t[i][j]==t[i-1][j+1]&&t[i][j]==t[i-2][j+2]&&t[i][j]==t[i-3][j+3])) {
					System.out.println("Player " + b + " wins");
					return true;
				}
			}
		}
		return false;
	}
}


class Choose extends Frame {
   //public static void main(String[] args) {new Choose();}

   Choose() {
      super("Choose column by clicking");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(800, 600);
      add("Center", new CvChoose());
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      setVisible(true);
   }
}

class CvChoose extends Canvas {
	int maxX, maxY, minMaxXY, xCenter, yCenter;
	static Vector<Point2D> v = new Vector<Point2D>();
	float x0, y0, rWidth = 10.0F, rHeight = 7.5F, pixelSize;
	boolean ready = true;
	static Point2D a;
	
	
	
	CvChoose() {
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
		maxX = d.width - 1; maxY = d.height - 1;
		minMaxXY = Math.min(maxX, maxY);
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		xCenter = maxX / 2; yCenter = maxY / 2;
	}
	
	int iX(float x) {return Math.round(x);}
	
	int iY(float y) {return maxY - Math.round(y);}

	float fx(int x) {return (x - xCenter) * pixelSize;}

	float fy(int y) {return (yCenter - y) * pixelSize;}
	
	static int Column () {
		new Choose();
		
		System.out.println(a.x);
		if (a.x < -4.5) {
			return 1;
		}
		else if (a.x < -4.0) {
			return 2;
		}
		else if (a.x < -3.5) {
			return 3;
		}
		else if (a.x < -3.0) {
			return 4;
		}
		else if (a.x < -2.5) {
			return 5;
		}
		else if (a.x < -2.0) {
			return 6;
		}
		else if (a.x < -1.5) {
			return 7;
		}
		else
			return 8;
	}

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
		//g.drawRect(iX(a.x) - 2, iY(a.y) - 2, 4, 4);
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