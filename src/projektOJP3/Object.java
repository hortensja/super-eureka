package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;
import static org.jsfml.system.Vector2f.*;

public class Object implements Drawable,Processable{


	protected static Random mRandom = new Random(System.nanoTime());
	private final Shape mShape;
	private CollidableShape mCollidableShape;
	
	private Vector2f mdV;
	private double mVel;
		
	private Vector2f mPos;
	
	
	public Object(double x, double y, Shape shape) {
		if (x>=(World.getMidX()*2) || y>=(World.getMidY()*2) || x<=0 || y<=0){
			this.resetPos();
		}
		//shape.setOrigin(-shape.getLocalBounds().width/2, -shape.getLocalBounds().height/2);
		mShape = shape;
		mPos = new Vector2f((float) x, (float) y);
		mCollidableShape = new CollidableShape(this.getShape());
		
		randomizeDirection();
		randomizeVelocity();
	}
	
	public Vector2f getdV() {
		return mdV;
	}
	public void setdV(Vector2f mdV) {
		this.mdV = mdV;
	}
	public Vector2f getPos() {
		return mPos;
	}
	public void setPos(Vector2f mPos) {
		this.mPos = mPos;
	}
	
	
	
	public double getVel() {
		return mVel;
	}
	public void setVel(double mVel) {
		this.mVel = mVel;
	}
	
	protected void randomizeDirection()	{
		mdV = new Vector2f(mRandom.nextFloat()*2-1, mRandom.nextFloat()*2-1);
	}
	
	protected void randomizeVelocity()	{
		mVel = mRandom.nextDouble();
	}
	
	protected void resetPos()	{
		mPos = new Vector2f((float)World.getMidX(),(float) World.getMidY());
	}

	protected boolean isInBounds()
	{
		double xMin=(World.getMidX())*2;
		double xMax=0;

		double yMin=(World.getMidY())*2;
		double yMax=0;
	
		for (int i=0;i<getShape().getPointCount();i++){
			
			if(getShape().getPoint(i).x<xMin)
				xMin=getShape().getPoint(i).x;
			if(getShape().getPoint(i).x>xMax)
				xMax=getShape().getPoint(i).x;
			if(getShape().getPoint(i).y<yMin)
				yMin=getShape().getPoint(i).y;
			if(getShape().getPoint(i).y>yMax)
				yMax=getShape().getPoint(i).y;
		}
		if((mPos.x+xMax)<(2*World.getMidX()) && (mPos.y+yMax)<(2*World.getMidY()) && (mPos.y-yMin)>0 && (mPos.x-xMin)>0)
			return true;
		return false;
	}
	
	protected Vector2f findNormalIfOutOfBounds() {
		double xMin=(World.getMidX())*2;
		double xMax=0;

		double yMin=(World.getMidY())*2;
		double yMax=0;
	
		for (int i=0;i<getShape().getPointCount();i++){
			
			if (getShape().getPoint(i).x<xMin) {
				xMin=getShape().getPoint(i).x;
			}
			if (getShape().getPoint(i).x>xMax) {
				xMax=getShape().getPoint(i).x;
			}
			if (getShape().getPoint(i).y<yMin){
				yMin=getShape().getPoint(i).y;
			}
			if (getShape().getPoint(i).y>yMax) {
				yMax=getShape().getPoint(i).y;
			}
		}
		
		if (mPos.x - xMin < 0) {
			return new Vector2f(-1, 0);
		}
		if (mPos.y - yMin < 0) {
			return new Vector2f(0, -1);
		}
		if (mPos.x + xMax > 2*World.getMidX()) {
			return new Vector2f(1, 0);
		}
		if (mPos.y + yMax > 2*World.getMidY()) {
			return new Vector2f(0, 1);
		}
		return new Vector2f(0,0);
	}
	
	protected void onOutOfBounds(Vector2f normal)	{
		bounce(normal);
		//resetPos();
		//randomizeDirection();
		randomizeVelocity();
	}
	
	
	protected void bounce(Vector2f normal){
	
		double kappa = 2*MathUtil.dot(mdV, normal);
		
		mdV = sub(mdV, mul(normal, (float) kappa));
		
		//mdV = newV;
		
	}
	
	protected double getRadius(){
		double h = this.getShape().getGlobalBounds().height;
		double w = this.getShape().getGlobalBounds().width;
		return (h+w)/4;
	}
	protected Vector2f getPosition(){
		return this.getShape().getPosition();
	}
	
	protected void updateShapePosition(){
		getShape().setPosition(mPos);
		mCollidableShape.setShapePosition(getPosition());
	}
	
	
	@Override
	public void process(double timestep) {
		if(!isInBounds())
		{
			onOutOfBounds(findNormalIfOutOfBounds());
		}
		
		mPos = add(mPos, mul(mdV, (float) (mVel*timestep)));

		updateShapePosition();
	}

	@Override
	public void draw(RenderWindow window) {
		window.draw(getShape());
		
	}
	public Shape getShape() {
		return mShape;
	}
	
	public CollidableShape getCollidableShape(){
		return mCollidableShape;
	}
	
}