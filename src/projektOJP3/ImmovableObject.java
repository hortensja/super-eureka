/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class ImmovableObject extends Object {

	public ImmovableObject(double x, double y, Shape shape) {
		super(x, y, shape);
		setdV(Vector2f.ZERO);
		setVel(0);
		updateShapePosition();		
	}

	
	@Override
	public void process(double timestep) {
	
	}
}
