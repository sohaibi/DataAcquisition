import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class SwingGui {
	
	private JComboBox<String> graphList;
	ProcessingSketch pWindows;
	private JFrame frame;
	private int windowIndex;
	private String[] tabNames = {"Main", "Display", "X Var", "Y Var", "Export", "Simulator"};
	private String[] argList;
	private ArrayList<String> selectedY;
	private String selectedX;
	
	public SwingGui(ProcessingSketch pWindows, String[] argList) {
		this.pWindows = pWindows;
		this.argList = argList;
		selectedY = new ArrayList<>();
		
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
			        //pWindows.setGraph(windowIndex); // TODO: Fixing this allows unlimited graphs to be added
			    }
			});
			graphList.setSelectedIndex(0);
			tmp.add(graphList);
		}
		else if (index == 1) {
			
		}
		else if (index == 2) {
			ButtonGroup xCollection = new ButtonGroup();
			JCheckBox[] xVars = new JCheckBox[argList.length];
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pWindows.updateX(selectedX);
				}
			});
			tmp.add(updateButton, BorderLayout.BEFORE_FIRST_LINE);
			for (int i = 0; i < xVars.length; i++) {
				xVars[i] = new JCheckBox(argList[i]);
				final String tmpStr = argList[i];
				xVars[i].addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							selectedX = tmpStr;
						}
					}
				});
				xCollection.add(xVars[i]);
				tmp.add(xVars[i]);
			}
		}
		else if (index == 3) {
			JCheckBox[] yVars = new JCheckBox[argList.length];
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pWindows.updateY(toArray(selectedY));
				}
			});
			tmp.add(updateButton, BorderLayout.BEFORE_FIRST_LINE);
			for (int i = 0; i < yVars.length; i++) {
				yVars[i] = new JCheckBox(argList[i]);
				final String tmpStr = argList[i];
				yVars[i].addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							selectedY.add(tmpStr);
						}
						else if (e.getStateChange() == ItemEvent.DESELECTED) {
							selectedY.remove(tmpStr);
						}
					}
				});
				tmp.add(yVars[i]);
			}
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private String[] toArray(ArrayList<String> str) {
		String[] res = new String[str.size()];
		for (int i = 0; i < str.size(); i++) {
			res[i] = str.get(i);
		}
		return res;
	}
}
