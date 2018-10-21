package a2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class YawRight extends AbstractAction {
	private Camera camera;
	
	public YawRight (Camera c) {
		camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		camera.yawRight();
	}
}
