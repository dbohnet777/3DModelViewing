package a2;

import static com.jogamp.opengl.GL.GL_NO_ERROR;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import static com.jogamp.opengl.GL2ES3.GL_COLOR;

import java.nio.FloatBuffer;

import javax.swing.JFrame;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import graphicslib3D.GLSLUtils;

@SuppressWarnings("serial")
public class Engine extends JFrame implements GLEventListener{
	private GLCanvas myCanvas;
	private GLSLUtils util = new GLSLUtils();
	private int rendering_program;
	private int vao[] = new int[1];
	
	private boolean toggleAxes = true;
	
	public Engine() {
		setTitle("Assignment #2");
		setSize(1000, 1000);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		setVisible(true);
	}

	public void start() {
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

	@SuppressWarnings("static-access")
	private int createShaderProgram(){
		GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("src/vert.shader"); // ./a1/vert.shader
		String fshaderSource[] = util.readShaderSource("src/frag.shader"); // ./a1/frag.shader

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
		
		//function to switch boolean value to draw axes or not
		public void toggleAxes() {
			toggleAxes = !toggleAxes;
		}
}
