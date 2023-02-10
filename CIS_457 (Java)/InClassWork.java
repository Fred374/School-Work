// Frederick Kelemen

// IN CLASS WORK

public class InClassWork {

	public static void main(String[] args) {
		double distance = 100;
		int[] lowestDistance = new int[2];
		double[][] list = {{-1,3},{-1,-1},{1,1},{2,-1},{2,.5},{3,3},{4,2},{4,-.5}};
		for (int i=0; i<list.length; i++) {
			for (int j=0; j<list.length; j++) {
				if (i!=j&&Math.sqrt(Math.pow((list[i][0]-list[j][0]),2)+Math.pow((list[i][1]-list[j][1]),2))<distance) {
					distance = Math.sqrt(Math.pow((list[i][0]-list[j][0]),2)+Math.pow((list[i][1]-list[j][1]),2));
					lowestDistance[0] = i;
					lowestDistance[1] = j;
				}
			}
		}
		System.out.println("The shortest distance is between " + list[lowestDistance[0]][0]+", "+list[lowestDistance[0]][1] + " and " + list[lowestDistance[1]][0]+", "+list[lowestDistance[1]][1]);
	}
}