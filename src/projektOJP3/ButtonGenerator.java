package projektOJP3;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class ButtonGenerator {

	private static org.jsfml.graphics.Font mFont = new Font();
	private static Vector2f mStandardSize = new Vector2f(150, 50);
	
	private ButtonGenerator(){}
	
	private static void loadFont(){
		try {
		    mFont.loadFromFile(Paths.get("resources\\bell.ttf"));
		    System.out.println("udalo sie");
		} catch(IOException ex) {
		    //Failed to load font
		    ex.printStackTrace();
		}
	}
	
	private static Button generateButton(Vector2f pos) {
		return new Button(mStandardSize, pos, new Text("pierwszy", mFont));
	}
	
	private static Vector2f getNextButtonPosition(Vector2f size, Vector2f pos){
		pos = Vector2f.add(pos, new Vector2f(0,size.y+10));
		return pos;
	}
	
	public static void generateAndAddAllButtons(OptionWindow optionWindow){
		loadFont();
		Vector2f position = new Vector2f(10,10);
		
		Button b = generateButton(position);
		optionWindow.addButton(b);
		
		position = getNextButtonPosition(b.getSize(), position);

		b = generateButton(position);
		optionWindow.addButton(b);
		

		position = getNextButtonPosition(b.getSize(), position);

		b = generateButton(position);
		optionWindow.addButton(b);
	}
}
