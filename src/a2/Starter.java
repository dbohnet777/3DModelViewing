package a2;

import java.awt.BorderLayout;
import java.nio.*;
import javax.swing.*;
import static com.jogamp.opengl.GL.GL_NO_ERROR;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.*;
import graphicslib3D.GLSLUtils.*;
import ray.input.InputManager;
import graphicslib3D.*;
import ray.input.action.Action;

public class Starter extends JFrame implements GLEventListener{
	private GLCanvas myCanvas;
	private int rendering_program;
	private int vao[] = new int[1];
	private GLSLUtils util = new GLSLUtils();
	private InputManager im;
	private float x = 0.0f;
	private float inc = 0.01f;

	public Starter(){	
		setTitle("Assignment #1");
		setSize(400, 500);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		setVisible(true);
		
		//create and start the FPS animator
		FPSAnimator animator = new FPSAnimator(myCanvas, 30);
		animator.start();
	}

	public void display(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glUseProgram(rendering_program);
		float bkg[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
		gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);		
		
		x += inc;
		if (x > 1.0f) inc = -0.01f;
		if (x < -1.0f) inc = 0.01f;
		int offset_loc = gl.glGetUniformLocation(rendering_program, "inc");
		gl.glProgramUniform1f(rendering_program, offset_loc, x);

		gl.glDrawArrays(GL_TRIANGLES,0,3);
	}

	public void init(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		rendering_program = createShaderProgram();
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		

		System.out.println("OpenGL Version: " + gl.glGetString(GL.GL_VERSION));
		System.out.println("JOGL Version: " + Package.getPackage("com.jogamp.opengl").getImplementationVersion());
		System.out.println("Java Version: " + System.getProperty("java.version"));
	}

	private int createShaderProgram(){
		GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("src/vert.shader"); // ./a1/vert.shader
		String fshaderSource[] = util.readShaderSource("src/frag.shader"); // ./a1/frag.shader
		int lengths[];

		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

		gl.glCompileShader(vShader);
		gl.glCompileShader(fShader);

		int vfprogram = gl.glCreateProgram();
		gl.glAttachShader(vfprogram, vShader);
		gl.glAttachShader(vfprogram, fShader);
		gl.glLinkProgram(vfprogram);
		
		checkOpenGLError();
		return vfprogram;
	}

	public static void main(String[] args) { new Starter(); }
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}
	
	
	//******************************************************Error Checking*************************************************************//
	public void printShaderLog(int shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();	
		int[] len = new int[1];
		int[] chWritten = new int[1];
		byte[] log = null;

		// determine the length of the shader compilation log
		gl.glGetShaderiv(shader, GL_INFO_LOG_LENGTH, len, 0);
		if (len[0] > 0)
		{	log = new byte[len[0]];
			gl.glGetShaderInfoLog(shader, len[0], chWritten, 0, log, 0);
			System.out.println("Shader Info Log: ");
			for (int i = 0; i < log.length; i++)
			{	System.out.print((char) log[i]);
			}
		}
	}
	public void printProgramLog(int prog) {
		GL4 gl = (GL4) GLContext.getCurrentGL();	
		int[] len = new int[1];
		int[] chWritten = new int[1];
		byte[] log = null;
		
		gl.glGetProgramiv(prog, GL_INFO_LOG_LENGTH, len, 0);
		if (len[0] > 0)
		{	log = new byte[len[0]];
			gl.glGetProgramInfoLog(prog, len[0], chWritten, 0, log, 0);
			System.out.println("Program Info Log: ");
			for (int i = 0; i < log.length; i++)
			{	System.out.print((char) log[i]);
			}
		}
	}
	public boolean checkOpenGLError() {
		GL4 gl = (GL4) GLContext.getCurrentGL();	
		boolean foundError = false;
		GLU glu = new GLU();
		int glErr = gl.glGetError();
		while (glErr != GL_NO_ERROR)
		{	System.err.println("glError: " + glu.gluErrorString(glErr));
			foundError = true;
			glErr = gl.glGetError();
		}
		return foundError;
	}
}