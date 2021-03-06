package a2;

import graphicslib3D.Matrix3D;

public class Camera {
	//camera position, pitch and yaw variables
	private float xPos, yPos, zPos;
	private float pitch;
	private float yaw;
	//view matrix for camera
	private Matrix3D vM;
	//increment value for camera movement
	private float cInc = 0.1f;
	
	public Camera(float x, float y, float z) {
		xPos = x;
		yPos = y;
		zPos = z;
		
		vM = new Matrix3D();
		computeView();
	}
	
	//function to compute the current view matrix based on camera location and orientation
	private void computeView() {
		
	}
	
	//function to set the initial values for camera variables
	private void initCamera() {
		xPos = 0.0f;
		yPos = 0.0f;
		zPos = 0.0f;
		pitch = 0.0f;
		yaw = 0.0f;
	}
	
	//************functions for camera movement and rotation*****************************//
	protected void moveForward() {
		zPos += cInc;
		computeView();
	}
	protected void moveBackward() {
		zPos -= cInc;
		computeView();
	}
	protected void moveLeft() {
		xPos += cInc;
		computeView();
	}
	protected void moveRight() {
		xPos -= cInc;
		computeView();
	}
	protected void moveUp() {
		yPos += cInc;
		computeView();
	}
	protected void moveDown() {
		yPos -= cInc;
		computeView();
	}
	protected void pitchUp() {
		pitch += cInc;
		computeView();
	}
	protected void pitchDown() {
		pitch -= cInc;
		computeView();
	}
	protected void yawLeft() {
		yaw -= cInc;
		computeView();
	}
	protected void yawRight() {
		yaw += cInc;
		computeView();
	}
}
