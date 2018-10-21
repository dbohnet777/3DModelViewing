package a2;

public class Starter{
	private Engine engine;

	public Starter(){
		//create the engine and start it
		engine = new Engine();
		engine.start();
	}

	public static void main(String[] args) {
		new Starter();
	}
	
}