package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MoveLeft extends AbstractAction {
	private Camera camera;
	
	public MoveLeft (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.moveLeft();
	}
}
