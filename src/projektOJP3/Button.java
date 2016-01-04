package projektOJP3;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import static org.jsfml.system.Vector2f.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;



public class Button {
	
	private Vector2f mSize;
	private final Shape mShape;
	private CollidableShape mCollidableShape;
	private Text mName;
	
	public Button(Vector2f size, Vector2f pos, Text text) {
		mSize = size;
		mShape = new RectangleShape(mSize);
		mShape.setPosition(pos);
		mName = text;
		mName.setColor(Color.BLACK);
		mName.setPosition(add(pos, new Vector2f(10, (mSize.y-40)/2)));
		mCollidableShape = new CollidableShape(mShape);
	}
	
	Event processEvent(Event e) {
		
		if(e.type == Event.Type.MOUSE_BUTTON_PRESSED) {
			Vector2i click = e.asMouseButtonEvent().position;
			Vector2f clickf = new Vector2f(click.x,click.y);
			if(mCollidableShape.areColliding(clickf)) {
				onClicked();
				mShape.setFillColor(Color.BLACK);
				
			}
			return null;
		}
		if(e.type == Event.Type.MOUSE_BUTTON_RELEASED) {
			if(mShape.getFillColor() == Color.BLACK) {
				onUnclicked();
				mShape.setFillColor(Color.WHITE);
			}
			return e;
		}
		
		if(e.type == Event.Type.MOUSE_MOVED) {
			Vector2i click = e.asMouseEvent().position;
			Vector2f clickf = new Vector2f(click.x,click.y);
			if(mShape.getFillColor() == Color.MAGENTA) {
				if(!mCollidableShape.areColliding(clickf)) {
					mShape.setFillColor(Color.GREEN);
					onMouseOut();
				}
			} 
			if(mShape.getFillColor() != Color.BLACK) {
				if(mCollidableShape.areColliding(clickf)) {
					mShape.setFillColor(Color.MAGENTA);
					onMouseOver();
				}
			}
			return e;
		}
		
		
		return e;
	}
	protected void setPosition(Vector2f pos) {
		mShape.setPosition(pos);
		mCollidableShape = new CollidableShape(mShape);
	}
	protected void onClicked() {
		
	}
	protected void onUnclicked() {
		
	}
	protected void onMouseOver() {
		
	}
	protected void onMouseOut() {
		
	}
	void draw(RenderWindow w) {
		w.draw(mShape);
		w.draw(mName);
	}
	
	public Vector2f getSize(){
		return mSize;
	}
}
