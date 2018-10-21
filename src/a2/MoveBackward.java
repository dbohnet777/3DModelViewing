package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MoveBackward extends AbstractAction {
	private Camera camera;
	
	public MoveBackward (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.moveBackward();
	}
}
