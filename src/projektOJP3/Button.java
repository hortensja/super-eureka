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
	protected final Shape mShape;

	protected boolean mIsValid = true;

	protected CollidableShape mCollidableShape;
	protected boolean mIsClicked = false;
	protected boolean mIsMouseOver = false;
	protected Text mName;


	protected Color mDefaultColor = Color.WHITE;
	protected Color mDefaultTextColor = Color.BLACK;
	protected Color mClickedColor = Color.BLUE;
	protected Color mOnMouseOverColor = new Color(200, 255, 255);


	public Button(Vector2f size, Vector2f pos, Text name) {
		mSize = size;
		mShape = new RectangleShape(mSize);
		mShape.setPosition(pos);
		mName = name;
		setNamePosition(pos);
		mName.setColor(mDefaultTextColor);
		mCollidableShape = new CollidableShape(mShape);
	}


	public void setValidity(boolean isValid){
		mIsValid = isValid;
	}

	protected void setNamePosition(Vector2f pos) {
		mName.setPosition(add(pos, new Vector2f(5, (mSize.y-mName.getCharacterSize()-10)/2)));
	}

	protected void setSize(Vector2f mSize) {
		this.mSize = mSize;
	}

	public Vector2f getSize(){
		return mSize;
	}
	protected Text getName() {
		return mName;
	}

	protected void setName(Text mName) {
		this.mName = mName;
	}
	protected Shape getShape() {
		return mShape;
	}


	protected Event processEvent(Event e) {

		if (mIsValid){
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
				if(mShape.getFillColor() == mOnMouseOverColor) {
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
		}
		return e;
	}


	protected void setPosition(Vector2f pos) {
		mShape.setPosition(pos);
		mCollidableShape = new CollidableShape(mShape);
	}
	protected void onClicked() {

		mShape.setFillColor(mClickedColor);
		mName.setColor(mDefaultColor);
	}
	protected void onUnclicked() {

		mShape.setFillColor(mDefaultColor);
		mName.setColor(mDefaultTextColor);
	}
	protected void onMouseOver() {
		if(!mIsClicked) {
			mShape.setFillColor(mOnMouseOverColor);
		}
	}
	protected void onMouseOut() {
		if(!mIsClicked) {
			mShape.setFillColor(mDefaultColor);
		}
	}

	public void draw(RenderWindow w) {
		w.draw(mShape);
		w.draw(mName);
	}

}
