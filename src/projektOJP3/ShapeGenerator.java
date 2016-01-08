/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.ConvexShape;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class ShapeGenerator {

private static Random rand = new Random(System.nanoTime());
	
	private ShapeGenerator() {}
	
	public static Shape generateShape(){
		Shape shape = new CircleShape((float) rand.nextDouble()*50+10, rand.nextInt(9)+3);
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
		shape.setFillColor(color);
		return shape;
	}
	
	
	public static Shape generatePie(double r, double angle){
		
		ConvexShape cshape = new ConvexShape();
		double temp = 0;
		//cshape.setPointCount(5);
		cshape.setPointCount((int)(Math.abs(angle)/(Math.PI/24)+1));
		//cshape.setPointCount(3);
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
				
		cshape.setPoint(0, org.jsfml.system.Vector2f.ZERO);
		
		for (int i=1;i<cshape.getPointCount();i++){
			cshape.setPoint(i, new Vector2f((float)(r*Math.cos(temp)),(float)(r*Math.sin(temp))));
			temp+=(angle)/(cshape.getPointCount()-2);
		}
		
		cshape.setFillColor(color);
				
		return cshape;
	}
	

	public static Shape generateTree(double r){
		
		Shape shape = new CircleShape((float)r,6);
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(0,255,0);
		shape.setFillColor(color);
		return shape;
	}
	
	public static Shape generateWall(){
		Shape shape = new RectangleShape(new Vector2f(rand.nextFloat()*150+10, rand.nextFloat()*150+10));

		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(255,0,0);
		shape.setFillColor(color);
		return shape;
	}
	

	public static Shape generateRect(float x, float y){
		Shape shape = new RectangleShape(new Vector2f(x,y));

		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(255,0,255);
		shape.setFillColor(color);
		return shape;
	}
	
}
