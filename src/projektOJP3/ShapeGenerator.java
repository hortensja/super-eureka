/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConvexShape;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class ShapeGenerator {

private static Random rand = new Random(System.nanoTime());
	
	private ShapeGenerator() {}
	
	public static Shape generateShape(){
		Shape shape = new CircleShape((float) rand.nextDouble()*50+10, rand.nextInt(9)+3);
		Color color = new Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
		shape.setFillColor(color);
		return shape;
	}
	
	
	public static Shape generatePie(double r, double angle, double truncation){
		
		if(truncation>0){
			return generateTruncatedPie(r, angle, truncation);
		} else {
		ConvexShape cshape = new ConvexShape();
		Color color = new Color((int)(rand.nextDouble()*255),(int)(rand.nextDouble()*255), (int)(rand.nextDouble()*255));
		cshape.setFillColor(color);
		double temp = 0;
		//cshape.setPointCount(5);
		
			cshape.setPointCount((int)(Math.abs(angle)/(Math.PI/24)+1));

			cshape.setPoint(0, Vector2f.ZERO);

			for (int i=1;i<cshape.getPointCount();i++){
				cshape.setPoint(i, new Vector2f((float)(r*Math.cos(temp)),(float)(r*Math.sin(temp))));
				temp+=(angle)/(cshape.getPointCount()-2);
			}
		return cshape;
		}	
	}
	
	public static Shape generateTruncatedPie(double r, double angle, double truncation) {
	
		ConvexShape cshape = new ConvexShape();
		double temp = 0;
		//cshape.setPointCount(5);
		cshape.setPointCount((int)(Math.abs(angle)/(Math.PI/24)+2));
		
		cshape.setPoint(0, new Vector2f((float)(truncation),0));
		
		for (int i=1;i<(cshape.getPointCount()-1);i++){
			cshape.setPoint(i, new Vector2f((float)(r*Math.cos(temp)),(float)(r*Math.sin(temp))));
			temp+=(angle)/(cshape.getPointCount()-3);
		}
		temp-=(angle)/(cshape.getPointCount()-3);
		cshape.setPoint(cshape.getPointCount()-1, new Vector2f((float)(truncation*Math.cos(temp)),(float)(truncation*Math.sin(temp))));
					
		return cshape;
		
	}

	public static Shape generateTree(double r){
		
		Shape shape = new CircleShape((float)r,6);
		Color color = new Color(0,255,0);
		shape.setFillColor(color);
		return shape;
	}
	
	public static Shape generateWall(){
		Shape shape = new RectangleShape(new Vector2f(rand.nextFloat()*150+10, rand.nextFloat()*150+10));

		Color color = new Color(255,0,0);
		shape.setFillColor(color);
		return shape;
	}
	

	public static Shape generateRect(float x, float y){
		Shape shape = new RectangleShape(new Vector2f(x,y));

		Color color = new Color(255,0,255);
		shape.setFillColor(color);
		return shape;
	}
	
	public static Shape generateCar(){
		Shape shape = generateRect(100,50);
		shape.setFillColor(Color.BLUE);
		//shape.setRotation(rand.nextInt(360));
		return shape;
	}
	
}
