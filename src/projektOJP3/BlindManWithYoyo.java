package projektOJP3;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;


public class BlindManWithYoyo {

	public static Object generateObjects(double x, double y){
		Object o = new Person(x, y);//,ShapeGenerator.generateTree(50));
		return o;
	}
	

	public static void main(String[] args) {

		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(1080,720), "Neurological cos tam cos tam");
	
		System.out.println(window.getSize());
		
		
		Shape kwadrat = new RectangleShape(new Vector2f(10,10));
		kwadrat.setPosition(1, 1);
		Shape kwadratjedn = new RectangleShape(new Vector2f(1,1));
		kwadratjedn.setPosition(9, 9.5f);
		
		CollidableShape cs = new CollidableShape(kwadrat);
		CollidableShape csjedn = new CollidableShape(kwadratjedn);
		System.out.println("noramla: "+ cs.minimalDistanceNormal(csjedn));
		Vector2f punkt = new Vector2f(0,-1);
		
		System.out.println(cs.areColliding(punkt));
		
		//window.close();
		
		boolean paused = false;
		
		World world = new World();
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
			world.draw(window);
			if(!paused){
				world.process(0.5);
			}
			window.display();
			
		    for(Event event : window.pollEvents()) {
		        if(event.type == Event.Type.CLOSED) {
		            //The user pressed the close button
		            window.close();
		        }
		        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED){
		        	world.addObject(generateObjects((double)event.asMouseButtonEvent().position.x, (double) event.asMouseButtonEvent().position.y));
		        }
		        if(event.type == Event.Type.RESIZED){
		        	world.onResize(window);
		        }
		        if(event.type == Event.Type.KEY_PRESSED){
		        	if(event.asKeyEvent().key == Keyboard.Key.P){
		        		
		        		paused = !paused;
		        	}
		        }
		    }
		}
	}

}
