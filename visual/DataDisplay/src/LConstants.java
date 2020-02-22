public final class LConstants {	
	
	// Button Constants
	// Confirm Button
	public static final int COMFIRM_BUTTON_X = 400;
	public static final int COMFIRM_BUTTON_Y = 650;
	public static final int COMFIRM_BUTTON_WIDTH = 200;
	public static final int COMFIRM_BUTTON_HEIGHT = 50;
	public static final String COMFIRM_BUTTON_TEXT = "Confirm";
	
	// Undo Button
	public static final int UNDO_BUTTON_X = 400;
	public static final int UNDO_BUTTON_Y = 725;
	public static final int UNDO_BUTTON_WIDTH = 200;
	public static final int UNDO_BUTTON_HEIGHT = 50;
	public static final String UNDO_BUTTON_TEXT = "Undo";
	
	// Text Constants
	// Text Font Stuff
	public final static int TEXT_SIZE = 32;
	public final static String TEXT_FONT = "Calibri";
	
	// Button Colors
	public final static int UNACTIVE_BUTTON_COLOR = 0xFFC8C8C8;
	public final static int ACTIVE_BUTTON_COLOR = 0xFFD2D2E6;
	public final static int HOVERED_BUTTON_COLOR = 0xFFD2E6D2;
	public final static int CLICKED_BUTTON_COLOR = 0xFFA0B4A0;

	// Button Codes
	public final static int UNACTIVE_BUTTON = 0;
	public final static int ACTIVE_BUTTON = 1;
	public final static int HOVERED_BUTTON = 2;
	public final static int CLICKED_BUTTON = 3;
	
	// Other Colors
	public final static int BACKGROUND_COLOR = 0xFFFFFFFF;
	public final static int NEUTRAL_COLOR = 0xFFB4B4B4;
	public final static int BLANK_COLOR = 0xFFFFFFFF;
	public final static int MISSED_COLOR = 0x00000000;
	public final static int DEFAULT_TEXT_COLOR = 0x00000000;
	

	// Shape Constants
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
	public final static int[] GRAPH_DATA_LINE_COLOR = {0xFF7293CB, 0xFFE1974C, 0xFF84BA5B, 0xFFD35E60, 
			0xFF808585, 0xFF9067A7, 0xAB6857, 0xCCC210};
	public final static int GRAPH_CIRCLE_DIAMETER = 20;
}