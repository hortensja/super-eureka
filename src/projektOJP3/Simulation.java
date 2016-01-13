/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;


public class Simulation {

	/**arbitrary units notice
	 * (i do not know how comments work)
	 * 
	 * 15 is person's size so it will be circa 40 cm (30 for nice calculations)
	 * walls' size vary between 10 and 160 which gives 20 cm to 3.2 m so it can be anything from colonne morris to shed or so
	 * trees' diameter is between 20 and 100 which makes 40 to 200 cm (pretty accurate)
	 * healthy human vision extends to 60 which is 1.2 m which is so very wrong
	 * 	or perhaps not if we interpret is as acute vision
	 *  
	 *  
	 *  cars are way too small in order to save space
	 */

	public static void main(String[] args) {

		RenderWindow window = new RenderWindow();
		OptionWindow windowWithOptions = new OptionWindow();
		ContextSettings settings = new ContextSettings(8);
		
		window.create(new VideoMode(1080,720), "Neurological disorders", org.jsfml.window.WindowStyle.DEFAULT ,settings);
		//window.create(VideoMode.getFullscreenModes()[0], "yoloyolo", RenderWindow.FULLSCREEN);
	
		boolean paused = false;	
		Options options = Options.DEFAULT_OPTIONS;	
		World world = new World(window, options);
		double timestep = 0.5;
		
		
		
		while(window.isOpen())
		{
			if (!windowWithOptions.process()) {
				window.close();
			}
			
			window.clear(Color.WHITE);
			world.draw(window);
			
			windowWithOptions.clear(Color.WHITE);
			windowWithOptions.display();
			
			if(!paused){
				world.process(timestep);
			}
			window.display();
			
		    for(Event event : window.pollEvents()) {
		        if(event.type == Event.Type.CLOSED) {
		            window.close();
		        }
		        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED){
		        		event = world.processEvent(event);
		        		if(event==null) continue;
		        	if(event.asMouseButtonEvent().button == Mouse.Button.LEFT){
		        		world.addPerson(PersonGenerator.generatePerson((double)event.asMouseButtonEvent().position.x, (double) event.asMouseButtonEvent().position.y, windowWithOptions.getOptions()));
		        	}
		        	/*if(event.asMouseButtonEvent().button == Mouse.Button.RIGHT){
		        		event = world.processEvent(event);
		        		if(event==null) continue;
		        		world.addObject(new ImmovableObject((double)event.asMouseButtonEvent().position.x, (double) event.asMouseButtonEvent().position.y, ShapeGenerator.generateShape()));
		        	}*/
		        }
		        
		        if(event.type == Event.Type.RESIZED){
		        	world.onResize(window);
		        }
		        if(event.type == Event.Type.KEY_PRESSED){
		        	if(event.asKeyEvent().key == Keyboard.Key.P){
		        		paused = !paused;
		        	}
		        	if(event.asKeyEvent().key == Keyboard.Key.UP){
		        		timestep += 0.05;
		        	}
		        	if(event.asKeyEvent().key == Keyboard.Key.DOWN){
		        		timestep -= 0.05;
		        	}
		        	if(event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
		        		window.close();
		        	}
		        }
		    }
		}
	}

}
