package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MoveUp extends AbstractAction {
	private Camera camera;
	
	public MoveUp (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.moveUp();
	}
}
