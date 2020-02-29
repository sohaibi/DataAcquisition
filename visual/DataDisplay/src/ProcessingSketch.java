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
		this.background(100);
		simGraph.display();
	}
	
	public void setGraph(int index) {
		if (index != simGraph.getIndex()) {
			simGraph.setIndex(index);
			simGraph.updateGraph();
		}
	}
	
	public boolean graphDone() {
		return simGraph != null && simGraph.graphDone();
	}
	
	public String[] getAllArgs() {
		return simGraph.getAllArgs();
	}
	
	public void updateX(String x) {
		simGraph.updateX(x);
	}
	
	public void updateY(String[] yArgs) {
		simGraph.updateY(yArgs);
	}
	
	public void run() {
		String[] processingArgs = {"ProcessingSketch"};
		PApplet.runSketch(processingArgs, this);
	}
}
