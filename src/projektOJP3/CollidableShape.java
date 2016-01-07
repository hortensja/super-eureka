/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

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

		mPoints = new Vector2f[shape.getPointCount()];
		mNormals = new Vector2f[shape.getPointCount()];
		
		float rotation = (float) (shape.getRotation()*Math.PI/180);
		
		Matrix2x2f rotationMatrix = MathUtil.getRotationMatrix(rotation);
		
		for(int i=0;i<shape.getPointCount();i++){
			mPoints[i] = MathUtil.matVecMul(rotationMatrix, shape.getPoint(i));

			//System.out.println("pkty: " + mPoints[i]);	
		}
		
		for(int i=0;i<shape.getPointCount();i++){
			Vector2f vector = sub(mPoints[i], mPoints[(i+1)%shape.getPointCount()]);
			mNormals[i] = new Vector2f(-vector.y, vector.x);
			mNormals[i] = MathUtil.normalizeVector(mNormals[i]);//div(mNormals[i], (float)MathUtil.vectorLength(mNormals[i]));
		}
		
		mShapePosition = sub(shape.getPosition(), shape.getOrigin());
	}
	
	protected void setShapePosition(Vector2f v){
		mShapePosition = v;
	}
	
	
	protected boolean areColliding(Vector2f v){
		double oneDot;
		for (int i=0;i<mNormals.length;i++){
			oneDot = MathUtil.dot(mNormals[i], add(mPoints[i], mShapePosition));
			if (MathUtil.dot(mNormals[i],v)>oneDot){
				return false;
			}
		}
		return true;	
	}

	public boolean areColliding(CollidableShape cs2){
		for (int i=0;i<cs2.mPoints.length;i++){
			if (areColliding(add(cs2.mPoints[i],cs2.mShapePosition))){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public Vector2f findSomeCollidingPoint(CollidableShape cs){
		double oneDot;
		for (int j=0;j<cs.mPoints.length;j++){
			for (int i=0;i<mNormals.length;i++){
				oneDot = MathUtil.dot(mNormals[i], add(mPoints[i], mShapePosition));
				if (MathUtil.dot(mNormals[i],add(cs.mPoints[j],cs.mShapePosition))>oneDot){
					return add(add(div(new Vector2f(mNormals[i].y, -mNormals[i].x), 2), mPoints[(i+1)%mPoints.length]), mShapePosition);
				}
			}
		}
		for (int j=0;j<mPoints.length;j++){
			for (int i=0;i<cs.mNormals.length;i++){
				oneDot = MathUtil.dot(cs.mNormals[i], add(cs.mPoints[i], cs.mShapePosition));
				if (MathUtil.dot(cs.mNormals[i],add(mPoints[j],mShapePosition))>oneDot){
					return add(add(div(new Vector2f(cs.mNormals[i].y, -cs.mNormals[i].x), 2), cs.mPoints[(i+1)%cs.mPoints.length]), cs.mShapePosition);					
				}
			}
		}
		return null;
	}
	
	
	
	protected double maximalDotProduct(Vector2f normal, Vector2f originPoint, CollidableShape cs){
		double maximum = Double.NEGATIVE_INFINITY;
		double currentDot = 0.0;
		double oneDot = MathUtil.dot(normal, originPoint);
		for(int i=0; i<cs.mPoints.length;i++){
			currentDot = -MathUtil.dot(normal, add(cs.mPoints[i], cs.mShapePosition)) + oneDot; 
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
	

}
