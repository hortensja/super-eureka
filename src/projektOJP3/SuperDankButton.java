package projektOJP3;

import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class SuperDankButton extends DankButton {
	public SuperDankButton(Vector2f size, Vector2f pos, Text text) {
		super(size, pos, text);
	}
	
	@Override
	protected void onMouseOver() {
		onClicked();
	}
}
