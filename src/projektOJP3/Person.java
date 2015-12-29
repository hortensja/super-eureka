package projektOJP3;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;

public class Person extends Object {

	//private Shape mPersonShape = new CircleShape(15);
	private double mRadiusOfVision = 100;
	private double mAngleOfVision = 140*Math.PI/180;
	
	private Shape mVision;
	private org.jsfml.graphics.Color mVisionColor = new org.jsfml.graphics.Color(120,120,120);
	
	public Person(double x, double y) {
		super(x, y, new CircleShape(15));
		org.jsfml.graphics.Color color = new org.jsfml.graphics.Color(mRandom.nextInt(256),mRandom.nextInt(256),mRandom.nextInt(256));
		this.getShape().setFillColor(color);
		Shape vision = ShapeGenerator.generatePie(mRadiusOfVision, Math.atan2(getdY(), getdX())+mAngleOfVision/2, Math.atan2(getdY(), getdX())-mAngleOfVision/2);
		//color = new org.jsfml.graphics.Color(mRandom.nextInt(256),mRandom.nextInt(256),mRandom.nextInt(256));
		vision.setFillColor(mVisionColor);
		vision.setOrigin(getShape().getOrigin().x-30, getShape().getOrigin().y-30);
		vision.setPosition((float)getX(), (float)getY());
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
		//mVision.setOrigin(getShape().getOrigin().x-15, getShape().getOrigin().y-15);
		//mVision.setPosition((float)getX(), (float)getY());
		// 0 jest pionowo w dó³
		double theta = Math.atan2(getdY(), getdX());
		Shape vision = ShapeGenerator.generatePie(mRadiusOfVision,theta-mAngleOfVision/2, theta+mAngleOfVision/2);// Math.atan2(getdY(), getdX())-mAngleOfVision/2, Math.atan2(getdY(), getdX())+mAngleOfVision/2);
		vision.setFillColor(mVisionColor);
		vision.setOrigin(getShape().getOrigin().x-15, getShape().getOrigin().y-15);
		vision.setPosition((float)getX(), (float)getY());
		this.mVision = vision;
	}


	@Override
	public void draw(RenderWindow window) {
		window.draw(getShape());
		window.draw(mVision);
		
	}
	
	
}
