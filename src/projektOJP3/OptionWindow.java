package projektOJP3;

import java.util.ArrayList;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class OptionWindow implements Window{

	private final ArrayList<Button> mButtons = new ArrayList<>();
	
	private final RenderWindow mWindow = new RenderWindow();
	
	public OptionWindow(){
		mWindow.create(new VideoMode(200, 720), "Options");
		mWindow.setPosition(new Vector2i(50, 50));
		ButtonGenerator.generateAndAddAllButtons(this);
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

	
}
