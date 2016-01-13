package projektOJP3;

import org.jsfml.system.Vector2f;

public class Car extends Object {

	public Car(double x, double y) {
		super(x, y, ShapeGenerator.generateCar());
		getShape().setRotation((float) ((Math.atan2(getdV().y, getdV().x))*180/Math.PI));	
		
		if (getVel() < 0.01){
			setVel(0.5);
		}
		//setCollidableShape(new CollidableShape(this.getShape()));
	}

	
	@Override
	public void bounce(Vector2f normal){
		setdV(Vector2f.neg(getdV()));
	}
	
	@Override
	public CollidableShape getCollidableShape(){
		return new CollidableShape(getShape());
	}
}
