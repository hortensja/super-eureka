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
	
	public CollidableShape(Shape shape) {
		
		mPoints = shape.getPoints();
	//to iê nie aktualizuje
		mNormals = new Vector2f[shape.getPointCount()];
		for(int i=0;i<shape.getPointCount();i++){
			Vector2f vector = sub(mPoints[i], mPoints[(i+1)%shape.getPointCount()]);
			mNormals[i] = new Vector2f(-vector.y, vector.x);
		}
		
		mShapePosition = shape.getPosition();
	}
	
	public void setShapePosition(Vector2f v){
		mShapePosition = v;
	}
	
	protected double dot(Vector2f v1, Vector2f v2){
		return v1.x*v2.x+v1.y*v2.y;
	}
	
	protected boolean areColliding(Vector2f v){

	//		System.out.println("shajp position: "+ mShapePosition);
		double oneDot;
		for (int i=0;i<mNormals.length;i++){
//			System.out.println("pkt: " + mPoints[i]);

		//	System.out.println("normala: " + mNormals[i]);
			oneDot = dot(mNormals[i], add( mPoints[i], mShapePosition));
			if (dot(mNormals[i],v)>oneDot)
				return false;
		}
		
		return true;
				
	}
	
	public boolean areColliding(CollidableShape cs2){
		
		for (int i=0;i<cs2.mPoints.length;i++){
			
			if (areColliding(add(cs2.mPoints[i],cs2.mShapePosition)))
				return true;
		}
		
		return false;
	}

}
