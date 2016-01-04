/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import java.util.ArrayList;
import java.util.Random;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class World implements Drawable, Processable{


	private final ArrayList<Object> mObjects = new ArrayList<>();
	private final ArrayList<Object> mPersons = new ArrayList<>();
	
	private static int midX;
	private static int midY;
	
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
	
	public World(RenderWindow window){
		midX = window.getSize().x/2;
		midY = window.getSize().y/2;
		
		//addObject()
		
		int numberOfTrees = mRandom.nextInt(maxNumberOfObjects)+1;
		int numberOfWalls = mRandom.nextInt(maxNumberOfObjects)+1;
		Object o;
		for(int i=0;i<numberOfTrees;i++){
			do {
				o = generateTrees(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2));
			} while(isCollidingWithAnything(o));
			addObject(o);
		}
		for(int i=0;i<numberOfWalls;i++){
			do {
				o = generateWalls(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2));
			} while(isCollidingWithAnything(o));
			addObject(o);
		}
		System.out.println(midX+ " " + midY);
	}
	
	protected boolean isCollidingWithAnything(Object o){
		for (Object object : mObjects){
			if (Collision.areCollidingBetterTest(o, object))
				return true;
		}
		return false;
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

	
	
	@Override
	public void draw(RenderWindow window) {
		for(Object object : mObjects) { 
			object.draw(window);
		}
		for (Object person : mPersons){
			person.draw(window);
		}
	}

	@Override
	public void process(double timestep) {
		for(Object object : mObjects) {
			object.process(timestep);
		}
		
		int i = 1;
		
		for (Object person : mPersons) {
			person.process(timestep);

			for(Object object : mObjects) {
				if (Collision.areCollidingBetterTest(object, person)){
					Collision.collide(object, person);
				}
			}

			for(int j=i; j<mPersons.size();j++){
				Object person2 = mPersons.get(j);
				if (Collision.areCollidingBetterTest(person, person2)){
					Collision.collide(person, person2);
				}
			}
			i++;
		}
	}
	
	
	public void addObject(Object object) {
		mObjects.add(object);
	}

	public void addPerson(Person person) {
		mPersons.add(person);
	}
}
