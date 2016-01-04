/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import org.jsfml.graphics.Shape;

public class ImmovableObject extends Object {

	public ImmovableObject(double x, double y, Shape shape) {
		super(x, y, shape);
		updateShapePosition();		
	}

	
	@Override
	public void process(double timestep) {
	
	}
}
