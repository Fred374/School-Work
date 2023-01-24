// Frederick Kelemen

// Dot Product Calculation.

public class DotProduct {
	
	public static int dotProduct(int[] a,int[] b) {
		int[] c=new int[a.length];
		int d=0;
		for (int i=0;i<a.length;i++) {
			c[i]=a[i]*b[i];
			d += c[i];
	}
	return d;
	}
}
