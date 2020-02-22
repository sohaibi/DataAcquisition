import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class Graph {
		
		private int highlight;
		private int selectIndex;
		private boolean prevSpace;
		private ArrayList<ArrayList<DataPoint>> dataList;
		private DataPoint extrema;
		private float deltaX;
		private PImage imgGraph;
		private PApplet p;
		private String xName;
		private String[] yNames;
		private PFont textFont;
		
		public Graph(PApplet p) {
			this.p = p;
		}
		
		public Graph(PApplet p, ArrayList<ArrayList<DataPoint>> data) {
			this.p = p;
			dataList = data;
			textFont = p.createFont("Calibri", 20); 
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
		
		public void update() {
			displayBackground();
			deltaX = (float) LConstants.GRAPH_DATA_WIDTH / dataList.get(0).size();
			extrema = findExtrema();
			displayVar();
			createGraphImage();
		}
		
		public void displayVar() {
			int colorNum = 0;
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
					//System.out.printf("[(%f, %f) -> (%f, %f)] ", pDX, pDY, cDX, cDY);
					prev = curr;
				}
				colorNum++;
			}
		}
		
		public void displayBackground() {
			p.noStroke();
			p.fill(LConstants.GRAPH_BACKGROUND_COLOR);
			p.rect(LConstants.GRAPH_INITIAL_X, LConstants.GRAPH_INITIAL_Y, 
					LConstants.GRAPH_FINAL_X, LConstants.GRAPH_FINAL_Y);
			p.stroke(LConstants.GRAPH_GRID_LINE_COLOR);
			p.line(LConstants.GRAPH_INITIAL_DATA_X, LConstants.GRAPH_INITIAL_DATA_Y, 
					LConstants.GRAPH_INITIAL_DATA_X, LConstants.GRAPH_FINAL_DATA_Y);
			p.line(LConstants.GRAPH_INITIAL_DATA_X, LConstants.GRAPH_FINAL_DATA_Y,
					LConstants.GRAPH_FINAL_DATA_X, LConstants.GRAPH_FINAL_DATA_Y);
			p.strokeWeight(2);
		}
		
		public float getDisplayY(float yVal) {
			return PApplet.map((float) yVal, (float) extrema.getX(), (float) extrema.getY(), 
					LConstants.GRAPH_FINAL_DATA_Y, LConstants.GRAPH_INITIAL_DATA_Y);
		}
		
		public float getDisplayX(float xVal) {
			return (float) (LConstants.GRAPH_INITIAL_DATA_X + xVal * deltaX);
		}
		
		public int getGraphX(float mouseX) {
			return (int) ((p.mouseX - LConstants.GRAPH_BUFFER_X) / deltaX);
		}
		
		private void createGraphImage() {
			imgGraph = p.createImage(LConstants.GRAPH_TOTAL_WIDTH, 
					LConstants.GRAPH_TOTAL_HEIGHT, PConstants.RGB);
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
			p.image(imgGraph, LConstants.GRAPH_INITIAL_X, LConstants.GRAPH_INITIAL_Y);
			displayMouse();
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
		
		public boolean displayHighlightInfo() {
			int displayIndex = getDisplayIndex();
			if (displayIndex != -1) {
				p.fill(255, 150);
				p.rect(p.mouseX + 10, p.mouseY + 10, 200, 55);
				String iName = xName == null ? "x" : xName;
				String dName = yNames == null ? "y" : yNames[displayIndex];
				p.fill(0);
				DataPoint pt = dataList.get(displayIndex).get(getGraphX(p.mouseX));
				p.textFont(textFont);
				p.text(String.format("%s: %.4f\n%s: %.4f", iName, pt.getX(), dName, pt.getY()), p.mouseX + 15, p.mouseY + 30);
			}
			return displayIndex != -1;
		}
		
		private int getDisplayIndex() {
			int resIndex = -1;
			modifySelectIndex();
			for (int i = 0; resIndex == -1 && i < dataList.size(); i++) {
				int testIndex = (i + selectIndex) % dataList.size();
				float yPos = getYFromMouse(testIndex);
				float diff = p.abs(yPos - p.mouseY);
				if (diff < LConstants.GRAPH_CIRCLE_DIAMETER / 2) {
					resIndex = testIndex;
				}
			}
			return resIndex;
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
				selectIndex = (selectIndex + 1) % dataList.size();
				indChange = true;
			}
			else if (!p.keyPressed) {
				prevSpace = false;
			}
			return indChange;
		}
	}