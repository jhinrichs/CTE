package gui;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import treeExploration.Simulator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class simulationPopUp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField  maxNodesMinField;
	public  JTextField maxNodesMaxField;
	public  JTextField minNodesMinField;
	public  JTextField minNodesMaxField;
	public  JTextField maxChildMinField;
	public  JTextField maxChildMaxField;
	public  JTextField minChildMinField;
	public  JTextField minChildMaxField;
	public  JTextField multiAgentsField;
	public 	JTextField numberOfRunsField;
	public  JCheckBox  isInfinite;
	public JButton runSimulationButton;
	public static JTextField txtActiveThreads;
	public static  JTextField txtUsedNodes;
	public static  JTextField txtStartedThread;
	public static  JTextField txtLastStartedNodes;
	public static  JTextField txtFinishedThreads;
public static JProgressBar progressBar;
	/**
	 * Create the panel.
	 * @param runSimulationListener 
	 * @param btnStop 
	 */
	public simulationPopUp(ActionListener btnStop, ActionListener runSimulationListener) {
		
		JPanel panel = new JPanel();
		panel.setBounds(72, 15, 1, 1);
		panel.setLayout(null);
		panel.setBackground(SystemColor.controlHighlight);
		
		JTextPane txtpnMaxnodes = new JTextPane();
		txtpnMaxnodes.setBounds(10, 11, 56, 20);
		txtpnMaxnodes.setText("MaxNodes");
		txtpnMaxnodes.setBackground(SystemColor.info);
		
		JTextPane txtpnMin = new JTextPane();
		txtpnMin.setBounds(76, 11, 22, 20);
		txtpnMin.setToolTipText("minimum depth of the tree");
		txtpnMin.setText("min");
		txtpnMin.setOpaque(false);
		
		maxNodesMinField = new JTextField();
		maxNodesMinField.setBounds(108, 11, 86, 20);
		maxNodesMinField.setToolTipText("Enter an Integer value here");
		maxNodesMinField.setText("5000");
		maxNodesMinField.setColumns(10);
		
		JTextPane txtpnMax = new JTextPane();
		txtpnMax.setBounds(204, 11, 26, 20);
		txtpnMax.setToolTipText("The tree creator will stop after this many nodes are created. ");
		txtpnMax.setText("max");
		txtpnMax.setOpaque(false);
		
		maxNodesMaxField = new JTextField();
		maxNodesMaxField.setBounds(240, 11, 86, 20);
		maxNodesMaxField.setToolTipText("Enter an Integer value here");
		maxNodesMaxField.setText("500000");
		maxNodesMaxField.setColumns(10);
		setLayout(null);
		add(txtpnMaxnodes);
		add(panel);
		add(txtpnMin);
		add(maxNodesMinField);
		add(txtpnMax);
		add(maxNodesMaxField);
		
		JTextPane txtpnMinnodes = new JTextPane();
		txtpnMinnodes.setText("MinNodes");
		txtpnMinnodes.setBackground(SystemColor.info);
		txtpnMinnodes.setBounds(10, 42, 56, 20);
		add(txtpnMinnodes);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setToolTipText("minimum depth of the tree");
		textPane_1.setText("min");
		textPane_1.setOpaque(false);
		textPane_1.setBounds(76, 42, 22, 20);
		add(textPane_1);
		
		minNodesMinField = new JTextField();
		minNodesMinField.setToolTipText("Enter an Integer value here");
		minNodesMinField.setText("1000");
		minNodesMinField.setColumns(10);
		minNodesMinField.setBounds(108, 42, 86, 20);
		add(minNodesMinField);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setToolTipText("The tree creator will stop after this many nodes are created. ");
		textPane_2.setText("max");
		textPane_2.setOpaque(false);
		textPane_2.setBounds(204, 42, 26, 20);
		add(textPane_2);
		
		minNodesMaxField = new JTextField();
		minNodesMaxField.setToolTipText("Enter an Integer value here");
		minNodesMaxField.setText("300000");
		minNodesMaxField.setColumns(10);
		minNodesMaxField.setBounds(240, 42, 86, 20);
		add(minNodesMaxField);
		
		JTextPane txtpnMaxchild = new JTextPane();
		txtpnMaxchild.setText("MaxChild");
		txtpnMaxchild.setBackground(SystemColor.info);
		txtpnMaxchild.setBounds(10, 73, 56, 20);
		add(txtpnMaxchild);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setToolTipText("minimum depth of the tree");
		textPane_4.setText("min");
		textPane_4.setOpaque(false);
		textPane_4.setBounds(76, 73, 22, 20);
		add(textPane_4);
		
		maxChildMinField = new JTextField();
		maxChildMinField.setToolTipText("Enter an Integer value here");
		maxChildMinField.setText("1");
		maxChildMinField.setColumns(10);
		maxChildMinField.setBounds(108, 73, 86, 20);
		add(maxChildMinField);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setToolTipText("The tree creator will stop after this many nodes are created. ");
		textPane_5.setText("max");
		textPane_5.setOpaque(false);
		textPane_5.setBounds(204, 73, 26, 20);
		add(textPane_5);
		
		maxChildMaxField = new JTextField();
		maxChildMaxField.setToolTipText("Enter an Integer value here");
		maxChildMaxField.setText("20");
		maxChildMaxField.setColumns(10);
		maxChildMaxField.setBounds(240, 73, 86, 20);
		add(maxChildMaxField);
		
		JTextPane txtpnMinchild = new JTextPane();
		txtpnMinchild.setText("MinChild");
		txtpnMinchild.setBackground(SystemColor.info);
		txtpnMinchild.setBounds(10, 104, 56, 20);
		add(txtpnMinchild);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setToolTipText("minimum depth of the tree");
		textPane_7.setText("min");
		textPane_7.setOpaque(false);
		textPane_7.setBounds(76, 104, 22, 20);
		add(textPane_7);
		
		minChildMinField = new JTextField();
		minChildMinField.setToolTipText("Enter an Integer value here");
		minChildMinField.setText("0");
		minChildMinField.setColumns(10);
		minChildMinField.setBounds(108, 104, 86, 20);
		add(minChildMinField);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setToolTipText("The tree creator will stop after this many nodes are created. ");
		textPane_8.setText("max");
		textPane_8.setOpaque(false);
		textPane_8.setBounds(204, 104, 26, 20);
		add(textPane_8);
		
		minChildMaxField = new JTextField();
		minChildMaxField.setToolTipText("Enter an Integer value here");
		minChildMaxField.setText("4");
		minChildMaxField.setColumns(10);
		minChildMaxField.setBounds(240, 104, 86, 20);
		add(minChildMaxField);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(108, 155, 120, 20);
		add(textPane_3);
		textPane_3.setToolTipText("gives the number of runs that are done for specified tree and  agent settings.");
		textPane_3.setText("Number of Runs");
		textPane_3.setOpaque(false);
		
		numberOfRunsField = new JTextField();
		numberOfRunsField.setBounds(108, 174, 120, 20);
		add(numberOfRunsField);
		numberOfRunsField.setToolTipText("");
		numberOfRunsField.setText("3");
		numberOfRunsField.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(108, 197, 120, 20);
		add(textPane);
		textPane.setToolTipText("multiple integer Values that calculate for each run on each tree");
		textPane.setText("multiple Agents Runs");
		textPane.setOpaque(false);
		
		multiAgentsField = new JTextField();
		multiAgentsField.setBounds(10, 217, 317, 20);
		add(multiAgentsField);
		multiAgentsField.setToolTipText("");
		multiAgentsField.setText("10;50;100;250;500;750;1000;1500;3000;5000;7500;10000;15000;30000;50000");
		multiAgentsField.setColumns(10);
		
		runSimulationButton = new JButton("start Simulation");
		runSimulationButton.addActionListener(runSimulationListener);
		runSimulationButton.setBounds(108, 312, 120, 23);
		add(runSimulationButton);
		
		isInfinite = new JCheckBox("infinite Loop");
		isInfinite.setBounds(108, 282, 97, 23);
		add(isInfinite);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(btnStop);
		stopButton.setBounds(108, 346, 122, 23);
		add(stopButton);
		stopButton.setBackground(new Color(204, 0, 51));
		
		txtActiveThreads = new JTextField();
		txtActiveThreads.setBounds(577, 11, 171, 20);
		add(txtActiveThreads);
		txtActiveThreads.setColumns(10);
		
		JTextPane txtpnActiveThreads = new JTextPane();
		txtpnActiveThreads.setText("active Threads");
		txtpnActiveThreads.setBackground(SystemColor.info);
		txtpnActiveThreads.setBounds(462, 11, 105, 20);
		add(txtpnActiveThreads);
		
		JTextPane txtpnUsedNodes = new JTextPane();
		txtpnUsedNodes.setText("used Nodes");
		txtpnUsedNodes.setBackground(SystemColor.info);
		txtpnUsedNodes.setBounds(462, 42, 105, 20);
		add(txtpnUsedNodes);
		
		txtUsedNodes = new JTextField();
		txtUsedNodes.setColumns(10);
		txtUsedNodes.setBounds(577, 42, 171, 20);
		add(txtUsedNodes);
		
		JTextPane txtpnLastStartedThread = new JTextPane();
		txtpnLastStartedThread.setText("last Started: thread#");
		txtpnLastStartedThread.setBackground(SystemColor.info);
		txtpnLastStartedThread.setBounds(462, 73, 109, 20);
		add(txtpnLastStartedThread);
		
		txtStartedThread = new JTextField();
		txtStartedThread.setColumns(10);
		txtStartedThread.setBounds(577, 73, 171, 20);
		add(txtStartedThread);
		
		JTextPane txtpnWith = new JTextPane();
		txtpnWith.setText("with");
		txtpnWith.setBackground(SystemColor.info);
		txtpnWith.setBounds(462, 104, 35, 20);
		add(txtpnWith);
		
		txtLastStartedNodes = new JTextField();
		txtLastStartedNodes.setColumns(10);
		txtLastStartedNodes.setBounds(507, 104, 146, 20);
		add(txtLastStartedNodes);
		
		JTextPane txtpnNodes = new JTextPane();
		txtpnNodes.setText("Nodes");
		txtpnNodes.setBackground(SystemColor.info);
		txtpnNodes.setBounds(663, 104, 85, 20);
		add(txtpnNodes);
		
		JTextPane txtpnFinishedThreads = new JTextPane();
		txtpnFinishedThreads.setText("finished Threads");
		txtpnFinishedThreads.setBackground(SystemColor.info);
		txtpnFinishedThreads.setBounds(462, 197, 105, 20);
		add(txtpnFinishedThreads);
		
		txtFinishedThreads = new JTextField();
		txtFinishedThreads.setColumns(10);
		txtFinishedThreads.setBounds(577, 197, 171, 20);
		add(txtFinishedThreads);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(462, 152, 286, 23);
		add(progressBar);

	}
	public int getminChildMin() {
		return Integer.parseInt(minChildMinField.getText());
	}
	public int getminChildMax() {
		return Integer.parseInt(minChildMaxField.getText());
	}
	public int getmaxChildMin() {
		return Integer.parseInt(maxChildMinField.getText());
	}
	public int getmaxChildMax() {
		return Integer.parseInt(maxChildMaxField.getText());
	}
	public int getminNodesMin() {
		return Integer.parseInt(minNodesMinField.getText());
	}
	public int getminNodesMax() {
		return Integer.parseInt(minNodesMaxField.getText());
	}
	public int getmaxNodesMin() {
		return Integer.parseInt(maxNodesMinField.getText());
	}
	public int getmaxNodesMax() {
		return Integer.parseInt(maxNodesMaxField.getText());
	}
	public static void updateValues(int activeThreads, int activeNodes, Simulator simulator, int finishedThreads, int numberOfSimulations) {
		txtActiveThreads.setText(activeThreads+"");
		txtUsedNodes.setText(""+activeNodes);
		txtLastStartedNodes.setText(""+ simulator.threadnumber);
		txtLastStartedNodes.setText((""+simulator.tree.getNumberOfNodesInTree()));
		txtFinishedThreads.setText(""+ finishedThreads);
		progressBar.setValue(numberOfSimulations);
	}
}
