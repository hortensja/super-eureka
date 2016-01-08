package projektOJP3;

import static org.jsfml.system.Vector2f.add;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class BooleanButton extends Button{
	
	private boolean mStatus = false;
	
	public BooleanButton(Vector2f size, Vector2f pos, Text name) {
		super(size, pos, name);
	}

	public boolean getStatus() {
		return mStatus;
	}
	
	@Override
	protected void onClicked() {
		mStatus = !mStatus;
		super.onClicked();
	}

	@Override
	protected void onUnclicked() {
		mStatus = !mStatus;
		super.onUnclicked();
	}
}
