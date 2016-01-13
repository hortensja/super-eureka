/*
 * hortensja
 *
 * neurological disorders v. 0.99
 */

package projektOJP3;

import java.util.ArrayList;
import java.util.Random;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

public class World implements Drawable, Processable{


	private static int midX;
	private static int midY;
	
	private final ArrayList<Object> mObjects = new ArrayList<>();
	private final ArrayList<Person> mPersons = new ArrayList<>();
	
	

	private Options mOptions;
	private Random mRandom = new Random(System.nanoTime());
	private int maxNumberOfObjects = 10;
	
	
	public World(RenderWindow window, Options options){
		midX = window.getSize().x/2;
		midY = window.getSize().y/2;
		
		mOptions = options;
		new String();
		
		//adding bounds just in case
		addObject(new ImmovableObject(0, -1, ShapeGenerator.generateRect(2*midX, 1)));
		addObject(new ImmovableObject(0, 2*midY+1, ShapeGenerator.generateRect(2*midX, 1)));
		addObject(new ImmovableObject(-1, 0, ShapeGenerator.generateRect(1, 2*midY)));
		addObject(new ImmovableObject(2*midX+1, 0, ShapeGenerator.generateRect(1, 2*midY)));
		
		
		/*addObject(new ImmovableObject(midX*0.75, midY*0.75, ShapeGenerator.generateRect((float)0.5*midX,(float) 10)));
		addObject(new ImmovableObject(midX*0.75, midY*1.25, ShapeGenerator.generateRect((float)0.5*midX, (float)10)));
		addObject(new ImmovableObject(midX*1.25, midY*0.75, ShapeGenerator.generateRect(10, (float)0.5*midY)));
		addObject(new ImmovableObject(midX*0.75, midY*0.75, ShapeGenerator.generateRect((float)10, (float)0.5*midY)));
		*/
		
		
		
		int numberOfTrees = mRandom.nextInt(maxNumberOfObjects)+1;
		int numberOfWalls = mRandom.nextInt(maxNumberOfObjects)+1;
		int numberOfCars = mRandom.nextInt(2)+1;
		Object o;

		//o = generateCars(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2));
		//addObject(o);
		
		
		for(int i=0;i<numberOfCars;i++){
			//do {
				o = generateCars(mRandom.nextInt(midX)+midX/2, mRandom.nextInt(midY)+midY/2);
			//} while(isCollidingWithAnything(o));
			addObject(o);
		}

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
		
		
		System.out.println("World size: " + midX+ " " + midY);
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
		
		for (Person person : mPersons) {
			
			person.process(timestep);

			for(Object object : mObjects) {
				
				if (Collision.areCollidingBetterTest(person.getCollidableShape(), object.getCollidableShape())){
					Collision.collideObjectWithObject(person, object);
				} else if (Collision.areCollidingBetterTest(person.getCollidableVision(), object.getCollidableShape())){
					Collision.collideVisionWithObject(person, object);
				}

			}

			for(int j=i; j<mPersons.size();j++){
				Object person1 = person;
				Object person2 = mPersons.get(j);
				Person person2prim = mPersons.get(j);
				
				if (Collision.areCollidingBetterTest(person1, person2)){
					Collision.collideObjectWithObject(person1, person2);
				} else if (Collision.areCollidingBetterTest(person.getCollidableVision(), person2.getCollidableShape())){
					Collision.collideVisionWithObject(person, person2);
				} else if (Collision.areCollidingBetterTest(person.getCollidableShape(), person2prim.getCollidableVision())){
					Collision.collideVisionWithObject(person2prim, person);
				}
			}
			i++;
		}
		
		i = 1;
		for(Object object : mObjects) {

			for(int j=i; j<mObjects.size();j++){

				if (Collision.areCollidingBetterTest(object.getCollidableShape(), mObjects.get(j).getCollidableShape())){
					Collision.collideObjectWithObject(object, mObjects.get(j));
				}
			}
			i++;
		}

		for(i=0;i<mPersons.size();i++) {
			if (mPersons.get(i).isDead()) {
				mPersons.remove(i);
			}
		}
		

	}
	
	public Event processEvent(Event e){ 
	
		for(Person person : mPersons){
			if(e!=null)
				e = person.processEvent(e);
			else
				return null;
		}
		return e;
	}
	
	public void addObject(Object object) {
		mObjects.add(object);
	}

	public void addPerson(Person person) {
		mPersons.add(person);
	}
	
	

	protected boolean isCollidingWithAnything(Object o){
		for (Object object : mObjects){
			if (Collision.areCollidingBetterTest(o, object))
				return true;
		}
		return false;
	}
	
	private Object generateTrees(double x, double y){
		return new ImmovableObject(x, y,ShapeGenerator.generateTree(mRandom.nextDouble()*80+20));
	}

	private Object generateWalls(double x, double y){
		return new ImmovableObject(x, y,ShapeGenerator.generateWall());
	}
	
	private Object generateCars(double x, double y) {
		return new Car(x, y);
	}
	
}
