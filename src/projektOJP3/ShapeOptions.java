package projektOJP3;

import java.util.Map;
import java.util.Map.Entry;

public class ShapeOptions {

	//private String mCurrentShape;
	
	private ShapeOptions() {}
	
	
	public static String getShapeOptions(Map<String, Button> buttons) throws OptionsException{
		
		
		String s;
		BooleanButton tempButton;
		
		s = new String("car");
		eliminateBooleanErrors(buttons, s);
		tempButton = ((BooleanButton) buttons.get(s));
		for (Entry<String, Button> b : buttons.entrySet()) {
			if (!(b.getValue().equals(tempButton))) {
				b.getValue().setValidity(!tempButton.getStatus());
			}
		}
		if (tempButton.getStatus()) {
			return s;
		}

		s = new String("tree");
		eliminateBooleanErrors(buttons, s);
		tempButton = ((BooleanButton) buttons.get(s));
		for (Entry<String, Button> b : buttons.entrySet()) {
			if (!(b.getValue().equals(tempButton))) {
				b.getValue().setValidity(!tempButton.getStatus());
			}
		}
		if (tempButton.getStatus()) {
			return s;
		}
		
		
		s = new String("wall");
		eliminateBooleanErrors(buttons, s);
		tempButton = ((BooleanButton) buttons.get(s));
		for (Entry<String, Button> b : buttons.entrySet()) {
			if (!(b.getValue().equals(tempButton))) {
				b.getValue().setValidity(!tempButton.getStatus());
			}
		}
		if (tempButton.getStatus()) {
			return s;
		}
		
		
		return new String();
		
		
	}
	
	protected static void eliminateBooleanErrors(Map<String, Button> buttons, String shape) throws OptionsException{
		if (!buttons.containsKey(shape)){
			throw new OptionsException(shape + " does not exist");
		} else if (!(buttons.get(shape) instanceof BooleanButton)){
			throw new OptionsException("button " + shape + " jest bulem inwalid¹");
		}
	}

}
