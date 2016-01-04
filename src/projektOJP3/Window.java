package projektOJP3;

import org.jsfml.graphics.RenderWindow;

public interface Window {

	void close();
	void clear(org.jsfml.graphics.Color color);
	void display();
	void process();
	
	RenderWindow getRenderWindow();
	
}
