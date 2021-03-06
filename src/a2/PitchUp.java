package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PitchUp extends AbstractAction {
	private Camera camera;
	
	public PitchUp (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.pitchUp();
	}
}
