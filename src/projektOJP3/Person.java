/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import static org.jsfml.system.Vector2f.mul;
import static org.jsfml.system.Vector2f.sub;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class Person extends Object {

	private double mRadiusOfVision = 60;
	private double mAngleOfVision = 140*Math.PI/180;
	private double mLeftVisionAngle = 70*Math.PI/180;
	//K¥T ZERO JEST POZIOMO
	
	private Shape mVision;
	private org.jsfml.graphics.Color mVisionColor = new org.jsfml.graphics.Color(180,180,180);
	
	public Person(double x, double y, Options o) {
		super(x, y, new CircleShape(15));
		this.getShape().setOrigin(15,15);
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(mRandom.nextInt(256),mRandom.nextInt(256),mRandom.nextInt(256));
		this.getShape().setFillColor(color);
		Shape vision = ShapeGenerator.generatePie(mRadiusOfVision, mAngleOfVision);
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
	
	protected void turn(Vector2f normal){
		//super.bounce(normal);

		double kappa = 0.1*MathUtil.dot(getdV(), normal);
		double originaldVLength = getdVMod();
		Vector2f newdV = sub(getdV(), mul(normal, (float) kappa));
		double newdVLength = MathUtil.vectorLength(newdV);
		setdV(mul(newdV, (float) (originaldVLength/newdVLength)));	
		//setVel(2*getVel());
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
	
//	@Override
//	public Shape getShape() {
//		return mVision;
//	}
}
