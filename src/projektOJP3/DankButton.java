package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class DankButton extends Button {
	private static Random mDankRandom = new Random();
	
	
	public DankButton(Vector2f size, Vector2f pos, Text name) {
		super(size, pos, name);
	}
	
	@Override
	protected void onClicked() {
		super.setPosition(new Vector2f(mDankRandom.nextInt(100),mDankRandom.nextInt(100)));
	}
}
