package projektOJP3;

import java.util.Map;

public class Options {

	
	public final boolean isMyopic;
	public final boolean isHyperopic;
	public final boolean isCerebellar;
	
	public final static Options DEFAULT_OPTIONS = new Options();
	
	private Options(){
		isMyopic = false;
		isHyperopic = false;
		isCerebellar = false;
	}
	
	public Options(Map<Disorder, Button> buttons) throws OptionsException{
		
		
		eliminateBooleanErrors(buttons, new Disorder("Myopia"));
		isMyopic = ((BooleanButton) buttons.get(new Disorder("Myopia"))).getStatus();

		eliminateBooleanErrors(buttons, new Disorder("Hyperopia"));
		isHyperopic = ((BooleanButton) buttons.get(new Disorder("Hyperopia"))).getStatus();

		eliminateBooleanErrors(buttons, new Disorder("Cerebellum"));
		isCerebellar = ((BooleanButton) buttons.get(new Disorder("Cerebellum"))).getStatus();
		System.out.println(isMyopic + " " + isHyperopic+" " + isCerebellar);
	}
	
	protected void eliminateBooleanErrors(Map<Disorder, Button> buttons, Disorder disorder) throws OptionsException{
		if (!buttons.containsKey(disorder)){
			throw new OptionsException(disorder.getName() + " does not exist");
		} else if (!(buttons.get(disorder) instanceof BooleanButton)){
			throw new OptionsException("button " + disorder.getName() + " jest inwalid¹");
		}
	}
	
}
