/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import static org.jsfml.system.Vector2f.*;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

public class Person extends Object {

	private double mRadiusOfVision = 60;
	private double mTruncationOfVision = 0;
	private double mAngleOfVision = 140*Math.PI/180;
	private double mLeftVisionAngle = 70*Math.PI/180;
	//K¥T ZERO JEST POZIOMO
	
	private Shape mVision;
	private org.jsfml.graphics.Color mVisionColor = new org.jsfml.graphics.Color(180,180,180,180);
	
	public Person(double x, double y, Options o) {
		super(x, y, new CircleShape(15));
		this.getShape().setOrigin(15,15);
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(mRandom.nextInt(256),mRandom.nextInt(256),mRandom.nextInt(256));
		this.getShape().setFillColor(color);
		
		if (o.isMyopic) {
			mRadiusOfVision *= (0.94+0.04*o.myopiaDegree);//0.75;
		}
		if (o.isHyperopic) {
			mRadiusOfVision /= (0.94-0.04*o.hyperopiaDegree);
			mTruncationOfVision += mRadiusOfVision*0.75;
		}		
		if (o.isLeftEyeDisabled && o.isRightEyeDisabled){
			mRadiusOfVision = 10;
			mVisionColor = new org.jsfml.graphics.Color(255,255,255,0);
		} else if (o.isRightEyeDisabled){
			mAngleOfVision /= 2;
			mLeftVisionAngle = mAngleOfVision;
		} else if (o.isLeftEyeDisabled) {
			mAngleOfVision /= 2;
			mLeftVisionAngle = 0;
		}
		
		
		Shape vision = ShapeGenerator.generateTruncatedPie(mRadiusOfVision, mAngleOfVision, mTruncationOfVision);

		vision.setFillColor(mVisionColor);
		vision.setPosition(getPos());
		vision.setRotation((float) ((Math.atan2(getdV().y, getdV().x)-mLeftVisionAngle)*180/Math.PI));
		this.mVision = vision;
		
	}

	

	public double getRadiusOfVision() {
		return mRadiusOfVision;
	}

	public void setRadiusOfVision(double mRadiusOfVision) {
		this.mRadiusOfVision = mRadiusOfVision;
	}

	public double getAngleOfVision() {
		return mAngleOfVision;
	}

	public void setAngleOfVision(double mAngleOfVision) {
		this.mAngleOfVision = mAngleOfVision;
	}
	
	@Override
	protected void updateShapePosition(){
		super.updateShapePosition();
		mVision.setPosition(getPos());	
	}

	@Override
	protected void bounce(Vector2f normal){
		super.bounce(normal);
		updateVisionRotation();
	}
	
	protected void turn(double distance, Vector2f normal) {
		/*Matrix2x2f m = MathUtil.getRotationMatrix((float) (Math.PI/2));
		Vector2f turnVector = MathUtil.matVecMul(m, normal);
		
		double kappa = 10000/(distance*distance);//MathUtil.dot(getdV(), turnVector);///(distance*distance);
		kappa = 0.2*MathUtil.dot(getdV(), normal);
		double originaldVLength = getdVMod();
		Vector2f newdV = sub(getdV(), mul(normal, (float) kappa));
		//Vector2f newdV = add(getdV(), mul(turnVector, (float) kappa));
		double newdVLength = MathUtil.vectorLength(newdV);
		//setdV(newdV);
		//setdV(mul(newdV, (float) (0.9*originaldVLength/newdVLength)));
*/		
		
		double kappa = 0.15*MathUtil.dot(getdV(), normal);///distance;
		double originaldVLength = getdVMod();
		Vector2f newdV = sub(getdV(), mul(normal, (float) kappa));
		double newdVLength = MathUtil.vectorLength(newdV);
		//setdV(mul(newdV, (float) (originaldVLength/newdVLength)));	
		//setVel(2*getVel());
		setdV(newdV);
		updateVisionRotation();
	}

	@Override
	public void draw(RenderWindow window) {
		window.draw(getShape());
		window.draw(mVision);
	}
	

	
	protected void updateVisionRotation(){
		mVision.setRotation((float) ((Math.atan2(getdV().y, getdV().x)-mLeftVisionAngle)*180/Math.PI));		
	}
	
	@Override
	public CollidableShape getCollidableShape(){
		return new CollidableShape(getShape());
	}
	
	public CollidableShape getCollidableVision(){
		return new CollidableShape(mVision);
	}
	
}
