package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class YawLeft extends AbstractAction {
	private Camera camera;
	
	public YawLeft (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.yawLeft();
	}
}
