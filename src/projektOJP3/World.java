package projektOJP3;

import java.util.ArrayList;
import java.util.Random;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class World implements Drawable, Processable{


	private final ArrayList<Object> mObjects = new ArrayList<>() ;
	
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
		int numberOfTrees = mRandom.nextInt(maxNumberOfObjects)+1;
		int numberOfWalls = mRandom.nextInt(maxNumberOfObjects)+1;
		Object o;
		for(int i=0;i<numberOfTrees;i++){
			do{
				o = generateTrees(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2));
			}while(isCollidingWithAnything(o));
			addObject(o);
		}
		for(int i=0;i<numberOfWalls;i++){
			do{
				o = generateWalls(mRandom.nextInt(midX*2), mRandom.nextInt(midY*2));
			}while(isCollidingWithAnything(o));
			addObject(o);
		}
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
	}

	@Override
	public void process(double timestep) {
		int i = 1;
		for(Object object : mObjects) {
			object.process(timestep);

			for(int j=i; j<mObjects.size();j++){
				Object object2 = mObjects.get(j);
				if (Collision.areCollidingBetterTest(object, object2)){
					Vector2f normal1 = object.getCollidableShape().minimalDistanceNormal(object2.getCollidableShape());
					Vector2f normal2 = object2.getCollidableShape().minimalDistanceNormal(object.getCollidableShape());
				
					if(CollidableShape.dot(new Vector2f((float) object.getdX(), (float) object.getdY()), normal2)<0){
						object.bounce(normal2);
						System.out.println("baunsuje o: " + normal2);
						//object2.bounce(normal1);
					}
					if(CollidableShape.dot(new Vector2f((float) object2.getdX(), (float) object2.getdY()), normal1)<0){
						object.bounce(normal2);
						object2.bounce(normal1);
						//System.out.println("baunsuje o: " + normal1);
					}
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
