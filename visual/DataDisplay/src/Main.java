public class Main {

	public static void main(String[] args) {
		ProcessingSketch test = new ProcessingSketch();
		SwingGui swingGui = new SwingGui(test);
		
		test.run();
		swingGui.show();
	}
}
