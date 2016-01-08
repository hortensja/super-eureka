package projektOJP3;

public class PersonGenerator {

	private PersonGenerator(){}
	

	public static Person generatePerson(double x, double y, Options o){
		Person p;
		if (o.isCerebellar == false){
			p = new Person(x, y, o);
			} else {
			p = new CerebellarPerson(x, y, o);
			}
		return p;
	}
	
}
