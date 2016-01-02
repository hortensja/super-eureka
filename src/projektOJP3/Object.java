package projektOJP3;

import java.util.Random;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;

public class Object implements Drawable,Processable{


	protected static Random mRandom = new Random(System.nanoTime());
	private final Shape mShape;
	private CollidableShape mCollidableShape;
	
	private double mdX;
	private double mdY;
	private double mVel;
	
	private double mX;
	private double mY;
	
	
	public Object(double x, double y, Shape shape)
	{
		if (x>=(World.getMidX()*2) || y>=(World.getMidY()*2) || x<=0 || y<=0){
			this.resetPos();
		}
		//shape.setOrigin(-shape.getLocalBounds().width/2, -shape.getLocalBounds().height/2);
		mShape = shape;
		mX = x;
		mY = y;	
		mCollidableShape = new CollidableShape(this.getShape());
		randomizeDirection();
		randomizeVelocity();
		
	}
	public double getdX() {
		return mdX;
	}
	public void setdX(double mdX) {
		this.mdX = mdX;
	}
	public double getdY() {
		return mdY;
	}
	public void setdY(double mdY) {
		this.mdY = mdY;
	}
	public double getVel() {
		return mVel;
	}
	public void setVel(double mVel) {
		this.mVel = mVel;
	}
	protected void randomizeDirection()
	{
		mdX = mRandom.nextDouble()*2-1;
		mdY = mRandom.nextDouble()*2-1;
	}
	
	protected void randomizeVelocity()
	{
		mVel = mRandom.nextDouble();
	}
	
	protected void resetPos()
	{
		mX = World.getMidX();
		mY = World.getMidY();
	}

	protected boolean isInBounds()
	{
		double xMin=(World.getMidX())*2;
		double xMax=0;

		double yMin=(World.getMidY())*2;
		double yMax=0;
	
		for (int i=0;i<getShape().getPointCount();i++){
			
			//System.out.println(mShape.getPoint(i));
			
			if(getShape().getPoint(i).x<xMin)
				xMin=getShape().getPoint(i).x;
			if(getShape().getPoint(i).x>xMax)
				xMax=getShape().getPoint(i).x;
			if(getShape().getPoint(i).y<yMin)
				yMin=getShape().getPoint(i).y;
			if(getShape().getPoint(i).y>yMax)
				yMax=getShape().getPoint(i).y;
			
		}
		
		//System.out.println(xMin + " " + xMax);
		
		if((mX+xMax)<(2*World.getMidX()) && (mY+yMax)<(2*World.getMidY()) && (mY-yMin)>0 && (mX-xMin)>0)
			return true;
		return false;
	}
	
	protected void onOutOfBounds()
	{
		bounce();
		//resetPos();
		//randomizeDirection();
		randomizeVelocity();
	}
	protected void bounce(){
	
		mdX = -mdX;
		mdY = -mdY;
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

		getShape().setPosition((float)mX, (float)mY);
		mCollidableShape.setShapePosition(getPosition());
	}
	
	public double getX() {
		return mX;
	}
	public void setX(double mX) {
		this.mX = mX;
	}
	public double getY() {
		return mY;
	}
	public void setY(double mY) {
		this.mY = mY;
	}
	@Override
	public void process(double timestep) {
		if(!isInBounds())
		{
			onOutOfBounds();
		}
		//System.out.println(mX+" "+mY);

		mX += timestep * mdX * mVel;
		mY += timestep * mdY * mVel;
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
