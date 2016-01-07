/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import javafx.scene.input.MouseButton;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;


public class BlindManWithYoyo {

	/**arbitrary units notice
	 * (i do not know how comments work)
	 * 
	 * 15 is person's size so it will be circa 40 cm (30 for nice calculations)
	 * walls' size vary between 10 and 160 which gives 20 cm to 3.2 m so it can be anything from colonne morris to shed or so
	 * trees' diameter is between 20 and 100 which makes 40 to 200 cm (pretty accurate)
	 * healthy human vision extends to 60 which is 1.2 m which is so very wrong
	 * 	or perhaps not if we interpret is as acute vision
	 *  
	 */

	public static void main(String[] args) {

		RenderWindow window = new RenderWindow();
		OptionWindow windowWithOptions = new OptionWindow();
		
		window.create(new VideoMode(1080,720), "Neurological disorders");
		
		boolean paused = false;	
		Options options = new Options(true, false);	
		World world = new World(window, options);
		double timestep = 0.5;
		/*Shape kwadrat = new RectangleShape(new Vector2f(10,10));
		kwadrat.setPosition(1, 1);
		Shape kwadratjedn = new RectangleShape(new Vector2f(1,1));
		kwadratjedn.setPosition(9, 9.5f);
		
		CollidableShape cs = new CollidableShape(kwadrat);
		CollidableShape csjedn = new CollidableShape(kwadratjedn);
		System.out.println("noramla: "+ cs.minimalDistanceNormal(csjedn));
		Vector2f punkt = new Vector2f(0,-1);
		
		System.out.println(cs.areColliding(punkt));
		*/
		//window.close();
		
		
		
		
		while(window.isOpen())
		{
			windowWithOptions.process();
			
			window.clear(Color.WHITE);
			world.draw(window);
			
			windowWithOptions.clear(Color.WHITE);
			//world.draw(windowWithOptions.getRenderWindow());
			windowWithOptions.display();
			
			if(!paused){
				world.process(timestep);
			}
			window.display();
			
		    for(Event event : window.pollEvents()) {
		        if(event.type == Event.Type.CLOSED) {
		            //The user pressed the close button
		            window.close();
		        }
		        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED){
		        	if(event.asMouseButtonEvent().button == Mouse.Button.LEFT){
		        		event = world.processEvent(event);
		        		if(event==null) continue;
		        		world.addPerson(PersonGenerator.generatePerson((double)event.asMouseButtonEvent().position.x, (double) event.asMouseButtonEvent().position.y, windowWithOptions.getOptions()));
		        	}
		        	if(event.asMouseButtonEvent().button == Mouse.Button.RIGHT){
		        		world.addObject(new ImmovableObject((double)event.asMouseButtonEvent().position.x, (double) event.asMouseButtonEvent().position.y, ShapeGenerator.generateShape()));
		        	}
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
		        }
		    }
		}
	}

}
