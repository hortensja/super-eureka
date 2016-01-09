package projektOJP3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class OptionWindow implements Window{
	
	private final RenderWindow mWindow = new RenderWindow();
	
	public final static int WINDOW_WIDTH = 200;
	public final static int WINDOW_HEIGHT = 720;
	
	public final static Vector2f STANDARD_SIZE = new Vector2f((float) (WINDOW_WIDTH*0.75), (float) (WINDOW_WIDTH*0.25));
	public final static Vector2f HALF_SIZE = new Vector2f((float) (WINDOW_WIDTH*0.375), (float) (WINDOW_WIDTH*0.25));
	public final static Vector2f SLIDE_BUTTON_SIZE = new Vector2f((float) (WINDOW_WIDTH*0.12), (float) (WINDOW_WIDTH*0.1));

	public final static int SLIDE_TEXT_SIZE = 15;
	public final static int HALF_TEXT_SIZE = 15;
	
	public final static Font mFont = new Font();
	
	private Map<Condition, Button> mButtons = new HashMap<>();
	
	
	public OptionWindow(){
		mWindow.create(new VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT), "Options");
		mWindow.setPosition(new Vector2i(50, 50));

		loadFont();
		
		Vector2f pos = new Vector2f((float)(WINDOW_WIDTH-STANDARD_SIZE.x)/2, (float)(WINDOW_WIDTH-STANDARD_SIZE.x)/2);
		
		BooleanButton myopia = new BooleanButton(STANDARD_SIZE, pos, new Text("Myopia", mFont));
		SlidingButton myopiaDegree = new SlidingButton(SLIDE_BUTTON_SIZE, pos = getNextButtonPosition(myopia.getSize(), pos), -Options.MIN_DEGREE, -Options.MAX_DEGREE);
		
		BooleanButton hyperopia = new BooleanButton(STANDARD_SIZE, pos = getNextButtonPosition(myopiaDegree.getSize(), pos), new Text("Hyperopia", mFont));
		SlidingButton hyperopiaDegree = new SlidingButton(SLIDE_BUTTON_SIZE, pos = getNextButtonPosition(hyperopia.getSize(), pos), Options.MIN_DEGREE, Options.MAX_DEGREE);
		
		BooleanButton cerebellum = new BooleanButton(STANDARD_SIZE, pos = getNextButtonPosition(hyperopiaDegree.getSize(), pos), new Text("Cerebellum", mFont));
		BooleanButton leftEye = new BooleanButton(HALF_SIZE, pos = getNextButtonPosition(cerebellum.getSize(), pos), new Text("Left eye\n disabled", mFont, HALF_TEXT_SIZE));
		BooleanButton rightEye = new BooleanButton(HALF_SIZE, Vector2f.add(pos, new Vector2f(75, 0)), new Text("Right eye\n disabled", mFont, HALF_TEXT_SIZE));

		
		mButtons.put(new Condition("Myopia"), myopia);
		mButtons.put(new Condition("Myopia degree"), myopiaDegree);
		mButtons.put(new Condition("Hyperopia"), hyperopia);
		mButtons.put(new Condition("Hyperopia degree"), hyperopiaDegree);
		mButtons.put(new Condition("Cerebellum"), cerebellum);
		mButtons.put(new Condition("Left eye disabled"), leftEye);
		mButtons.put(new Condition("Right eye disabled"), rightEye);
		
	}
	

	private void loadFont(){
		try {
		    mFont.loadFromFile(Paths.get("resources\\bell.ttf"));
		} catch(IOException ex) {
		    //Failed to load font
		    ex.printStackTrace();
		}
	}
		
	
	public boolean process(){
		getOptions();
	    for(Event event : mWindow.pollEvents()) {
	        if(event.type == Event.Type.CLOSED) {
	          close();
	          return false;
	        }
	        
	        for(Entry<Condition, Button> button : mButtons.entrySet()){
	        	button.getValue().processEvent(event);
	        }
	    }
	    return true;
	}

	public void clear(org.jsfml.graphics.Color color){
		mWindow.clear(color);
	}
	
	public void display(){

        for(Entry<Condition, Button> button : mButtons.entrySet()){
        	button.getValue().draw(mWindow);
        }
		mWindow.display();
	}
	
	public RenderWindow getRenderWindow(){
		return mWindow;
	}
	
	@Override
	public void close() {
		mWindow.close();
	}

	public Options getOptions() {
		try {
			return new Options(mButtons);
		} catch (OptionsException e) {
			e.printStackTrace();
			return Options.DEFAULT_OPTIONS;
		}
	}
	
	
	
	private Vector2f getNextButtonPosition(Vector2f size, Vector2f pos){
		pos = Vector2f.add(pos, new Vector2f(0,size.y+10));
		return pos;
	}
	
	
}
