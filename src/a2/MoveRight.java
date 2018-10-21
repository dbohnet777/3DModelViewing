package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MoveRight extends AbstractAction {
	private Camera camera;
	
	public MoveRight (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.moveRight();
	}
}
