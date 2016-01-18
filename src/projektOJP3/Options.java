package projektOJP3;

import java.util.Map;

public class Options {

	public final static int MIN_DEGREE = 1;
	public final static int MAX_DEGREE = 10;
	
	public final boolean isMyopic;
	public int myopiaDegree = MIN_DEGREE;
	public final boolean isHyperopic;
	public int hyperopiaDegree = MIN_DEGREE;
	public final boolean isCerebellar;
	public final boolean isLeftEyeDisabled;
	public final boolean isRightEyeDisabled;
	public final boolean isLeftSideDisabled;
	public final boolean isRightSideDisabled;
	
	
	
	public final static Options DEFAULT_OPTIONS = new Options();
	
	private Options(){
		isMyopic = false;
		isHyperopic = false;
		isCerebellar = false;
		isLeftEyeDisabled = false;
		isRightEyeDisabled = false;
		isLeftSideDisabled = false;
		isRightSideDisabled = false;
	}
	
	public Options(Map<Condition, Button> buttons) throws OptionsException{
		
		
		eliminateBooleanErrors(buttons, new Condition("Myopia"));
		isMyopic = ((BooleanButton) buttons.get(new Condition("Myopia"))).getStatus();
		
		eliminateIntSlideErrors(buttons, new Condition("Myopia degree"));
		myopiaDegree = ((SlidingButton) buttons.get(new Condition("Myopia degree"))).getValue();
		((SlidingButton) buttons.get(new Condition("Myopia degree"))).setValidity(isMyopic);
		
		eliminateBooleanErrors(buttons, new Condition("Hyperopia"));
		isHyperopic = ((BooleanButton) buttons.get(new Condition("Hyperopia"))).getStatus();
		
		((BooleanButton) buttons.get(new Condition("Hyperopia"))).setValidity(!isMyopic);
		((BooleanButton) buttons.get(new Condition("Myopia"))).setValidity(!isHyperopic);
		
		eliminateIntSlideErrors(buttons, new Condition("Hyperopia degree"));
		hyperopiaDegree = ((SlidingButton) buttons.get(new Condition("Hyperopia degree"))).getValue();
		((SlidingButton) buttons.get(new Condition("Hyperopia degree"))).setValidity(isHyperopic);
		
		
		eliminateBooleanErrors(buttons, new Condition("Cerebellum"));
		isCerebellar = ((BooleanButton) buttons.get(new Condition("Cerebellum"))).getStatus();

		eliminateBooleanErrors(buttons, new Condition("Left eye disabled"));
		isLeftEyeDisabled = ((BooleanButton) buttons.get(new Condition("Left eye disabled"))).getStatus();
		
		eliminateBooleanErrors(buttons, new Condition("Right eye disabled"));
		isRightEyeDisabled = ((BooleanButton) buttons.get(new Condition("Right eye disabled"))).getStatus();
		

		eliminateBooleanErrors(buttons, new Condition("Left side disabled"));
		isLeftSideDisabled = ((BooleanButton) buttons.get(new Condition("Left side disabled"))).getStatus();
		
		eliminateBooleanErrors(buttons, new Condition("Right eye disabled"));
		isRightSideDisabled = ((BooleanButton) buttons.get(new Condition("Right side disabled"))).getStatus();
		

		((BooleanButton) buttons.get(new Condition("Left side disabled"))).setValidity(!isRightSideDisabled);
		((BooleanButton) buttons.get(new Condition("Right side disabled"))).setValidity(!isLeftSideDisabled);
		
	}
	
	protected void eliminateBooleanErrors(Map<Condition, Button> buttons, Condition disorder) throws OptionsException{
		if (!buttons.containsKey(disorder)){
			throw new OptionsException(disorder.getName() + " does not exist");
		} else if (!(buttons.get(disorder) instanceof BooleanButton)){
			throw new OptionsException("button " + disorder.getName() + " jest bulem inwalid¹");
		}
	}

	protected void eliminateIntSlideErrors(Map<Condition, Button> buttons, Condition disorder) throws OptionsException{
		if (!buttons.containsKey(disorder)){
			throw new OptionsException(disorder.getName() + " does not exist");
		} else if (!(buttons.get(disorder) instanceof SlidingButton)){
			throw new OptionsException("button " + disorder.getName() + " jest intem inwalid¹");
		}
	}
}
