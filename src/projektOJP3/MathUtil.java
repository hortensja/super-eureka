package projektOJP3;

import org.jsfml.system.Vector2f;
import static org.jsfml.system.Vector2f.*;

public class MathUtil {


	private MathUtil(){}
	
	public static double dot(Vector2f v1, Vector2f v2){
		return v1.x*v2.x+v1.y*v2.y;
	}

	public static double vectorLength(Vector2f v){
		return Math.sqrt(v.x*v.x+v.y*v.y);
	}
	
	public static Vector2f matVecMul(Matrix2x2f m, Vector2f v){
		float ret1 = m.a11*v.x+m.a12*v.y;
		float ret2 = m.a21*v.x+m.a22*v.y;
		return new Vector2f(ret1, ret2);
	}
	
	public static Matrix2x2f getRotationMatrix(float theta){
		return new Matrix2x2f((float) Math.cos(theta), (float) -Math.sin(theta), (float) Math.sin(theta), (float) Math.cos(theta));
	}
	
	public static Vector2f normalizeVector(Vector2f v){
		double length = vectorLength(v);
		return mul(v, (float) (1/length));
	}
}
