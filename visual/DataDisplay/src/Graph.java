import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class Graph {

	private PApplet p;
	private int highlightIndex;
	private boolean prevSpace;
	private ArrayList<ArrayList<DataPoint>> dataList;
	private DataPoint extrema;
	private float deltaX;
	private PImage imgGraph;
	private String xName;
	private String[] yNames;
	private PFont textFont;
	private boolean noData;
	private boolean graphDone;

	public Graph(PApplet p) {
		this.p = p;
		noData = true;
	}

	public Graph(PApplet p, ArrayList<ArrayList<DataPoint>> data) {
		this.p = p;
		dataList = data;
		textFont = p.createFont(LConstants.GRAPH_TEXT_FONT, LConstants.GRAPH_TEXT_SIZE);
		noData = false;
	}

	public void setXName(String xName) {
		this.xName = xName;
	}

	public void setYNames(String[] yNames) {
		this.yNames = new String[yNames.length];
		for (int i = 0; i < yNames.length; i++) {
			this.yNames[i] = yNames[i];
		}
	}
	
	public String getXName() {
		return xName;
	}
	
	public String[] getYNames() {
		return yNames;
	}

	public void update() {
		if (imgGraph == null) {
			displayBackground();
			if (!noData) {
				deltaX = (float) LConstants.GRAPH_DATA_WIDTH / dataList.get(0).size();
				extrema = findExtrema();
				displayVar();
			}
			createGraphImage();
			graphDone = true;
		}
	}

	public void displayVar() {
		int colorNum = 0;
		displayGrid();
		displayAxisInfo();
		for (ArrayList<DataPoint> dataVar : dataList) {
			p.stroke(LConstants.GRAPH_DATA_LINE_COLOR[colorNum]);
			DataPoint prev = dataVar.get(0);
			DataPoint curr;
			for (int i = 1; i < dataVar.size(); i++) {
				curr = dataVar.get(i);
				float pDX = getDisplayX(i - 1);
				float cDX = getDisplayX(i);
				float pDY = getDisplayY(prev.getY());
				float cDY = getDisplayY(curr.getY());
				p.line(pDX, pDY, cDX, cDY);
				// System.out.printf("[(%f, %f) -> (%f, %f)] ", pDX, pDY, cDX, cDY);
				prev = curr;
			}
			colorNum++;
		}
	}

	public void displayBackground() {
		p.noStroke();
		p.fill(LConstants.GRAPH_BACKGROUND_COLOR);
		p.rect(LConstants.GRAPH_INITIAL_X, LConstants.GRAPH_INITIAL_Y, LConstants.GRAPH_FINAL_X,
				LConstants.GRAPH_FINAL_Y);
		p.stroke(LConstants.GRAPH_GRID_LINE_COLOR);
		p.line(LConstants.GRAPH_INITIAL_DATA_X, LConstants.GRAPH_INITIAL_DATA_Y, LConstants.GRAPH_INITIAL_DATA_X,
				LConstants.GRAPH_FINAL_DATA_Y);
		p.line(LConstants.GRAPH_INITIAL_DATA_X, LConstants.GRAPH_FINAL_DATA_Y, LConstants.GRAPH_FINAL_DATA_X,
				LConstants.GRAPH_FINAL_DATA_Y);
		p.strokeWeight(2);
	}

	public float getDisplayY(float yVal) {
		return PApplet.map((float) yVal, (float) extrema.getX(), (float) extrema.getY(), LConstants.GRAPH_FINAL_DATA_Y,
				LConstants.GRAPH_INITIAL_DATA_Y);
	}

	public float getDisplayX(float xVal) {
		return (float) (LConstants.GRAPH_INITIAL_DATA_X + xVal * deltaX);
	}

	public int getGraphX(float mouseX) {
		return (int) ((p.mouseX - LConstants.GRAPH_BUFFER_X) / deltaX);
	}

	private void createGraphImage() {
		imgGraph = p.createImage(LConstants.GRAPH_TOTAL_WIDTH, LConstants.GRAPH_TOTAL_HEIGHT, PConstants.RGB);
		imgGraph.loadPixels();
		for (int x = LConstants.GRAPH_INITIAL_X; x < LConstants.GRAPH_FINAL_X; x++) {
			for (int y = LConstants.GRAPH_BUFFER_Y; y < LConstants.GRAPH_FINAL_Y; y++) {
				imgGraph.pixels[x + y * LConstants.GRAPH_TOTAL_WIDTH] = p.get(x, y);
			}
		}
		imgGraph.updatePixels();
	}

	public DataPoint findExtrema() {
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		for (ArrayList<DataPoint> dataVar : dataList) {
			for (DataPoint pt : dataVar) {
				float comp = pt.getY();
				if (comp < min) {
					min = comp;
				}
				if (comp > max) {
					max = comp;
				}
			}
		}
		return new DataPoint(min, max);
	}

	public void display() {
		if (imgGraph != null) {
			p.image(imgGraph, LConstants.GRAPH_INITIAL_X, LConstants.GRAPH_INITIAL_Y);
		}
		if (!noData) { 
			displayMouse();
		}
	}

	private void displayMouse() {
		if (p.mouseX >= LConstants.GRAPH_INITIAL_DATA_X && p.mouseX < LConstants.GRAPH_FINAL_DATA_X) {
			displayHighlightInfo();
			p.stroke(LConstants.GRAPH_MOUSE_LINE_COLOR);
			p.line(p.mouseX, LConstants.GRAPH_INITIAL_DATA_Y, p.mouseX, LConstants.GRAPH_FINAL_DATA_Y);
			for (int i = 0; i < dataList.size(); i++) {
				float yPos = getYFromMouse(i);
				p.fill(LConstants.GRAPH_DATA_LINE_COLOR[i]);
				p.ellipse(p.mouseX, yPos, LConstants.GRAPH_CIRCLE_DIAMETER, LConstants.GRAPH_CIRCLE_DIAMETER);
			}
		}
	}

	private void displayGrid() {
		float gridDistance = (float) LConstants.GRAPH_DATA_HEIGHT / LConstants.GRAPH_GRID_AMOUNT;
		p.stroke(LConstants.GRAPH_GRID_COLOR, LConstants.GRAPH_GRID_OPACITY);
		for (int i = 1; i <= LConstants.GRAPH_GRID_AMOUNT; i++) {
			float yPos = LConstants.GRAPH_FINAL_DATA_Y - gridDistance * i;
			p.line(LConstants.GRAPH_BUFFER_X, yPos, LConstants.GRAPH_FINAL_DATA_X, yPos);
		}
		p.stroke(255, 200);
		@SuppressWarnings("static-access")
		float yPos = p.map(0, extrema.getX(), extrema.getY(), LConstants.GRAPH_FINAL_DATA_Y, LConstants.GRAPH_BUFFER_Y);
		p.line(LConstants.GRAPH_BUFFER_X, yPos, LConstants.GRAPH_FINAL_DATA_X, yPos);
	}

	private void displayAxisInfo() {
		float gridDistance = (float) LConstants.GRAPH_DATA_HEIGHT / LConstants.GRAPH_GRID_AMOUNT;
		p.fill(255);
		p.textAlign(PConstants.RIGHT, PConstants.CENTER);
		for (int i = 0; i <= LConstants.GRAPH_GRID_AMOUNT; i++) {
			float yPos = LConstants.GRAPH_FINAL_DATA_Y - gridDistance * i;
			@SuppressWarnings("static-access")
			float textNum = p.map(i, 0, LConstants.GRAPH_GRID_AMOUNT, extrema.getX(), extrema.getY());
			p.text(String.format("%.2f", textNum), LConstants.GRAPH_BUFFER_X, yPos);
		}
		@SuppressWarnings("static-access")
		float yPos = p.map(0, extrema.getX(), extrema.getY(), LConstants.GRAPH_FINAL_DATA_Y, LConstants.GRAPH_BUFFER_Y);
		p.text("0", LConstants.GRAPH_BUFFER_X, yPos);
	}

	private boolean displayHighlightInfo() {
		int displayIndex = getDisplayIndex();
		if (displayIndex != -1) {
			p.fill(LConstants.GRAPH_HIGHLIGHT_BACKGROUND_COLOR, LConstants.GRAPH_HIGHLIGHT_BACKGROUND_OPACITY);
			p.rect(p.mouseX + LConstants.GRAPH_HIGHLIGHT_OFFSET_X, p.mouseY + LConstants.GRAPH_HIGHLIGHT_OFFSET_Y,
					LConstants.GRAPH_HIGHLIGHT_WIDTH, LConstants.GRAPH_HIGHLIGHT_HEIGHT);
			String iName = xName == null ? "x" : xName;
			String dName = yNames == null ? "y" : yNames[displayIndex];
			DataPoint pt = dataList.get(displayIndex).get(getGraphX(p.mouseX));
			p.textFont(textFont);
			p.fill(LConstants.GRAPH_TEXT_COLOR);
			p.textAlign(PConstants.LEFT, PConstants.BASELINE);
			p.text(String.format("%s: %.4f\n%s: %.4f", iName, pt.getX(), dName, pt.getY()),
					p.mouseX + LConstants.GRAPH_HIGHLIGHT_TEXT_OFFSET_X,
					p.mouseY + LConstants.GRAPH_HIGHLIGHT_TEXT_OFFSET_Y);
		}
		return displayIndex != -1;
	}

	private int getDisplayIndex() {
		int resIndex = -1;
		modifySelectIndex();
		for (int i = 0; resIndex == -1 && i < dataList.size(); i++) {
			int testIndex = (i + highlightIndex) % dataList.size();
			float yPos = getYFromMouse(testIndex);
			@SuppressWarnings("static-access")
			float diff = p.abs(yPos - p.mouseY);
			if (diff < LConstants.GRAPH_CIRCLE_DIAMETER / 2) {
				resIndex = testIndex;
			}
		}
		return resIndex;
	}
	
	public boolean graphDone() {
		return graphDone;
	}

	private float getYFromMouse(int index) {
		int pointX = getGraphX(p.mouseX);
		float yPos = getDisplayY(dataList.get(index).get(pointX).getY());
		return yPos;
	}

	private boolean modifySelectIndex() {
		boolean indChange = false;
		if (p.keyPressed && p.key == ' ' && !prevSpace) {
			prevSpace = true;
			highlightIndex = (highlightIndex + 1) % dataList.size();
			indChange = true;
		} else if (!p.keyPressed) {
			prevSpace = false;
		}
		return indChange;
	}
}