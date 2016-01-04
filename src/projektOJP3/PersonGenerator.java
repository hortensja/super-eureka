package projektOJP3;

public class PersonGenerator {

	private PersonGenerator(){}
	

	public static Person generatePerson(double x, double y, Options o){
		//if (o.get == sdgd){
			Person p = new Person(x, y, o);
			//} else {
			//Person p = new DrunkPerson(x, y, o);
			//}
		return p;
	}
	
}
