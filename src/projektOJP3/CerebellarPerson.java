package projektOJP3;

import org.jsfml.system.Vector2f;

public class CerebellarPerson extends Person {

	public CerebellarPerson(double x, double y, Options o) {
		super(x, y, o);
	}

	@Override
	public void process(double timestep){
		Vector2f randomFail = new Vector2f(5*(float) (mRandom.nextFloat()*timestep-timestep/2), 5*(float) (mRandom.nextFloat()*timestep-timestep/2));
		setPos(Vector2f.add(getPos(), randomFail));
		
		double newVel = getVel()+(mRandom.nextDouble()-0.5)*0.15;

		if (newVel>0 && newVel<1.1){
			setVel(newVel);
		}		
		super.process(timestep);
		updateVisionRotation();
	}
}
