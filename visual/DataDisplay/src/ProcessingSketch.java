import processing.core.PApplet;

public class ProcessingSketch extends PApplet {
	
	public SimGraphics test;

	
	public void settings() {
		size(LConstants.SCREEN_WIDTH, LConstants.SCREEN_HEIGHT);
	}
	
	public void setup() {
		String[] dep = {"x", "a_long", "cell_heat_w", "pos_x", "v"};
		test = new SimGraphics(this, "results.csv", "t", dep);
		test.updateGraph();
	}
	
	public void draw() {
		this.background(240);
		test.display();
	}
	
	public void run() {
		String[] processingArgs = {"ProcessingSketch"};
		PApplet.runSketch(processingArgs, this);
	}
}
