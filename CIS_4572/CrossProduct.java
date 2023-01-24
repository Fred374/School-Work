// Frederick Kelemen

// Cross Product Calculation.

public class CrossProduct {
	
	public static void main(String[] args) {
		int[] a = {1,1,0};
		int[] b = {2,1,0};
		int[] crossAnswer = new int[a.length];
		crossAnswer = crossProduct(a,b);
		System.out.println(crossAnswer[0]+" "+crossAnswer[1] +" "+crossAnswer[2]);
	}
	
	public static int[] crossProduct(int[] a,int[] b) {
		int[] c=new int[a.length];
		for (int i=0;i<a.length;i++) {
			if (i<a.length-2)
				c[i]=a[i+1]*b[i+2]-a[i+2]*b[i];
			else if (i < a.length-1)
				c[i]=a[i+1]*b[0]-a[0]*b[i+1];
			else
				c[i]=a[0]*b[1]-a[1]*b[0];
			}
	return c;
	}
}