package a2;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class InputManager {
	
	private Camera camera;
	private Engine engine;
	
	public InputManager(Camera cam, Engine e) {
		camera = cam;
		engine = e;
	}
	
	private void setupInputs() {
		//get the content pane from the engine window
		JPanel contentPane = (JPanel) engine.getContentPane();
		//TODO define comments for input and action map
		int inputMap = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap iMap = contentPane.getInputMap(inputMap);
		ActionMap aMap = contentPane.getActionMap();
		
		//Create action names for all inputs
		AbstractAction moveForward = new MoveForward(camera);
		AbstractAction moveBackward = new MoveBackward(camera);
		AbstractAction moveLeft = new MoveLeft(camera);
		AbstractAction moveRight = new MoveRight(camera);
		AbstractAction moveUp = new MoveUp(camera);
		AbstractAction moveDown = new MoveDown(camera);
		AbstractAction pitchUp = new PitchUp(camera);
		AbstractAction pitchDown = new PitchDown(camera);
		AbstractAction yawLeft = new YawLeft(camera);
		AbstractAction yawRight = new YawRight(camera);
		AbstractAction toggleAxes = new ToggleAxes(engine);
		
		//Set keystrokes and actions for each button
		KeyStroke wKey = KeyStroke.getKeyStroke("w");
		iMap.put(wKey, "Forward");
		aMap.put("Forward", moveForward);
		
		KeyStroke sKey = KeyStroke.getKeyStroke("s");
		iMap.put(sKey, "Backward");
		aMap.put("Backward", moveBackward);
		
		KeyStroke aKey = KeyStroke.getKeyStroke("a");
		iMap.put(aKey, "Left");
		aMap.put("Left", moveLeft);
		
		KeyStroke dKey = KeyStroke.getKeyStroke("d");
		iMap.put(dKey, "Right");
		aMap.put("Right", moveRight);
		
		KeyStroke qKey = KeyStroke.getKeyStroke("q");
		iMap.put(qKey, "Up");
		aMap.put("Up", moveUp);
		
		KeyStroke eKey = KeyStroke.getKeyStroke("e");
		iMap.put(eKey, "Down");
		aMap.put("Down", moveDown);
		
		KeyStroke lArrowKey = KeyStroke.getKeyStroke("LEFT");
		iMap.put(lArrowKey, "lArrow");
		aMap.put("lArrow", yawLeft);
		
		KeyStroke rArrowKey = KeyStroke.getKeyStroke("RIGHT");
		iMap.put(rArrowKey, "rArrow");
		aMap.put("rArrow", yawRight);
		
		KeyStroke uArrowKey = KeyStroke.getKeyStroke("UP");
		iMap.put(uArrowKey, "uArrow");
		aMap.put("uArrow", pitchUp);
		
		KeyStroke dArrowKey = KeyStroke.getKeyStroke("DOWN");
		iMap.put(dArrowKey, "dArrow");
		aMap.put("dArrow", pitchDown);
		
		KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
		iMap.put(spaceKey, "Spacebar");
		aMap.put("Spacebar", toggleAxes);
		
	}
}
