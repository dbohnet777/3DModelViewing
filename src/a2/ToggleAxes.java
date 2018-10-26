package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ToggleAxes extends AbstractAction {
	private Engine engine;
	
	public ToggleAxes (Engine eng) {
		engine = eng;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		engine.toggleAxes();
	}
}
