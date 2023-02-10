// Frederick Kelemen
// 2715308

public class LaptopQuiz1 {
	public static void main(String[] args) {
		Point2D A=new Point2D(2,2);
		Point2D B=new Point2D(8,2);
		Point2D C=new Point2D(5,5);
		System.out.println(Tools2D.area2(A,B,C));
		if (Tools2D.area2(A,B,C)>0)
			System.out.println("Counter Clockwise");
		else
			System.out.println("Clockwise");
		
		System.out.println(Tools2D.area2(B,A,C));
		if (Tools2D.area2(B,A,C)>0)
			System.out.println("Counter Clockwise");
		else
			System.out.println("Clockwise");
		
		
		C=new Point2D(5,2);
		System.out.println(Tools2D.area2(A,B,C));
		if (Tools2D.area2(A,B,C)>0)
			System.out.println("Counter Clockwise");
		else
			System.out.println("Clockwise");
		
		System.out.println(Tools2D.area2(B,A,C));
		if (Tools2D.area2(B,A,C)>0)
			System.out.println("Counter Clockwise");
		else
			System.out.println("Clockwise");
	}
}