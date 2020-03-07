import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class SwingGui {
	
	private JComboBox<String> graphList;
	ProcessingSketch pWindows;
	private JFrame frame;
	private int windowIndex;
	private String[] tabNames = {"Main", "Display", "X Var", "Y Var", 
								"Range", "Export", "Simulator"};
	private String[] argList;
	private ArrayList<String> selectedY;
	private String selectedX;
	private String currColorArg;
	private Color currColor;
	private DataPoint currRange;
	
	public SwingGui(ProcessingSketch pWindows, String[] argList) {
		this.pWindows = pWindows;
		this.argList = argList;
		selectedY = new ArrayList<>();
		currRange = pWindows.getRange();
		
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
		frame.setSize(800, 425);
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
		else if (index == 1) {
			JComboBox<String> lineList = new JComboBox<>();
			lineList.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JComboBox<String> tmp = (JComboBox<String>) e.getSource();
			        currColorArg = (String) tmp.getSelectedItem();
			    }
			});
			String[] names = pWindows.getCurrArgs();
			for (String n : names) {
				lineList.addItem(n);
			}
			JColorChooser colorMenu = new JColorChooser(Color.WHITE);
			colorMenu.getSelectionModel().addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					currColor = colorMenu.getColor(); 
				}
			});
			currColor = Color.WHITE;
			JButton updateColor = new JButton("Update");
			updateColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int tmp = 0xFF << 24 | currColor.getRed() << 16 |
							currColor.getGreen() << 8 | currColor.getBlue();
					pWindows.updateColor(currColorArg, tmp);
				}
			});
			tmp.add(lineList, BorderLayout.WEST);
			tmp.add(colorMenu);
			tmp.add(updateColor);
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
		else if (index == 4) {
		    NumberFormat format = NumberFormat.getIntegerInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
			JFormattedTextField min = new JFormattedTextField(formatter);
			JFormattedTextField max = new JFormattedTextField(formatter);
			min.setValue((int) currRange.getX()); 
			max.setValue((int) currRange.getY()); 
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(Integer.MIN_VALUE);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    //formatter.setCommitsOnValidEdit(true); 
		    min.setColumns(7);
		    min.addPropertyChangeListener("value", new PropertyChangeListener() {
		    	public void propertyChange(PropertyChangeEvent e) {
		    		if (min.getValue() != null && max.getValue() != null) {
		    			currRange = new DataPoint(((Integer) min.getValue()).intValue(), currRange.getY());
		    		}
		    	}
		    });
		    max.setColumns(7);
		    max.addPropertyChangeListener("value", new PropertyChangeListener() {
		    	public void propertyChange(PropertyChangeEvent e) {
		    		if (min.getValue() != null && max.getValue() != null) {
		    			currRange = new DataPoint(currRange.getX(), ((Integer) max.getValue()).intValue());
		    		}
		    	}
		    });
		    JButton resetButton = new JButton("Reset Range");
			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pWindows.setRange(0, 0);
					currRange = pWindows.getRange();
					min.setValue((int) currRange.getX());
					max.setValue((int) currRange.getY());
				}
			});
		    JButton updateButton = new JButton("Update Range");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pWindows.setRange(currRange.getX(), currRange.getY());
				}
			});
			tmp.add(resetButton);
			tmp.add(min);
			tmp.add(max);
			tmp.add(updateButton);
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
