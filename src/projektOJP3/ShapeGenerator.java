package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.ConvexShape;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class ShapeGenerator {

private static Random rand = new Random(System.nanoTime());
	
	private ShapeGenerator() {
		
	}
	
	public static Shape generateShape(){
		Shape shape = new CircleShape((int)( rand.nextDouble()*100+1),(int) (rand.nextDouble()*10+3));
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
		//color.
		shape.setFillColor(color);
		return shape;
	}
	
	public static Shape generatePie(double r, double angle1, double angle2){
		
		ConvexShape cshape = new ConvexShape();
		double angle = angle1;
		cshape.setPointCount((int)(Math.abs(angle2-angle1)/(Math.PI/24)+1));
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
				
		cshape.setPoint(0, org.jsfml.system.Vector2f.ZERO);
		
		for (int i=1;i<cshape.getPointCount();i++){
			cshape.setPoint(i, new Vector2f((float)(r*Math.cos(angle)),(float)(r*Math.sin(angle))));
			angle+=(angle2-angle1)/(cshape.getPointCount()-2);
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
		Shape shape = new RectangleShape(new Vector2f(rand.nextFloat()*100+10, rand.nextFloat()*100+10));

		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(255,0,0);
		shape.setFillColor(color);
		return shape;
	}
	
}
