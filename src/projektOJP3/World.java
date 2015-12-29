package projektOJP3;

import java.util.ArrayList;

import org.jsfml.graphics.RenderWindow;

public class World implements Drawable, Processable{

	
	private static float midX = 540;
	private static float midY = 360;
	
	
	
	
	public void onResize(RenderWindow window){
		setMidX((window.getSize().x)/2);
		setMidY((window.getSize().y)/2);
		System.out.println(window.getSize());
		System.out.println(getMidX()+" "+getMidY());
	}
	


	public static float getMidX() {
		return midX;
	}

	public static void setMidX(float midX) {
		World.midX = midX;
	}

	public static float getMidY() {
		return midY;
	}

	public static void setMidY(float midY) {
		World.midY = midY;
	}

	private final ArrayList<Object> mObjects = new ArrayList<>() ;
	
	
	@Override
	public void draw(RenderWindow window) {
		for(Object object : mObjects) { 
			object.draw(window);
		}
	}

	@Override
	public void process(double timestep) {
		int i = 1;
		for(Object object : mObjects) {
			object.process(timestep);

			for(int j=i; j<mObjects.size();j++){
				Object object2 = mObjects.get(j);
				if (Collision.areCollidingCircleTest(object, object2)){
					object.bounce();
					object2.bounce();
				}
			}
			i++;
		}
		
	}
	public void addObject(Object object)
	{
		mObjects.add(object);
	}
}
