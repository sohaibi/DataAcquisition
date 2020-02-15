import java.util.ArrayList;

import processing.core.PApplet;

public class SimGraphics {

	private PApplet p;
	private DataManager daddyData;
	private Graph currGraph;
	
	public SimGraphics(PApplet p, String textFile, String x, String[] yArgs) {
		this.p = p;
		daddyData = new DataManager(textFile);
		currGraph = new Graph(p, daddyData.getData(x, getArgsList(yArgs)));
	}
	
	private ArrayList<String> getArgsList(String[] yArgs) {
		ArrayList<String> res = new ArrayList<>();
		for (int i = 0; i < yArgs.length; i++) {
			res.add(yArgs[i]);
		}
		return res;
	}
	
	public void updateGraph() {
		currGraph.update();
	}
	
	public void display() {
		currGraph.display();
	}
}
