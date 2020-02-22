import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class SwingGui {
	
	private JComboBox<String> graphList;
	ProcessingSketch pWindows;
	private JFrame frame;
	private int windowIndex;
	private String[] tabNames = {"Main", "Display", "X Var", "Y Var", "Export", "Simulator"};
	
	public SwingGui(ProcessingSketch pWindows) {
		this.pWindows = pWindows;
		
		JTabbedPane tabs = new JTabbedPane();
		frame = new JFrame("Graph Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		JMenuItem addGraph = new JMenuItem("Add Graph");
		file.add(addGraph);
		
		JPanel[] test = new JPanel[tabNames.length];
		for (int i = 0; i < test.length; i++) {
			test[i] = new JPanel();
			initPanels(test[i], i);
			tabs.add(tabNames[i], test[i]);
		}	
		addGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphList.addItem("Graph " + (graphList.getItemCount() + 1));
			}
		});
		frame.add(tabs);
		frame.setSize(800, 400);
	}
	
	public void initPanels(JPanel tmp, int index) {
		if (index == 0) {
			graphList = new JComboBox<String>(new String[] {"Graph 1"});
			graphList.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JComboBox<String> tmp = (JComboBox<String>) e.getSource();
			        windowIndex = tmp.getSelectedIndex();
			        pWindows.setGraph(windowIndex);
			    }
			});
			graphList.setSelectedIndex(0);
			tmp.add(graphList);
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}
}
