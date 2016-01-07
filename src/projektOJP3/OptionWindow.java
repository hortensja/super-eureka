package projektOJP3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class OptionWindow implements Window{

	private final ArrayList<Button> mButtons = new ArrayList<>();
	
	private final RenderWindow mWindow = new RenderWindow();

	private org.jsfml.graphics.Font mFont = new Font();
	private static Vector2f mStandardSize = new Vector2f(150, 50);
	
	
	private BooleanButton mMyopia;
	private BooleanButton mHyperopia;
	private BooleanButton mCerebellum;
	
	public OptionWindow(){
		mWindow.create(new VideoMode(200, 720), "Options");
		mWindow.setPosition(new Vector2i(50, 50));

		loadFont();
		
		Vector2f pos = new Vector2f(10,10);
		
		mMyopia = new BooleanButton(mStandardSize, pos, new Text("Myopia", mFont));
		mHyperopia = new BooleanButton(mStandardSize, pos = getNextButtonPosition(mMyopia.getSize(), pos), new Text("Hyperopia", mFont));
		mCerebellum = new BooleanButton(mStandardSize, pos = getNextButtonPosition(mHyperopia.getSize(), pos), new Text("Cerebellum", mFont));

		
		addButton(mMyopia);
		addButton(mHyperopia);
		addButton(mCerebellum);
	}
	

	private void loadFont(){
		try {
		    mFont.loadFromFile(Paths.get("resources\\bell.ttf"));
		} catch(IOException ex) {
		    //Failed to load font
		    ex.printStackTrace();
		}
	}
		
	
	public void process(){
	    for(Event event : mWindow.pollEvents()) {
	        if(event.type == Event.Type.CLOSED) {
	          close();
	        }
	        
	        for(Button button : mButtons){
	        	button.processEvent(event);
	        }
	    }
	}
	
	public void addButton(Button button){
		mButtons.add(button);
	}
	
	public void clear(org.jsfml.graphics.Color color){
		mWindow.clear(color);
	}
	
	public void display(){

        for(Button button : mButtons){
        	button.draw(mWindow);
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
		return new Options(mMyopia.getStatus(), mHyperopia.getStatus());
	}
	
	
	
	private Vector2f getNextButtonPosition(Vector2f size, Vector2f pos){
		pos = Vector2f.add(pos, new Vector2f(0,size.y+10));
		return pos;
	}
	
	
}
