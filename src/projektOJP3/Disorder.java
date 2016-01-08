package projektOJP3;

public class Disorder {

	private final String mName;
	
	public Disorder(String name){
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
		if(!(o instanceof Disorder)) {
			return false;
		}
		Disorder d = (Disorder) o;
		return d.mName.equalsIgnoreCase(mName);
	}
	
	@Override
	public int hashCode(){
		return mName.hashCode();
	}
	
}
