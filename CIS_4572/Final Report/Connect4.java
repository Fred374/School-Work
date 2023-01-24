import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Connect4 extends Choose{
	public static void main(String[] args) {
		int[][] t= {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{1,2,3,4,5,6,7}};
		Scanner scan=new Scanner(System.in);
		printT(t);
		int b=1;
		int a;
		int c;
		boolean win=false;
		while (win==false) {
			a = scan.nextInt();
			while (!(a>0&&a<8)) {
				System.out.println("please try again");
				a = scan.nextInt();
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