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
	
	public static boolean areCollidingBetterTest(CollidableShape cs1, CollidableShape cs2){
		return cs1.areColliding(cs2) || cs2.areColliding(cs1);
	}
	
	public static void collideObjectWithObject(Object object, Object object2){
		
		Vector2f normal1 = object.getCollidableShape().minimalDistanceNormal(object2.getCollidableShape());
		Vector2f normal2 = object2.getCollidableShape().minimalDistanceNormal(object.getCollidableShape());
	
		if(object instanceof Person && object2 instanceof Car) {
			object.die();
			return;
		}
		if (object instanceof Car && object2 instanceof Person) {
			object2.die();
			return;
		}
		
		if(MathUtil.dot(object.getdV(), normal2)<0){
			object.bounce(normal2);
			//System.out.println("baunsuje o: " + normal2);
		}
		if(MathUtil.dot(object2.getdV(), normal1)<0){
			object2.bounce(normal1);
			//System.out.println("baunsuje o: " + normal1);
		}
	}
public static void collideVisionWithObject(Person person, Object object2){
		
		Vector2f normal1 = person.getCollidableVision().minimalDistanceNormal(object2.getCollidableShape());
		Vector2f normal2 = object2.getCollidableShape().minimalDistanceNormal(person.getCollidableShape());
	
		double distance = MathUtil.vectorLength(Vector2f.sub(person.getShape().getPosition(), object2.getCenter()));
		
		//System.out.println("dist: " + Vector2f.sub(person.getShape().getPosition(), someCollidingPoint));
		
		//System.out.println(distance);
		
		if(object2 instanceof Car) {
			if ((MathUtil.dot(person.getdV(), object2.getdV()) < -0.25) && (MathUtil.dot(person.getdV(), object2.getdV()) >-0.75)) {
				person.setdV(Vector2f.neg(person.getdV()));
				person.turn(distance, normal2);
				person.run();
			}
		}
		
		if(MathUtil.dot(person.getdV(), normal2)<0){
			person.turn(distance, normal2);
			//person.bounce(normal2);
			//System.out.println("baunsuje o: " + normal2);
			//object2.bounce(normal2);
		}
		if(MathUtil.dot(object2.getdV(), normal1)<0){
			person.turn(distance, normal1);
			//person.bounce(normal1);
			//object2.bounce(normal1);
			//System.out.println("baunsuje o: " + normal1);
		}
	}

	
	
	
}
