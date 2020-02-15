import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Graph {
		
		private int highlight;
		private ArrayList<ArrayList<DataPoint>> dataList;
		private DataPoint extrema;
		private double deltaX;
		private PImage imgGraph;
		private String xName;
		private String yName;
		private PApplet p;
		
		public Graph(PApplet p) {
			this.p = p;
		}
		
		public Graph(PApplet p, ArrayList<ArrayList<DataPoint>> data) {
			this.p = p;
			dataList = data;
		}
		
		public void update() {
			displayBackground();
			deltaX = (double) LConstants.GRAPH_DATA_WIDTH / dataList.get(0).size();
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
		
		public float getDisplayY(double yVal) {
			return PApplet.map((float) yVal, (float) extrema.getX(), (float) extrema.getY(), 
					LConstants.GRAPH_FINAL_DATA_Y, LConstants.GRAPH_INITIAL_DATA_Y);
		}
		
		public float getDisplayX(int xVal) {
			return (float) (LConstants.GRAPH_INITIAL_DATA_X + xVal * deltaX);
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
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			for (ArrayList<DataPoint> dataVar : dataList) {
				for (DataPoint pt : dataVar) {
					double comp = pt.getY();
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
				p.stroke(LConstants.GRAPH_MOUSE_LINE_COLOR);
				p.line(p.mouseX, LConstants.GRAPH_INITIAL_DATA_Y, p.mouseX, LConstants.GRAPH_FINAL_DATA_Y);
				int pointX = (int) ((p.mouseX - LConstants.GRAPH_BUFFER_X) / deltaX);
				for (int i = 0; i < dataList.size(); i++) {
					float yPos = getDisplayY(dataList.get(i).get(pointX).getY());
					p.fill(LConstants.GRAPH_DATA_LINE_COLOR[i]);
					p.ellipse(p.mouseX, yPos, 20, 20);
				}
			}
		}
	}