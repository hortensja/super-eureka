package projektOJP3;

public class Condition {

	private final String mName;
	
	public Condition(String name){
		mName = name;
	}
	
	public String getName(){
		return mName;
	}
	
	@Override
	public boolean equals(java.lang.Object o){
		if(o==null) {
			return false;
		}
		if(!(o instanceof Condition)) {
			return false;
		}
		Condition d = (Condition) o;
		return d.mName.equalsIgnoreCase(mName);
	}
	
	@Override
	public int hashCode(){
		return mName.hashCode();
	}
	
}
