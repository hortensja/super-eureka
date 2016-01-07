package projektOJP3;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
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
	private boolean mIsClicked = false;
	private boolean mIsMouseOver = false;
	
	public Button(Vector2f size, Vector2f pos, Text name) {
		mSize = size;
		mShape = new RectangleShape(mSize);
		mShape.setPosition(pos);
		mName = name;
		mName.setColor(Color.BLACK);
		mName.setPosition(add(pos, new Vector2f(10, (mSize.y-40)/2)));
		mCollidableShape = new CollidableShape(mShape);
	}
	

	
	
	protected Event processEvent(Event e) {

		if(e.type == Event.Type.MOUSE_BUTTON_PRESSED) {
			Vector2i click = e.asMouseButtonEvent().position;
			Vector2f clickf = new Vector2f(click.x,click.y);
			if(mCollidableShape.areColliding(clickf)) {
				if(!mIsClicked) {
				mIsClicked = true;
				onClicked();
				} else {
					mIsClicked = false;
					onUnclicked();
				}

			}
			return null;
		}
		/*if(e.type == Event.Type.MOUSE_BUTTON_RELEASED) {
			if(mIsClicked) {
				mIsClicked = false;
				onUnclicked();
			}
			return e;
		}*/

		if(e.type == Event.Type.MOUSE_MOVED) {
			Vector2i click = e.asMouseEvent().position;
			Vector2f clickf = new Vector2f(click.x,click.y);
			if(mShape.getFillColor() == Color.MAGENTA) {
				if(!mCollidableShape.areColliding(clickf)) {
					mIsMouseOver = false;
					onMouseOut();
				}
			} 
			if(mCollidableShape.areColliding(clickf)) {
				mIsMouseOver = true;
				onMouseOver();
			}
		}
		return e;
	}

	
	protected void setPosition(Vector2f pos) {
		mShape.setPosition(pos);
		mCollidableShape = new CollidableShape(mShape);
	}
	protected void onClicked() {

		mShape.setFillColor(Color.BLACK);
		mName.setColor(Color.WHITE);
	}
	protected void onUnclicked() {

		mShape.setFillColor(Color.WHITE);
		mName.setColor(Color.BLACK);
	}
	protected void onMouseOver() {
		if(!mIsClicked) {
			mShape.setFillColor(Color.MAGENTA);
		}
	}
	protected void onMouseOut() {
		if(!mIsClicked) {
		mShape.setFillColor(Color.GREEN);
		}
	}
	
	void draw(RenderWindow w) {
		w.draw(mShape);
		w.draw(mName);
	}
	
	public Vector2f getSize(){
		return mSize;
	}
}
