public final class LConstants {	
	// Screen Dimensions
	public final static int SCREEN_WIDTH = 1400;
	public final static int SCREEN_HEIGHT = 800;
	
	// Board Dimensions
	public final static int START_X = 200;
	public final static int START_Y = 200;
	public final static int SQUARE_WIDTH = 100;
	
	// --- Graphical Constants ---
	public static final int GRAPH_INITIAL_X = 0;
	public static final int GRAPH_INITIAL_Y = 0;
	public static final int GRAPH_FINAL_X = SCREEN_WIDTH;
	public static final int GRAPH_FINAL_Y = SCREEN_HEIGHT;
	public static final int GRAPH_BUFFER_X = 100;
	public static final int GRAPH_BUFFER_Y = 50;
	
	public static final int GRAPH_TOTAL_WIDTH = GRAPH_FINAL_X - GRAPH_INITIAL_X;
	public static final int GRAPH_TOTAL_HEIGHT = GRAPH_FINAL_Y - GRAPH_INITIAL_Y;
	public static final int GRAPH_INITIAL_DATA_X = GRAPH_INITIAL_X + GRAPH_BUFFER_X;
	public static final int GRAPH_INITIAL_DATA_Y = GRAPH_INITIAL_Y + GRAPH_BUFFER_Y;
	public static final int GRAPH_FINAL_DATA_X = GRAPH_FINAL_X - GRAPH_BUFFER_X;
	public static final int GRAPH_FINAL_DATA_Y = GRAPH_FINAL_Y - GRAPH_BUFFER_Y;
	public static final int GRAPH_DATA_WIDTH = GRAPH_FINAL_DATA_X - GRAPH_INITIAL_DATA_X;
	public static final int GRAPH_DATA_HEIGHT = GRAPH_FINAL_DATA_Y - GRAPH_INITIAL_DATA_Y;
	
	public final static int GRAPH_BACKGROUND_COLOR = 0;
	public final static int GRAPH_GRID_LINE_COLOR = 255;
	public final static int GRAPH_MOUSE_LINE_COLOR = 180;
	public final static int GRAPH_CIRCLE_DIAMETER = 20;
	public final static int GRAPH_GRID_AMOUNT = 5;
	
	public final static int GRAPH_GRID_COLOR = 160;
	public final static int GRAPH_GRID_OPACITY = 150;
	
	public final static int GRAPH_HIGHLIGHT_BACKGROUND_COLOR = 255;
	public final static int GRAPH_HIGHLIGHT_BACKGROUND_OPACITY = 100;
	public final static int GRAPH_HIGHLIGHT_WIDTH = 200;
	public final static int GRAPH_HIGHLIGHT_HEIGHT = 55;
	public final static int GRAPH_HIGHLIGHT_OFFSET_X = 10;
	public final static int GRAPH_HIGHLIGHT_OFFSET_Y = 10;
	public final static int GRAPH_HIGHLIGHT_TEXT_OFFSET_X = 5 + GRAPH_HIGHLIGHT_OFFSET_X;
	public final static int GRAPH_HIGHLIGHT_TEXT_OFFSET_Y = 20 + GRAPH_HIGHLIGHT_OFFSET_Y;
	
	public final static String GRAPH_TEXT_FONT = "Calibri";
	public final static int GRAPH_TEXT_SIZE = 20;
	public final static int GRAPH_TEXT_COLOR = 0;
}