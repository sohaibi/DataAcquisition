import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class SwingGui {
	
	private JFrame frame;
	
	public SwingGui(ProcessingSketch sketch) {
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton pickColor = new JButton("Color...");
		pickColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(pickColor, "Memer", Color.RED);
			}
		});
		
		frame.add(pickColor);
		frame.setSize(200, 100);
	}
	
	public void show() {
		frame.setVisible(true);
	}

}
