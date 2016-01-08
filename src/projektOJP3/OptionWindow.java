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

	private org.jsfml.graphics.Font mFont = new Font();
	private static Vector2f mStandardSize = new Vector2f(150, 50);
	
	private Map<Disorder, Button> mButtons = new HashMap<>();
	
	
	public OptionWindow(){
		mWindow.create(new VideoMode(200, 720), "Options");
		mWindow.setPosition(new Vector2i(50, 50));

		loadFont();
		
		Vector2f pos = new Vector2f(10,10);
		
		BooleanButton myopia = new BooleanButton(mStandardSize, pos, new Text("Myopia", mFont));
		BooleanButton hyperopia = new BooleanButton(mStandardSize, pos = getNextButtonPosition(myopia.getSize(), pos), new Text("Hyperopia", mFont));
		BooleanButton cerebellum = new BooleanButton(mStandardSize, pos = getNextButtonPosition(hyperopia.getSize(), pos), new Text("Cerebellum", mFont));

		
		mButtons.put(new Disorder("Myopia"), myopia);
		mButtons.put(new Disorder("Hyperopia"), hyperopia);
		mButtons.put(new Disorder("Cerebellum"), cerebellum);
		
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
	        
	        for(Entry<Disorder, Button> button : mButtons.entrySet()){
	        	button.getValue().processEvent(event);
	        }
	    }
	}

	public void clear(org.jsfml.graphics.Color color){
		mWindow.clear(color);
	}
	
	public void display(){

        for(Entry<Disorder, Button> button : mButtons.entrySet()){
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
