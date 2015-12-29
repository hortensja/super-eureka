package projektOJP3;

import java.util.ArrayList;
import java.util.Random;

import org.jsfml.graphics.RenderWindow;

public class World implements Drawable, Processable{

	
	private static int midX = 540;
	private static int midY = 360;
	
	private Random mRandom = new Random(System.nanoTime());
	private int maxNumberOfObjects = 10;
	
	private Object generateTrees(double x, double y){
		Object o = new ImmovableObject(x, y,ShapeGenerator.generateTree(50));
		return o;
	}

	private Object generateWalls(double x, double y){
		Object o = new ImmovableObject(x, y,ShapeGenerator.generateWall());
		return o;
	}
	
	public World(){
		int numberOfTrees = mRandom.nextInt(maxNumberOfObjects);
		int numberOfWalls = mRandom.nextInt(maxNumberOfObjects);
		int newX = mRandom.nextInt(midX*2);
		int newY = mRandom.nextInt(midY*2);
		for(int i=0;i<numberOfTrees;i++){
			addObject(generateTrees(newX, newY));
			newX = mRandom.nextInt(midX*2);
			newY = mRandom.nextInt(midY*2);
		}
		for(int i=0;i<numberOfWalls;i++){
			addObject(generateWalls(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2)));
		}
	}
	
	
	public void onResize(RenderWindow window){
		setMidX((window.getSize().x)/2);
		setMidY((window.getSize().y)/2);
		System.out.println(window.getSize());
		System.out.println(getMidX()+" "+getMidY());
	}
	


	public static float getMidX() {
		return midX;
	}

	public static void setMidX(int midX) {
		World.midX = midX;
	}

	public static float getMidY() {
		return midY;
	}

	public static void setMidY(int midY) {
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
