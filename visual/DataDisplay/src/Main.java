import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ProcessingSketch pWindows = 
				new ProcessingSketch(new String[] {"t", "lap", "pos_x", "pos_y", "x", "v"});
		pWindows.run();
		
		while (!pWindows.graphDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		SwingGui swingGui = new SwingGui(pWindows, pWindows.getAllArgs());
//		swingGui.show();
	}
}
