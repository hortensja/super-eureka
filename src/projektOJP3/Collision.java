/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import org.jsfml.system.Vector2f;

public class Collision {

	private Collision(){}
	
	public static boolean areCollidingCircleTest(Object o1, Object o2){
		
		double r1 = o1.getRadius();
		double r2 = o2.getRadius();
		
		double xDist = o1.getPosition().x-o2.getPosition().x;
		double yDist = o1.getPosition().y-o2.getPosition().y;
		
		return (Math.sqrt(xDist*xDist+yDist*yDist)<=r1+r2);
	}
	
	public static boolean areCollidingBetterTest(Object o1, Object o2){

		CollidableShape cs1 = o1.getCollidableShape();
		CollidableShape cs2 = o2.getCollidableShape();
		
		return cs1.areColliding(cs2) || cs2.areColliding(cs1);
		
	}
	
	public static void collide(Object object, Object object2){
		
		Vector2f normal1 = object.getCollidableShape().minimalDistanceNormal(object2.getCollidableShape());
		Vector2f normal2 = object2.getCollidableShape().minimalDistanceNormal(object.getCollidableShape());
	
		if(MathUtil.dot(object.getdV(), normal2)<0){
			object.bounce(normal2);
			//System.out.println("baunsuje o: " + normal2);
			object2.bounce(normal2);
		}
		if(MathUtil.dot(object2.getdV(), normal1)<0){
			object.bounce(normal1);
			object2.bounce(normal1);
			//System.out.println("baunsuje o: " + normal1);
		}
	}
	
}
