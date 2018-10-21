package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PitchDown extends AbstractAction {
	private Camera camera;
	
	public PitchDown (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.pitchDown();
	}
}
