// Frederick Kelemen

// IN CLASS WORK

public class InClassWork2 {
	public static void main(String[] args) {
		int[] a = {1,1};
		int[] b = {2,1};
		int dotAnswer;
		int[] crossAnswer = new int[a.length];
		dotAnswer = dotProduct(a,b);
		System.out.println(dotAnswer);
		crossAnswer = crossProduct(a,b);
	System.out.println(crossAnswer[0]+" "+crossAnswer[1]);
	}
	
	public static int dotProduct(int[] a,int[] b) {
		int[] c=new int[a.length];
		int d=0;
		for (int i=0;i<a.length;i++) {
			c[i]=a[i]*b[i];
			d += c[i];
	}
	return d;
	}
	
	public static int[] crossProduct(int[] a,int[] b) {
		int[] c=new int[a.length];
		for (int i=0;i<a.length;i++) {
			c[i]=a[i]*b[i];
			}
	return c;
	}
	}
