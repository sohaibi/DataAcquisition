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
	
	public int getIndex() {
		return currentIndex;
	}
	
	public boolean graphDone() {
		return currGraph.get(currentIndex).graphDone();
	}
	
	public void updateGraph() {
		currGraph.get(currentIndex).update();
	}
	
	public void display() {
		currGraph.get(currentIndex).display();
	}
	
	public void updateX(String x) {
		String[] currY = currGraph.get(currentIndex).getYNames();
		updateData(x, currY);
		currGraph.set(currentIndex, new Graph(p, daddyData.getData(x, getArgsList(currY))));
		currGraph.get(currentIndex).setXName(x);
		currGraph.get(currentIndex).setYNames(daddyData.getDisplayedNames());
		currGraph.get(currentIndex).update();
	}
	
	public void updateY(String[] yArgs) {
		String currX = currGraph.get(currentIndex).getXName();
		updateData(currX, yArgs);
	}
	
	public void updateData(String x, String[] yArgs) {
		currGraph.set(currentIndex, new Graph(p, daddyData.getData(x, getArgsList(yArgs))));
		currGraph.get(currentIndex).setXName(x);
		currGraph.get(currentIndex).setYNames(daddyData.getDisplayedNames());
		currGraph.get(currentIndex).update();
	}
	
	public String[] getCurrentArgs() {
		return currGraph.get(currentIndex).getYNames();
	}
	
	public String[] getAllArgs() {
		return daddyData.getAllArgs();
	}
	
	public void updateColor(String colorArg, int color) {
		currGraph.get(currentIndex).updateColor(colorArg, color);
	}
	
	public DataPoint getExtrema() {
		return currGraph.get(currentIndex).getExtrema();
	}
	
	public void setRange(float min, float max) {
		currGraph.get(currentIndex).setCustomRange(min, max);
	}
}
