package projektOJP3;

public class Collision {

	private Collision(){}
	
	public static boolean areCollidingCircleTest(Object o1, Object o2){
		
		double r1 = o1.getRadius();
		double r2 = o2.getRadius();
		
		double xDist = o1.getPosition().x-o2.getPosition().x;
		double yDist = o1.getPosition().y-o2.getPosition().y;
		
		return (Math.sqrt(xDist*xDist+yDist*yDist)<=r1+r2);
	}
	
	
}
