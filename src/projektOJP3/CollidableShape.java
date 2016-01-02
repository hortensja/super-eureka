package projektOJP3;

import java.util.ArrayList;
import java.util.Arrays;

import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;
import static org.jsfml.system.Vector2f.*;

public class CollidableShape {

	
	private final Vector2f[] mPoints;
	private final Vector2f[] mNormals;
	private Vector2f mShapePosition;
	
	private double vectorLength(Vector2f v){
		return Math.sqrt(v.x*v.x+v.y*v.y);
	}
	
	public CollidableShape(Shape shape) {
		
		mPoints = shape.getPoints();
		mNormals = new Vector2f[shape.getPointCount()];
		for(int i=0;i<shape.getPointCount();i++){
			Vector2f vector = sub(mPoints[i], mPoints[(i+1)%shape.getPointCount()]);
			mNormals[i] = new Vector2f(-vector.y, vector.x);
			mNormals[i] = div(mNormals[i], (float)vectorLength(mNormals[i]));
		}
		
		mShapePosition = shape.getPosition();
	}
	
	public void setShapePosition(Vector2f v){
		mShapePosition = v;
	}
	
	public static double dot(Vector2f v1, Vector2f v2){
		return v1.x*v2.x+v1.y*v2.y;
	}
	
	protected boolean areColliding(Vector2f v){

		double oneDot;
		for (int i=0;i<mNormals.length;i++){

			oneDot = dot(mNormals[i], add(mPoints[i], mShapePosition));
			if (dot(mNormals[i],v)>oneDot)
				return false;
		}
		
		return true;	
	}
	
	protected double maximalDotProduct(Vector2f normal, Vector2f originPoint, CollidableShape cs){
		double maximum = Double.NEGATIVE_INFINITY;
		double currentDot = 0.0;
		double oneDot = dot(normal, originPoint);
		for(int i=0; i<cs.mPoints.length;i++){
			currentDot = -dot(normal, add(cs.mPoints[i], cs.mShapePosition)) + oneDot; 
			if (currentDot>maximum){
				maximum = currentDot;
			}
		}
		return maximum;
	}
	
	protected Vector2f minimalDistanceNormal(CollidableShape cs){
		int minimumNormalPosition = 0;
		double minimalDistance = Double.MAX_VALUE;
		double maximalCurrentDotProduct = 0.0;
		for(int i=0;i<mNormals.length;i++){
			maximalCurrentDotProduct = maximalDotProduct(mNormals[i], add(mShapePosition,mPoints[i]), cs);
			if (maximalCurrentDotProduct<minimalDistance){
				minimalDistance = maximalCurrentDotProduct;
				minimumNormalPosition = i;
			}
		}
		return mNormals[minimumNormalPosition];
	}
	
	public boolean areColliding(CollidableShape cs2){
		
		for (int i=0;i<cs2.mPoints.length;i++){
			
			if (areColliding(add(cs2.mPoints[i],cs2.mShapePosition)))
				return true;
		}
		
		return false;
	}

}
