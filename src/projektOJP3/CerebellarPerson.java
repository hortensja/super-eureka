package projektOJP3;

import org.jsfml.system.Vector2f;

public class CerebellarPerson extends Person {

	public CerebellarPerson(double x, double y, Options o) {
		super(x, y, o);
	}

	@Override
	public void process(double timestep){
		Vector2f randomFail = new Vector2f(10*(float) (mRandom.nextFloat()*timestep-timestep/2), 10*(float) (mRandom.nextFloat()*timestep-timestep/2));
		setPos(Vector2f.add(getPos(), randomFail));
		
		super.process(timestep);
		updateVisionRotation();
	}
}
