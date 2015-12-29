package projektOJP3;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
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
		
		World world = new World();
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
			world.draw(window);
			world.process(0.5);
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
		    }
		}
	}

}
