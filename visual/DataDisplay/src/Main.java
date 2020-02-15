import processing.core.PApplet;

public class Main extends PApplet {
	
	public SimGraphics test;

	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	public void settings() {
		size(LConstants.SCREEN_WIDTH, LConstants.SCREEN_HEIGHT);
	}
	
	public void setup() {
		String[] dep = {"v", "x", "cell_soc", "a_long"};
		test = new SimGraphics(this, "results.csv", "t", dep);
		test.updateGraph();
	}
	
	public void draw() {
		this.background(240);
		test.display();
	}
}
