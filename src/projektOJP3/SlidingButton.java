package projektOJP3;

import static org.jsfml.system.Vector2f.add;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

public class SlidingButton extends Button {


	protected Color mDefaultColor = Color.CYAN;
	
	private Shape mSlide;
	private int mValue;
	private final int mMinValue;
	private final int mMaxValue;

	private final Vector2f mPosition;

	public SlidingButton(Vector2f size, Vector2f pos, int minValue, int maxValue) {
		super(size, pos, new Text(Integer.toString(minValue), OptionWindow.mFont, OptionWindow.mSlideTextSize));
		mShape.setFillColor(mDefaultColor);
		setNamePosition(pos);
		mIsValid = false;
		mSlide = new RectangleShape(new Vector2f(OptionWindow.mStandardSize.x, 2));
		mSlide.setFillColor(mDefaultTextColor);
		mSlide.setPosition(Vector2f.add(pos,new Vector2f(0, OptionWindow.mSlideButtonSize.y/2)));
		mPosition = pos;
		mMinValue = minValue;
		mMaxValue = maxValue;
	}

	
	@Override
	protected void onClicked() {

		mShape.setFillColor(mClickedColor);
		getName().setColor(mDefaultColor);
	}

	@Override
	protected void onUnclicked() {

		mShape.setFillColor(mDefaultColor);
		getName().setColor(mDefaultTextColor);
	}


	@Override
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
		if(e.type == Event.Type.MOUSE_BUTTON_RELEASED) {
			if(mIsClicked) {
				mIsClicked = false;
				onUnclicked();
			}
			return e;
		}

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
				if(mIsClicked) {
					move(clickf);
				}
			}
		}
		return e;
	}

	@Override
	protected void onMouseOver(){

		super.onMouseOver();
		
	}
	@Override
	protected void onMouseOut(){
		//if(!mIsClicked) {
			mShape.setFillColor(mDefaultColor);
			mIsClicked = false;
		//}
	}

	@Override
	public void draw(RenderWindow w) {
		if (mIsValid){
			w.draw(mSlide);
			w.draw(getShape());
			w.draw(getName());
		}
	}


	private void move(Vector2f pos){
		
		//System.out.println((pos.x > mPosition.x) + " " + (pos.x < Vector2f.add(mPosition, mSlide.getPoint(1)).x-OptionWindow.mSlideButtonSize.x/2) + " pos: " + pos.x);
		//System.out.println(pos.x);
		//System.out.println(pos.x);
		
		if(pos.x > mPosition.x && pos.x < Vector2f.add(mPosition, mSlide.getPoint(1)).x-OptionWindow.mSlideButtonSize.x/2){
			
			Vector2f newPos = new Vector2f(pos.x, mPosition.y);
			
			mShape.setPosition(newPos);
			setName(new Text(Integer.toString(getValue()), OptionWindow.mFont, OptionWindow.mSlideTextSize));
			setNamePosition(newPos);
			
			mCollidableShape = new CollidableShape(mShape);
		}
		//System.out.println("value: " + getValue());
	}
	
	public int getValue(){
		float relativePos = mShape.getPosition().x-mPosition.x;
		return mMinValue + (int)((mMaxValue-mMinValue+1)*relativePos/mSlide.getPoint(1).x);
	}
}
