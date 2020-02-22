import java.util.ArrayList;

import processing.core.PApplet;

public class SimGraphics {

	private PApplet p;
	private DataManager daddyData;
	private ArrayList<Graph> currGraph;
	private int currentIndex;
	
	public SimGraphics(PApplet p, String textFile, String x, String[] yArgs) {
		this.p = p;
		currentIndex = 0;
		daddyData = new DataManager(textFile);
		currGraph = new ArrayList<Graph>();
		currGraph.add(new Graph(p, daddyData.getData(x, getArgsList(yArgs))));
		currGraph.get(currentIndex).setXName(x);
		currGraph.get(currentIndex).setYNames(daddyData.getDisplayedNames());
	}
	
	private ArrayList<String> getArgsList(String[] yArgs) {
		ArrayList<String> res = new ArrayList<>();
		for (int i = 0; i < yArgs.length; i++) {
			res.add(yArgs[i]);
		}
		return res;
	}
	
	public void setIndex(int index) {
		currentIndex = index;
		if (currentIndex >= currGraph.size()) {
			currGraph.add(new Graph(p));
		}
	}
	
	public void updateGraph() {
		currGraph.get(currentIndex).update();
	}
	
	public void display() {
		currGraph.get(currentIndex).display();
	}
}
