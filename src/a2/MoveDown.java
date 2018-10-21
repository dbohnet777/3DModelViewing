package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MoveDown extends AbstractAction {
	private Camera camera;
	
	public MoveDown (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.moveDown();
	}
}

