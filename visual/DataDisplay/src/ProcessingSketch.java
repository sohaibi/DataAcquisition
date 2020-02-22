import processing.core.PApplet;

public class ProcessingSketch extends PApplet {
	
	public SimGraphics simGraph;
	private String[] args;

	public ProcessingSketch(String[] args) {
		this.args = args;
	}
	
	public void settings() {
		size(LConstants.SCREEN_WIDTH, LConstants.SCREEN_HEIGHT);
	}
	
	public void setup() {
		simGraph = new SimGraphics(this, "results.csv", "t", args);
		simGraph.updateGraph();
	}
	
	public void draw() {
		this.background(240);
		simGraph.display();
	}
	
	public void setGraph(int index) {
		simGraph.setIndex(index);
		simGraph.updateGraph();
	}
	
	public boolean graphInitialized() {
		return simGraph != null;
	}
	
	public void run() {
		String[] processingArgs = {"ProcessingSketch"};
		PApplet.runSketch(processingArgs, this);
	}
}
